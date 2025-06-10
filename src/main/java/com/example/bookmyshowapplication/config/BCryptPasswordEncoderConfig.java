package com.example.bookmyshowapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class BCryptPasswordEncoderConfig {
    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoderMethod(){
        return new BCryptPasswordEncoder();
    }
}
