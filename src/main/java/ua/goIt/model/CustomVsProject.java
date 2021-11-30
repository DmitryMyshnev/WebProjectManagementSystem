package ua.goIt.model;

import lombok.Data;

@Data
public class CustomVsProject implements Identity{
    private Long customerId;
    private Long projectId;
    @Override
    public Long getId() {
        return customerId;
    }
}
