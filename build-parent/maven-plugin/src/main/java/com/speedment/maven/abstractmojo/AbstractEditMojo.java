package com.speedment.maven.abstractmojo;

import com.speedment.common.json.Json;
import com.speedment.runtime.config.*;
import com.speedment.runtime.config.util.DocumentDbUtil;
import com.speedment.runtime.config.util.DocumentTranscoder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.speedment.tool.core.internal.util.ConfigFileHelper.DEFAULT_CONFIG_LOCATION;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

/**
 * @author Emil Forslund
 * @since  3.1.4
 */
public abstract class AbstractEditMojo extends AbstractMojo {

    private final static String SET_OPERATOR = ":";
    private final static String PATH_SEPARATOR = "->";

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject mavenProject;
    private @Parameter(defaultValue = "${configFile}") String configFile;
    private @Parameter(defaultValue = "${what}") String what;
    private @Parameter(defaultValue = "${where}") String where;
    private @Parameter(defaultValue = "${set}") String set;
    private @Parameter(defaultValue = "${delete}") boolean delete;

    @Override
    @SuppressWarnings("unchecked")
    public final void execute() throws MojoExecutionException, MojoFailureException {
        if (hasConfigFile(configLocation())) {

            if (!delete) {
                if (StringUtils.isBlank(set)
                || !set.contains(SET_OPERATOR)
                || set.indexOf(SET_OPERATOR) <= 0)
                    throw setNotValid();
            }

            final Project loaded = DocumentTranscoder.load(configLocation(), AbstractEditMojo::decodeJson);
            final LongAdder edits = new LongAdder();
            locate(loaded).collect(toList()).forEach(doc -> {
                if (delete) {
                    doc.getParent().ifPresent(parent -> {
                        final Collection<? extends Map<String, Object>> coll;
                        if (doc instanceof Dbms) {
                            coll = (Collection<? extends Map<String, Object>>) parent.getData().get(Project.DBMSES);
                        } else if (doc instanceof Schema) {
                            coll =(Collection<? extends Map<String, Object>>) parent.getData().get(Dbms.SCHEMAS);
                        } else if (doc instanceof Table) {
                            coll = (Collection<? extends Map<String, Object>>) parent.getData().get(Schema.TABLES);
                        } else if (doc instanceof Column) {
                            coll = (Collection<? extends Map<String, Object>>) parent.getData().get(Table.COLUMNS);
                        } else if (doc instanceof PrimaryKeyColumn) {
                            coll = (Collection<? extends Map<String, Object>>) parent.getData().get(Table.PRIMARY_KEY_COLUMNS);
                        } else if (doc instanceof IndexColumn) {
                            coll = (Collection<? extends Map<String, Object>>) parent.getData().get(Index.INDEX_COLUMNS);
                        } else if (doc instanceof ForeignKeyColumn) {
                            coll = (Collection<? extends Map<String, Object>>) parent.getData().get(ForeignKey.FOREIGN_KEY_COLUMNS);
                        } else if (doc instanceof ForeignKey) {
                            coll = (Collection<? extends Map<String, Object>>) parent.getData().get(ForeignKey.FOREIGN_KEY_COLUMNS);
                        } else if (doc instanceof Index) {
                            coll = (Collection<? extends Map<String, Object>>) parent.getData().get(Table.INDEXES);
                        } else {
                            getLog().error("Doc was of type " + doc.getClass());
                            return;
                        }

                        final String id = doc.getAsString("id").orElseGet(() -> doc.getAsString("name").orElseThrow(() -> new RuntimeException("No id in document.")));
                        if (coll.removeIf(d -> d.get("id").equals(id))) {
                            edits.increment();
                        } else {
                            getLog().error("Could not remove doc " + doc);
                        }
                    });
                } else {
                    final int equalPos = set.indexOf(SET_OPERATOR);
                    final String param = set.substring(0, equalPos);
                    final String value = set.substring(equalPos + SET_OPERATOR.length());

                    final Object newValue = parse(doc, param, value);
                    doc.getData().put(param, newValue);
                    edits.increment();
                }
            });

            DocumentTranscoder.save(loaded, configLocation(), Json::toJson);
            getLog().info(format("Done! %d entries modified.", edits.longValue()));
        } else {
            throw new MojoExecutionException(format(
                "The configFile '%s' can't be loaded.",
                configLocation().toString()));
        }
    }

    protected final MavenProject project() {
        return mavenProject;
    }

    protected final String configFile() {
        return configFile;
    }

    protected final Path configLocation() {
        final String top = StringUtils.isBlank(configFile())
            ? DEFAULT_CONFIG_LOCATION
            : configFile();

        System.out.println(project());

        return project()
            .getBasedir()
            .toPath()
            .resolve(top);
    }

