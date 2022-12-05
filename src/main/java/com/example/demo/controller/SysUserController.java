package com.example.demo.controller;

import com.example.demo.model.SysUser;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class SysUserController {

    @Resource
    private UserService userService;

    @GetMapping("user")
    public ResponseEntity<List<SysUser>> sysUser(SysUser sysUser) {
        return this.userService.users(sysUser);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<SysUser> sysUserById(@PathVariable Integer id) {
        return this.userService.user(id);
    }

}
