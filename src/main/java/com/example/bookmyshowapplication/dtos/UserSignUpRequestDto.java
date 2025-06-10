package com.example.bookmyshowapplication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestDto {
    private String username;
    private String email;
    private String password;

}
