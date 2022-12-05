package com.example.demo.controller;

import com.example.demo.model.SysUser;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangqi
 */
@RestController
@RequestMapping("/")
public class SysUserController {

    @Resource
    private UserService userService;

    @PutMapping("user")
    public ResponseEntity<Integer> saveSysUser(SysUser sysUser) {
        return this.userService.saveUser(sysUser);
    }

    @GetMapping("user")
    public ResponseEntity<List<SysUser>> sysUser(SysUser sysUser) {
        return this.userService.users(sysUser);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<SysUser> sysUserById(@PathVariable Integer id) {
        return this.userService.user(id);
    }

}
