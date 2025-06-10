package com.example.bookmyshowapplication.services;

import com.example.bookmyshowapplication.exceptions.UserAlreadyExistException;
import com.example.bookmyshowapplication.models.User;
import com.example.bookmyshowapplication.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    //send everything as parameter here since it is service
    public User signUp(String username, String email, String password) throws UserAlreadyExistException {
        //check if user already exist, so check via user repository
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User savedUser = null;
        //if this user exists, we don't need to signup
        if(optionalUser.isPresent()){
            //User with the give email is already present
            //Ask them to try to login or ask them to change the password
            System.out.println("User with the give email is already present. So check login or login with different email or change the password. Then, try again");
            throw new UserAlreadyExistException("User already exist");

        }else {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            savedUser = userRepository.save(user);
        }
        return savedUser;
    }
}
