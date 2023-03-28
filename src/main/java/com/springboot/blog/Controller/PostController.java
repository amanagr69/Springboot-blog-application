package com.springboot.blog.Controller;

import com.springboot.blog.DTO.PostDTO;
import com.springboot.blog.DTO.PostResponse;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Service.PostService;
import com.springboot.blog.Utils.ApplicationConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PostController
{
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO)
    {
        return new ResponseEntity<>(postService.createPost(postDTO),HttpStatus.CREATED);
    }
    @GetMapping("/posts")
    public PostResponse getAllPosts(@RequestParam(value = "pageNo",defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo
            , @RequestParam(value = "pageSize",defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,@RequestParam(value = "sortBy",defaultValue = ApplicationConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                    @RequestParam (value = "sortDir",defaultValue =ApplicationConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir )
    {
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO>getById(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO>updatePost(@RequestBody PostDTO postDTO, @PathVariable long id) throws ResourceNotFoundException {
      PostDTO response=postService.updatePosts(postDTO,id);
      return new ResponseEntity<>(response,HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) throws ResourceNotFoundException {
       postService.deletePost(id);
       return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }

}
