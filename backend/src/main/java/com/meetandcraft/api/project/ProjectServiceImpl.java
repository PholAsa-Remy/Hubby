package com.meetandcraft.api.project;

import com.meetandcraft.api.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponse getAllProject(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Project> projects = projectRepository.findAll(pageable);

        List<Project> listOfProject = projects.getContent();
        List<ProjectDto> content = listOfProject.stream().map(ProjectMapper::mapToDto).toList();

        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setContent(content);
        projectResponse.setPageNo(projects.getNumber());
        projectResponse.setPageSize(projects.getSize());
        projectResponse.setTotalElements(projects.getTotalElements());
        projectResponse.setTotalPages(projects.getTotalPages());
        projectResponse.setLast(projects.isLast());

        return projectResponse;

    }
    @Override
    public ProjectDto getProjectById(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("The project couldn't be found"));
        return ProjectMapper.mapToDto(project);
    }

    @Override
    public Boolean projectExist(UUID projectId) {
        return projectRepository.existsById(projectId);
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        Project savedProject = projectRepository.save(project);
        return ProjectMapper.mapToDto(savedProject);
    }

    @Override
    public ProjectDto updateProjectById(UUID projectId, ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("The project couldn't be found"));
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        Project updatedProject = projectRepository.save(project);
        return ProjectMapper.mapToDto(updatedProject);
    }

    @Override
    public void deleteProjectById(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("The project couldn't be found"));
        projectRepository.delete(project);
    }




}
