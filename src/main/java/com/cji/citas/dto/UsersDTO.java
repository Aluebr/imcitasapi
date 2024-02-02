package com.cji.citas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsersDTO {

    private Long id;

    private String name;


    private String email;

}
