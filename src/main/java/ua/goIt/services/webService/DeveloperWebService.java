package ua.goIt.services.webService;

import ua.goIt.dao.*;
import ua.goIt.model.Developer;
import ua.goIt.model.Project;
import ua.goIt.model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeveloperWebService implements CrudWeb<Developer> {
    private final DeveloperDao developerDao;
    private final SkillDao skillDao;
    private final ProjectDao projectDao;
    private static DeveloperWebService developerWebService;

    private DeveloperWebService() {
        developerDao =  DeveloperDao.getInstance();
        skillDao = SkillDao.getInstance();
        projectDao =  ProjectDao.getInstance();
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
        List<Developer> allDeveloper = developerDao.getAll();
        allDeveloper.forEach(developer -> {
            List<Skill> skills = new ArrayList<>(getAllSkills(developer.getId()));
            List<Project>  projects = new ArrayList<>(getAllProject(developer.getId()));
            developer.setSkills(skills);
            developer.setProjects(projects);
        });
        return allDeveloper;
    }

    @Override
    public Optional<Developer> findById(Long id) {
        Optional<Developer> developer = developerDao.getById(id);
        developer.ifPresent(dev->{
            dev.setProjects(projectDao.getProjectByDeveloperId(id));
            dev.setSkills(skillDao.getSkillsByDeveloperId(id));
        });
        return developer;
    }

    public static DeveloperWebService getInstance() {
        if (developerWebService == null) {
            developerWebService = new DeveloperWebService();
        }
        return developerWebService;
    }

    public List<Skill> getAllSkills(Long id) {
        return skillDao.getSkillsByDeveloperId(id);
    }


    public List<Project> getAllProject(Long id){
        return projectDao.getProjectByDeveloperId(id);
    }
}
