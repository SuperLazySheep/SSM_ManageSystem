package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Roles;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户管理页面
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

   //查询用户
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfo = userService.findAll();
        mv.addObject("userList",userInfo);
        mv.setViewName("user-list");
        return mv;
    }

    //添加用户
    @RequestMapping("/save.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //用户详情(根据id查询)
    @RequestMapping("/findById.do")
    //@PreAuthorize("authentication.principal.username='admin'")
    public ModelAndView findById(@RequestParam(name = "id" , required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //根据id查询用户以及用户为拥有的权限
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id" , required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据id查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据id查询用户未拥有的权限
        List<Roles> roles = userService.findOtherRole(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }
    //用户添加权限
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId ,@RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId , roleIds);
        return "redirect:findAll.do";
    }
}
