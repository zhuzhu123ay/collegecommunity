<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" >
$(function(){
	$(".lessUI li").off("click");
	$(".lessUI li").on("click",function(){
		var cate = $(this);
		if(!cate.hasClass("active")){
			cate.addClass("active");
			cate.siblings().removeClass("active");
		}
		var url = cate.attr("url");
		$(".listContent").load(url);
	});
	//默认激发第一个li
	$(".lessUI li:first").trigger("click");
});	
</script>
<div class="container" style="margin-top:30px;">
	<ul class="lessUI" style="list-style:none">
		<li url="toCollectTask.action" style="cursor:pointer" class="taskList">
			收藏的任务
		</li>
		<li url="" style="cursor:pointer" class="myPublish">
			收藏的商品
		</li>
	</ul>
	
	<div class="tab-content listContent container">
	
	</div>
</div>