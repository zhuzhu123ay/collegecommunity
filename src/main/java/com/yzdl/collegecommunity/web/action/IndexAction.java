package com.yzdl.collegecommunity.web.action;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yzdl.collegecommunity.bean.User;
import com.yzdl.collegecommunity.common.util.MailUtils;
import com.yzdl.collegecommunity.service.IUserService;

import org.apache.struts2.convention.annotation.ParentPackage; 

@ParentPackage("struts-default")
public class IndexAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUserService userService;
	
	private String username;
	private String password;
	private String msg;
	private String re_password;
	private String gender;
	private String telephone;
	private String mail;
	private Integer validate;
	private String publish_username;
	private static Integer re_validate;
	/**
	 * 跳转到登录页
	 * http://localhost:8888/csc/toLogin.action
	 * */
	@Action(value="toLogin",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/login.jsp")})
	public String toLogin(){
		return SUCCESS;
	}
	
	/**
	 * 跳转到注册页
	 * http://localhost:8888/csc/toRegister.action
	 * */
	@Action(value="toRegister",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/register.jsp")})
	public String toRegister(){
		return SUCCESS;
	}
	/**
	 * 登录
	 * http://localhost:8888/csc/login.action
	 * */
	@Action(value="login",
			results={@Result(name="success",
			location="/WEB-INF/jsp/index.jsp"),
					@Result(name="input",
					location="/WEB-INF/jsp/login.jsp")})
	public String login(){
		String flag="success";
		try {
			User user=userService.login(username, password);
			HttpSession session=ServletActionContext.getRequest().getSession();
			session.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			msg=e.getMessage();
			flag="input";
		}
		return flag;
	}

	/**
	 * 注册
	 * http://localhost:8888/csc/register.action
	 * */
	@Action(value="register",
			results={@Result(name="success",location="toLogin",type="chain"),
					 @Result(name="error",location="toRegister",type="chain")})
	public String register(){
		String flag="success";
		String defaultpath="images/defaultPhoto.jpg";
		User user=new User(null, username, password, gender, telephone,0,0,0,0,defaultpath,mail,null,null);
		if(password.equals(re_password)&&re_validate.equals(validate)){			
			try {
				userService.register(user);
			} catch (Exception e) {
				e.printStackTrace();
				msg=e.getMessage();
				flag="error";
			}
		}else{
			msg="密码不一致或验证码输入错误!";
			flag="error";			
		}
		return flag;
	}
	
	
	//发送验证码
	@Action(value="validated")
	public void validated(){
		try {
			re_validate=MailUtils.sendMail(mail);
			System.out.println(re_validate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 退出
	 * http://localhost:8888/csc/logout.action
	 * */
	@Action(value="logout",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/login.jsp")})
	public String logout(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.removeAttribute("user");
		return SUCCESS;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRe_password() {
		return re_password;
	}

	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getValidate() {
		return validate;
	}

	public void setValidate(Integer validate) {
		this.validate = validate;
	}

	public String getPublish_username() {
		return publish_username;
	}

	public void setPublish_username(String publish_username) {
		this.publish_username = publish_username;
	}

	
}
