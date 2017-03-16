package com.yzdl.collegecommunity.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yzdl.collegecommunity.bean.Task;
import com.yzdl.collegecommunity.bean.User;
import com.yzdl.collegecommunity.common.util.PageBean;
import com.yzdl.collegecommunity.service.ITaskService;
import com.yzdl.collegecommunity.service.IUserService;

@ParentPackage("csc-package")
@InterceptorRef(value="cscAuthStack")
public class TaskAction extends ActionSupport implements ServletResponseAware{
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IUserService userService;
	
	private HttpServletResponse response;
	
	private List<Task> taskList;
	private String description;
	private Double pay;
	private Long id;
	private List<Task> publishList;
	private String msg;
	private List<Task> acceptList;
	
	private PageBean pageBean; //封装了分页信息和数据内容的pageBean    
	private int pageSize;
	/**
	 * 跳转到任务大厅
	 * http://localhost:8888/csc/toLogin.action
	 * */
	@Action(value="toTaskHall",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/task_hall.jsp")})
	public String toTaskHall(){
		return SUCCESS;
	}
	
	

	/**
	 * 跳转到任务列表
	 * http://localhost:8888/csc/toLogin.action
	 * */
	@Action(value="toTaskList",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/task_list.jsp")})
	public String toTaskList(){
		taskList=taskService.findAll();
		/*this.pageBean = taskService.queryForPage(pageSize, 1);//获取封装了分页信息和数据的pageBean 
		this.taskList = this.pageBean.getList(); //获取数据    */
		
		/*String str = JSON.toJSONString(taskList);
		
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return SUCCESS;
	}
	
	

	/**
	 * 跳转到我发布的
	 * http://localhost:8888/csc/toPublishByMe.action
	 * */
	@Action(value="toPublishByMe",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/publish_by_me.jsp")})
	public String toPublishByMe(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User publishUser=(User) session.getAttribute("user");
		try {
			publishList=taskService.findByPublishName(publishUser.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 跳转到我接收的
	 * http://localhost:8888/csc/toAcceptByMe.action
	 * */
	@Action(value="toAcceptByMe",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/accept_by_me.jsp")})
	public String toAcceptByMe(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User acceptUser=(User) session.getAttribute("user");
		try {
			acceptList=taskService.findByAcceptName(acceptUser.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
		}
		return SUCCESS;
	}
	
	/**
	 * 发布任务
	 * http://localhost:8888/csc/publishTask.action
	 * */
	@Action(value="publishTask")
	public void publishTask(){
		Task task=new Task();
		task.setPublisurDate(new Date());
		task.setDescription(description);
		task.setPay(pay);
		task.setState("未接收");
		HttpSession session=ServletActionContext.getRequest().getSession();
		User publish_user=(User) session.getAttribute("user");
		task.setPublish_user(publish_user);
		task.setCollectTimes(0);
		//限定发布次数
		publish_user.setPublishTimes(publish_user.getPublishTimes()+1);
		publish_user.setIntegrate(publish_user.getIntegrate()+10);
		if(publish_user.getPublishTimes()<=5){
			userService.update(publish_user);
			taskService.save(task);
		}
	}
	
	/**
	 * 接收任务
	 * http://localhost:8888/csc/acceptTask.action
	 * */
	@Action(value="acceptTask")
	public void acceptTask(){
		Task task=taskService.findById(id);
		HttpSession session=ServletActionContext.getRequest().getSession();
		User accept_user=(User) session.getAttribute("user");
		task.setAccept_user(accept_user);
		task.setState("进行时");
		//限定接收次数
		accept_user.setAcceptTimes(accept_user.getAcceptTimes()+1);
		accept_user.setIntegrate(accept_user.getIntegrate()+15);
		if(accept_user.getAcceptTimes()<=3){
			userService.update(accept_user);
			taskService.update(task);
		}
	}
	
	/**
	 * 完成任务
	 * http://localhost:8888/csc/finishTask.action
	 * */
	@Action(value="finishTask")
	public void finishTask(){
		Task task=taskService.findById(id);
		task.setState("已完成");
		User finish_user=task.getAccept_user();
		finish_user.setFinishTimes(finish_user.getFinishTimes()+1);
		finish_user.setIntegrate(finish_user.getIntegrate()+20);
		userService.update(finish_user);
		taskService.update(task);
	}
	
	/**
	 * 未完成任务
	 * http://localhost:8888/csc/finishTask.action
	 * */
	@Action(value="unfinishTask")
	public void funfinishTask(){
		Task task=taskService.findById(id);
		task.setState("未完成");
		User unfinish_user=task.getAccept_user();
		unfinish_user.setIntegrate(unfinish_user.getIntegrate()-15);
		userService.update(unfinish_user);
		taskService.update(task);
	}
	
	/**
	 * 取消任务
	 * http://localhost:8888/csc/cancelTask.action
	 * */
	@Action(value="cancelTask")
	public void cancelTask(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User cancel_user=(User) session.getAttribute("user");
		cancel_user.setPublishTimes(cancel_user.getPublishTimes()-1);
		cancel_user.setIntegrate(cancel_user.getIntegrate()-10);
		userService.update(cancel_user);
		taskService.delete(id);
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public List<Task> getPublishList() {
		return publishList;
	}

	public void setPublishList(List<Task> publishList) {
		this.publishList = publishList;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Task> getAcceptList() {
		return acceptList;
	}

	public void setAcceptList(List<Task> acceptList) {
		this.acceptList = acceptList;
	}



	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
