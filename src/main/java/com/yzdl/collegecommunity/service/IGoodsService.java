package com.yzdl.collegecommunity.service;

import java.util.List;

import com.yzdl.collegecommunity.bean.Goods;

import com.yzdl.collegecommunity.common.util.PageBean;

public interface IGoodsService extends IBaseService<Goods>{
	List<Goods>  findByBuyName(String username) throws Exception;
	List<Goods> findBySellName(String username) throws Exception;
	public PageBean queryForPage(int pageSize, int currentPage);
}
