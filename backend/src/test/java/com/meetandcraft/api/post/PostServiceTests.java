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

    @InjectMocks
    private PostServiceImpl postService;

    private Project project;
    private Post post;
    private ProjectDto projectDto;
    private PostDto postDto;

    @BeforeEach
    public void init () {
        this.project = Project.builder().title("Project").description("Description").build();
        this.projectDto = ProjectDto.builder().id(new UUID(10,10)).title("Project").description("Description").build();
        this.post = Post.builder().title("Post").content("Content").build();
        this.postDto = PostDto.builder().title("Post").content("Content").build();
    }

    @Test
    public void PostService_CreatePost_ReturnPostDto (){
        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        PostDto savedPost = postService.createPost(projectDto,postDto);
        assertThat(savedPost).isNotNull();
    }

    @Test
    public void PostService_GetAllPostsFromProject_ReturnListPostDto () {
        when(postRepository.findByProjectId(Mockito.any(UUID.class))).thenReturn(Arrays.asList(post));

        List<PostDto> posts = postService.getAllPostFromProject(projectDto);

        assertThat(posts).isNotNull();
    }

    @Test
    public void PostService_GetPostsFromProjectById_ReturnListPostDto () {
        UUID postId = new UUID(10,10);

        when(postRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.ofNullable(post));
        PostDto post = postService.getPostById(projectDto,postId);

        assertThat(post).isNotNull();
    }

    @Test
    public void PostService_UpdatePostById_ReturnPostDto () {
        UUID postId = new UUID(10,10);

        when(postRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.ofNullable(post));
        when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

        PostDto post = postService.updatePostById(postId,postDto);

        assertThat(post).isNotNull();
    }

    @Test
    public void PostService_DeletePostById_PostDeleted () {
        UUID postId = new UUID(10,10);
        assertAll(() -> postService.deletePostById(postId));
    }
}
