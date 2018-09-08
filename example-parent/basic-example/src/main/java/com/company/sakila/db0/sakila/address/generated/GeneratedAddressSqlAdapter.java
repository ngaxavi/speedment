package com.company.sakila.db0.sakila.address.generated;

import com.company.sakila.db0.sakila.address.Address;
import com.company.sakila.db0.sakila.address.AddressImpl;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.SqlAdapter;
import com.speedment.runtime.core.db.SqlFunction;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.speedment.common.injector.State.RESOLVED;

/**
 * The generated Sql Adapter for a {@link
 * com.company.sakila.db0.sakila.address.Address} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedAddressSqlAdapter implements SqlAdapter<Address> {
    
    private final TableIdentifier<Address> tableIdentifier;
    
    protected GeneratedAddressSqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("db0", "sakila", "address");
    }
    
    protected Address apply(ResultSet resultSet, int offset) throws SQLException {
        return createEntity()
            .setAddressId(  resultSet.getInt(1 + offset))
            .setAddress(    resultSet.getString(2 + offset))
            .setAddress2(   resultSet.getString(3 + offset))
            .setDistrict(   resultSet.getString(4 + offset))
            .setCityId(     resultSet.getInt(5 + offset))
            .setPostalCode( resultSet.getString(6 + offset))
            .setPhone(      resultSet.getString(7 + offset))
            .setLocation(   resultSet.getBlob(8 + offset))
            .setLastUpdate( resultSet.getTimestamp(9 + offset))
            ;
    }
    
    protected AddressImpl createEntity() {
        return new AddressImpl();
    }
    
    @Override
    public TableIdentifier<Address> identifier() {
        return tableIdentifier;
    }
    
    @Override
    public SqlFunction<ResultSet, Address> entityMapper() {
        return entityMapper(0);
    }
    
    @Override
    public SqlFunction<ResultSet, Address> entityMapper(int offset) {
        return rs -> apply(rs, offset);
    }
}