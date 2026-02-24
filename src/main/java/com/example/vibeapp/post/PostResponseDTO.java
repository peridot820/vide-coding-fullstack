package com.example.vibeapp.post;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDTO(
        Long no,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer views,
        String tags) {
    public static PostResponseDTO from(Post post, List<PostTag> tags) {
        if (post == null)
            return null;
        String tagString = tags == null ? "" : String.join(", ", tags.stream().map(PostTag::getTagName).toList());
        return new PostResponseDTO(
                post.getNo(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getViews(),
                tagString);
    }
}
