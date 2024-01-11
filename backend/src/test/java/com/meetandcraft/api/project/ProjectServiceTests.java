package com.meetandcraft.api.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTests {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void ProjectService_CreateProject_ReturnProjectDto () {
        Project project = Project.builder().title("Project").description("Description").build();
        ProjectDto projectDto = ProjectDto.builder().title("Project").description("Description").build();

        when(projectRepository.save(Mockito.any(Project.class))).thenReturn(project);

        ProjectDto savedProject = projectService.createProject(projectDto);
        assertThat(savedProject).isNotNull();
    }

    @Test
    public void ProjectService_GetAllProject_ReturnResponseDto () {
        ProjectResponse projectReturn = Mockito.mock(ProjectResponse.class);
        Page<Project> projects = Mockito.mock(Page.class);
        when(projectRepository.findAll(Mockito.any(Pageable.class))).thenReturn(projects);
        ProjectResponse saveProject = projectService.getAllProject(1, 10);
        assertThat(saveProject).isNotNull();
    }

    @Test
    public void ProjectService_GetById_ReturnProjectDto () {
        UUID id = new UUID(10,10);
        Project project = Project.builder().id(id).title("Project").description("Description").build();

        when(projectRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.ofNullable(project));

        ProjectDto savedProject = projectService.getProjectById(id);
        assertThat(savedProject).isNotNull();
    }

    @Test
    public void ProjectService_UpdateById_ReturnProjectDto () {
        UUID id = new UUID(10,10);
        Project project = Project.builder().id(id).title("Project").description("Description").build();
        ProjectDto projectDto = ProjectDto.builder().title("Project").description("new Description").build();

        when(projectRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.ofNullable(project));
        when(projectRepository.save(Mockito.any(Project.class))).thenReturn(ProjectMapper.mapToEntity(projectDto));

        ProjectDto savedProject = projectService.updateProjectById(id,projectDto);

        assertThat(savedProject).isNotNull();
        assertThat(savedProject.getDescription()).isNotEqualTo("Description");
    }

    @Test
    public void ProjectService_DeleteById_ReturnProjectDto () {
        UUID id = new UUID(10,10);
        Project project = Project.builder().id(id).title("Project").description("Description").build();

        when(projectRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.ofNullable(project));

        assertAll(() -> projectService.deleteProjectById(id));
    }
}
