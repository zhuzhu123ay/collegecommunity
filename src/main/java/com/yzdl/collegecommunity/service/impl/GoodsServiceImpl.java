package com.yzdl.collegecommunity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzdl.collegecommunity.bean.Goods;
import com.yzdl.collegecommunity.dao.BaseDao;
import com.yzdl.collegecommunity.dao.GoodsDao;
import com.yzdl.collegecommunity.service.IGoodsService;

@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService{
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public BaseDao<Goods> getDao() {
		return goodsDao;
	}

}
