package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.UserInfo;

//@Mapper
public interface UserInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);

	List<UserInfo> selectUserInfoListPage(UserInfo userInfo);

//	Map<String, BigDecimal> selectTest(Integer id);
}