    protected final boolean hasConfigFile(Path file) {
        if (file == null) {
            final String msg = "The expected .json-file is null.";
            getLog().info(msg);
            return false;
        } else if (!Files.exists(file)) {
            final String msg = "The expected .json-file '"
                + file + "' does not exist.";
            getLog().info(msg);
            return false;
        } else if (!Files.isReadable(file)) {
            final String err = "The expected .json-file '"
                + file + "' is not readable.";
            getLog().error(err);
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> decodeJson(String json) {
        return (Map<String, Object>) Json.fromJson(json);
    }

    private Stream<? extends Document> locate(Project project) throws MojoExecutionException {
        if (StringUtils.isNotBlank(what)) {
            final Class<? extends Document> docClass;
            switch (what.toLowerCase()) {
                case "p": case "project": case "projects": return Stream.of(project);
                case "d": case "dbms": case "dbmses": docClass = Dbms.class; break;
                case "s": case "schema": case "schemas": docClass = Schema.class; break;
                case "t": case "table": case "tables": docClass = Table.class; break;
                case "c": case "column": case "columns": docClass = Column.class; break;
                case "pk": case "primarykey": case "primarykeys":
                case "pkc": case "primarykeycolumn": case "primarykeycolumns": docClass = PrimaryKeyColumn.class; break;
                case "i": case "index": case "indexes": docClass = Index.class; break;
                case "ic": case "indexcolumn": case "indexcolumns": docClass = IndexColumn.class; break;
                case "fk": case "foreignkey": case "foreignkeys": docClass = ForeignKey.class; break;
                case "fkc": case "foreignkeycolumn": case "foreignkeycolumns": docClass = ForeignKeyColumn.class; break;
                default: throw whatNotValid();
            }

            return DocumentDbUtil.traverseOver(project, docClass).filter(this::matches);
        } else return DocumentDbUtil.traverseOver(project, Document.class).filter(this::matches);
    }

    private boolean matches(Document document) {
        return matches(document, where);
    }

    private boolean matches(Document document, String where) {
        final String lastWhere;
        if (StringUtils.isBlank(where)) {
            return true;
        } else if (where.contains(PATH_SEPARATOR)) {
            final int index = where.lastIndexOf(PATH_SEPARATOR);
            final String parentIds = where.substring(0, index).trim();
            lastWhere = where.substring(index + PATH_SEPARATOR.length()).trim();
            if (document.getParent().isPresent()) {
                if (!matches(document.getParent().get(), parentIds)) {
                    return false;
                }
            }
        } else {
            lastWhere = where.trim();
        }

        final int index = lastWhere.indexOf(SET_OPERATOR);
        final String key, value;
        if (index == -1) {
            key   = "id";
            value = "^" + lastWhere + "$";
        } else {
            key = lastWhere.substring(0, index);
            value = lastWhere.substring(index + SET_OPERATOR.length());
        }

        return checkIf(document, key, value);
    }

    private boolean checkIf(Document doc, String param, String value) {
        final Pattern pattern = Pattern.compile(value);
        return doc.get(param)
            .map(Object::toString)
            .filter(pattern.asPredicate())
            .isPresent();
    }

    private Object parse(Document doc, String param, String value) {
        final Optional<Object> current = doc.get(param);
        final Object newValue;
        if (current.isPresent()) {
            switch (current.get().getClass().getName()) {
                case "java.lang.Long": newValue = Long.parseLong(value); break;
                case "java.lang.Double": newValue = Double.parseDouble(value); break;
                case "java.lang.Boolean": newValue = Boolean.parseBoolean(value); break;
                case "java.lang.String": newValue = value; break;
                default: throw new RuntimeException(format(
                    "Unknown type of existing value '%s' for param '%s'.",
                    current.get(), param
                ));
            }
        } else {
            if ("true".equals(value) || "false".equals(value)) {
                newValue = Boolean.parseBoolean(value);
            } else {
                Object newValue2;
                try { newValue2 = Long.parseLong(value); }
                catch (final NumberFormatException ex) {
                    try { newValue2 = Double.parseDouble(value); }
                    catch (final NumberFormatException ex2) {
                        newValue2 = value;
                    }
                }
                newValue = newValue2;
            }
        }
        return newValue;
    }

    private MojoExecutionException setNotValid() {
        return new MojoExecutionException(format(
            "Parameter 'set' with value '%s' is not valid. Should be " +
            "specified as 'key=value'.", set));
    }

    private MojoExecutionException whatNotValid() {
        return new MojoExecutionException(format(
            "Parameter what '%s' is not valid. Accepted values are " +
                "'project', 'dbms', 'schema', 'table', 'column', " +
                "'primaryKeyColumn', 'foreignKey', 'foreignKeyColumn', " +
                "'index' and 'indexColumn'.", what));
    }
}
