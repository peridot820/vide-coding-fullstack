package com.example.vibeapp.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    public PostService(PostRepository postRepository, PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
    }

    public PostResponseDTO findById(Long no) {
        postRepository.incrementViews(no);
        Post post = postRepository.findById(no);
        List<PostTag> tags = postTagRepository.findByPostNo(no);
        return PostResponseDTO.from(post, tags);
    }

    @Transactional
    public void save(PostCreateDto dto) {
        Post post = dto.toEntity();
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);

        saveTags(post.getNo(), dto.tags());
    }

    @Transactional
    public void updatePost(Long no, PostUpdateDto dto) {
        Post post = postRepository.findById(no);
        if (post != null) {
            dto.updateEntity(post);
            post.setUpdatedAt(LocalDateTime.now());
            postRepository.update(post); // Wait, does update exist? Let me check.

            postTagRepository.deleteByPostNo(no);
            saveTags(no, dto.tags());
        }
    }

    private void saveTags(Long postNo, String tagsString) {
        if (tagsString != null && !tagsString.isBlank()) {
            List<String> tagNames = Arrays.stream(tagsString.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            for (String tagName : tagNames) {
                postTagRepository.save(new PostTag(null, postNo, tagName));
            }
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
