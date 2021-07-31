package com.shdh.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shdh.vo.ResponseOrder;

// 유레카에 등록된 서비스 네
@FeignClient(name="ORDER-SERVICE")
public interface OrderServiceClient {

	@GetMapping("/order-service/{userId}/orders")
	List<ResponseOrder> getOrders(@PathVariable String userId);
}
