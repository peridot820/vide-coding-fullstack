package com.example.vibeapp.post;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(Long no) {
        return postRepository.findById(no);
    }

    public void save(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }

    public void updatePost(Long no, Post updateParam) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setTitle(updateParam.getTitle());
            post.setContent(updateParam.getContent());
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteById(no);
    }

    public List<Post> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findAll(offset, size);
    }

    public int getTotalPages(int size) {
        int totalCount = postRepository.count();
        return (int) Math.ceil((double) totalCount / size);
    }
}
