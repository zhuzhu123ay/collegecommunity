package com.yzdl.collegecommunity.service;

import java.util.List;

import com.yzdl.collegecommunity.bean.User;


/**
 * 用户相关接口
 * */
public interface IUserService extends IBaseService<User> {
	void register(User user) throws Exception; 
	User login(String username,String password) throws Exception;
	List<User> findAllByOrder() throws Exception;
}
