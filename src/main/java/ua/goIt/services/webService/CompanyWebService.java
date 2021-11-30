package ua.goIt.services.webService;

import ua.goIt.dao.CompanyDao;
import ua.goIt.dao.CompanyToProjectDao;
import ua.goIt.model.Company;

import java.util.List;
import java.util.Optional;

public class CompanyWebService implements CrudWeb<Company> {
    private static CompanyWebService companyWebService;
    private final CompanyDao companyDao;
    private final CompanyToProjectDao companyToProjectDao;

    private CompanyWebService() {
        companyDao = new CompanyDao();
        companyToProjectDao = new CompanyToProjectDao();
    }

    @Override
    public void save(Company entity) {
        companyDao.create(entity);
    }

    @Override
    public void update(Company entity) {
        companyDao.update(entity);
    }

    @Override
    public void delete(Company entity) {
        companyToProjectDao.getAllById(entity.getId()).forEach(companyToProjectDao::delete);
        companyDao.delete(entity);
    }

    @Override
    public List<Company> getAll() {
        return companyDao.getAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyDao.getById(id);
    }

    public static CompanyWebService getInstance() {
        if (companyWebService == null) {
            companyWebService = new CompanyWebService();
        }
        return companyWebService;
    }
}
