package com.springboot.blog.Service;

import com.springboot.blog.DTO.CommentDTO;
import com.springboot.blog.Exception.ResourceNotFoundException;

import java.util.List;

public interface CommentService
{
    CommentDTO createComment(CommentDTO commentDTO, long id) throws ResourceNotFoundException;
    List<CommentDTO> getCommentsByPostId( long postId);
}
