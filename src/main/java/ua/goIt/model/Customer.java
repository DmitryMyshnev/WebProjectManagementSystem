package ua.goIt.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Customer implements Identity {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Project> projects;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.projects = new ArrayList<>();
    }

    public Customer() {
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
