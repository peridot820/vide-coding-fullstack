package com.example.vibeapp.post;

import java.time.LocalDateTime;

public record PostResponseDTO(
        Long no,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer views
) {
    public static PostResponseDTO from(Post post) {
        if (post == null) return null;
        return new PostResponseDTO(
                post.getNo(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getViews()
        );
    }
}
