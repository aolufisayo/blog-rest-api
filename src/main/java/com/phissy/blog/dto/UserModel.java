package com.phissy.blog.dto;

import lombok.Data;

@Data
public class UserModel {
    private String name;

    private String email;

    private String password;

    private String role;
}
