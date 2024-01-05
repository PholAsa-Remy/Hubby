package com.meetandcraft.api.project;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProjectService {
    public ProjectResponse getAllProject (int pageNo, int pageSize);
    public ProjectDto getProjectById(UUID projectId);
    public ProjectDto createProject (ProjectDto projectDto);
    public ProjectDto updateProjectById (UUID projectId, ProjectDto projectDto);
    public void deleteProjectById (UUID projectId);

}
