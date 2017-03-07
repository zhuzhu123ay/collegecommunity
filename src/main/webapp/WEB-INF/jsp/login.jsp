<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大学生社区--登录页面</title>
</head>
<body>
	<s:form action="login.action" validate="true" method="POST">
		<s:textfield name="username" label="用户名"></s:textfield>
		<s:password name="password" label="密   码"></s:password>
		<s:submit value="提交"></s:submit>
	</s:form>
	<a href="toRegister.action">没有账号？请注册</a>
	<%-- <center>
		<h1>用户登录</h1>
		<div style="color:red">${msg }</div>
		<form action="login.action" method="POST">
		<table style="width:400px;">
			<tr>
				<td>用户名</td>
				<td>
					<input type="text" name="username"/>
				</td>
			</tr>
			<tr>
				<td>密码</td>
				<td>
					<input type="password" name="password"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit"  value="登录">
					<a href="toRegister.action">没有账号？请注册</a>
				</td>
			</tr>
		</table>
		</form> 
	</center>--%>
</body>
</html>