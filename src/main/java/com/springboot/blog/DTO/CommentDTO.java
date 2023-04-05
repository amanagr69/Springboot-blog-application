package com.springboot.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
