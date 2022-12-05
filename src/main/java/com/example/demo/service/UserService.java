package com.example.demo.service;

import com.example.demo.model.SysUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<SysUser>> users(SysUser sysUser);

    ResponseEntity<SysUser> user(Integer id);

}
