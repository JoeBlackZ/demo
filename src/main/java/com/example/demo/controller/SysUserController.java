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
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private UserService userService;

    @PutMapping()
    public ResponseEntity<Integer> saveSysUser(SysUser sysUser) {
        return this.userService.saveUser(sysUser);
    }

    @GetMapping()
    public ResponseEntity<List<SysUser>> sysUser(SysUser sysUser) {
        return this.userService.users(sysUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<SysUser> sysUserById(@PathVariable Integer id) {
        return this.userService.user(id);
    }

    @GetMapping("msg/{id}")
    public void msgSend(@PathVariable Integer id) {
        this.userService.sendUserMsg(id);
    }

}
