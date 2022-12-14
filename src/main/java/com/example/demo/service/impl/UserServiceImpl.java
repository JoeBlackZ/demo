package com.example.demo.service.impl;

import com.example.demo.model.SysUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangqi
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Integer> saveUser(SysUser user) {
        SysUser saveSysUser = this.userRepository.save(user);
        return ResponseEntity.ok(saveSysUser.getId());
    }

    @Override
    public ResponseEntity<Boolean> removeUser(Integer id) {
        this.userRepository.deleteById(id);
        Optional<SysUser> sysUserOptional = this.userRepository.findById(id);
        if (sysUserOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE);
        } else {
            return ResponseEntity.ok(Boolean.TRUE);
        }
    }

    @Override
    public ResponseEntity<List<SysUser>> users(SysUser user) {
        Example<SysUser> userExample = Example.of(user);
        List<SysUser> users = userRepository.findAll(userExample);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<SysUser> user(Integer id) {
        Optional<SysUser> optionalSysUser = this.userRepository.findById(id);
        return optionalSysUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Async
    @Override
    public void sendUserMsg(Integer id) {
        Optional<SysUser> optionalSysUser = this.userRepository.findById(id);
        if (optionalSysUser.isEmpty()) {
            log.error("SysUser doesn't exist.");
            return;
        }
        SysUser sysUser = optionalSysUser.get();
        String phone = sysUser.getPhone();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Send message to {} successfully", phone);
    }
}
