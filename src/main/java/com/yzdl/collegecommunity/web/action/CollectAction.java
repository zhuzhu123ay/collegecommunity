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
import com.yzdl.collegecommunity.bean.TU;
import com.yzdl.collegecommunity.bean.Task;
import com.yzdl.collegecommunity.bean.User;
import com.yzdl.collegecommunity.service.ITUService;
import com.yzdl.collegecommunity.service.ITaskService;
@ParentPackage("csc-package")
@InterceptorRef(value="cscAuthStack")
public class CollectAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<TU> collectList;
	private String msg;
	private Long id;
	@Autowired
	private ITUService tuService;
	@Autowired
	private ITaskService taskService;
	
	
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

}
