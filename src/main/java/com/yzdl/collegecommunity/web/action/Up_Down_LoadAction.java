package com.yzdl.collegecommunity.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yzdl.collegecommunity.bean.User;
import com.yzdl.collegecommunity.service.IUserService;

@ParentPackage("csc-package")
@InterceptorRef(value="cscAuthStack")
public class Up_Down_LoadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private File file;
	private String fileContentType;
	private String fileFileName;
	
	private InputStream attachstream;//文件读取流对象
	private String attachname;//下载文件的名字
	private String fileName;
	
	@Autowired
	private IUserService userService;
	/**
	 * 跳转到头像内容
	 * http://localhost:8888/cms/toPhotoContent.action
	 * */
	@Action(value="toPhotoContent",
			results={@Result(name=SUCCESS,
			location="/WEB-INF/jsp/photoContent.jsp")})
	public String toPhotoContent(){
		return SUCCESS;
	}
	
	/**
	 * 修改头像
	 * http://localhost:8888/cms/alterPhoto.action
	 * */
	@Action(value="alterPhoto")
	public void alterPhoto(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User user=(User) session.getAttribute("user");
		String path=ServletActionContext.getServletContext().getRealPath("/images");
		try {
			//删除原图片
			String curPath=ServletActionContext.getServletContext().getRealPath("/")+user.getPath();
			new File(curPath).delete();
			//将新头像上传到服务器
			FileUtils.copyFile(file, new File(new File(path),fileFileName));
			//修改数据库中用户的头像路径信息
			user.setPath("images/"+fileFileName);
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载
	 * http://localhost:8888/cms/download.action
	 * */
	@Action(value="download",
			results={@Result(name = "download",type = "stream",params = { 
					        "contentType", "application/octet-stream", 
					        "inputName", "attachstream", 
					        "bufferSize", "4096", 
					        "contentDisposition", "attachment;filename=\"${attachname}\""
					    })})
	public String download(){	
		String path = ServletActionContext.getServletContext().getRealPath("/" + fileName);// fileName是页面传过来的参数
		try {
			attachstream = new FileInputStream(path);
			String[] attachnames = fileName.split("/");
			attachname = attachnames[attachnames.length - 1];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "download";
	}

	
	
	public InputStream getAttachstream() {
		return attachstream;
	}

	public void setAttachstream(InputStream attachstream) {
		this.attachstream = attachstream;
	}

	public String getAttachname() {
		return attachname;
	}

	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}
