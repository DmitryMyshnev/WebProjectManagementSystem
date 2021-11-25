package ua.goIt.command;

import ua.goIt.services.consoleService.ServiceContainer;

import java.util.regex.Pattern;

import static ua.goIt.services.consoleService.Validate.*;

public class CrudCommand implements Command {
    @Override
    public void execute(String... param) {
        String command = param[0];
        String entity = param[1];
        String arg = param[2];
        if (entity.isEmpty()) {
            System.out.println("Not found entity.");
            return;
        }
        if (arg.isEmpty()) {
            System.out.println("No any have arguments.");
            return;
        }
        if (ServiceContainer.getService(entity) != null) {
            switch (command) {
                case "create":
                    ServiceContainer.getService(entity).save(arg);
                    break;
                case "update":
                    ServiceContainer.getService(entity).update(arg);
                    break;
                case "delete":
                    ServiceContainer.getService(entity).delete(arg);
                    break;
                case "read":
                    if (isValidByPattern(Pattern.compile("^(\\d+)$"), arg)) {
                        ServiceContainer.getService(entity).findById(Long.parseLong(arg));
                    }else {
                        System.out.println("Mistake in  argument '" + arg + "'. Id need contain  only digital  symbols");
                    }
                    break;
            }
        } else {
            System.out.println("Not found entity '" + entity + "'.");
        }
    }
}
