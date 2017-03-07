package com.yzdl.collegecommunity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzdl.collegecommunity.bean.Task;
import com.yzdl.collegecommunity.common.util.PageBean;
import com.yzdl.collegecommunity.dao.BaseDao;
import com.yzdl.collegecommunity.dao.TaskDao;
import com.yzdl.collegecommunity.service.ITaskService;

@Service
public class TaskServiceImpl extends BaseServiceImpl<Task> implements ITaskService{
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public BaseDao<Task> getDao() {
		return taskDao;
	}

	@Override
	public List<Task> findByPublishName(String username) throws Exception {
		List<Task> publishList=taskDao.findByPublishName(username);
		if(publishList!=null){
			return publishList;
		}else{
			throw new Exception("您还没有发布任务~");
		}
		
	}

	@Override
	public List<Task> findByAcceptName(String username) throws Exception {
		List<Task> acceptList=taskDao.findByAcceptName(username);
		if(acceptList!=null){
			return acceptList;
		}else{
			throw new Exception("您还没有接收任务~");
		}
	}

	/**   
	 * 分页查询     
	 * @param pageSize  每页显示多少记录   
	 * @param currentPage 当前页   
	 * @return 封装了分页信息的bean   
	 */    
	public PageBean queryForPage(int pageSize, int page) {
		String hql = "select count(*) from Task where accept_user=null";
		int count = taskDao.getCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
		int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentPage = PageBean.countCurrentPage(page);
		List<Task> list = taskDao.queryForPage("from Task where accept_user=null", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}


}
