package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.Developer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDao extends AbstractDao<Developer> {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);

    String getTableName() {
        return "developers";
    }

    @Override
    Developer  mapToEntity(ResultSet resultSet) throws SQLException {
        Developer dev = new Developer();
        dev.setId(resultSet.getLong("id"));
        dev.setName(resultSet.getString("name"));
        dev.setAge(resultSet.getInt("age"));
        dev.setSex(resultSet.getString("sex"));
        dev.setSalary(resultSet.getInt("salary"));
        return dev;
    }

    @Override
    public void create(Developer user) {
        String query = "insert into developers(name, age,sex,salary) values (?, ?, ?, ?)";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getSex());
            ps.setInt(4, user.getSalary());
        });
    }

    @Override
    public void update(Developer user) {
        String query = "update developers set name = ?, age = ?, sex = ?, salary = ? where id = ?";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getSex());
            ps.setInt(4, user.getSalary());
            ps.setLong(5, user.getId());
        });
    }

    public  List<Developer> findByName(String name){
        List<Developer> list = new ArrayList<>();
        String query = "select * from developers where name = ?";
        try {
            ResultSet resultSet = DbStatement.executeStatementQuery(
                    query, ps -> ps.setString(1, name));
            while (resultSet.next()) {
                list.add(mapToEntity(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.info(e.getSQLState());
            LOGGER.info(e.getMessage());
        }
        return list;
    }
    public Integer getAllSalaryByProject(String projectName) {
        String query = "select sum(salary) as total from developers as d" +
                "  join developer_project as dxp on d.id = dxp.developer_id" +
                "  join projects as p on p.id = dxp. project_id" +
                "  where p.id = (select id from projects where project_name = ?);";
        try {
            ResultSet resultSet = DbStatement.executeStatementQuery(
                    query, ps -> ps.setString(1, projectName));
            while (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            LOGGER.info(e.getSQLState());
            LOGGER.info(e.getMessage());
        }
        return -1;
    }

    public List<Developer> getAllDeveloperByProject(String projectName) {
        List<Developer> list = new ArrayList<>();
        String query = " select * from developers d where id in (" +
                " select developer_id from developer_project dp where dp.project_id =(" +
                " select id from projects p where project_name = ?));";
        try {
            ResultSet resultSet = DbStatement.executeStatementQuery(
                    query, ps -> ps.setString(1, projectName));
            while (resultSet.next()) {
                list.add(mapToEntity(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.info(e.getSQLState());
            LOGGER.info(e.getMessage());
        }
        return list;
    }
    public List<Developer> getDeveloperBySkills(String skillsName){
        List<Developer> list = new ArrayList<>();
        String query = "select * from developers  where id in(" +
                " select developer_id from developer_skills  where skills_id in (" +
                " select id from skills where language = ?));";
        try {
            ResultSet resultSet = DbStatement.executeStatementQuery(
                    query, ps -> ps.setString(1, skillsName));
            while (resultSet.next()) {
                list.add(mapToEntity(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.info(e.getSQLState());
            LOGGER.info(e.getMessage());
        }
        return list;
    }
    public List<Developer> getDeveloperByLevel(String level){
        List<Developer> list = new ArrayList<>();
        String query = " select * from developers  where id in(" +
                " select developer_id from developer_skills  where skills_id in (" +
                " select id from skills where level = ?));";
        try {
            ResultSet resultSet = DbStatement.executeStatementQuery(
                    query, ps -> ps.setString(1, level));
            while (resultSet.next()) {
                list.add(mapToEntity(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.info(e.getSQLState());
            LOGGER.info(e.getMessage());
        }
        return list;
    }

}
