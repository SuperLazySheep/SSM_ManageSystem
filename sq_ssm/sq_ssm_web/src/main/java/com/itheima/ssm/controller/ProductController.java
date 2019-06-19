package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *订单页面
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    //添加商品
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return  "redirect:findAll.do";
    }

    //查询全部信息
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findALL();
        mv.addObject("productList",products);
        mv.setViewName("product-list");
        return mv;
    }
}
