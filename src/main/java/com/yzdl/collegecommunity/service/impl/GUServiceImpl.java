package com.yzdl.collegecommunity.service.impl;

import java.util.List;

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

	@Override
	public List<GU> findByName(String username) throws Exception {
		List<GU> collectList=guDao.findByName(username);
		if(collectList!=null)
		{
			return collectList;
		}else
		{
			throw new Exception("您还没有收藏商品~");
		}
		
	}

	@Override
	public List<GU> findByNameAndId(String username, Long id) throws Exception {
		List<GU> cancelList=guDao.findByNameAndId(username, id);
		
		return cancelList;
	}

}
