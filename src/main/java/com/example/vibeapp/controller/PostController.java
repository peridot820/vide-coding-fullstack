package com.example.vibeapp.controller;

import com.example.vibeapp.domain.Post;
import com.example.vibeapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(Model model) {
        model.addAttribute("posts", postService.findPosts());
        return "posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable Long no, Model model) {
        Post post = postService.findPost(no);
        model.addAttribute("post", post);
        return "post_detail";
    }
}
