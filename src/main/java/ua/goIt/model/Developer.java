package ua.goIt.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Developer implements Identity {
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Integer salary;
    private List<Skill> skills;
    private List<Project> projects;

    public Developer() {
        projects = new ArrayList<>();
        skills = new ArrayList<>();
    }

    public Developer(String name, Integer age, String sex, Integer salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
        projects = new ArrayList<>();
        skills = new ArrayList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

}
