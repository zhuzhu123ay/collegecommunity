package com.yzdl.collegecommunity.service;

import java.util.List;

import com.yzdl.collegecommunity.bean.Task;
import com.yzdl.collegecommunity.common.util.PageBean;


/**
 * 任务相关接口
 * */
public interface ITaskService extends IBaseService<Task>{
	List<Task> findByPublishName(String username) throws Exception;
	List<Task> findByAcceptName(String username) throws Exception;
	public PageBean queryForPage(int pageSize, int currentPage);
}
