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

    void setProject(Project project) {
        this.projects.add(project);
    }

    @Override
    public Long getId() {
        return id;
    }

}
