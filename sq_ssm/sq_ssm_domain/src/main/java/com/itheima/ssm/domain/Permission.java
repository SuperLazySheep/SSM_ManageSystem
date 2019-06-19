package com.itheima.ssm.domain;

import java.io.Serializable;
import java.util.List;

public class Permission implements Serializable {
    private String id;
    private String permissionName; //权限名
    private String url; // 资源路径
    private List<Roles> roles; // 对应的角色

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
