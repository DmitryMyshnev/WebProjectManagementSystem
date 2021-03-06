package ua.goIt.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goIt.DbStatement;
import ua.goIt.model.Identity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class AbstractDao<T extends Identity> implements Dao<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    abstract String getTableName();
    abstract T mapToEntity(ResultSet rs) throws SQLException;

    @Override
    public void delete(T entity) {
        String query = String.format("delete from %s where id = ?", getTableName());
        DbStatement.executeStatementUpdate(query, ps -> ps.setLong(1, entity.getId()));
        LOGGER.debug("Deleted record from " + getTableName());
    }

    @Override
    public Optional<T> getById(Long id) {
        String query = String.format("select * from %s where id = ?", getTableName());
        try {
            ResultSet resultSet = DbStatement.executeStatementQuery(
                    query, ps -> ps.setLong(1, id));
            if (resultSet.next()) {
                LOGGER.debug("Record was selected");
                return Optional.of(mapToEntity(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<T> getAll() {
        List<T> resultList = new ArrayList<>();
        String query = String.format("select * from %s", getTableName());
        try {
            ResultSet resultSet = DbStatement.executeStatementQuery(
                    query, ps -> {
                    });
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Get all method exception", e);
        }
        return resultList;
    }

}
