package com.yzdl.collegecommunity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzdl.collegecommunity.bean.GU;
import com.yzdl.collegecommunity.dao.BaseDao;
import com.yzdl.collegecommunity.dao.GUDao;
import com.yzdl.collegecommunity.service.IGUService;

@Service
public class GUServiceImpl extends BaseServiceImpl<GU> implements IGUService{
	@Autowired
	private GUDao guDao;
	
	@Override
	public BaseDao<GU> getDao() {
		return guDao;
	}

}
