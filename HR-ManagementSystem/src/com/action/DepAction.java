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
public class DepAction implements IDepAction {
	private Dep dep;
	private String path;
	
	@Resource(name="BizService")
	private BizService bizs;
	public BizService getBizs() {
		return bizs;
	}

	public void setBizs(BizService bizs) {
		this.bizs = bizs;
	}

	
	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Action(value="save_Dep",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String save() {
		boolean flag=bizs.getDepbiz().save(dep);
		if(flag){
			path="findAll_Dep";
			return "ok";
		}
		return null;
	}
	@Action(value="findAll_Dep",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String findAll() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		List<Dep> lsdep=bizs.getDepbiz().findAll();
//		for (Dep dep : lsdep) {
//			dep.setEnums(dep.getEmps().size());
//		}
		session.setAttribute("lsdep", lsdep);
		path="dep.jsp";
		return "ok";
	}

}
