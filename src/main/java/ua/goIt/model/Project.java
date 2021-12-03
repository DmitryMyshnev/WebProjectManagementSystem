package ua.goIt.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Project implements Identity {
    private Long id;
    private String name;
    private String description;
    private Integer cost;
    private String date;
    private List<Developer> developers;
    private List<Company> companies;
    private List<Customer> customers;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.developers = new ArrayList<>();
        this.companies = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public Project() {
    }

    @Override
    public Long getId() {
        return id;
    }

}
