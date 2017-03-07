package com.yzdl.collegecommunity.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.yzdl.collegecommunity.bean.TU;


@Repository
public class TUDao extends BaseDao<TU>{
	public List<TU> findByName(String  username){
		String hql="from TU where collect_task_user.username=?";
		List<TU> collectList=findByHQL(hql, username);
		return  collectList;
	}
	public List<TU>  findByNameAndId(String username,Long id){
		String hql="from TU where collect_task_user.username=? and collect_task.id=?";
		List<TU> cancelList=findByHQL(hql,username,id);
		return cancelList;
	}
}
