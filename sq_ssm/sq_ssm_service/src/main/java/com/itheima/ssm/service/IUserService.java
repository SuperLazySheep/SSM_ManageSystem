package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Roles;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    public List<UserInfo> findAll() throws Exception;

    public void save(UserInfo userInfo) throws Exception;

    public UserInfo findById(String userId) throws Exception;

    public List<Roles> findOtherRole(String userId) throws Exception;

    public void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
