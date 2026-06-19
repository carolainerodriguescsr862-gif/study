package com.example.study.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message= "Name cannot be empty")
    private String login;
    @NotBlank(message= "Password cannot be empty")
    private String password;

}
