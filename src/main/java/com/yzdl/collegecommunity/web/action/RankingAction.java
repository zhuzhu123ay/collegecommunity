package com.yzdl.collegecommunity.web.action;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yzdl.collegecommunity.bean.User;
import com.yzdl.collegecommunity.service.IUserService;
@ParentPackage("csc-package")
@InterceptorRef(value="cscAuthStack")
public class RankingAction  extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<User> rankingList;
	private User topOne;
	@Autowired
	private IUserService userService;
	
	/**
	 * 跳转到排行榜
	 * http://localhost:8888/cms/toCollect.action
	 * */
	@Action(value="toRankingList",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/ranking.jsp")})
	public String toRankingList(){
		try {
			rankingList=userService.findAllByOrder();
			topOne=rankingList.get(0);
			//一周清零
			Timer timer = new Timer();
		    timer.schedule(new TimerTask() {
		      public void run() {
		    	 for (User user : rankingList) {
					user.setAcceptTimes(0);
					user.setPublishTimes(0);
					user.setFinishTimes(0);
					user.setIntegrate(0);
					userService.update(user);
				} 
		      }
		    }, 604800000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public List<User> getRankingList() {
		return rankingList;
	}
	public void setRankingList(List<User> rankingList) {
		this.rankingList = rankingList;
	}
	public User getTopOne() {
		return topOne;
	}
	public void setTopOne(User topOne) {
		this.topOne = topOne;
	}
}
