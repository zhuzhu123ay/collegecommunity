package com.yzdl.collegecommunity.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yzdl.collegecommunity.bean.Goods;
import com.yzdl.collegecommunity.bean.Task;
import com.yzdl.collegecommunity.bean.User;
import com.yzdl.collegecommunity.service.IGoodsService;
import com.yzdl.collegecommunity.service.IUserService;

@ParentPackage("csc-package")
@InterceptorRef(value = "cscAuthStack")
public class GoodsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IUserService userService;

	private List<Goods> goodsList;
	private List<Goods> sellList;
	private List<Goods> buyList;
	
	private String msg;
	private String description;
	private Double price;
	private String goods_photo;
	private File file;
	private String fileFileName;
	 private String fileContentType;
	
	private Long id;

	/**
	 * 跳转到商品大厅 http://localhost:8888/csc/toLogin.action
	 */
	@Action(value = "toGoodsHall", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/goods_hall.jsp") })
	public String toGoodsHall() {
		return SUCCESS;
	}

	/**
	 * 跳转到商品列表 http://localhost:8888/csc/toLogin.action
	 */
	@Action(value = "toGoodsList", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/goods_list.jsp") })
	public String toGoodsList() {
		goodsList = goodsService.findAll();
		/*
		 * this.pageBean = taskService.queryForPage(pageSize,
		 * 1);//获取封装了分页信息和数据的pageBean this.taskList = this.pageBean.getList();
		 * //获取数据
		 */ return SUCCESS;
	}

	/**
	 * 跳转到我销售的商品页面
	 *  http://localhost:8888/csc/toSellByMe.action
	 */
	@Action(value = "toSellByMe", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/sell_by_me.jsp") })
	public String toSellByMe() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User sellUser = (User) session.getAttribute("user");
		try {
			sellList = goodsService.findBySellName(sellUser.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到我购买的 http://localhost:8888/csc/toBuyByMe.action
	 */
	@Action(value = "toBuyByMe", results = { @Result(name = SUCCESS, location = "/WEB-INF/jsp/buy_by_me.jsp") })
	public String toBuyByMe() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User buyUser = (User) session.getAttribute("user");
		try {
			buyList = goodsService.findByBuyName(buyUser.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return SUCCESS;
	}

	/**
	 * 发布商品
	 *  http://localhost:8888/csc/publishGoods.action
	 * @throws IOException 
	 */
	@Action(value = "publishGoods")
	public void publishGoods() throws IOException {
		HttpSession session=ServletActionContext.getRequest().getSession();
		String goods_photo=ServletActionContext.getServletContext().getRealPath("/images");
		FileUtils.copyFile(file, new File(new File(goods_photo),fileFileName));
	       
	       
	          
		Goods goods=new Goods();
		goods.setPublisurDate(new Date());
		goods.setDescription(description);
		goods.setPrice(price);
		goods.setGoods_photo("images/"+fileFileName);
		goods.setState("未接受（购买）");
	
		
		User sell_user = (User) session.getAttribute("user");
		goods.setSell_user(sell_user);
		goods.setCollectTimes(0);
		// 限定发布次数
		sell_user.setPublishTimes(sell_user.getPublishTimes()+1);
		sell_user.setIntegrate(sell_user.getIntegrate() + 10);
		if (sell_user.getPublishTimes() <= 5) {
			userService.update(sell_user);
			goodsService.save(goods);
		}
		
	}

	/**
	 * 接收商品
	 * http://localhost:8888/csc/acceptGoods.action
	 * */
	@Action(value="acceptGoods")
	public void acceptGoods(){
		Goods goods=goodsService.findById(id);
		HttpSession session=ServletActionContext.getRequest().getSession();
		User buy_user=(User) session.getAttribute("user");
		goods.setBuy_user(buy_user);
		goods.setState("销售进行时");
		//限定接收次数
		buy_user.setAcceptTimes(buy_user.getAcceptTimes()+1);
		buy_user.setIntegrate(buy_user.getIntegrate()+15);
		if(buy_user.getAcceptTimes()<=3){
			userService.update(buy_user);
			goodsService.update(goods);
		}
	}
	

	/**
	 * 商品成功销售
	 * http://localhost:8888/csc/finishGoodsSell.action
	 */
	@Action(value = "finishGoodsSell")
	public void finishGoodsSell() {
		Goods goods=goodsService.findById(id);
		
		goods.setState("已完成");
		User finish_user = goods.getBuy_user();
		finish_user.setFinishTimes(finish_user.getFinishTimes() + 1);
		finish_user.setIntegrate(finish_user.getIntegrate() + 20);
		userService.update(finish_user);
		goodsService.update(goods);
	}

	/**
	 * 未能购买商品
	 *  http://localhost:8888/csc/unfinishGoods.action
	 */
	@Action(value = "unfinishGoods")
	public void unfinishGoods() {
		Goods goods=goodsService.findById(id);
		goods.setState("未完成");
		User unfinish_user = goods.getBuy_user();
		unfinish_user.setIntegrate(unfinish_user.getIntegrate() - 15);
		userService.update(unfinish_user);
		goodsService.update(goods);
	}

	/**
	 * 删除商品 
	 * http://localhost:8888/csc/deleteGoods.action
	 */
	@Action(value = "deleteGoods")
	public void deleteGoods() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User delete_user = (User) session.getAttribute("user");
		delete_user.setPublishTimes(delete_user.getPublishTimes() - 1);
		delete_user.setIntegrate(delete_user.getIntegrate() - 10);
		userService.update(delete_user);
		goodsService.delete(id);
	}
	
	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<Goods> getSellList() {
		return sellList;
	}

	public void setSellList(List<Goods> sellList) {
		this.sellList = sellList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Goods> getBuyList() {
		return buyList;
	}

	public void setBuyList(List<Goods> buyList) {
		this.buyList = buyList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getGoods_photo() {
		return goods_photo;
	}

	public void setGoods_photo(String goods_photo) {
		this.goods_photo = goods_photo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

}
