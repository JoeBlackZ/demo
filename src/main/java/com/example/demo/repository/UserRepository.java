package com.example.demo.repository;

import com.example.demo.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhangqi
 */
public interface UserRepository extends JpaRepository<SysUser, Integer> {

}