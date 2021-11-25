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
        String query = "update skills set language = ? where id = ?";
        DbStatement.executeStatementUpdate(query, ps -> {
            ps.setString(1, entity.getLanguage());
            ps.setLong(2,entity.getId());
        });
    }
    public List<Skill> findByName(String name){
        List<Skill> list = new ArrayList<>();
        String query = "select * from skills where language = ?";
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
}
