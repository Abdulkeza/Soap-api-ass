package com.example.user.models;

import javax.persistence.Entity;

import com.example.demo.utils.Bcrypt;
import com.example.user.User;
import com.example.user.UserType;





@Entity
public class Physician extends User {


    public void setPassword(String password) throws Exception {
        if(password.length()!=6){
            throw new Exception("Password should be 6 characters");
        }
        this.password = Bcrypt.hashPassword(password);
    }
    
}
