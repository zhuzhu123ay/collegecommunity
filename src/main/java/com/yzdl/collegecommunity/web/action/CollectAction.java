package com.yzdl.collegecommunity.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yzdl.collegecommunity.bean.GU;
import com.yzdl.collegecommunity.bean.Goods;
import com.yzdl.collegecommunity.bean.TU;
import com.yzdl.collegecommunity.bean.Task;
import com.yzdl.collegecommunity.bean.User;
import com.yzdl.collegecommunity.service.IGUService;
import com.yzdl.collegecommunity.service.IGoodsService;
import com.yzdl.collegecommunity.service.ITUService;
import com.yzdl.collegecommunity.service.ITaskService;
@ParentPackage("csc-package")
@InterceptorRef(value="cscAuthStack")
public class CollectAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<TU> collectList;
	private List<GU>  collectGoodsList;
	private String msg;
	private Long id;
	@Autowired
	private ITUService tuService;
	@Autowired
	private IGUService guService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IGoodsService goodsService;
	
	
	/**
	 * 跳转到我的收藏
	 * http://localhost:8888/cms/toCollect.action
	 * */
	@Action(value="toCollect",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/collect.jsp")})
	public String toCollect(){
		return SUCCESS;
	}
	

	/**
	 * 跳转到我收藏的任务
	 * http://localhost:8888/cms/toCollectTask.action
	 * */
	@Action(value="toCollectTask",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/collect_task_list.jsp")})
	public String toCollectTask(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User collectUser=(User) session.getAttribute("user");
		try {
			collectList=tuService.findByName(collectUser.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
		}
		return SUCCESS;
	}
	/**
	 * 跳转到我收藏的商品
	 * http://localhost:8888/cms/toCollectGoods.action
	 * */
	@Action(value="toCollectGoods",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/collect_goods_list.jsp")})
	public String toCollectGoods(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User collectUser=(User) session.getAttribute("user");
		try {
			collectGoodsList=guService.findByName(collectUser.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
		}
		return SUCCESS;
	}   

	/**
	 * 收藏任务
	 * http://localhost:8888/cms/collectTask.action
	 * 一个人只能对同一个任务进行收藏
	 * */
	@Action(value="collectTask")
	public void collectTask(){
		TU tu=new TU();
		HttpSession session=ServletActionContext.getRequest().getSession();
		User collect_user=(User) session.getAttribute("user");
		tu.setCollect_task_user(collect_user);
		Task collect_task=taskService.findById(id);
		collect_task.setCollectTimes(collect_task.getCollectTimes()+1);
		taskService.update(collect_task);
		Task new_collect_task=taskService.findById(id);
		tu.setCollect_task(new_collect_task);
		tu.setCollectDate(new Date());
		tuService.save(tu);
	}
	
	/**
	 * 收藏商品
	 * http://localhost:8888/cms/collectGoods.action
	 * 一个人对同一个商品只能收藏一次
	 * */
	@Action(value="collectGoods")
	public void collectGoods(){
		GU gu=new GU(); 
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		User collect_user=(User) session.getAttribute("user");
		gu.setCollect_goods_user(collect_user);
		Goods collect_goods=goodsService.findById(id);
		collect_goods.setCollectTimes(collect_goods.getCollectTimes()+1);
		goodsService.update(collect_goods);
		Goods new_collect_goods=goodsService.findById(id);
		gu.setCollect_goods(new_collect_goods);
		gu.setCollectDate(new Date());
		guService.save(gu);
	}
	
	/**
	 * 取消收藏任务
	 * http://localhost:8888/cms/cancelCollect.action
	 * */
	@Action(value="cancelCollect")
	public void cancelCollect(){
		Task collect_task=taskService.findById(id);
		collect_task.setCollectTimes(collect_task.getCollectTimes()-1);
		taskService.update(collect_task);
		HttpSession session=ServletActionContext.getRequest().getSession();
		User collect_user=(User) session.getAttribute("user");
		try {
			List<TU> cancelList=tuService.findByNameAndId(collect_user.getUsername(), id);
			tuService.deleteObject(cancelList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取消收藏商品
	 * http://localhost:8888/cms/cancelCollectGoods.action
	 * */
	@Action(value="cancelCollectGoods")
	public void cancelCollectGoods(){
		Goods collect_goods=goodsService.findById(id);
		collect_goods.setCollectTimes(collect_goods.getCollectTimes()-1);
		goodsService.update(collect_goods);
		HttpSession session=ServletActionContext.getRequest().getSession();
		User collect_user=(User) session.getAttribute("user");
		try {
			List<GU> cancelGoodsList=guService.findByNameAndId(collect_user.getUsername(), id);
			guService.deleteObject(cancelGoodsList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<TU> getCollectList() {
		return collectList;
	}

	public void setCollectList(List<TU> collectList) {
		this.collectList = collectList;
	}


	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public List<GU> getCollectGoodsList() {
		return collectGoodsList;
	}


	public void setCollectGoodsList(List<GU> collectGoodsList) {
		this.collectGoodsList = collectGoodsList;
	}

}
