package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.DeveloperVsProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DeveloperToProjectDao extends AbstractDao<DeveloperVsProject>{
    private static final Logger LOGGER = LogManager.getLogger(DeveloperToProjectDao.class);
    @Override
    String getTableName() {
        return "developer_project";
    }

    @Override
    DeveloperVsProject mapToEntity(ResultSet rs) throws SQLException {
        DeveloperVsProject developerVsProject = new DeveloperVsProject();
        developerVsProject.setDeveloperId(rs.getLong("developer_id"));
        developerVsProject.setProjectId(rs.getLong("project_id"));
        return developerVsProject;
    }

    @Override
    public void create(DeveloperVsProject entity) {
        String query = "insert into developer_project(developer_id, project_id) values (?, ?)";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setLong(1, entity.getDeveloperId());
            ps.setLong(2, entity.getProjectId());
        });
    }

    @Override
    public void update(DeveloperVsProject entity) {
        String query = "update developer_project set developer_id = ?, project_id = ? where id = ?";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setLong(1, entity.getDeveloperId());
            ps.setLong(2, entity.getProjectId());
        });
    }

    public List<DeveloperVsProject> getAllById(Long id) {
        List<DeveloperVsProject> resultList = new ArrayList<>();
        String query = String.format("select * from %s where developer_id = ?",getTableName());
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
    public void delete(DeveloperVsProject entity) {
        String query = String.format("delete from %s where developer_id = ?", getTableName());
        DbStatement.executeStatementUpdate(query, ps -> ps.setLong(1, entity.getId()));
        LOGGER.debug("Deleted record from " + getTableName());
    }
}
