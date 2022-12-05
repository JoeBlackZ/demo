package com.example.demo.service.impl;

import com.example.demo.model.SysUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zhangqi
 */
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
}
