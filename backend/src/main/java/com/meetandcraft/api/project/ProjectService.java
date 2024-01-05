package com.meetandcraft.api.project;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProjectService {
    ProjectResponse getAllProject (int pageNo, int pageSize);
    ProjectDto getProjectById(UUID projectId);
    boolean projectExist (UUID projectId);
    ProjectDto createProject (ProjectDto projectDto);
    ProjectDto updateProjectById (UUID projectId, ProjectDto projectDto);
    void deleteProjectById (UUID projectId);

}
