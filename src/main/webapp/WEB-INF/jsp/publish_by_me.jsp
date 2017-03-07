<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(".finish").off();
	$(".finish").on("click", function() {
		var cate=$(this);
		var id = $(this).attr("val");
		var url = "finishTask.action";
		$.post(url, {id : id}, function() {
			alert("完成任务,获得20积分");
			$(".listContent").load("toPublishByMe.action");		
	});
});	
	
	$(".unfinish").off();
	$(".unfinish").on("click", function() {
		var cate=$(this);
		var id = $(this).attr("val");
		var url = "unfinishTask.action";
		$.post(url, {id : id}, function() {
			alert("对方没有完成任务,失去15积分");
			$(".listContent").load("toPublishByMe.action");		
	});
});	
	
	$(".cancel").off();
	$(".cancel").on("click", function() {
		var id = $(this).attr("val");
		var url = "cancelTask.action";
		$.post(url, {id : id}, function() {
			alert("取消任务,失去10积分");
			$(".listContent").load("toPublishByMe.action");		
	});
});
</script>
	<div class="taskList">
		<c:forEach items="${publishList }" var="p">
			<div class="row" style="border: 1px solid #ccc">
				<div>${p.publish_user.username }</div>
			 	<div class="pull-right">${p.publisurDate }</div>
				<div>${p.description }</div>
				<div>${p.pay }</div>
				<div style="color:red">${p.state }</div> 		
				<c:if test="${!empty p.accept_user.username }">
					${p.accept_user.username }
					<a class="finish pull-right" style="cursor:pointer" val="${p.id }">${p.state eq'已完成'||p.state eq'未完成'?"":"完成"}</a>
					<a class="unfinish pull-right" style="cursor:pointer" val="${p.id }">${p.state eq'已完成'||p.state eq'未完成'?"":"未完成"}</a>
				</c:if>
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">
					联系对方
				</button>
				<c:if test="${p.state eq'未接收' }">
					<a class="pull-right cancel"  val="${p.id }">取消</a>
				</c:if>
			</div>
		</c:forEach>
	</div>
