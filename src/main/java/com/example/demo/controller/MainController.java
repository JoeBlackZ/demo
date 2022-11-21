package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/")
@RestController
public class MainController {

    @Resource
    private UserService userService;

    @GetMapping("test")
    public ResponseEntity<List<User>> main() {
        return this.userService.users();
    }

}
