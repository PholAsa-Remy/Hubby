package com.meetandcraft.api.post;

import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.project.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PostService {
    List<PostDto> getAllPostFromProject (ProjectDto projectDto);
    PostDto createPost(ProjectDto projectDto, PostDto postDto);
    PostDto getPostById (ProjectDto projectDto, UUID postId);
    PostDto updatePostById (UUID postId, PostDto postDto);
    void deletePostById(UUID postId);
}
