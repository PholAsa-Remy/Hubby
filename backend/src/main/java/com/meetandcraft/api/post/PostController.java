package com.meetandcraft.api.post;

import com.meetandcraft.api.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/project/{projectId}/post")
    public ResponseEntity<List<PostDto>> getAllPostFromProject (
            @PathVariable(value = "projectId")UUID projectId
    ){
        return new ResponseEntity<>(postService.getAllPostFromProject(projectId), HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}/post/{postId}")
    public ResponseEntity<PostDto> getPostById (
            @PathVariable(value = "projectId") UUID projectId,
            @PathVariable(value = "postId") UUID postId
    ){
        return new ResponseEntity<>(postService.getPostById(projectId,postId),HttpStatus.OK);
    }

    @PostMapping("/project/{projectId}/post")
    public ResponseEntity<PostDto> createPost (
            @PathVariable(value = "projectId") UUID projectId,
            @RequestBody PostDto postDto
    ){
        return new ResponseEntity<>(postService.createPost(projectId,postDto),HttpStatus.CREATED);
    }

    @PutMapping("/project/{projectId}/post/{postId}")
    public ResponseEntity<PostDto> updatePost (
            @PathVariable(value = "projectId") UUID projectId,
            @PathVariable(value = "postId") UUID postId,
            @RequestBody PostDto postDto
    ){
        return new ResponseEntity<>(postService.updatePostById(projectId,postId,postDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/project/{projectId}/post/{postId}")
    public ResponseEntity<String> deletePost (
            @PathVariable(value = "projectId") UUID projectId,
            @PathVariable(value = "postId") UUID postId
    ){
        postService.deletePostById(projectId,postId);
        return new ResponseEntity<>("The post have been deleted successfully", HttpStatus.OK);
    }
}
