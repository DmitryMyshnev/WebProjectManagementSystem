package ua.goIt.services.consoleService;


import ua.goIt.dao.ProjectDao;
import ua.goIt.model.Project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.goIt.services.consoleService.ValidatePattern.*;
import static ua.goIt.services.consoleService.Validate.*;

public class ProjectService implements CrudConsole {
    private static ProjectDao projectDao;
    private final Project project;
    private static  ProjectService  projectService;

    private ProjectService() {
        project = new Project();
        projectDao = new ProjectDao();
    }


    private boolean isValid(String param) {
        String[] arrayParam = param.split(",");

       if(!isValidByPattern(NAME_PATTERN,arrayParam[0])){
           System.out.printf((NAME_ERROR) + "%n", arrayParam[0]);
           return false;
       }
       if(!isValidByPattern(DIGITAL_PATTERN,arrayParam[2])){
           System.out.printf((DIGITAL_ERROR) + "%n",arrayParam[2]);
           return false;
       }
       if(arrayParam.length == 4){
           if(!isValidByPattern(DIGITAL_PATTERN,arrayParam[3])){
               System.out.printf((DIGITAL_ERROR) + "%n",arrayParam[3]);
               return false;
           }
       }
        return true;
    }

    @Override
    public void save(String arg) {
        if (!isValidByPattern(PROJECT_SAVE_PATTERN, arg)) {
            System.out.println(TEMPLATE_ERROR);
        }else
        if (isValid(arg)) {
            project.setDate(new SimpleDateFormat("d.M.yyyy").format(System.currentTimeMillis()));
            prepareInstance(arg);
            projectDao.create(project);
            System.out.println("Project '" + project.getName() + "' was added.");
        }
    }

    @Override
    public void update(String arg) {
        if (!isValidByPattern(PROJECT_UPDATE_PATTERN, arg)) {
            System.out.println(TEMPLATE_ERROR);
            return;
        }
        if (!isValidByPattern(DIGITAL_PATTERN,arg.split(",")[3])){
            System.out.printf((DIGITAL_ERROR) + "%n",arg);
            return;
        }
        if (isValid(arg)) {
            prepareInstance(arg);
            projectDao.update(project);
            System.out.println("Project '" + project.getName() + "' was updated.");
        }
    }

    @Override
    public void delete(String arg) {
        if(!isValidByPattern(DIGITAL_PATTERN,arg)){
            System.out.printf((DIGITAL_ERROR) + "%n",arg);
        }else {
            project.setId(Long.parseLong(arg));
            getDao().delete(project);
            System.out.println("Project was deleted.");
        }
    }

    @Override
    public List<Object> getAll() {
        List<Project> all = getDao().getAll();
        all.forEach(System.out::println);
        return new ArrayList<>(all);
    }

    @Override
    public Optional<Object> findById(Long id) {
        Optional<Project> project = getDao().getById(id);
        project.ifPresent(System.out::println);
        if (project.isEmpty()) {
            return Optional.empty();
        } else
            return Optional.of(project.get());
    }

    public static ProjectDao getDao() {
        if (projectDao == null) {
            projectDao = new ProjectDao();
        }
        return projectDao;
    }
    public static ProjectService getInstance() {
        if (projectService == null) {
            projectService = new ProjectService();
        }
        return projectService;
    }
    private void prepareInstance(String data) {
        String[] fields = data.split(",");
        project.setName(fields[0]);
        project.setDescription(fields[1]);
        project.setCost(Integer.parseInt(fields[2]));
        if(fields.length == 4){
            project.setId(Long.parseLong(fields[3]));
        }
    }
}
