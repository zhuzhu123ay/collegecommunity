<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$("#alterPhotoForm").on("submit", function() {
	$(this).ajaxSubmit({
		success : function() {
			alert("修改成功");
			$(".photoContent").load("toPhotoContent.action");
	},
		resetForm : true
    });
	return false;
});
</script>
<div class="userPhoto">
	<img src="${user.path }" alt="" style="width:100px;height:100px;">
	<form action="alterPhoto.action" id="alterPhotoForm" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="修改头像">
	</form>
	<a href="download.action?fileName=images/defaultPhoto.jpg">下载</a>
</div>