package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.CompanyVsProject;
import ua.goIt.model.DeveloperVsProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyToProjectDao extends AbstractDao<CompanyVsProject>{
    private static final Logger LOGGER = LogManager.getLogger(CompanyToProjectDao.class);
    @Override
    String getTableName() {
        return "company_project";
    }

    @Override
    CompanyVsProject mapToEntity(ResultSet rs) throws SQLException {
        CompanyVsProject companyVsProject = new CompanyVsProject();
        companyVsProject.setCompanyId(rs.getLong("company_id"));
        companyVsProject.setProjectId(rs.getLong("project_id"));
        return companyVsProject;
    }

    @Override
    public void create(CompanyVsProject entity) {

    }

    @Override
    public void update(CompanyVsProject entity) {
    }

    public List<CompanyVsProject> getAllById(Long id) {
        List<CompanyVsProject> resultList = new ArrayList<>();
        String query = String.format("select * from %s where company_id = ?",getTableName());
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

    @Override
    public void delete(CompanyVsProject entity) {
        String query = String.format("delete from %s where company_id = ?", getTableName());
        DbStatement.executeStatementUpdate(query, ps -> ps.setLong(1, entity.getId()));
        LOGGER.debug("Deleted record from " + getTableName());
    }
}
