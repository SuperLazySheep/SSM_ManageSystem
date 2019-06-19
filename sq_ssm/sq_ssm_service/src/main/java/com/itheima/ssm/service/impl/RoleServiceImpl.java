package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Roles;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    /**
     * 查询全部角色
     * @return
     * @throws Exception
     */
    @Override
    public List<Roles> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 添加角色
     * @param roles
     * @throws Exception
     */
    @Override
    public void save(Roles roles) throws Exception {
        roleDao.save(roles);
    }

    /**
     * 根据id查询角色
     * @param roleId
     * @return
     */
    @Override
    public Roles findById(String roleId) throws Exception {
        return roleDao.findRole2ById(roleId);
    }

    /**
     * 查询未拥有的权限
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception{
        return roleDao.findOtherPermission(roleId);
    }

    /**
     * 角色添加权限
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    /**
     * 详情查询（根据id）
     * @param roleId
     * @return
     */
    @Override
    public Roles findByIdAll(String roleId) throws Exception {
        return roleDao.findByIdAll(roleId);
    }
}
