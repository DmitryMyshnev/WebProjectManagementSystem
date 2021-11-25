package ua.goIt.command;

import ua.goIt.services.consoleService.DeveloperService;

public class AllSalaryCommand implements Command {

    @Override
    public void execute(String... param) {
        Integer total = DeveloperService.getAllSalary(param[1]);
        if(total == 0){
            System.out.println("No such  project '" + param[1] + "'. Or '" + param[1] + "' does not have developers.");
        }else
        System.out.println("Total: " + total);
    }
}
