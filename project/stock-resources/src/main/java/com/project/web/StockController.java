package com.project.web;

import com.project.entity.Stock;
import com.project.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock/{id}")
    public Stock queryOrderById(@PathVariable Long id){

        return stockService.queryById(id);
    }
}
