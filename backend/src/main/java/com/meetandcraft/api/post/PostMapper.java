package com.meetandcraft.api.post;

public class PostMapper {
    public static PostDto mapToDto (Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        return postDto;
    }

    public static Post mapToEntity (PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }
}
