package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.PageInfo;
import com.example.demo.dao.UserInfoMapper;
import com.example.demo.interceptor.MyPageIntercept;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public void addUser(UserInfo userInfo) {
		userInfoMapper.insert(userInfo);
	}

	@Override
	public UserInfo getUser(Integer id) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
		return userInfo;
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		userInfoMapper.updateByPrimaryKey(userInfo);
	}

	@Override
	public PageInfo selectUserInfoListPage(UserInfo userInfo) {
		List<UserInfo> list = userInfoMapper.selectUserInfoListPage(userInfo);
		PageInfo pageInfo = MyPageIntercept.getPageLocal().get();
		pageInfo.setData(list);
		return pageInfo;
	}

}
