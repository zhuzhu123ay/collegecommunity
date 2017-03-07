<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(".cancelCollect").off();
	$(".cancelCollect").on("click", function() {
		var id = $(this).attr("val");
		var url = "cancelCollect.action";
		$.post(url, {
			id : id
		}, function() {
			alert("您取消收藏任务");
			$(".listContent").load("toCollectTask.action");
		});
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
				$(".listContent").load("toCollectTask.action");
			});
		}
	});
	</script>
<div class="taskList">
	${msg }
		<c:forEach items="${collectList }" var="t">
			<div class="row" style="border: 1px solid #ccc">
				<input type="hidden" val="${user.acceptTimes }" class="hidden2">
				<div>${t.collect_task.publish_user.username }</div>
			 	<div class="pull-right">${t.collect_task.publisurDate }</div>
				<div>${t.collect_task.description }</div>
				<div>${t.collect_task.pay }</div>
				<div style="color:red">${t.collect_task.state }</div> 
				 <c:if test="${t.collect_task.publish_user.username!=user.username }">
				 	<c:if test="${empty t.collect_task.accept_user }">
						<a class="btn btn-primary  accept pull-right" val="${t.collect_task.id }">我接收</a>
					</c:if>
					<a class="btn btn-warning  cancelCollect pull-right" val="${t.collect_task.id }">取消收藏</a>
				</c:if>		
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">
					联系对方
				</button>
				<div>收藏时间：${t.collectDate }</div>
			</div>
		</c:forEach>
	</div>
