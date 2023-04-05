package com.springboot.blog.DTO;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDTO
{
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "It should have atleast 2 characters ")
    private String title;
    @Size(min =10)
    private String description;
    private String content;
    private Set<CommentDTO> comments;

}
