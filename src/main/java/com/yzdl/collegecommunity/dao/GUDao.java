package com.yzdl.collegecommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yzdl.collegecommunity.bean.GU;

@Repository
public class GUDao extends BaseDao<GU>{
	public List<GU> findByName(String  username){
		String hql="from GU where collect_goods_user.username=?";
		List<GU> collectList=findByHQL(hql, username);
		return  collectList;
	}
	public List<GU>  findByNameAndId(String username,Long id){
		String hql="from GU where collect_goods_user.username=? and collect_goods.id=?";
		List<GU> cancelList=findByHQL(hql,username,id);
		return cancelList;
	}
}
