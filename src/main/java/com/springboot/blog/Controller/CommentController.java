package com.springboot.blog.Controller;

import com.springboot.blog.DTO.CommentDTO;
import com.springboot.blog.Exception.BlogAPIException;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController
{
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable long postId,@Valid @RequestBody CommentDTO commentDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.createComment(commentDTO,postId), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable Long postId)
    {
        return commentService.getCommentsByPostId(postId);
    }
    @PutMapping("/posts/{postsId}/comments/{id}")
        public ResponseEntity<CommentDTO>updatedComment(@PathVariable Long postId,@PathVariable Long commentId,@Valid @RequestBody CommentDTO commentDTO) throws BlogAPIException, ResourceNotFoundException {
       CommentDTO updatedComment= commentService.updateComment(postId,commentId,commentDTO);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comment/{commentId}")
    public void deleteComment(@PathVariable long commentId,@PathVariable long postId) throws BlogAPIException, ResourceNotFoundException {
        commentService.deletedComment(postId,commentId);
    }
}

