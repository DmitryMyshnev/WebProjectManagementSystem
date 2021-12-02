package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.Company;
import ua.goIt.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends AbstractDao<Customer>{
    private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);
    private static CustomerDao customerDao;

    private CustomerDao() {
    }

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

    @Override
    public void delete(Customer entity) {
        String query = "delete from custom_project where custom_id = ?;" +
                       "delete from %s where id = ?;";
        query = String.format(query, getTableName());
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setLong(1, entity.getId());
            ps.setLong(2,entity.getId());
        });
        LOGGER.debug("Deleted record from " + getTableName());
    }
    public List<Customer> getAllCustomerByProjectId(Long id){
        List<Customer> resultList = new ArrayList<>();
        String query = String.format("select * from %s c  where c.id in(select custom_id from custom_project cp  where cp.project_id = ?)", getTableName());
        try {
            ResultSet resultSet = DbStatement.executeStatementQuery(
                    query, ps -> ps.setLong(1,id));
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Get all method exception", e);
        }
        return resultList;
    }
    public static CustomerDao getInstance(){
        if(customerDao == null){
            customerDao = new CustomerDao();
        }
        return customerDao;
    }
}
