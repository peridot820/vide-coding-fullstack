package com.example.vibeapp.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int size = 5;
        model.addAttribute("posts", postService.findAll(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postService.getTotalPages(size));
        return "post/posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable Long no, Model model) {
        PostResponseDTO post = postService.findById(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/new")
    public String showNewForm(Model model) {
        model.addAttribute("post", new PostCreateDto("", ""));
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String add(@Valid @ModelAttribute("post") PostCreateDto post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/post_new_form";
        }
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable Long no, Model model) {
        PostResponseDTO post = postService.findById(no);
        model.addAttribute("post", post);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String update(@PathVariable Long no, @Valid @ModelAttribute("post") PostUpdateDto post,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Re-populate metadata for the edit form by creating a new record instance
            PostResponseDTO originalPost = postService.findById(no);
            PostUpdateDto updatedPostWithMetadata = new PostUpdateDto(
                    post.title(),
                    post.content(),
                    originalPost.no(),
                    originalPost.views(),
                    originalPost.createdAt(),
                    originalPost.updatedAt());
            model.addAttribute("post", updatedPostWithMetadata);
            return "post/post_edit_form";
        }
        postService.updatePost(no, post);
        return "redirect:/posts/{no}";
    }

    @PostMapping("/posts/{no}/delete")
    public String delete(@PathVariable Long no) {
        postService.deletePost(no);
        return "redirect:/posts";
    }
}
