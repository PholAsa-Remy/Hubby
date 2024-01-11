package com.meetandcraft.api.project;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProjectRepositoryTests {
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void ProjectRepository_SaveAll_ReturnSavedProject () {
        Project project = Project.builder().title("Project").description("Description").build();

        Project savedProject = projectRepository.save(project);

        assertThat(savedProject).isNotNull();
        assertThat(savedProject.getId()).isNotNull();
    }

    @Test
    public void ProjectRepository_FindAll_ReturnAtLeastOneProject () {
        Project project1 = Project.builder().title("Project 1").build();
        Project project2 = Project.builder().title("Project 2").build();
        projectRepository.save(project1);
        projectRepository.save(project2);

        List<Project> projects = projectRepository.findAll();

        assertThat(projects.size()).isGreaterThan(0);
    }

    @Test
    public void ProjectRepository_FindById_ReturnProject (){
        Project project = Project.builder().title("Project").build();
        Project saveProject = projectRepository.save(project);

        Project foundProject = projectRepository.findById(saveProject.getId()).get();

        assertThat(foundProject).isNotNull();
    }

    @Test
    public void ProjectRepository_Delete_ProjectIsDeleted () {
        Project project = Project.builder().title("Project").build();

        Project saveProject = projectRepository.save(project);
        projectRepository.delete(saveProject);
        Boolean isFound = projectRepository.findById(saveProject.getId()).isPresent();

        assertThat(isFound).isFalse();
    }

    @Test
    public void ProjectRepository_Update_TitleChanged () {
        Project project = Project.builder().title("Project").build();
        Project saveProject = projectRepository.save(project);

        Project projectSave = projectRepository.findById(saveProject.getId()).get();
        projectSave.setTitle("New Project");
        projectRepository.save(projectSave);

        Project projectUpdate = projectRepository.findById(saveProject.getId()).get();
        assertThat(projectUpdate).isNotEqualTo("Project");
    }

}
