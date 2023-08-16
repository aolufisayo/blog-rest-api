package com.phissy.blog.service.user;

import com.phissy.blog.dto.UserModel;
import com.phissy.blog.entity.User;

public interface UserService {
    User createUser(UserModel userModel);

    User readUser(String email);

    User updateUser(String email, UserModel userModel);

    void deleteUser(String email);
}
