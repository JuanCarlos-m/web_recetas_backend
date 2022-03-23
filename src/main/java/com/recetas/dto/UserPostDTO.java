package com.recetas.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPostDTO {

    private String username;
    private String password;
    private List<String> roles;

}
