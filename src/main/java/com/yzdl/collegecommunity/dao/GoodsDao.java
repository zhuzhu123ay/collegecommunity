package com.yzdl.collegecommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yzdl.collegecommunity.bean.Goods;
import com.yzdl.collegecommunity.bean.Task;
/**
 * 二手市场的Dao类
 * @author Administrator
 *
 */
@Repository
public class GoodsDao extends BaseDao<Goods>{
   public List<Goods> findByBuyName(String username)
   {
	   String hql="from Goods where buy_user.username=?";
		List<Goods> buyList=findByHQL(hql, username);
		return  buyList;
   }
   public List<Goods> findBySellName(String username)
   {
	   String hql="from Goods where sell_user.username=?";
		List<Goods> sellList=findByHQL(hql, username);
		return  sellList;
   }
}
