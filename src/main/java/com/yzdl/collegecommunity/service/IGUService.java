package com.yzdl.collegecommunity.service;

import java.util.List;

import com.yzdl.collegecommunity.bean.GU;


public interface IGUService extends IBaseService<GU>{
	List<GU> findByName(String username) throws Exception;
	List<GU> findByNameAndId(String username,Long id) throws Exception;
}
