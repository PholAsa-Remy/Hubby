package com.meetandcraft.api.post;

import com.meetandcraft.api.project.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void PostRepository_SaveAll_ReturnSavecPost () {
        Post post = Post.builder().title("Post").content("Content").build();

        Post savedPost = postRepository.save(post);

        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getId()).isNotNull();
    }

    @Test
    public void PostRepository_FindById_ReturnPost () {
        Post post = Post.builder().title("Post").content("Content").build();
        Post savedPost = postRepository.save(post);

        Post foundPost = postRepository.findById(post.getId()).get();

        assertThat(foundPost).isNotNull();
        assertThat(foundPost.getTitle()).isEqualTo("Post");
    }

    @Test
    public void PostRepository_FindAll_ReturnMultiplePost () {
        Post post1 = Post.builder().build();
        Post post2 = Post.builder().build();

        postRepository.save(post1);
        postRepository.save(post2);

        List<Post> foundAll = postRepository.findAll();
        assertThat(foundAll.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    public void PostRepository_UpdateReview_ReturnReviewWithModifiedTitle () {
        Post oldPost = Post.builder().title("Old Title").build();
        postRepository.save(oldPost);

        Post savePost = postRepository.findById(oldPost.getId()).get();
        savePost.setTitle("New Title");
        Post updatedPost = postRepository.save(savePost);

        assertThat(updatedPost.getId()).isEqualTo(savePost.getId());
        assertThat(updatedPost.getTitle()).isNotEqualTo("Old Title");
    }

    @Test
    public void PostRepository_Delete_PostIsDeleted () {
        Post post = Post.builder().title("Post").build();

        Post savePost = postRepository.save(post);
        postRepository.delete(savePost);
        Boolean isFound = postRepository.findById(savePost.getId()).isPresent();

        assertThat(isFound).isFalse();
    }
}
