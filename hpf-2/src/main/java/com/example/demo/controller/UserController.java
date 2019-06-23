package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.TimeCost;
import com.example.demo.common.PageInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import com.example.demo.util.RabbitSendUtils;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RabbitSendUtils rabbitSendUtils;

	@RequestMapping("test")
	public Map<String, Object> test(String userName) {
		Map<String, Object> resultMap = new HashMap<String, Object>(1);
		resultMap.put("userName1222", userName);
//		resultMap.put("userName1223", redisUtil.get("hpf"));
		rabbitSendUtils.send(resultMap);
		return resultMap;
	}

	@RequestMapping("getUserInfoList")
	@TimeCost
	public Map<String, Object> getUserInfoList(@RequestBody UserInfo userInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		PageInfo pageInfo = userService.selectUserInfoListPage(userInfo);
		resultMap.put("pageInfo", pageInfo);
		resultMap.put("code", "00");
		return resultMap;
	}

	@RequestMapping("addUser")
	public Map<String, Object> addUser(@RequestBody UserInfo userInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		userService.addUser(userInfo);
		resultMap.put("code", "00");
		return resultMap;
	}

	@RequestMapping("getUser")
	public Map<String, Object> getUser(Integer id) {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		UserInfo userInfo = userService.getUser(id);
		resultMap.put("userInfo", userInfo);
		resultMap.put("code", "00");
		return resultMap;
	}

	@RequestMapping("updateUser")
	public Map<String, Object> updateUser(@RequestBody UserInfo userInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		userService.updateUser(userInfo);
		resultMap.put("code", "00");
		return resultMap;
	}
}
