package ua.goIt.services.consoleService;

import java.util.Map;

public class ServiceContainer {
    private final Map<String, CrudConsole> entityMap;
    private static ServiceContainer serviceContainer;

    private ServiceContainer() {
        entityMap = Map.of(
                "developer",  DeveloperService.getInstance(),
                "project",ProjectService.getInstance(),
                "customer", CustomerService.getInstance(),
                "company", CompanyService.getInstance(),
                "skills", SkillsService.getInstance());
    }

    public static CrudConsole getService(String service) {
        return getInstance().entityMap.getOrDefault(service, null);
    }
    private static ServiceContainer getInstance(){
        if (serviceContainer == null) {
            serviceContainer = new ServiceContainer();
        }
        return serviceContainer;
    }
}
