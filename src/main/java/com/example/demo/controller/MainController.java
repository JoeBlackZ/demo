package com.example.demo.controller;

import com.example.demo.model.SysUser;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController
public class MainController {

    @Resource
    private UserService userService;

    @GetMapping("user")
    public ResponseEntity<List<SysUser>> main() {
        return this.userService.users();
    }

}
