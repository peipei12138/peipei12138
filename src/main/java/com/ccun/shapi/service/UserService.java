package com.ccun.shapi.service;

import com.ccun.shapi.entity.User;

public interface UserService {

    /**
     * 根据token查找用户
     * @param token: token
     * @return user
     */
    User selectUserByToken(String token);

    /**
     * 根据id查找用户
     * @param id: id
     * @return user
     */
    User selectUserById(Integer id);
}
