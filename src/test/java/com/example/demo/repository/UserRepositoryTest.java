package com.example.demo.repository;

import com.example.demo.model.SysUser;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    void testList() {
        List<SysUser> users = this.userRepository.findAll();
        log.info(String.valueOf(users));
    }

    @Test
    void testFindByName() {
        SysUser sysUser = this.userRepository.findByName("admin");
        log.info(String.valueOf(sysUser));
    }

}