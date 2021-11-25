package ua.goIt.services.consoleService;

import ua.goIt.dao.DeveloperDao;
import ua.goIt.model.Developer;

import static ua.goIt.services.consoleService.ValidatePattern.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static ua.goIt.services.consoleService.Validate.*;

public class DeveloperService implements CrudConsole {
    private final Developer developer;
    private static DeveloperDao developerDao;
    private static DeveloperService developerService;

    private DeveloperService() {
        developer = new Developer();
        developerDao = new DeveloperDao();
    }


    private boolean isValid(String param) {
        String[] arrayParam = param.split(",");

        if (!isValidByPattern(NAME_PATTERN, arrayParam[0])) {
            System.out.printf((NAME_ERROR) + "%n", arrayParam[0]);
            return false;
        }
        if (!isValidByPattern(AGE_PATTERN, arrayParam[1])) {
            System.out.printf((AGE_ERROR) + "%n", arrayParam[1]);
            return false;
        }
        if (!isValidByPattern(GENDER_PATTERN, arrayParam[2])) {
            System.out.printf((GENDER_ERROR) + "%n", arrayParam[2]);
            return false;
        }
        if (!isValidByPattern(DIGITAL_PATTERN, arrayParam[3])) {
            System.out.printf((DIGITAL_ERROR) + "%n", arrayParam[3]);
            return false;
        } else
            return true;
    }

    @Override
    public void save(String arg) {
        if (!isValidByPattern(DEVELOPER_SAVE_PATTERN, arg)) {
            System.out.println(TEMPLATE_ERROR);
        } else if (isValid(arg)) {
            prepareInstance(arg);
            getDao().create(developer);
            System.out.println("Developer '" + developer.getName() + "' was added.");
        }
    }

    @Override
    public void update(String arg) {
        if (!isValidByPattern(DEVELOPER_UPDATE_PATTERN, arg)) {
            System.out.println(TEMPLATE_ERROR);
            return;
        }
        if (!isValidByPattern(DIGITAL_PATTERN, arg.split(",")[4])) {
            System.out.printf((DIGITAL_ERROR) + "%n", arg);
            return;
        }
        if (isValid(arg)) {
            prepareInstance(arg);
            getDao().update(developer);
            System.out.println("Developer '" + developer.getName() + "' was updated.");
        }
    }

    @Override
    public void delete(String arg) {
        if (!isValidByPattern(DIGITAL_PATTERN, arg)) {
            System.out.printf((DIGITAL_ERROR) + "%n", arg);
        } else {
            developer.setId(Long.parseLong(arg));
            getDao().delete(developer);
            System.out.println("Developer was deleted.");
        }
    }

    @Override
    public Optional<Object> findById(Long id) {
        Optional<Developer> developer = getDao().getById(id);
        developer.ifPresent(System.out::println);
        if (developer.isEmpty()) {
            return Optional.empty();
        } else
            return Optional.of(developer.get());
    }

    @Override
    public List<Object> getAll() {
        List<Developer> all = getDao().getAll();
        all.forEach(System.out::println);
        return new ArrayList<>(all);
    }

    public static Integer getAllSalary(String projectName) {
        return getDao().getAllSalaryByProject(projectName);
    }

    public static List<Developer> getAllDeveloperFromProject(String projectName) {
        return getDao().getAllDeveloperByProject(projectName);
    }

    public static List<Developer> getDeveloperSkill(String skillsName) {
        return getDao().getDeveloperBySkills(skillsName);
    }

    public static List<Developer> getDeveloperLevel(String level) {
        return getDao().getDeveloperByLevel(level);
    }

    private static DeveloperDao getDao() {
        if (developerDao == null) {
            developerDao = new DeveloperDao();
        }
        return developerDao;
    }

    public static DeveloperService getInstance() {
        if (developerService == null) {
            developerService = new DeveloperService();
        }
        return developerService;
    }

    private void prepareInstance(String data) {
        String[] fields = data.split(",");
        developer.setName(fields[0]);
        developer.setAge(Integer.parseInt(fields[1]));
        developer.setSex(fields[2]);
        developer.setSalary(Integer.parseInt(fields[3]));
        if (fields.length == 5) {
            developer.setId(Long.parseLong(fields[4]));
        }
    }
}
