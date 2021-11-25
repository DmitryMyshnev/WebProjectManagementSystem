package ua.goIt.command;

import ua.goIt.model.Project;
import ua.goIt.services.consoleService.DeveloperService;
import ua.goIt.services.consoleService.ProjectService;

import java.util.List;

public class AllProjectsCommand implements Command{
    @Override
    public void execute(String... param) {
       List<Project> listProject =  ProjectService.getDao().getAll();
       if(listProject.isEmpty()){
           System.out.println("No such projects!");
       }else {
           listProject.forEach(prj->{
               Integer totalDev = DeveloperService.getAllDeveloperFromProject(prj.getName()).size();
               String message = prj.getDate() + " | " + prj.getName() + " | " + totalDev;
               System.out.println(message);
           });
       }
    }
}
