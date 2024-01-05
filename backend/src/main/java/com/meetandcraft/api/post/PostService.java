package com.meetandcraft.api.post;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PostService {
    List<PostDto> getAllPostFromProject (UUID projectId);
    PostDto createPost(UUID projectId, PostDto postDto);
    PostDto getPostById (UUID projectId, UUID postId);
    PostDto updatePostById (UUID projectId, UUID postId, PostDto postDto);
    void deletePostById(UUID projectId, UUID postId);
}
