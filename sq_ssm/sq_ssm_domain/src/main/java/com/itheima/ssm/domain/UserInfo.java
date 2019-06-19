package com.itheima.ssm.domain;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {
    private String id;
    private String email;
    private String username;
    private String password;
    private String phoneNum; // 电话
    private Integer status; // 状态 0 未开启 ，1 开启
    private String statusStr;
    private List<Roles> roles; // 对应的角色

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
       // 状态 0 未开启 ，1 开启
        if(status != null){
            if(status == 0)
                statusStr = "未开启";
            if(status == 1)
                statusStr = "开启";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
