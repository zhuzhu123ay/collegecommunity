package com.yzdl.collegecommunity.service;


import java.util.List;

import com.yzdl.collegecommunity.bean.TU;


public interface ITUService extends IBaseService<TU>{
	List<TU> findByName(String username) throws Exception;
	List<TU> findByNameAndId(String username,Long id) throws Exception;
}
