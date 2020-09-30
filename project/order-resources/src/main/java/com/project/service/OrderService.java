package com.project.service;

import com.project.mapper.OrderMapper;
import com.project.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public Order queryById(Long id) {

//        // 模拟接口运行时抛出代码异常
//        if (id.equals(1L)) {
//            throw new RuntimeException("111");
//        }
        return orderMapper.selectByPrimaryKey(id);
    }
}
