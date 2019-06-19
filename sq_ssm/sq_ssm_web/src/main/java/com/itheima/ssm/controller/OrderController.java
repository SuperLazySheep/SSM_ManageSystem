package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *订单详情页面
 */

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

   /* //未分页查询
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = orderService.findAll();
        mv.addObject("ordersList",orders);
        mv.setViewName("orders-list");
        return mv;
    }*/

   //根据id详情查询
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id", required = true) String orderId) throws Exception {
        Orders orders = orderService.findById(orderId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }


    //分页查询
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page" ,required = true,defaultValue = "1" ) Integer page,@RequestParam(name="size",required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = orderService.findAll(page ,size);
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

}
