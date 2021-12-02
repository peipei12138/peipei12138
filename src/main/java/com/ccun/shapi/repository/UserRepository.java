package com.ccun.shapi.repository;

import com.ccun.shapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByToken(String token);

    User findUserById(Integer id);
}
