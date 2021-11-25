package ua.goIt.command;

import ua.goIt.model.Developer;
import ua.goIt.services.consoleService.DeveloperService;

import java.util.List;

public class DeveloperLevelCommand implements Command{


    @Override
    public void execute(String... param) {
        List<Developer> devList = DeveloperService.getDeveloperLevel(param[1].split(" ")[0]);
        if (devList.isEmpty()) {
            System.out.println("No such  level '" + param[1] + "'. Or developers does not have level '" + param[1] + "'.");
        } else {
            devList.forEach(dev -> {
                System.out.println(dev.toString());
            });
        }
    }
}
