package com.example.demo.endpoint;

import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Bcrypt;
import com.example.user.GetAllUsersRequest;
import com.example.user.GetAllUsersResponse;
import com.example.user.LoginRequest;
import com.example.user.LoginResponse;
// import com.example.demo.service.UserService;
import com.example.user.RegisterUserRequest;
import com.example.user.RegisterUserResponse;
import com.example.user.UpdateUserRequest;
import com.example.user.UpdateUserResponse;
import com.example.user.User;
import com.example.user.UserType;
import com.example.user.models.Patient;
import com.example.user.models.Pharmacists;
import com.example.user.models.Physician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE = "http://example.com/user";
    private static final Logger logger = LoggerFactory.getLogger(UserEndpoint.class);

    private UserRepository repository;
    @Autowired
    UserEndpoint(UserRepository repo){
        this.repository=repo;
    }
    @PayloadRoot(localPart = "registerUserRequest", namespace = NAMESPACE)
    @ResponsePayload
    public RegisterUserResponse register(@RequestPayload RegisterUserRequest request) {
        RegisterUserResponse response = new RegisterUserResponse();
        try {
        if(repository.findByEmail(request.getEmail())!=null){
            throw new Exception("Email already in use");
        }
        UserType role = request.getType();
        User user=null;
        if(role.equals(UserType.PATIENT)){
            user = new Patient();
        }
        if(role.equals(UserType.PHYSICIAN)){
            user = new Physician();
        }
        
        if(role.equals(UserType.PHARMACIST)){
            user = new Pharmacists();
        }
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        repository.save(user);
        response.setMessage("User created sucess fully");
        response.setUser(user);
        
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }



    // #####################333




    @PayloadRoot(localPart = "getAllUsersRequest", namespace = NAMESPACE)
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        GetAllUsersResponse response = new GetAllUsersResponse();
        response.getUser().addAll(repository.findAll());
        return response;
        
    }


    @PayloadRoot(localPart = "loginRequest", namespace = NAMESPACE)
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            User user = repository.findByEmail(request.getEmail());
            if(user==null){
                throw new Exception("Oops Incorect email or password");
            }
            if(!Bcrypt.checkPassword(request.getPassword(), user.getPassword())){
                throw new Exception("Oops Incorect email or password");
            }
            response.setMessage("Logedin successfuly");
            response.setEmail(user.getEmail());
            response.setName(user.getName());
            response.setToken("blablabla");
            
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
        
    }


    @PayloadRoot(localPart = "updateUserRequest", namespace = NAMESPACE)
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        try {
            User user = repository.findById(request.getId());
            if(user==null){
                throw new Exception("User not found in our system");
            }
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            repository.save(user);
            response.setMessage("Updated!");
            
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
        
    }

}