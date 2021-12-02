package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.Developer;
import ua.goIt.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDao extends AbstractDao<Developer> {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);
    private static DeveloperDao developerDao;

    private DeveloperDao() {
    }

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

    @Override
    public void delete(Developer entity) {
        String query = "delete from developer_project where developer_id = ?;" +
                "delete from developer_skills where developer_id =?;" +
                "delete from %s where id = ?;";
        query = String.format(query, getTableName());
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setLong(1, entity.getId());
            ps.setLong(2,entity.getId());
            ps.setLong(3,entity.getId());
        });
        LOGGER.debug("Deleted record from " + getTableName());
    }
   public List<Developer> getAllDeveloperByProjectId(Long id){
       List<Developer> resultList = new ArrayList<>();
       String query = String.format("select * from %s d  where d.id in(select developer_id from developer_project dp  where dp.project_id = ?)", getTableName());
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

    public List<Developer> getAllDeveloperBySkillsId(Long id){
        List<Developer> resultList = new ArrayList<>();
        String query = String.format("select * from %s d  where d.id in(select developer_id from developer_skills ds  where ds.skills_id = ?)", getTableName());
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

   public static DeveloperDao getInstance(){
        if(developerDao == null){
            developerDao = new DeveloperDao();
        }
        return developerDao;
   }
}
