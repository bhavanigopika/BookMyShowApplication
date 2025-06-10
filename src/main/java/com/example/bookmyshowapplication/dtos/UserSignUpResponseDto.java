package com.example.bookmyshowapplication.dtos;

import com.example.bookmyshowapplication.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Response back to the user or client
//In response, we are not send the password but User object have userName, email, password
public class UserSignUpResponseDto {
    private String username;
    private String email;
    private ResponseStatus responseStatus;
}
