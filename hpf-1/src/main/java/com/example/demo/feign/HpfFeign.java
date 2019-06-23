package com.example.demo.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client1")
public interface HpfFeign {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	Map<String, Object> test(@RequestParam(value = "userName") String name);

}
