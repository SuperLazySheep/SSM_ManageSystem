package com.itheima.ssm;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 用户密码加密工具类
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String getPasswordEncoder(String password){
        return bCryptPasswordEncoder.encode(password);
    }



    //测试
    public static void main(String[] args) {
        String password = "admin";
        System.out.println(getPasswordEncoder(password));
    }
}
