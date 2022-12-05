package com.example.demo.service.impl;

import com.example.demo.model.SysUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<SysUser>> users() {
        List<SysUser> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
