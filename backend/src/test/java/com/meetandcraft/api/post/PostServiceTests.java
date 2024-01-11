package com.meetandcraft.api.post;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.project.ProjectDto;
import com.meetandcraft.api.project.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {
    @Mock
    private PostRepository postRepository;
    @Mock
    private ProjectService projectService;

    @InjectMocks
    private PostServiceImpl postService;

    private Project project;
    private Post post;
    private ProjectDto projectDto;
    private PostDto postDto;

    @BeforeEach
    public void init () {
        this.project = Project.builder().title("Project").description("Description").build();
        this.projectDto = ProjectDto.builder().title("Project").description("Description").build();
        this.post = Post.builder().title("Post").content("Content").build();
        this.postDto = PostDto.builder().title("Post").content("Content").build();
    }

    @Test
    public void PostService_CreatePost_ReturnPostDto (){
        when(projectService.getProjectById(project.getId())).thenReturn(projectDto);
        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        PostDto savedPost = postService.createPost(project.getId(),postDto);
        assertThat(savedPost).isNotNull();
    }

    @Test
    public void PostService_GetAllPostsFromProject_ReturnListPostDto () {
        UUID id = new UUID(10,10);
        when(postRepository.findByProjectId(Mockito.any(UUID.class))).thenReturn(Arrays.asList(post));

        List<PostDto> posts = postService.getAllPostFromProject(id);

        assertThat(posts).isNotNull();
    }

    @Test
    public void PostService_GetPostsFromProjectById_ReturnListPostDto () {
        UUID projectId = new UUID(10,10);
        UUID postId = new UUID(10,10);

        when(postRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.ofNullable(post));
        when(projectService.projectExist(Mockito.any(UUID.class))).thenReturn(true);

        PostDto post = postService.getPostById(projectId,postId);

        assertThat(post).isNotNull();
    }

    @Test
    public void PostService_UpdatePostById_ReturnPostDto () {
        UUID projectId = new UUID(10,10);
        UUID postId = new UUID(10,10);

        when(postRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.ofNullable(post));
        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
        when(projectService.projectExist(Mockito.any(UUID.class))).thenReturn(true);

        PostDto post = postService.updatePostById(projectId,postId,postDto);

        assertThat(post).isNotNull();
    }

    @Test
    public void PostService_DeletePostById_PostDeleted () {
        UUID projectId = new UUID(10,10);
        UUID postId = new UUID(10,10);

        when(projectService.projectExist(Mockito.any(UUID.class))).thenReturn(true);

        assertAll(() -> postService.deletePostById(projectId,postId));
    }
}
