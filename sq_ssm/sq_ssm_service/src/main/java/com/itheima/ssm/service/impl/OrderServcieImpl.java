package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrderDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServcieImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    /**
     * 查询全部订单
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    /**
     * 订单详情查询
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public Orders findById(String orderId) throws Exception{
        return orderDao.findById(orderId);
    }
}
