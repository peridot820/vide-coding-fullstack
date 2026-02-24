package com.example.vibeapp.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record PostUpdateDto(
        @NotBlank(message = "제목은 필수입니다.") @Size(max = 100, message = "제목은 최대 100자까지 가능합니다.") String title,
        String content,
        String tags,

        // fields for re-rendering the edit form on validation error
        Long no,
        Integer views,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    // Custom constructor for Spring binding from form (title, content, tags)
    public PostUpdateDto(String title, String content, String tags) {
        this(title, content, tags, null, null, null, null);
    }

    public void updateEntity(Post post) {
        post.setTitle(this.title);
        post.setContent(this.content);
    }
}
