package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao extends AbstractDao<Project>{
    private static final Logger LOGGER = LogManager.getLogger(ProjectDao.class);

    @Override
    String getTableName() {
        return "projects";
    }

    @Override
    Project mapToEntity(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setId(rs.getLong("id"));
        project.setName(rs.getString("project_name"));
        project.setDescription(rs.getString("description"));
        project.setCost(rs.getInt("cost"));
        project.setDate(rs.getString("date"));
        return project;
    }

    @Override
    public void create(Project entity) {
        SimpleDateFormat date = new SimpleDateFormat("d.MM.yyyy");
        String query = "insert into projects(project_name, description,cost,date) values (?, ?, ?, ?)";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getCost());
            ps.setString(4, date.format(System.currentTimeMillis()));
        });
    }

    @Override
    public void update(Project entity) {
        String query = "update projects set project_name = ?, description = ?, cost = ?, date = ? where id = ?";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getCost());
            ps.setString(4, entity.getDate());
            ps.setLong(5,entity.getId());
        });
    }
    public List<Project> getProjectByDeveloperId(Long id){
        List<Project> resultList = new ArrayList<>();
        String query = String.format("select * from %s p  where p.id in(select project_id from developer_project dp  where dp.developer_id = ?)", getTableName());
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
