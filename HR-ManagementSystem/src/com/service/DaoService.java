package com.service;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.*;
@Service("DaoService")
public class DaoService {
	@Resource(name="AdminDAO")
	private AdminDAO adao;
	
	@Resource(name="DepDAO")
	private DepDAO depdao;
	
	@Resource(name="WelfareDAO")
	private WelfareDAO wdao;
	
	@Resource(name="EmpDAO")
	private EmpDAO edao;
	
	@Resource(name="SalaryDAO")
	private SalaryDAO sdao;
	
	@Resource(name="EmpwelfareDAO")
	private EmpwelfareDAO ewdao;
	
	public DepDAO getDepdao() {
		return depdao;
	}
	public void setDepdao(DepDAO depdao) {
		this.depdao = depdao;
	}
	public WelfareDAO getWdao() {
		return wdao;
	}
	public void setWdao(WelfareDAO wdao) {
		this.wdao = wdao;
	}
	public EmpDAO getEdao() {
		return edao;
	}
	public void setEdao(EmpDAO edao) {
		this.edao = edao;
	}
	public SalaryDAO getSdao() {
		return sdao;
	}
	public void setSdao(SalaryDAO sdao) {
		this.sdao = sdao;
	}
	public EmpwelfareDAO getEwdao() {
		return ewdao;
	}
	public void setEwdao(EmpwelfareDAO ewdao) {
		this.ewdao = ewdao;
	}
	public AdminDAO getAdao() {
		return adao;
	}
	public void setAdao(AdminDAO adao) {
		this.adao = adao;
	}
	
	
}
