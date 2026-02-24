package com.example.vibeapp.post;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> store = new ArrayList<>();
    private static long sequence = 0L;

    public PostRepository() {
        // Init with 15 mock data
        for (int i = 1; i <= 15; i++) {
            save(new Post(
                    null,
                    "게시글 제목 " + i,
                    "게시글 내용입니다. " + i,
                    LocalDateTime.now().minusDays(15 - i),
                    i * 10));
        }
    }

    public Post save(Post post) {
        post.setNo(++sequence);
        store.add(post);
        return post;
    }

    public List<Post> findAll() {
        return store.stream()
                .sorted((p1, p2) -> Long.compare(p2.getNo(), p1.getNo()))
                .toList();
    }

    public List<Post> findAll(int offset, int limit) {
        return store.stream()
                .sorted((p1, p2) -> Long.compare(p2.getNo(), p1.getNo()))
                .skip(offset)
                .limit(limit)
                .toList();
    }

    public int count() {
        return store.size();
    }

    public Post findById(Long no) {
        return store.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(Long no) {
        store.removeIf(post -> post.getNo().equals(no));
    }
}
