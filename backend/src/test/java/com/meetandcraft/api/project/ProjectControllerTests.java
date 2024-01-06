package com.meetandcraft.api.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetandcraft.api.post.Post;
import com.meetandcraft.api.post.PostDto;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProjectControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;
    private ProjectDto projectDto;

    @BeforeEach
    public void init () {
        this.projectDto = ProjectDto.builder().title("Project").description("Description").build();
    }

    @Test
    public void ProjectController_CreateProject_ReturnCreated () throws Exception {
        when (projectService.createProject(Mockito.any(ProjectDto.class))).thenReturn(projectDto);

        ResultActions response = mockMvc.perform(post("/api/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(projectDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(projectDto.getDescription())));
    }

    @Test
    public void ProjectController_GetAllProject_ReturnResponseDto () throws Exception {
        ProjectResponse responseDto = ProjectResponse.builder().pageSize(10).pageNo(1).last(true).content(Arrays.asList(projectDto)).build();
        when (projectService.getAllProject(1,10)).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/api/project")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo","1")
                .param("pageSize","10"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(responseDto.getContent().size())));
    }

    @Test
    public void ProjectController_GetProject_ReturnProjectDto () throws Exception {
        UUID id = new UUID(10,10);
        when(projectService.getProjectById(Mockito.any(UUID.class))).thenReturn(projectDto);

        ResultActions response = mockMvc.perform(get("/api/project/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(projectDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(projectDto.getDescription())));
    }

    @Test
    public void ProjectController_UpdateProject_ReturnProjectDto () throws Exception {
        UUID id = new UUID(10,10);
        when(projectService.updateProjectById(Mockito.any(UUID.class),Mockito.any(ProjectDto.class))).thenReturn(projectDto);

        ResultActions response = mockMvc.perform(put("/api/project/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(projectDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(projectDto.getDescription())));
    }

    @Test
    public void ProjectController_DeleteProject_ReturnString () throws Exception {
        UUID id = new UUID(10,10);
        doNothing().when(projectService).deleteProjectById(Mockito.any(UUID.class));

        ResultActions response = mockMvc.perform(delete("/api/project/" + id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
