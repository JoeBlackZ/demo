package com.example.demo.service.impl;

import com.example.demo.model.SysUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<SysUser>> users(SysUser sysUser) {
        Example<SysUser> userExample = Example.of(sysUser);
        List<SysUser> users = userRepository.findAll(userExample);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<SysUser> user(Integer id) {
        Optional<SysUser> optionalSysUser = this.userRepository.findById(id);
        return optionalSysUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
