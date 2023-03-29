package com.springboot.blog.Controller;

import com.springboot.blog.DTO.CommentDTO;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController
{
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable long postId, @RequestBody CommentDTO commentDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.createComment(commentDTO,postId), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable Long postId)
    {
        return commentService.getCommentsByPostId(postId);
    }
}

