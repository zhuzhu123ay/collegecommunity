package com.yzdl.collegecommunity.web.action;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("csc-package")
@InterceptorRef(value="cscAuthStack")
public class GoodsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
}
