package com.example.vibeapp.repository;

import com.example.vibeapp.domain.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> store = new ArrayList<>();

    public PostRepository() {
        // Init with 10 mock data
        for (int i = 1; i <= 10; i++) {
            store.add(new Post(
                    (long) i,
                    "게시글 제목 " + i,
                    "게시글 내용입니다. " + i,
                    LocalDateTime.now().minusDays(10 - i),
                    i * 10));
        }
    }

    public List<Post> findAll() {
        return new ArrayList<>(store);
    }
}
