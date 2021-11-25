package ua.goIt.services.webService;

import ua.goIt.dao.DeveloperDao;
import ua.goIt.model.Developer;
import ua.goIt.services.CrudWeb;

import java.util.List;
import java.util.Optional;


public class DeveloperWebService implements CrudWeb<Developer> {
    private DeveloperDao developerDao;
    private static DeveloperWebService developerWebService;

    private DeveloperWebService() {
        developerDao = new DeveloperDao();
    }

    @Override
    public void save(Developer entity) {
        developerDao.create(entity);
    }

    @Override
    public void update(Developer entity) {
        developerDao.update(entity);
    }

    @Override
    public void delete(Developer entity) {
        developerDao.delete(entity);
    }

    @Override
    public List<Developer> getAll() {
        return developerDao.getAll();
    }

    @Override
    public Optional<Developer> findById(Long id) {
        return developerDao.getById(id);
    }

    public static DeveloperWebService getInstance() {
        if (developerWebService == null) {
            developerWebService = new DeveloperWebService();
        }
        return developerWebService;
    }
}
