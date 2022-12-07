package com.example.demo.repository;

import com.example.demo.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author zhangqi
 */
public interface UserRepository extends JpaRepository<SysUser, Integer> {

    /**
     * Find sysUser by name
     * 
     * @param sysUserName sysUserName
     * @return sysUser
     */
    @Query(value = "SELECT * FROM sys_user WHERE name =:sysUserName", nativeQuery = true)
    SysUser findByName(String sysUserName);
    
}