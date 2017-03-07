<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="taskList">
		<c:forEach items="${acceptList }" var="a">
			<div class="row" style="border: 1px solid #ccc">
				<div>${a.publish_user.username }</div>
			 	<div class="pull-right"> ${a.publisurDate }</div>
				<div>${a.description }</div>
				<div>${a.pay }</div>
				<div style="color:red">${a.state }</div> 
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">
					联系对方
				</button>
			</div>
		</c:forEach>
	</div>
