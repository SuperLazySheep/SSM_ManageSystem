package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Roles;

import java.util.List;

public interface IRoleService {

    public List<Roles> findAll() throws Exception;

    public void save(Roles roles) throws  Exception;

    public Roles findById(String roleId) throws Exception;

    public List<Permission> findOtherPermission(String roleId) throws Exception;

    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

    public Roles findByIdAll(String roleId) throws Exception;
}
