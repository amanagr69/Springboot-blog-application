package com.springboot.blog.Service;

import com.springboot.blog.DTO.PostDTO;
import com.springboot.blog.DTO.PostResponse;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.Exception.ResourceNotFoundException;

import java.util.List;

public interface PostService
{
    PostDTO createPost(PostDTO postDTO);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDTO getPostById(Long id) throws ResourceNotFoundException;
    PostDTO updatePosts(PostDTO postDTO,long id) throws ResourceNotFoundException;
    void deletePost(long id) throws ResourceNotFoundException;
}
