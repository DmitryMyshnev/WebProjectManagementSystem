package ua.goIt.services.webService;

import ua.goIt.dao.CompanyDao;
import ua.goIt.dao.CustomerDao;
import ua.goIt.dao.DeveloperDao;
import ua.goIt.dao.ProjectDao;
import ua.goIt.model.Company;
import ua.goIt.model.Project;

import java.util.List;
import java.util.Optional;

public class ProjectWebService implements CrudWeb<Project> {
    private static ProjectWebService projectWebService;
    private final ProjectDao projectDao;
    private final DeveloperDao developerDao;
    private final CompanyDao companyDao;
    private final CustomerDao customerDao;

    private ProjectWebService() {
        projectDao =  ProjectDao.getInstance();
        developerDao = DeveloperDao.getInstance();
        companyDao = CompanyDao.getInstance();
        customerDao = CustomerDao.getInstance();
    }

    @Override
    public void save(Project entity) {
        projectDao.create(entity);
    }

    @Override
    public void update(Project entity) {
        projectDao.update(entity);
    }

    @Override
    public void delete(Project entity) {
        projectDao.delete(entity);
    }

    @Override
    public List<Project> getAll() {
        List<Project> allProject = projectDao.getAll();
        allProject.forEach(project -> {
            project.setDevelopers(developerDao.getAllDeveloperByProjectId(project.getId()));
            project.setCompanies(companyDao.getAllCompanyByProjectId(project.getId()));
            project.setCustomers(customerDao.getAllCustomerByProjectId(project.getId()));
        });
  return allProject;
    }

    @Override
    public Optional<Project> findById(Long id) {
        Optional<Project> project = projectDao.getById(id);
        project.ifPresent(dev-> {
            dev.setDevelopers(developerDao.getAllDeveloperByProjectId(id));
            dev.setCompanies(companyDao.getAllCompanyByProjectId(id));
            dev.setCustomers(customerDao.getAllCustomerByProjectId(id));
        });
        return project;
    }

    public static ProjectWebService getInstance() {
        if (projectWebService == null) {
            projectWebService = new ProjectWebService();
        }
        return projectWebService;
    }
}
