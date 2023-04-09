package com.springboot.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO
{
    private String name;
    private String userName;
    private String email;
    private String password;
}
