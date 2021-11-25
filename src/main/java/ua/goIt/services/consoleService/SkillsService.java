package ua.goIt.services.consoleService;

import ua.goIt.dao.SkillDao;
import ua.goIt.model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.goIt.services.consoleService.Validate.*;
import static ua.goIt.services.consoleService.ValidatePattern.*;

public class SkillsService implements CrudConsole {
    private final Skill skill;
    private static SkillDao skillDao;
    private static SkillsService skillsService;

    private SkillsService() {
        skill = new Skill();
        skillDao = new SkillDao();
    }


    private boolean isValid(String param) {
        if (!isValidByPattern(NAME_PATTERN,param)) {
            System.out.printf((NAME_ERROR) + "%n", param);
            return false;
        }
        return true;
    }

    @Override
    public void save(String arg) {
        if (isValid(arg)) {
            prepareInstance(arg);
            skill.setLevel("Junior");
            getDao().create(skill);
            skill.setLevel("Middle");
            getDao().create(skill);
            skill.setLevel("Senior");
            getDao().create(skill);
            System.out.println("Skills '" + skill.getLanguage() + "' was added.");
        }
    }

    @Override
    public void update(String arg) {
        if (!isValidByPattern(SKILLS_UPDATE_PATTERN, arg)) {
            System.out.println(TEMPLATE_ERROR);
            return;
        }
        if (!isValidByPattern(DIGITAL_PATTERN, arg.split(",")[1])) {
            System.out.printf((DIGITAL_ERROR) + "%n",arg);
            return;
        }
        String[] arrayParam = arg.split(",");
        prepareInstance(arg);
        getDao().getById(Long.parseLong(arrayParam[1])).ifPresentOrElse(lang -> {
            getDao().findByName(lang.getLanguage()).forEach(sk -> {
                skill.setId(sk.getId());
                getDao().update(skill);
            });
            System.out.println("Skills '" + skill.getLanguage() + "' was updated.");
        }, () -> System.out.println("Not Found '" + skill + "' by id = " + arrayParam[1]));

    }

    @Override
    public void delete(String arg) {
        if (!isValidByPattern(DIGITAL_PATTERN, arg)) {
            System.out.printf((DIGITAL_ERROR) + "%n",arg);
        } else {
            getDao().getById(Long.parseLong(arg)).ifPresentOrElse(sk -> {
                getDao().findByName(sk.getLanguage()).forEach(lang -> {
                    skill.setId(lang.getId());
                    getDao().delete(skill);
                });
                System.out.println("Skills  was deleted.");
            }, () -> System.out.println("Not Found skills by id = " + arg));
        }
    }


    @Override
    public List<Object> getAll() {
        List<Skill> all = getDao().getAll();
        all.forEach(System.out::println);
        return new ArrayList<>(all);
    }

    @Override
    public Optional<Object> findById(Long id) {
        Optional<Skill> skill = getDao().getById(id);
        skill.ifPresent(System.out::println);
        if (skill.isEmpty()) {
            return Optional.empty();
        } else
            return Optional.of(skill.get());
    }

    public static SkillDao getDao() {
        if (skillDao == null) {
            skillDao = new SkillDao();
        }
        return skillDao;
    }

    public static SkillsService getInstance() {
        if (skillsService == null) {
            skillsService = new SkillsService();
        }
        return skillsService;
    }

    private void prepareInstance(String data) {
        String[] fields = data.split(",");
        skill.setLanguage(fields[0]);
        if (fields.length == 2) {
            skill.setId(Long.parseLong(fields[1]));
        }
    }
}
