package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Roles;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    //查询全部角色
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Roles> roles = roleService.findAll();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    //详情查询(根据id查询)
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
       Roles roles = roleService.findByIdAll(roleId);
        mv.addObject("role",roles);
        mv.setViewName("role-show");
        return mv;
    }


    //添加角色
    @RequestMapping("/save.do")
    public String save(Roles roles) throws Exception {
        roleService.save(roles);
        return "redirect:findAll.do";
    }

    //根据id查询角色以及给角色添加未拥有的权限
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据id查询角色
        Roles roles = roleService.findById(roleId);
        //查询未拥有的权限
        List<Permission> permissions = roleService.findOtherPermission(roleId);
        mv.addObject("role", roles);
        mv.addObject("permissionList", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //添加角色权限
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        System.out.println(roleId);
        System.out.println(Arrays.toString(permissionIds));
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
