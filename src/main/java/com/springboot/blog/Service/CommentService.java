package com.springboot.blog.Service;

import com.springboot.blog.DTO.CommentDTO;
import com.springboot.blog.Exception.BlogAPIException;
import com.springboot.blog.Exception.ResourceNotFoundException;

import java.util.List;

public interface CommentService
{
    CommentDTO createComment(CommentDTO commentDTO, long id) throws ResourceNotFoundException;
    List<CommentDTO> getCommentsByPostId( long postId);
    CommentDTO updateComment(Long postId,Long commentId,CommentDTO commentRequest) throws ResourceNotFoundException, BlogAPIException;
    void deletedComment(long postId,long commentId) throws ResourceNotFoundException, BlogAPIException;
}
