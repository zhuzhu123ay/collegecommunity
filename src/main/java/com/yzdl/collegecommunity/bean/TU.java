package com.yzdl.collegecommunity.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_task_user")
public class TU implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Task collect_task;
	private User collect_task_user;
	private Date collectDate;
	public TU() {
		
	}
	public TU(Long id, Task collect_task, User collect_task_user, Date collectDate) {
		super();
		this.id = id;
		this.collect_task = collect_task;
		this.collect_task_user = collect_task_user;
		this.collectDate = collectDate;
	}
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "t_id")
	public Task getCollect_task() {
		return collect_task;
	}
	public void setCollect_task(Task collect_task) {
		this.collect_task = collect_task;
	}
	public Date getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "t_u_id")
	public User getCollect_task_user() {
		return collect_task_user;
	}
	
	public void setCollect_task_user(User collect_task_user) {
		this.collect_task_user = collect_task_user;
	}
}

