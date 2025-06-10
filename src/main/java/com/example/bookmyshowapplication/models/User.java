package com.example.bookmyshowapplication.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//user is the reserved keyword in mysql and table not created in the name of user
//so, change the name of user into users...now table users is created
@Entity(name = "users")
public class User extends BaseModel{
    private String username;
    private String email;
    private String password;
}
