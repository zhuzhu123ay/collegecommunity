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
@Table(name="tbl_goods_user")
public class GU implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Goods collect_goods;
	private User collect_goods_user;
	private Date collectDate;
	
	public GU() {
	}

	public GU(Long id, Goods collect_goods, User collect_goods_user, Date collectDate) {
		super();
		this.id = id;
		this.collect_goods = collect_goods;
		this.collect_goods_user = collect_goods_user;
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
    @JoinColumn(name = "g_id")
	public Goods getCollect_goods() {
		return collect_goods;
	}

	public void setCollect_goods(Goods collect_goods) {
		this.collect_goods = collect_goods;
	}
	public Date getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "g_u_id")
	public User getCollect_goods_user() {
		return collect_goods_user;
	}

	public void setCollect_goods_user(User collect_goods_user) {
		this.collect_goods_user = collect_goods_user;
	}
	
}
