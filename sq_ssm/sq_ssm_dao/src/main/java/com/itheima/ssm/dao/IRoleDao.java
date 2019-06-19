package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Roles;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId = #{userId})")
    public List<Roles> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role where id in(select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id" ,javaType = java.util.List.class ,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Roles> findRoleById(String userId) throws Exception;

    @Select("select * from role")
    public List<Roles> findAll() throws Exception;

    @Insert("insert into role (roleName,roleDesc) values (#{roleName} , #{roleDesc})")
    public void save(Roles roles) throws Exception;

    @Select("select * from role where id = #{roleId}")
    public Roles findRole2ById(String roleId) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findOtherPermission(String roleId) throws Exception;

    @Insert("insert into role_permission(permissionId,roleId) values (#{permissionId}, #{roleId})")
    public void addPermissionToRole(@Param("roleId") String roleId , @Param("permissionId") String permissionId) throws Exception;

    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true ,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id" ,javaType = java.util.List.class , many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId")),
            @Result(property = "userInfo",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IUserDao.findUserByRoleId"))
    })
    public Roles findByIdAll(String roleId) throws Exception;
}
