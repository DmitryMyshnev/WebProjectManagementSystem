package ua.goIt.services.webService;

import ua.goIt.dao.CompanyDao;
import ua.goIt.dao.ProjectDao;
import ua.goIt.model.Company;
import ua.goIt.model.Project;

import java.util.List;
import java.util.Optional;

public class CompanyWebService implements CrudWeb<Company> {
    private static CompanyWebService companyWebService;
    private final CompanyDao companyDao;
    private final ProjectDao projectDao;


    private CompanyWebService() {
        companyDao =  CompanyDao.getInstance();
        projectDao = ProjectDao.getInstance();
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
        companyDao.delete(entity);
    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = companyDao.getAll();
        companyList.forEach(company -> company.setProjects(getAllProjectById(company.getId())));
        return companyList;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyDao.getById(id);
    }

    private List<Project> getAllProjectById(Long id){
      return projectDao.getProjectByCompanyId(id);
    }

    public static CompanyWebService getInstance() {
        if (companyWebService == null) {
            companyWebService = new CompanyWebService();
        }
        return companyWebService;
    }
}
