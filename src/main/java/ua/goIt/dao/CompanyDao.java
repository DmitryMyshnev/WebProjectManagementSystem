package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.Company;
import ua.goIt.model.Developer;
import ua.goIt.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao extends AbstractDao<Company>{
    private static final Logger LOGGER = LogManager.getLogger(CompanyDao.class);
    private static CompanyDao companyDao;

    private CompanyDao() {
    }

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

    @Override
    public void delete(Company entity) {
        String query = "delete from company_project where company_id = ?;" +
                "delete from %s where id = ?;";
        query = String.format(query, getTableName());
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setLong(1, entity.getId());
            ps.setLong(2,entity.getId());
        });
        LOGGER.debug("Deleted record from " + getTableName());
    }
    public static CompanyDao getInstance(){
        if(companyDao == null){
            companyDao = new CompanyDao();
        }
        return companyDao;
    }
    public List<Company> getAllCompanyByProjectId(Long id){
        List<Company> resultList = new ArrayList<>();
        String query = String.format("select * from %s c  where c.id in(select company_id from company_project cp  where cp.project_id = ?)", getTableName());
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
}
