package com.meetandcraft.api.post;

import com.meetandcraft.api.project.ProjectDto;
import com.meetandcraft.api.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class PostController {
    private PostService postService;
    private ProjectService projectService;

    @Autowired
    public PostController(PostService postService, ProjectService projectService) {
        this.postService = postService;
        this.projectService = projectService;
    }

    @GetMapping("/project/{projectId}/post")
    public ResponseEntity<List<PostDto>> getAllPostFromProject (
            @PathVariable(value = "projectId")UUID projectId
    ){
        ProjectDto projectDto = projectService.getProjectById(projectId);
        return new ResponseEntity<>(postService.getAllPostFromProject(projectDto), HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}/post/{postId}")
    public ResponseEntity<PostDto> getPostById (
            @PathVariable(value = "projectId") UUID projectId,
            @PathVariable(value = "postId") UUID postId
    ){
        ProjectDto projectDto = projectService.getProjectById(projectId);
        return new ResponseEntity<>(postService.getPostById(projectDto,postId),HttpStatus.OK);
    }

    @PostMapping("/project/{projectId}/post")
    public ResponseEntity<PostDto> createPost (
            @PathVariable(value = "projectId") UUID projectId,
            @RequestBody PostDto postDto
    ){
        ProjectDto projectDto = projectService.getProjectById(projectId);
        return new ResponseEntity<>(postService.createPost(projectDto,postDto),HttpStatus.CREATED);
    }

    @PutMapping("/project/{projectId}/post/{postId}")
    public ResponseEntity<PostDto> updatePost (
            @PathVariable(value = "projectId") UUID projectId,
            @PathVariable(value = "postId") UUID postId,
            @RequestBody PostDto postDto
    ){
        ProjectDto projectDto = projectService.getProjectById(projectId);
        return new ResponseEntity<>(postService.updatePostById(postId,postDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/project/{projectId}/post/{postId}")
    public ResponseEntity<String> deletePost (
            @PathVariable(value = "projectId") UUID projectId,
            @PathVariable(value = "postId") UUID postId
    ){
        postService.deletePostById(postId);
        return new ResponseEntity<>("The post have been deleted successfully", HttpStatus.OK);
    }
}
