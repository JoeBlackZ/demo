package com.example.demo.service;

import com.example.demo.model.SysUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author zhangqi
 */
public interface UserService {

    /**
     * Add a user to the
     * @param user user to add
     * @return user id
     */
    ResponseEntity<Integer> saveUser(SysUser user);

    /**
     * Delete a user from the
     * @param id id
     * @return true if successful, false otherwise
     */
    ResponseEntity<Boolean> removeUser(Integer id);

    /**
     * Get the user associated the user params
     * @param user the user params
     * @return the user list
     */
    ResponseEntity<List<SysUser>> users(SysUser user);

    /**
     * Get the user associated the user params
     * @param id the user id
     * @return user
     */
    ResponseEntity<SysUser> user(Integer id);

}
