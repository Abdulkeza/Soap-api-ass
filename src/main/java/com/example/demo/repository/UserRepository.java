package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.User;
// import java.util.Optional;
import java.util.List;




public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User findById(long id);
}
