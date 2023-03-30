package com.springboot.blog.Service.implementation;

import com.springboot.blog.DTO.CommentDTO;
import com.springboot.blog.Entity.Comment;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.Exception.BlogAPIException;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Repository.CommentRepository;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService
{
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository ,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        List<Comment> comments=commentRepository.findCommentByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, long postId) throws ResourceNotFoundException {
        Comment comment=mapToEntity(commentDTO);
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        comment.setPost(post);
        Comment comment1=commentRepository.save(comment);
        return mapToDTO(comment1);

    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentRequest) throws ResourceNotFoundException, BlogAPIException {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogAPIException(HttpStatus.BAD_GATEWAY,"Comment doesn't belong to the post");
        }
        comment.setBody(commentRequest.getBody());
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        Comment updatedComment=commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    private CommentDTO mapToDTO(Comment comment)
    {
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }
    private Comment mapToEntity(CommentDTO commentDTO)
    {
        Comment comment=new Comment();
        comment.setId(commentDTO.getId());
        comment.setBody(commentDTO.getBody());
        comment.setEmail(commentDTO.getEmail());
        comment.setName(commentDTO.getName());
        return comment;
    }
}
