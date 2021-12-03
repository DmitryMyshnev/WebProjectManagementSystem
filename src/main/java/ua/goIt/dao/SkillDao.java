package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillDao extends AbstractDao<Skill>{
    private static final Logger LOGGER = LogManager.getLogger(SkillDao.class);
    private static SkillDao skillDao;

    private SkillDao() {
    }

    @Override
    String getTableName() {
        return "skills";
    }

    @Override
    Skill mapToEntity(ResultSet rs) throws SQLException {
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setLanguage(rs.getString("language"));
        skill.setLevel(rs.getString("level"));
        return skill;
    }

    @Override
    public void create(Skill entity) {
        String query = "insert into skills(language, level) values (?, ?)";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getLanguage());
            ps.setString(2, entity.getLevel());
        });
    }

    @Override
    public void update(Skill entity) {
        String query = "update skills set language = ?, level = ? where id = ?";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getLanguage());
            ps.setString(2,entity.getLevel());
            ps.setLong(3,entity.getId());
        });
    }

    @Override
    public void delete(Skill entity) {
        String query = "delete from developer_skills where developer_id =?;" +
                "delete from %s where id = ?;";
        query = String.format(query, getTableName());
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setLong(1, entity.getId());
            ps.setLong(2,entity.getId());
        });
        LOGGER.debug("Deleted record from " + getTableName());
    }

    public List<Skill> getSkillsByDeveloperId(Long id){
        List<Skill> resultList = new ArrayList<>();
        String query = String.format("select * from %s s where s.id in(select skills_id from developer_skills ds where ds.developer_id = ?)", getTableName());
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
    public static SkillDao getInstance(){
        if(skillDao == null){
            skillDao = new SkillDao();
        }
        return skillDao;
    }
}
