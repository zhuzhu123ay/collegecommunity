<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".sended").off();
	$(".sended").on("click",function(){
		var mail=$(".mail").val();
		$.post("validated.action",{mail:mail},function(){
			alert("发送成功，请注意查收");
		}); 
	}); 
});
	
</script>
</head>
<body>
	<center class="content">
		<h1>用户注册</h1>
		<div style="color:red">${msg }</div>
		<form action="register.action" method="POST">
		<table style="width:400px;">
			<tr>
				<td>用户名</td>
				<td>
					<input type="text" name="username" id="username"/>
				</td>
				<td><span id="msg"></span></td>
			</tr>
			<tr>
				<td>密码</td>
				<td>
					<input type="password" name="password"/>
				</td>
			</tr>
			<tr>
				<td>重复密码</td>
				<td>
					<input type="password" name="re_password"/>
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<label>
						<input type="radio" name="gender" 
						value="男" checked="checked"/>
						男
					</label>
					<label>
						<input type="radio" name="gender" value="女"/>
						女
					</label>
					
				</td>
			</tr>
			<tr>
				<td>手机号</td>
				<td>
					<input type="text" name="telephone"/>
				</td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td>
					<input type="text" name="mail" class="mail"/>
				</td>
			</tr>
			<tr>
				<td><a class="sended" href="javascript:alter(1)">发送验证码</a></td>
				<td>
					<input type="text" name="validate"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit"  value="注册">
					<a href="toLogin.action">有账号？点击登录</a>
				</td>
			</tr>
		</table>
		</form>
	</center>
</body>
</html>