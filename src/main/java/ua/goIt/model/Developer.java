package ua.goIt.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Developer  implements Identity {
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Integer salary;
    private List<Skill> skills;
    private List<Project> projects;

    public Developer() {
    }

    public Developer(String name, Integer age, String sex, Integer salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
        projects = new ArrayList<>();
        skills = new ArrayList<>();
    }

    public void setProject(Project project) {
        this.projects.add(project);
    }

    public void setSkill(Skill skill) {
        this.skills.add(skill);
    }

    @Override
    public Long getId() {
        return id;
    }

}
