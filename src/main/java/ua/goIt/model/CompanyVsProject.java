package ua.goIt.model;

import lombok.Data;

@Data
public class CompanyVsProject implements Identity{
    private Long companyId;
    private Long projectId;
    @Override
    public Long getId() {
        return companyId;
    }
}
