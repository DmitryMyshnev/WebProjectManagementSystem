package ua.goIt.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Company implements Identity {
    private Long id;
    private String name;
    private Integer quantityEmployee;
    private List<Project> projects;

    public Company(String name, Integer quantityEmployee) {
        this.name = name;
        this.quantityEmployee = quantityEmployee;
        this.projects = new ArrayList<>();
    }

    public Company() {
    }

    public String getProjectsAsString() {
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
