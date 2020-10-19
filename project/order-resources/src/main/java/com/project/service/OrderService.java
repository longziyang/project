package com.project.service;

import com.project.config.annotation.Transfer;
import com.project.mapper.OrderMapper;
import com.project.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transfer
    public Order queryById(Long id) {


        Order order = orderMapper.selectByPrimaryKey(id);
        order.getIntegral();

        return order;
    }
}
