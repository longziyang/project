package com.project.service;

import com.project.mapper.StockMapper;
import com.project.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockMapper stockMapper;

    public Stock queryById(Long id) {

//        // 模拟接口运行时抛出代码异常
//        if (id.equals(1L)) {
//            throw new RuntimeException("111");
//        }
        return stockMapper.selectByPrimaryKey(id);
    }
}
