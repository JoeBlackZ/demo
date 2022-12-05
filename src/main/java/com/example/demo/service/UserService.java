package com.example.demo.service;

import com.example.demo.model.SysUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<Integer> saveUser(SysUser user);

    ResponseEntity<Boolean> removeUser(Integer id);

    ResponseEntity<List<SysUser>> users(SysUser user);

    ResponseEntity<SysUser> user(Integer id);

}
