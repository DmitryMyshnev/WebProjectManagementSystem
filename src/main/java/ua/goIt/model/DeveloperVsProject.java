package ua.goIt.model;

import lombok.Data;

@Data
public class DeveloperVsProject implements Identity {
    private Long developerId;
    private Long projectId;
    @Override
    public Long getId() {
        return developerId;
    }
}
