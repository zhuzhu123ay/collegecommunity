package com.yzdl.collegecommunity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzdl.collegecommunity.bean.Goods;
import com.yzdl.collegecommunity.bean.Task;
import com.yzdl.collegecommunity.common.util.PageBean;
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

	@Override
	public List<Goods> findByBuyName(String username) throws Exception {
		List<Goods> buyList=goodsDao.findByBuyName(username);
		if(buyList!=null)
		{
			return buyList;
		}else
		{
			throw new Exception("您还没有购买商品~");
		}
		
	}

	@Override
	public List<Goods> findBySellName(String username) throws Exception {
		List<Goods> sellList=goodsDao.findBySellName(username);
		if(sellList!=null)
		{
			return sellList;
		}else
		{
			throw new Exception("您还没有发布商品~");
		}
		
	}
	/**   
	 * 分页查询     
	 * @param pageSize  每页显示多少记录   
	 * @param currentPage 当前页   
	 * @return 封装了分页信息的bean   
	 */    
	@Override
	public PageBean queryForPage(int pageSize, int page) {
		String hql = "select count(*) from Goods where buy_user=null";
		int count = goodsDao.getCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
		int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentPage = PageBean.countCurrentPage(page);
		List<Task> list = goodsDao.queryForPage("from Goods where buy_user=null", offset, length); // 该分页的记录
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
