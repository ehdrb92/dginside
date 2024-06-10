package com.donggyu.dginside.comment;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;

    public Comment createComment(CommentCreateDTO commentCreateDTO) {
        Comment newComment = new Comment();

        newComment.setContents(commentCreateDTO.getContents());

        return commentRepository.save(newComment);
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment Not Found"));
    }

    public Iterable<Comment> getComments() {
        return commentRepository.findAll();
    }

    public boolean updateComment(Long id, CommentUpdateDTO commentUpdateDTO) {
        commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment Not Found"));
        int updateCount = commentRepository.updateCommentById(id, commentUpdateDTO.getContents());
        return updateCount == 1;
    }

    public void deleteComment(Long id) {
        commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment Not Found"));
        commentRepository.deleteById(id);
    }
}
