package com.meetandcraft.api.post;

import com.meetandcraft.api.exceptions.PostNotFoundException;
import com.meetandcraft.api.exceptions.ProjectNotFoundException;
import com.meetandcraft.api.project.Project;
import com.meetandcraft.api.project.ProjectDto;
import com.meetandcraft.api.project.ProjectMapper;
import com.meetandcraft.api.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ProjectService projectService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ProjectService projectService) {
        this.postRepository = postRepository;
        this.projectService = projectService;
    }

    @Override
    public List<PostDto> getAllPostFromProject(UUID projectId) {
        List<Post> posts = postRepository.findByProjectId(projectId);
        return posts.stream().map(PostMapper::mapToDto).toList();
    }

    @Override
    public PostDto getPostById(UUID projectId, UUID postId) {
        if (!projectService.projectExist(projectId))
            throw new ProjectNotFoundException("The project doesn't exist");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("The post couldn't be found"));
        return PostMapper.mapToDto(post);
    }

    @Override
    public PostDto createPost(UUID projectId, PostDto postDto) {
        ProjectDto projectDto = projectService.getProjectById(projectId);
        Project project = ProjectMapper.mapToEntity(projectDto);

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setProject(project);

        postRepository.save(post);
        return PostMapper.mapToDto(post);
    }

    @Override
    public PostDto updatePostById(UUID projectId, UUID postId, PostDto postDto) {
        if (!projectService.projectExist(projectId))
            throw new ProjectNotFoundException("The project doesn't exist");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("The post couldn't be found"));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return PostMapper.mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(UUID projectId, UUID postId) {
        if (!projectService.projectExist(projectId))
            throw new ProjectNotFoundException("The project doesn't exist");

        postRepository.deleteById(postId);
    }
}
