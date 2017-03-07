package com.yzdl.collegecommunity.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.yzdl.collegecommunity.bean.Task;


/**
 * 任务的Dao类
 * 实现任务信息的增删改查
 * */
@Repository
public class TaskDao extends BaseDao<Task>{
	public List<Task> findByPublishName(String  username){
		String hql="from Task where publish_user.username=?";
		List<Task> publishList=findByHQL(hql, username);
		return  publishList;
		
	}
	
	public List<Task> findByAcceptName(String  username){
		String hql="from Task where accept_user.username=?";
		List<Task> acceptList=findByHQL(hql, username);
		return acceptList;
		
	}

	
}
