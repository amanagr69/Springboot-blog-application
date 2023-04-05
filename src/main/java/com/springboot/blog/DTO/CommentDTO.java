package com.springboot.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO
{
    private long id;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty
    @Email(message = "Message should contain @")
    private String email;
    @Size(max = 500)
    private String body;

/*
    difference between @NotNull and @NotEmpty is that a list can be not but empty ex: " "
    @Size(max = 100 , min = 23 message= "About Me must be between 10 and 200 characters")
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
 */
}
