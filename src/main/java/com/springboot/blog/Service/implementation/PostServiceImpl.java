package com.springboot.blog.Service.implementation;

import com.springboot.blog.DTO.PostDTO;
import com.springboot.blog.DTO.PostResponse;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.Service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService
{
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO)
    {
        Post post=mapToEntity(postDTO);
        Post newPost=postRepository.save(post);
        return mapToDTO(newPost);
    }
    private Post mapToEntity(PostDTO postDTO)
    {
        Post post=new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        return post;
    }


    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir)

    {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable=PageRequest.of(pageNo,pageSize, sort);
        Page<Post>posts=postRepository.findAll(pageable);

        List<Post>listOfPosts=posts.getContent();
        List<PostDTO>content= listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalElements(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
          return postResponse;
    }

    @Override
    public PostDTO getPostById(@PathVariable Long id) throws ResourceNotFoundException {
        Post post=postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public void deletePost(long id) throws ResourceNotFoundException {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);
    }

    @Override
    public PostDTO updatePosts(PostDTO postDTO,long id) throws ResourceNotFoundException
    {
         Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
         post.setTitle(postDTO.getTitle());
         post.setContent(postDTO.getContent());
         post.setDescription(postDTO.getDescription());
         Post updatedPost=postRepository.save(post);
         return mapToDTO(updatedPost);
    }

    private PostDTO mapToDTO(Post post)
    {
        PostDTO postDTO=new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent());
        postDTO.setDescription(post.getDescription());
        postDTO.setTitle(post.getTitle());
        return postDTO;
    }
}
