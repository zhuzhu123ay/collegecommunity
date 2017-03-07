package com.yzdl.collegecommunity.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzdl.collegecommunity.bean.TU;
import com.yzdl.collegecommunity.dao.BaseDao;
import com.yzdl.collegecommunity.dao.TUDao;
import com.yzdl.collegecommunity.service.ITUService;

@Service
public class TUServiceImpl extends BaseServiceImpl<TU> implements ITUService{

	@Autowired
	private TUDao tuDao;
	@Override
	public BaseDao<TU> getDao() {
		return tuDao;
	}
	@Override
	public List<TU> findByName(String username) throws Exception {
		List<TU> collectList=tuDao.findByName(username);
		if(collectList!=null){
			return collectList;
		}else{
			throw new Exception("您还没有收藏任务~");
		}
		
	}
	@Override
	public 	List<TU> findByNameAndId(String username, Long id) throws Exception {
		List<TU> cancelList=tuDao.findByNameAndId(username, id);
		return cancelList;
	}
}
