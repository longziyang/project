package com.project.web;

import com.project.entity.Stock;
import com.project.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock/{id}")
    public Stock queryOrderById(@PathVariable Long id) {

    	Stock stock = new Stock();
    	stock.setId(id);
        return stock;
        //return stockService.queryById(id);
    }

}
