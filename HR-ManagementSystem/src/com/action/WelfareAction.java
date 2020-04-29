package com.action;
import com.po.*;
import com.service.BizService;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
@Controller	
@Namespace("/")
@ParentPackage("struts-default")
public class WelfareAction implements IWelfareAction {
	private Welfare wf;
	private String path;
	
	@Resource(name="BizService")	
	private BizService bizs;
	
	
	public BizService getBizs() {
		return bizs;
	}

	public void setBizs(BizService bizs) {
		this.bizs = bizs;
	}
	public Welfare getWf() {
		return wf;
	}

	public void setWf(Welfare wf) {
		this.wf = wf;
	}

	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Action(value="save_Welfare",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String save() {
		boolean flag=bizs.getWbiz().save(wf);
		if(flag){
			path="findAll_Welfare";
			return "ok";
		}
		return null;
	}
	@Action(value="findAll_Welfare",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String findAll() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		List<Welfare> lswf=bizs.getWbiz().findAll();
		for (Welfare welfare : lswf) {
			System.out.println(welfare.getWname());
		}
		session.setAttribute("lswf", lswf);
		path="welfare.jsp";
		return "ok";
	}

}
