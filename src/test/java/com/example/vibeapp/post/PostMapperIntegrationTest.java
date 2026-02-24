package com.example.vibeapp.post;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("classpath:schema.sql")
class PostMapperIntegrationTest {

    @Autowired
    private PostMapper postMapper;

    @Test
    void testInsertAndFindAll() {
        // Given
        Post post = new Post();
        post.setTitle("Test Title");
        post.setContent("Test Content");

        // When
        postMapper.insert(post);
        assertThat(post.getNo()).isNotNull();
        List<Post> posts = postMapper.findAll();

        // Then
        assertThat(posts).isNotEmpty();
        assertThat(posts.get(0).getTitle()).isEqualTo("Test Title");
        assertThat(posts.get(0).getContent()).isEqualTo("Test Content");
    }

    @Test
    void testFindById() {
        // Given
        Post post = new Post();
        post.setTitle("Another Post");
        post.setContent("Content");
        postMapper.insert(post);
        Long no = post.getNo();

        // When
        Post found = postMapper.findById(no);

        // Then
        assertThat(found).isNotNull();
        assertThat(found.getNo()).isEqualTo(no);
        assertThat(found.getTitle()).isEqualTo("Another Post");
    }
}
