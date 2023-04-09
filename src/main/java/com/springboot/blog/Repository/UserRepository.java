package com.springboot.blog.Repository;

import com.springboot.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User>findByEmail(String email);
    Optional<User>findByUserNameOrEmail(String username,String email);
    Optional<User>findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
}
