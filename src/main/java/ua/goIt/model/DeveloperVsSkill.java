package ua.goIt.model;

import lombok.Data;

@Data
public class DeveloperVsSkill implements Identity{
    private Long developerId;
    private Long skillId;
    @Override
    public Long getId() {
        return developerId;
    }
}
