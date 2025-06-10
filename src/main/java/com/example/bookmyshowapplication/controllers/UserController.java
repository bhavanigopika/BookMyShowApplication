package com.example.bookmyshowapplication.controllers;

import com.example.bookmyshowapplication.dtos.ResponseStatus;
import com.example.bookmyshowapplication.dtos.UserSignUpRequestDto;
import com.example.bookmyshowapplication.dtos.UserSignUpResponseDto;
import com.example.bookmyshowapplication.models.User;
import com.example.bookmyshowapplication.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    //user can be sign up or login
    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }
    //Generally in controllers we won't pass parameters, sending as in requestDto and response from responseDto
    //In services, we will pass parameters
    @PostMapping("/signup")
    public UserSignUpResponseDto signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
        try {

            User user = userService.signUp(userSignUpRequestDto.getUsername(), userSignUpRequestDto.getEmail(), userSignUpRequestDto.getPassword());

            userSignUpResponseDto.setUsername(user.getUsername());
            userSignUpResponseDto.setEmail(user.getEmail());
            userSignUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {

            userSignUpResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return userSignUpResponseDto;

    }

//
}
