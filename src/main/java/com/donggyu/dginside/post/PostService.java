package com.donggyu.dginside.post;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;

    public Post createPost(PostCreateDTO postCreateDTO) {
        Post newPost = new Post();

        newPost.setTitle(postCreateDTO.getTitle());
        newPost.setCategory(postCreateDTO.getCategory());
        newPost.setContents(postCreateDTO.getContents());

        return postRepository.save(newPost);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post Not Found"));
    }

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    public boolean updatePost(Long id, PostUpdateDTO postUpdateDTO) {
        postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post Not Found"));
        int updateCount = postRepository.updatePostById(id, postUpdateDTO.getTitle(), postUpdateDTO.getCategory(), postUpdateDTO.getContents(), postUpdateDTO.getAttachedFile());
        return updateCount == 1;
    }

    public void deletePost(Long id) {
        postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post Not Found"));
        postRepository.deleteById(id);
    }


}
