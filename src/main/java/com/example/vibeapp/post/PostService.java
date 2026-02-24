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

    public PostResponseDTO findById(Long no) {
        postRepository.incrementViews(no);
        Post post = postRepository.findById(no);
        return PostResponseDTO.from(post);
    }

    public void save(PostCreateDto dto) {
        Post post = dto.toEntity();
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }

    public void updatePost(Long no, PostUpdateDto dto) {
        Post post = postRepository.findById(no);
        if (post != null) {
            dto.updateEntity(post);
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteById(no);
    }

    public List<PostListDto> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findAllWithPaging(offset, size).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalCount = postRepository.count();
        return (int) Math.ceil((double) totalCount / size);
    }
}
