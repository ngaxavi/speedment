package com.speedment.tool.core.internal.menubar;

import com.speedment.common.injector.State;
import com.speedment.common.injector.annotation.ExecuteBefore;
import com.speedment.common.injector.annotation.InjectKey;
import com.speedment.common.injector.annotation.WithState;
import com.speedment.tool.core.component.UserInterfaceComponent;
import com.speedment.tool.core.menubar.MenuBarComponent;
import com.speedment.tool.core.menubar.MenuBarTab;
import com.speedment.tool.core.resource.SpeedmentIcon;
import com.speedment.tool.core.util.InjectionLoader;
import javafx.scene.control.CheckMenuItem;

/**
 * Installs the default menu item factories used by the Speedment Tool.
 *
 * @author Emil Forslund
 * @since  3.1.5
 */
@InjectKey(DefaultMenuItemFactories.class)
public final class DefaultMenuItemFactories {

    /*
    private @FXML MenuItem mbNew;
    private @FXML MenuItem mbOpen;
    private @FXML MenuItem mbSave;
    private @FXML MenuItem mbSaveAs;
    private @FXML MenuItem mbQuit;
    private @FXML MenuItem mbGenerate;
    private @FXML CheckMenuItem mbProjectTree;
    private @FXML CheckMenuItem mbWorkspace;
    private @FXML CheckMenuItem mbOutput;
    private @FXML MenuItem mbGitter;
    private @FXML MenuItem mbGitHub;
    private @FXML MenuItem mbComponents;
    private @FXML MenuItem mbAbout;
     */

    @ExecuteBefore(State.INITIALIZED)
    void install(@WithState(State.INITIALIZED) MenuBarComponent menuBar,
                 @WithState(State.INITIALIZED) UserInterfaceComponent ui,
                 @WithState(State.INITIALIZED) InjectionLoader loader) {
        menuBar.forTab(MenuBarTab.FILE)
            .addMenuItem("_New", SpeedmentIcon.NEW_PROJECT, ev -> ui.newProject())
            .addMenuItem("_Open", SpeedmentIcon.OPEN_PROJECT, ev -> ui.openProject())
            .addSeparator()
            .addMenuItem("_Save", SpeedmentIcon.DISK, ev -> ui.saveProject())
            .addMenuItem("Save _As", SpeedmentIcon.DISK_MULTIPLE, ev -> ui.saveProjectAs())
            .addSeparator()
            .addMenuItem("_Quit", SpeedmentIcon.DOOR_IN, ev -> ui.quit());

        menuBar.forTab(MenuBarTab.EDIT)
            .addMenuItem("_Generate", SpeedmentIcon.RUN_PROJECT, ev -> ui.generate());

        menuBar.forTab(MenuBarTab.WINDOW)
            .set("project-tree", () -> {
                final CheckMenuItem mi = new CheckMenuItem("_Project Tree");
                mi.setGraphic(SpeedmentIcon.APPLICATION_SIDE_TREE.view());
                mi.selectedProperty().bindBidirectional(ui.projectTreeVisibleProperty());
                return mi;
            })
            .set("workspace", () -> {
                final CheckMenuItem mi = new CheckMenuItem("_Workspace");
                mi.setGraphic(SpeedmentIcon.APPLICATION_FORM.view());
                mi.selectedProperty().bindBidirectional(ui.workspaceVisibleProperty());
                return mi;
            })
            .set("output", () -> {
                final CheckMenuItem mi = new CheckMenuItem("_Output");
                mi.setGraphic(SpeedmentIcon.APPLICATION_XP_TERMINAL.view());
                mi.selectedProperty().bindBidirectional(ui.outputVisibleProperty());
                return mi;
            });

        menuBar.forTab(MenuBarTab.HELP)
            .addMenuItem("Show _Gitter", SpeedmentIcon.USER_COMMENT, ev -> ui.showGitter())
            .addMenuItem("Show Git_Hub", SpeedmentIcon.USER_COMMENT, ev -> ui.showGithub())
            .addSeparator()
            .addMenuItem("_Components", SpeedmentIcon.BRICKS, ev -> loader.loadAsModal("Components"))
            .addSeparator()
            .addMenuItem("_About", SpeedmentIcon.INFORMATION, ev -> loader.loadAsModal("About"));
    }
}
