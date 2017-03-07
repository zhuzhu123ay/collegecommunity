package com.yzdl.collegecommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yzdl.collegecommunity.bean.User;


/**
 * 用户的Dao类
 * 进行用户信息的增删改查
 * */
@Repository
public class UserDao extends BaseDao<User> {

	public User findByUserName(String  username){
		String hql="from User where username=?";
		List<User> list=findByHQL(hql, username);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
		
	}
	public List<User> findAllByOrder(){
		String hql="from User order by integrate desc";
		List<User> rankList=findByHQL(hql);
		return rankList;
	}
}
