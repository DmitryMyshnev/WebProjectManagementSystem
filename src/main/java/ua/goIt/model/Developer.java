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

    public void setProject(Project project) {
        this.projects.add(project);
    }

    public void setSkill(Skill skill) {
        this.skills.add(skill);
    }

    public String getSkillsAsString() {
        StringBuffer res = new StringBuffer();
        skills.forEach(skill -> {
            res.append(skill.getLanguage()).append(" (").append(skill.getLevel()).append(");<br>");
        });
        return res.toString();
    }
    public String getProjectAsString(){
        StringBuffer res = new StringBuffer();
        projects.forEach(project -> {
            res.append(project.getName()).append(";<br>");
        });
        return res.toString();
    }
    @Override
    public Long getId() {
        return id;
    }

}
