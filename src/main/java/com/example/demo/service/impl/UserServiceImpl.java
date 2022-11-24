package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<List<User>> users() {
        List<User> users = List.of(new User(1, "Joe BlackZ"), new User(2, "John Vet"));
        return ResponseEntity.ok(users);
    }
}
