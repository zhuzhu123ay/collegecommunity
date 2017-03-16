<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$("#publishTaskForm").on("submit", function() {
		var publishTimes=$(".hidden").attr("val");
		if(publishTimes>=5){
			$(".publish").addClass("disabled");
			alert("发布失败，最多可发布5个,您今日已达上限");
			$(".description").val("");
			$(".pay").val(""); 
		}
		else{
			$(this).ajaxSubmit({
			success : function() {
				alert("发布成功！获得10积分");
				$(".listContent").load("toTaskList.action");
			},
			resetForm : true
			});
		}
		return false;
	});
	
	$(".accept").off();
	$(".accept").on("click", function() {
		var acceptTimes=$(".hidden2").attr("val");
		if(acceptTimes>=3){
			$(".accept").addClass("disabled");
			alert("接收失败，最多可接收3个,您今日已达上限");
		}
		else {
			var id = $(this).attr("val");
			var url = "acceptTask.action";
			$.post(url, {
				id : id
			}, function() {
				alert("成功接收任务！获得15积分");
				$(".listContent").load("toTaskList.action");
			});
		}
	});
	
	$(".collect").off();
	$(".collect").on("click", function() {	
			var id = $(this).attr("val");
			if($(this).text().trim()=="收藏"){
				var url = "collectTask.action";
				$.post(url, {id : id}, function() {
					alert("您成功收藏任务");
					$(".listContent").load("toTaskList.action");
				});
			}else if($(this).text().trim()=="已收藏") {
				var url = "cancelCollect.action";
				$.post(url, {id : id}, function() {
					alert("您取消收藏任务");
					$(".listContent").load("toTaskList.action");
				});
			}
		});
	$(".chat").off();
	$(".chat").on("click", function() {
		var publish_username=$(this).attr("val");
		$.post("login.action?username=rrr&password=rrr",{publish_username:publish_username});
	});
</script>
<div class="row publishBox" style="border: 1px solid #ccc">
	<form class="form-horizontal" action="publishTask.action" method="post" id="publishTaskForm">
		<input type="hidden" value="${pageBean.allRow }" class="allRow">
		<input type="hidden" value="${pageBean.pageSize }" class="pageSize">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">任务描述:</label>
			<div class="col-sm-10">
				<textarea  class="form-control description" name="description">请输入你要发布的内容。。。</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">酬谢金额:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control pay" name="pay">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit"  value="发布" class="btn btn-success publish"> 
			</div>
		</div>
		<input type="hidden" val="${user.publishTimes }" class="hidden">
		<input type="hidden" val="${user.acceptTimes }" class="hidden2">
		
	</form>
</div>

	<div class="taskContent">
		<c:forEach items="${taskList }" var="t">
			<c:if test="${empty t.accept_user }">
				<div class="row" style="border: 1px solid #ccc">
					<div id="publish_user">${t.publish_user.username }</div>
					<div class="pull-right">发布日期：${t.publisurDate }</div> 
					<div>任务描述：${t.description }</div>
					<div>酬金：${t.pay }</div>
				    <div style="color:red">任务状态：${t.state }</div>
				    <c:if test="${t.publish_user.username!=user.username }">
						<a class="btn btn-primary  accept pull-right" val="${t.id }">我接收</a>
						<a class="btn btn-danger collect pull-right" val="${t.id }">
							<c:set var="flag" value="false"></c:set>
							  <c:forEach items="${t.task_users}" var="u">
								   <c:if test="${u.collect_task_user.id==user.id && u.collect_task.id==t.id}">
								    <c:set var="flag" value="true"></c:set>   
								   </c:if>
							  </c:forEach>
							  <c:if test="${flag==true}">已收藏</c:if> 
							  <c:if test="${flag==false}">收藏</c:if>
						</a>
						<div class="pull-right btn">已收藏${t.collectTimes>0?t.collectTimes:0 }次</div>
						<button  val="${t.publish_user.username }" type="button" class="btn btn-success chat" data-toggle="modal" data-target="#myModal">
							联系对方
						</button>
					</c:if>
				</div>
			</c:if>
		</c:forEach>
	</div>
