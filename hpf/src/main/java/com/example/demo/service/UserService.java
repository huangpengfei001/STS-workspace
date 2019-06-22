package com.example.demo.service;

import com.example.demo.common.PageInfo;
import com.example.demo.model.UserInfo;

public interface UserService {

	void addUser(UserInfo userInfo);

	UserInfo getUser(Integer id);

	void updateUser(UserInfo userInfo);

	PageInfo selectUserInfoListPage(UserInfo userInfo);

}
