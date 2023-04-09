package com.springboot.blog.Service.implementation;

import com.springboot.blog.DTO.LoginDTO;
import com.springboot.blog.DTO.SignupDTO;
import com.springboot.blog.Entity.Role;
import com.springboot.blog.Entity.User;
import com.springboot.blog.Exception.BlogAPIException;
import com.springboot.blog.Repository.RoleRepository;
import com.springboot.blog.Repository.UserRepository;
import com.springboot.blog.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Logged-in";
    }

    @Override
    public String signUp(SignupDTO signupDTO) throws BlogAPIException {
        if (userRepository.existsByUserName(signupDTO.getUserName())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username already exists");

        }
        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        User user = new User();
        user.setUserName(signupDTO.getUserName());
        user.setEmail(signupDTO.getEmail());
        user.setName(signupDTO.getName());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "Sign-up successful";

    }
}
