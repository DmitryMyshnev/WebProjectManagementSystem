package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.DeveloperVsSkill;
import ua.goIt.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperToSkillDao extends AbstractDao<DeveloperVsSkill>{
    private static final Logger LOGGER = LogManager.getLogger(DeveloperToSkillDao.class);
    @Override
    String getTableName() {
        return "developer_skills";
    }

    @Override
    DeveloperVsSkill mapToEntity(ResultSet rs) throws SQLException {
        DeveloperVsSkill developerVsSkill = new DeveloperVsSkill();
        developerVsSkill.setDeveloperId(rs.getLong("developer_id"));
        developerVsSkill.setSkillId(rs.getLong("skills_id"));
        return developerVsSkill;
    }

    @Override
    public void create(DeveloperVsSkill entity) {

    }

    @Override
    public void update(DeveloperVsSkill entity) {

    }

    public List<DeveloperVsSkill> getAllById(Long id) {
        List<DeveloperVsSkill> resultList = new ArrayList<>();
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
    public void delete(DeveloperVsSkill entity) {
        String query = String.format("delete from %s where developer_id = ?", getTableName());
        DbStatement.executeStatementUpdate(query, ps -> ps.setLong(1, entity.getId()));
        LOGGER.debug("Deleted record from " + getTableName());
    }

}
