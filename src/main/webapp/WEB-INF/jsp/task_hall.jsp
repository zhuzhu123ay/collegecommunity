<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" >
$(function(){
	var allRow=0;
	var pageSize=5;
	$(".lessUI li").off("click");
	$(".lessUI li").on("click",function(){
		var cate = $(this);
		if(!cate.hasClass("active")){
			cate.addClass("active");
			cate.siblings().removeClass("active");
		}
		var url = cate.attr("url");
		$(".listContent").load(url,{"pageSize":5},function(data){
			allRow=$(".allRow").val();
			pageSize=$(".pageSize").val();
			
		});
	});
	//默认激发第一个li
	$(".lessUI li:first").trigger("click");
	
	$(window).scroll(function(){
		var docHeight=$(document).height();
		var winHeight=$(window).height();
		var winScrollHeight=$(window).scrollTop();
		if(docHeight==winHeight+winScrollHeight){			
			pageSize=parseInt(pageSize)+parseInt(5);
		    $(".listContent").load("toTaskList.action",{"pageSize":pageSize}); 		 
			if(pageSize>allRow){
				$(window).unbind("scroll"); 
			} 
		}
	});
	
});	

</script>
<div class="container" style="margin-top:160px;">
	<ul class="lessUI" style="list-style:none">
		<li url="toTaskList.action" style="cursor:pointer" class="taskList" >
			任务中心
		</li>
		<li url="toPublishByMe.action" style="cursor:pointer" class="myPublish">
			我发布的
		</li>
		<li url="toAcceptByMe.action" style="cursor:pointer" class="myAccept">
			我接收的
		</li>
	</ul>
	
	<div class="tab-content listContent container">
	
	</div>
</div>