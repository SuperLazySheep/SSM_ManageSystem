package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    /**
     * 查询全部产品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findALL() throws Exception {
        return productDao.findAll();
    }

    /**
     * 添加产品
     * @param product
     * @throws Exception
     */
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
