package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<User>> users();

}
