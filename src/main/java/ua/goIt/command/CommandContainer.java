package ua.goIt.command;


import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private final Map<String, Command> commandMap;
    private final UnknownCommand unknownCommand;

    public CommandContainer() {
        CrudCommand crudCommand = new CrudCommand();
        this.commandMap = new HashMap<>();
        commandMap.put("migration", new MigrationCommand());
        commandMap.put( "all_salary", new AllSalaryCommand());
        commandMap.put("all_devFromPrj", new AllUseDeveloperCommand());
        commandMap.put("dev_skills", new DeveloperSkillsCommand());
        commandMap.put( "dev_level", new DeveloperLevelCommand());
        commandMap.put("all_projects", new AllProjectsCommand());
        commandMap.put("help", new HelpCommand());
        commandMap.put( "create",crudCommand);
        commandMap.put("update",crudCommand);
        commandMap.put("delete",crudCommand);
        commandMap.put("read",crudCommand);

        unknownCommand = new UnknownCommand();
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
