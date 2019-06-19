package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderService {

    public List<Orders> findAll(int page, int size) throws Exception;

    public Orders findById(String orderId) throws Exception;
}
