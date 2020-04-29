package com.service;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.*;
@Service("BizService")
public class BizService {
	@Resource(name="DepBiz")
	private IDepBiz depbiz;
	
	@Resource(name="AdminBiz")
	private IAdminBiz abiz;
	
	
	@Resource(name="WelfareBiz")
	private IWelfareBiz wbiz;
	
	@Resource(name="EmpBiz")
	private IEmpBiz ebiz;
	
	
	public IDepBiz getDepbiz() {
		return depbiz;
	}
	public void setDepbiz(IDepBiz depbiz) {
		this.depbiz = depbiz;
	}
	public IAdminBiz getAbiz() {
		return abiz;
	}
	public void setAbiz(IAdminBiz abiz) {
		this.abiz = abiz;
	}
	public IWelfareBiz getWbiz() {
		return wbiz;
	}
	public void setWbiz(IWelfareBiz wbiz) {
		this.wbiz = wbiz;
	}
	public IEmpBiz getEbiz() {
		return ebiz;
	}
	public void setEbiz(IEmpBiz ebiz) {
		this.ebiz = ebiz;
	}
	
	
}
