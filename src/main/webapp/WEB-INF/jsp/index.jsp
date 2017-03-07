<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//http://localhost:8888/sms_web
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="theme/1/css/index.css" rel="stylesheet">
<script src="js/jquery-1.11.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>


<script type="text/javascript" >
$(function(){
	$(".baseUI li").off("click");
	$(".baseUI li").on("click",function(){
		var cate = $(this);
		if(!cate.hasClass("active")){
			cate.addClass("active");
			cate.siblings().removeClass("active");
		}
		var url = cate.attr("url");
		$(".photoContent").load("toPhotoContent.action");
		$(".mainContent").load(url);
	});
	//默认激发第一个li
	$(".baseUI li:first").trigger("click");
});	

//websocket
	var webSocket=new WebSocket("ws://127.0.0.1:8888/");
</script>

<title>大学生社区-首页</title>
</head>
<body style="padding-top:50px;">
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
   <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" 
         data-target="#example-navbar-collapse">
         <span class="sr-only">切换导航</span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand">大学生社区</a>
   </div>
   <div class="collapse navbar-collapse" id="example-navbar-collapse">
      <ul class="nav navbar-nav baseUI">
         <li url="toTaskHall.action"><a>任务大厅</a></li>
         <li url="toSecondMarket.action"><a>二手市场</a></li>
         <li url="toCollect.action"><a>我的收藏</a></li>
         <li url="toRankingList.action"><a>排行榜</a></li>
      </ul>
       <ul class="nav navbar-nav navbar-right">
       	<li><a>在线用户人数：${fn:length(alluser)}</a></li>
        <li><a>欢迎您:${user.username }</a></li>
        <li><a href="logout.action" style="margin-right:20px;" class="pull-right">退出</a></li>
      </ul>
   </div>
</nav>

<div class="container photoContent">

</div>
<div class="container mainContent">

</div>

<div class="footer" style="background:#191e29;padding-top:20px;margin-top:30px;">
	<div class="container">
		<div class="row">
			<div class="col-md-4 clearfix footer-col">
				<img alt="" src="">
				<div class="footer-slogan">让你的大学生活丰富多彩</div>		
			</div>
			<div class="col-xs-6 col-sm-3 col-md-2 footer-col" style="min-height:50px">
				<div class="col-title">友情链接：</div>
			</div>
			<div class="col-xs-6 col-sm-3 col-md-2 footer-col">
				<a href="">xxx|</a>
			</div>
			<div class="col-xs-6 col-sm-3 col-md-2 footer-col">
				<a href="">xxx|</a>
			</div>
			<div class="col-xs-6 col-sm-3 col-md-2 footer-col">
				<a href="">xxx</a>
			</div>
		</div>
		
	</div>
	<div class="text-center copyright"	style="font-size:12px;">
			<span>Copyright @2017 大学生社区</span>
			<span class="ver-line">|</span>
			<a>170203号</a>
	</div>
</div>

	<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary">发送</button>
      </div>
    </div>
  </div>
</div>
 
</body>
</html>