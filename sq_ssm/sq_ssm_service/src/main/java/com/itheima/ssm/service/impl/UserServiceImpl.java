package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Roles;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * 用戶登陸判断，页面--->springSecurity--->service
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成userDetails(根据状态判断账号是否有效)
        User user = new User(userInfo.getUsername(),  userInfo.getPassword(),userInfo.getStatus() ==0?false:true ,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    //判断 "访问系统的人,必须有ROLE_USER的角色"
    public List<SimpleGrantedAuthority> getAuthority(List<Roles> roles){
       List<SimpleGrantedAuthority> authorities =  new ArrayList();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authorities;
    }

    /**
     * 用户查询
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    /**
     * 用户添加
     * @param userInfo
     * @throws Exception
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //用户密码加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * 根据id查询详情查询（多表查询）
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo findById(String userId) throws Exception {
        return userDao.findById(userId);
    }

    /**
     * 查询用户为拥有的权限
     * @param userId
     * @return
     */
    @Override
    public List<Roles> findOtherRole(String userId) throws Exception {
        return userDao.findOtherRole(userId);
    }

    /**
     * 给用户添加权限
     * @param userId
     * @param roleIds
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception{
       for(String roleId : roleIds){
           userDao.addRoleToUser(userId,roleId);
       }
    }


}
