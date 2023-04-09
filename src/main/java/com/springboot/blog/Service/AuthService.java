package com.springboot.blog.Service;

import com.springboot.blog.DTO.LoginDTO;
import com.springboot.blog.DTO.SignupDTO;
import com.springboot.blog.Exception.BlogAPIException;

public interface AuthService
{
    String login(LoginDTO loginDTO);
    String signUp(SignupDTO signupDTO) throws BlogAPIException;
}
