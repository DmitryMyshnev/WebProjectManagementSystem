package ua.goIt.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Skill implements Identity {
    private Long id;
    private String language;
    private String level;
    private List<Developer> developers;

    public Skill(String language, String level) {
        this.language = language;
        this.level = level;
        this.developers = new ArrayList<>();
    }

    public Skill() {
        this.developers = new ArrayList<>();
    }

    public String getDevelopersAsString(){
        StringBuffer res = new StringBuffer();
        developers.forEach(developer -> res.append(developer.getName()).append(";<br>"));
        return res.toString();
    }


    @Override
    public Long getId() {
        return id;
    }

}
