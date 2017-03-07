package com.yzdl.collegecommunity.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

@Entity
@Table(name="tbl_task")
public class Task implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//编号
	private Date publisurDate;//发布时间
	private String description;//任务描述
	private Double pay;//酬谢金额
	private String state;//任务状态
	private User publish_user;//发布者
	private	User accept_user;//接受者
	private Integer collectTimes;//收藏次数
	
	private Set<TU> task_users=new HashSet<TU>();
	
	//构造函数
	public Task() {
		
	}

	public Task(Long id, Date publisurDate, String description, Double pay, String state, User publish_user,
			User accept_user, Integer collectTimes, Set<TU> task_users) {
		super();
		this.id = id;
		this.publisurDate = publisurDate;
		this.description = description;
		this.pay = pay;
		this.state = state;
		this.publish_user = publish_user;
		this.accept_user = accept_user;
		this.collectTimes = collectTimes;
		this.task_users = task_users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPublisurDate() {
		return publisurDate;
	}

	public void setPublisurDate(Date publisurDate) {
		this.publisurDate = publisurDate;
	}
	@Type(type="text") 
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="p_u_id")
	public User getPublish_user() {
		return publish_user;
	}

	public void setPublish_user(User publish_user) {
		this.publish_user = publish_user;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="a_u_id")
	public User getAccept_user() {
		return accept_user;
	}

	public void setAccept_user(User accept_user) {
		this.accept_user = accept_user;
	}
	@OneToMany(mappedBy="collect_task")
	@Cascade(value={CascadeType.ALL}) 
	public Set<TU> getTask_users() {
		return task_users;
	}

	public void setTask_users(Set<TU> task_users) {
		this.task_users = task_users;
	}

	public Integer getCollectTimes() {
		return collectTimes;
	}

	public void setCollectTimes(Integer collectTimes) {
		this.collectTimes = collectTimes;
	}

}
