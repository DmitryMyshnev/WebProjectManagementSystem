package ua.goIt.dao;

import ua.goIt.DbStatement;
import ua.goIt.model.Developer;
import ua.goIt.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ProjectDao extends AbstractDao<Project>{

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
}
