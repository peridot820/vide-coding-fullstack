package com.example.vibeapp.controller;

import com.example.vibeapp.domain.Post;
import com.example.vibeapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/posts/new")
    public String showNewForm() {
        return "post_new_form";
    }

    @PostMapping("/posts/add")
    public String add(Post post) {
        postService.addPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable Long no, Model model) {
        Post post = postService.findPost(no);
        model.addAttribute("post", post);
        return "post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String update(@PathVariable Long no, Post post) {
        postService.updatePost(no, post);
        return "redirect:/posts/{no}";
    }
}
