package com.example.bookmyshowapplication;

import com.example.bookmyshowapplication.controllers.UserController;
import com.example.bookmyshowapplication.dtos.ResponseStatus;
import com.example.bookmyshowapplication.dtos.UserSignUpRequestDto;
import com.example.bookmyshowapplication.dtos.UserSignUpResponseDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {
    private UserController userController;
    public BookMyShowApplication(UserController userController) {
        this.userController = userController;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserSignUpRequestDto userSignUpRequestDto = new UserSignUpRequestDto();
        userSignUpRequestDto.setUsername("Krish");
        userSignUpRequestDto.setEmail("krish343@gmail.com");
        userSignUpRequestDto.setPassword("krishi%24521");
        UserSignUpResponseDto userSignUpResponseDto = userController.signUp(userSignUpRequestDto);
        if(userSignUpResponseDto.getResponseStatus() == ResponseStatus.SUCCESS){
            //complete the code
        }else{
            //throw an exception
        }
    }
}
