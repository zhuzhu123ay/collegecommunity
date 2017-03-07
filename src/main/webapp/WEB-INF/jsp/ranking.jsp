<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<div class="row">
  	本周冠军：${topOne.username }
</div>
<c:forEach items="${rankingList}" var="r" varStatus="s">
	<div class="row" style="border:1px dotted #ccc">
		<div>用户${r.username }</div>
		<div>发布了${r.publishTimes }个任务</div>
		<div>接收了${r.acceptTimes }个任务</div>
		完成率为<fmt:formatNumber type="number" value="${r.acceptTimes==0?0:r.finishTimes/r.acceptTimes *100}" maxFractionDigits="2"/>%
		<div>共获得积分${r.integrate }</div>
		<div>排行第${s.index+1 }</div>
		
	</div>
</c:forEach>
