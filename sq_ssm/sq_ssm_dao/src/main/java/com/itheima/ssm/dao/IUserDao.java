package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Roles;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    //用户登录查询
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true ,property = "id", column = "id"),
            @Result(property = "email" , column = "email"),
            @Result(property = "username" , column = "username"),
            @Result(property = "password" , column = "password"),
            @Result(property = "phoneNum" , column = "phoneNum"),
            @Result(property = "status" , column = "status"),
            @Result(property = "roles" , column = "id" ,javaType = java.util.List.class ,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    //用户查询
    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    //用户添加
    @Insert("insert into users (email,username,password,phoneNum,status) values(#{email}, #{username}, #{password}, #{phoneNum}, #{status})")
    public void save(UserInfo userInfo) throws Exception;

    //根据id详情查询
    @Select("select * from users where id = #{userId}")
    @Results({
            @Result(id = true ,property = "id", column = "id"),
            @Result(property = "email" , column = "email"),
            @Result(property = "username" , column = "username"),
            @Result(property = "password" , column = "password"),
            @Result(property = "phoneNum" , column = "phoneNum"),
            @Result(property = "status" , column = "status"),
            @Result(property = "roles" ,column = "id" ,javaType = java.util.List.class ,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleById"))
    })
    public UserInfo findById(String userId) throws Exception;

    //查询用户未拥有的权限
    @Select("select * from role where id not in ( select roleId from users_role where userId = #{userId})")
    public List<Roles> findOtherRole(String userId) throws Exception;

    @Insert("insert into users_role values (#{userId}, #{roleId})")
    public void addRoleToUser(@Param("userId") String userId , @Param("roleId") String roleId) throws Exception;

    @Select("select * from users where id in (select userId from users_role where roleId = #{roleId})")
    public List<UserInfo> findUserByRoleId(String roleId);

}
