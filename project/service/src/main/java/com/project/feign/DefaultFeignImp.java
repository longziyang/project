package com.project.feign;

import org.springframework.stereotype.Component;

@Component
public class DefaultFeignImp implements DefaultFeign {
    @Override
    public String queryOrderInfo(Long id) {

        return "网络错误";
    }

	@Override
	public String queryStockInfo(Long id) {
		// TODO Auto-generated method stub
		return "服务已断开";
	}
}
