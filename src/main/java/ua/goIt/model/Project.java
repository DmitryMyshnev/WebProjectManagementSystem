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

  public String getDevelopersAsString(){
      StringBuffer res = new StringBuffer();
      developers.forEach(developer -> res.append(developer.getName()).append(";<br>"));
      return res.toString();
  }
    public String getCompanyAsString(){
        StringBuffer res = new StringBuffer();
        companies.forEach(company -> res.append(company.getName()).append(";<br>"));
        return res.toString();
    }
    public String getCustomerAsString(){
        StringBuffer res = new StringBuffer();
        customers.forEach(company -> res.append(company.getFirstName()).append(" ").append(company.getLastName()).append(";<br>"));
        return res.toString();
    }
    @Override
    public Long getId() {
        return id;
    }

}
