package com.yzdl.collegecommunity.web.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.yzdl.collegecommunity.bean.User;



@WebListener
public class OnLinePeopleListener implements ServletContextListener,HttpSessionListener,HttpSessionAttributeListener{
	// 声明一个ServletContext对象   
    private ServletContext application = null; 
	@SuppressWarnings("unchecked")
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// 如果登陆成功，则将用户名保存在列表之中   
        List<User> l = (List<User>) this.application.getAttribute("alluser");   
        l.add((User) arg0.getValue());   
        this.application.setAttribute("alluser", l);  
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// 将用户名称从列表中删除   
        List<User> l = (List<User>) this.application.getAttribute("alluser");   
        User value =(User) arg0.getSession().getAttribute("user");   
        l.remove(value);   
        this.application.setAttribute("alluser", l);   
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 容器初始化时，向application中存放一个空的容器   
        this.application = arg0.getServletContext();   
        this.application.setAttribute("alluser", new ArrayList<User>());  
	}

}
