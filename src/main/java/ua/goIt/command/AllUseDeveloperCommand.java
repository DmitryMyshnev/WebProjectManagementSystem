package ua.goIt.command;

import ua.goIt.model.Developer;
import ua.goIt.services.consoleService.DeveloperService;

import java.util.List;


public class AllUseDeveloperCommand implements Command{


    @Override
    public void execute(String... param) {
      List<Developer> devList = DeveloperService.getAllDeveloperFromProject(param[1]);
         if(devList.isEmpty()){
             System.out.println("No such  project '" + param + "'. Or '" + param + "' does not have developers.");
         }else {
             devList.forEach(dev->{
                 System.out.println(dev.toString());
             });
         }



    }
}
