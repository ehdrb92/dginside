package com.donggyu.dginside.post;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/post")
public class PostController {
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostCreateDTO postCreateDTO) {
        return new ResponseEntity<>(postService.createPost(postCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPost(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getPosts() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updatePost(@PathVariable(value = "id") Long id, @RequestBody PostUpdateDTO postUpdateDTO) {
        return new ResponseEntity<>(postService.updatePost(id, postUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
