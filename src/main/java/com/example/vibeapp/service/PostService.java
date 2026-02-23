package com.example.vibeapp.service;

import com.example.vibeapp.domain.Post;
import com.example.vibeapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Post findPost(Long no) {
        return postRepository.findByNo(no);
    }

    public void addPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }

    public void updatePost(Long no, Post updateParam) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setTitle(updateParam.getTitle());
            post.setContent(updateParam.getContent());
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }
}
