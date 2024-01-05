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

    @PostMapping("/project/{projectId}/post")
    public ResponseEntity<PostDto> createPost (
            @PathVariable(value = "projectId") UUID projectId,
            @RequestBody PostDto postDto
    ){
        return new ResponseEntity<>(postService.createPost(projectId,postDto),HttpStatus.CREATED);
    }
}
