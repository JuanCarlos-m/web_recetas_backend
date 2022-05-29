package com.recetas.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserPostDTO {

    private String username;
    private String password;
    private String name;
    private String lastname;
    private Date fecha_nac;
    private List<String> roles;

}
