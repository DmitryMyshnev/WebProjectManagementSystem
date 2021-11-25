package ua.goIt.dao;

import ua.goIt.DbStatement;
import ua.goIt.model.Company;
import ua.goIt.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends AbstractDao<Customer>{

    @Override
    String getTableName() {
        return "customers";
    }

    @Override
    Customer mapToEntity(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        return customer;
    }

    @Override
    public void create(Customer entity) {
        String query = "insert into customers(first_name, last_name) values (?, ?)";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
        });
    }

    @Override
    public void update(Customer entity) {
        String query = "update customers set first_name = ?, last_name = ? where id = ?";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setLong(3,entity.getId());
        });
    }
}
