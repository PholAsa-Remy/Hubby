package com.meetandcraft.api.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.project.ProjectController;
import com.meetandcraft.api.project.ProjectDto;
import com.meetandcraft.api.project.ProjectService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PostControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;

    private Project project;
    private Post post;
    private ProjectDto projectDto;
    private PostDto postDto;

    @BeforeEach
    public void init () {
        this.project = Project.builder().id(new UUID(10,10)).title("Project").description("Description").build();
        this.projectDto = ProjectDto.builder().id(new UUID(10,10)).title("Project").description("Description").build();
        this.post = Post.builder().id(new UUID(10,10)).title("Post").content("Content").build();
        this.postDto = PostDto.builder().id(new UUID(10,10)).title("Post").content("Content").build();
    }

    @Test
    public void PostController_GetAllPostsFromProject_ReturnPostDto () throws Exception {
        UUID id = new UUID(10, 10);
        when(postService.getAllPostFromProject(Mockito.any(ProjectDto.class))).thenReturn(Arrays.asList(postDto));
        when(projectService.getProjectById(Mockito.any(UUID.class))).thenReturn(projectDto);

        ResultActions response = mockMvc.perform(get("/api/project/" + id + "/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(Arrays.asList(postDto).size())));
    }

    @Test
    public void PostController_UpdatePost_ReturnPostDto () throws Exception {
        UUID projectId = new UUID(10,10);
        UUID postId = new UUID(10,10);
        when(postService.updatePostById(Mockito.any(UUID.class),Mockito.any(PostDto.class))).thenReturn(postDto);
        when(projectService.getProjectById(Mockito.any(UUID.class))).thenReturn(projectDto);

        ResultActions response = mockMvc.perform(put("/api/project/" + projectId + "/post/" + postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(postDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", CoreMatchers.is(postDto.getContent())));
    }

    @Test
    public void PostController_CreatePostForProject_ReturnPostDtoCreated () throws Exception {
        UUID projectId = new UUID(10,10);
        when(postService.createPost(Mockito.any(ProjectDto.class),Mockito.any(PostDto.class))).thenReturn(postDto);
        when(projectService.getProjectById(Mockito.any(UUID.class))).thenReturn(projectDto);

        ResultActions response = mockMvc.perform(post("/api/project/" + projectId + "/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(postDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", CoreMatchers.is(postDto.getContent())));
    }

    @Test
    public void PostController_GetPostFromProject_ReturnPostDto () throws Exception {
        UUID projectId = new UUID(10, 10);
        UUID postId = new UUID(10, 10);
        when(postService.getPostById(Mockito.any(ProjectDto.class),Mockito.any(UUID.class))).thenReturn(postDto);
        when(projectService.getProjectById(Mockito.any(UUID.class))).thenReturn(projectDto);

        ResultActions response = mockMvc.perform(get("/api/project/" + projectId + "/post/" + postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(postDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", CoreMatchers.is(postDto.getContent())));
    }

    @Test
    public void PostController_DeletePostFromProject_ReturnOk () throws Exception {
        UUID postId = new UUID(10, 10);
        when(projectService.getProjectById(Mockito.any(UUID.class))).thenReturn(projectDto);
        doNothing().when(postService).deletePostById(postId);

        ResultActions response = mockMvc.perform(delete("/api/project/" + projectDto.getId() + "/post/" + postId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
