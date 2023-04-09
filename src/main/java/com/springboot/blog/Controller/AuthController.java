package com.springboot.blog.Controller;

import com.springboot.blog.DTO.LoginDTO;
import com.springboot.blog.DTO.SignupDTO;
import com.springboot.blog.Exception.BlogAPIException;
import com.springboot.blog.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    @Autowired
    private AuthService authService;
    @PostMapping("login")
    public ResponseEntity<String>login(@RequestBody LoginDTO loginDTO)
    {
        String response=authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = {"/signup","/register"})
     public ResponseEntity<String>signup(@RequestBody SignupDTO signupDTO) throws BlogAPIException {
        String response=authService.signUp(signupDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
