package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.User;
import com.example.demo.repository.UserRepository;
import com.example.user.RegisterUserRequest;
import com.example.user.RegisterUserResponse;


@Service
public class UserService {
    

   
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repo){
        this.userRepository=repo;
    }

    public RegisterUserResponse user (RegisterUserRequest request){
     
        RegisterUserResponse response = new RegisterUserResponse();
        // User user = new User();
        // user.setEmail(request.getEmail());
        // user.setName(request.getName());
        // user.setPassword(request.getPassword());
        // user.setType(request.getType());
        // User createdUser = userRepository.save(user);
        // response.(createdUser);
        return response;
    }


    public User getUser(User user){
        return user;
    }

    
}
