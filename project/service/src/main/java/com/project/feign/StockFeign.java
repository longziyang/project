package com.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value="stock-resource",fallback=DefaultFeignImp.class)
public interface StockFeign {

	 @GetMapping("/stock/{id}")
	 public String queryStockInfo(@PathVariable Long id) ;

}
