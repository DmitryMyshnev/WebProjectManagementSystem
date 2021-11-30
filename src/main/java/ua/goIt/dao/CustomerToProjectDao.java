package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.CustomVsProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerToProjectDao extends AbstractDao<CustomVsProject>{
    private static final Logger LOGGER = LogManager.getLogger(CustomerToProjectDao.class);
    @Override
    String getTableName() {
        return "custom_project";
    }

    @Override
    CustomVsProject mapToEntity(ResultSet rs) throws SQLException {
        CustomVsProject customVsProject = new CustomVsProject();
        customVsProject.setCustomerId(rs.getLong("custom_id"));
        customVsProject.setProjectId(rs.getLong("project_id"));
        return customVsProject;
    }

    @Override
    public void create(CustomVsProject entity) {

    }

    @Override
    public void update(CustomVsProject entity) {
    }

    public List<CustomVsProject> getAllById(Long id) {
        List<CustomVsProject> resultList = new ArrayList<>();
        String query = String.format("select * from %s where custom_id = ?",getTableName());
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
    public void delete(CustomVsProject entity) {
        String query = String.format("delete from %s where custom_id = ?", getTableName());
        DbStatement.executeStatementUpdate(query, ps -> ps.setLong(1, entity.getId()));
        LOGGER.debug("Deleted record from " + getTableName());
    }
}
