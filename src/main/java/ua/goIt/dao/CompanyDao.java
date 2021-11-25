package ua.goIt.dao;

import ua.goIt.DbStatement;
import ua.goIt.model.Company;
import ua.goIt.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDao extends AbstractDao<Company>{
    @Override
    String getTableName() {
        return "companies";
    }

    @Override
    Company mapToEntity(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getLong("id"));
        company.setName(rs.getString("company_name"));
        company.setQuantityEmployee(rs.getInt("quantity_employee"));
        return company;
    }

    @Override
    public void create(Company entity) {
        String query = "insert into companies(company_name, quantity_employee) values (?, ?)";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getQuantityEmployee());
        });
    }

    @Override
    public void update(Company entity) {
        String query = "update companies set company_name = ?, quantity_employee = ? where id = ?";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getQuantityEmployee());
            ps.setLong(3,entity.getId());
        });
    }
}
