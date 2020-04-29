package com.biz.imp;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.po.*;
import com.service.DaoService;
import com.biz.IEmpBiz;

@Service("EmpBiz")
@Transactional
public class EmpBiz implements IEmpBiz {
	@Resource(name="DaoService")
	private DaoService daos;

	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}
	
	public boolean save(Emp emp) {
		if(emp.getPhoto()==null||emp.getPhoto().trim().equals("")){
			emp.setPhoto("default.jpg");
		}
		/****处理薪资数据*******/
		Set<Salary> ss=new HashSet<Salary>();
		Salary sa=new Salary(emp,emp.getEmoney());
		ss.add(sa);
		emp.setSalaries(ss);//向薪资表保存记录
		/********************/
		
		/****处理员工福利数据***************************/
		Integer[] wids=emp.getWids();
		Set<Empwelfare> ews=new HashSet<Empwelfare>();
		if(wids!=null&&wids.length>0){//判断是否从界面获取福利编号
			for (int i = 0; i < wids.length; i++) {
				//获取选择的福利对象
				Welfare wf=daos.getWdao().findById(wids[i]);
				Empwelfare ewf=new Empwelfare(emp, wf);//为员工福利关系表准备数据
				ews.add(ewf);//将员工福利对象设置到员工福利集合中
			}
		}
		emp.setEmpwelfares(ews);//向员工福利表插入数据
		/*******************************************/		
		
		try {
			daos.getEdao().save(emp);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Emp emp) {
		//获取原来的员工对象
		Emp oldemp=daos.getEdao().findById(emp.getEid());
		
		/****更新薪资***************/
		Set<Salary> oldss=oldemp.getSalaries();
		if(oldss!=null&&!oldss.isEmpty()){//判断是否存在薪资
			for (Salary sa : oldss) {
				sa.setEmoney(emp.getEmoney());
			}
			emp.setSalaries(oldss);
		}else{
			Set<Salary> ss=new HashSet<Salary>();
			Salary sa=new Salary(emp,emp.getEmoney());
			ss.add(sa);
			emp.setSalaries(ss);//向薪资表保存记录
		}		
		/*************************/
		
		/****处理员工福利的数据*********************/
		//获取原来的员工福利集合
		Set<Empwelfare> oldews=oldemp.getEmpwelfares();
		if(oldews!=null&&!oldews.isEmpty()){//判断原来的员工是否存在福利
			//删除原来的员工福利
			for (Empwelfare empwelfare : oldews) {
				daos.getEwdao().delete(empwelfare);
			}
		}
		
		//加入新的员工福利
		Integer[] wids=emp.getWids();
		Set<Empwelfare> ews=new HashSet<Empwelfare>();
		if(wids!=null&&wids.length>0){//判断是否从界面获取福利编号
			for (int i = 0; i < wids.length; i++) {
				//获取选择的福利对象
				Welfare wf=daos.getWdao().findById(wids[i]);
				Empwelfare ewf=new Empwelfare(emp, wf);//为员工福利关系表准备数据
				ews.add(ewf);//将员工福利对象设置到员工福利集合中
			}
		}
		emp.setEmpwelfares(ews);//向员工福利表插入数据
		
		/*************************************/
		
		try {
			daos.getEdao().merge(emp);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delById(Integer eid) {
		Emp emp=daos.getEdao().findById(eid);
		try {
			daos.getEdao().delete(emp);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Emp findById(Integer eid) {
		Emp emp=daos.getEdao().findById(eid);
		
		/***获取员工的薪资，为修改页面的薪资文本框准备数据**********/
		Set<Salary> ss=emp.getSalaries();
		for (Salary salary : ss) {
			emp.setEmoney(salary.getEmoney());
		}
		/**********************************************/
		
		/**********获取员工福利编号数组，为修改界面的福利复选框选中值准备数据*****/
		Set<Empwelfare> ews=emp.getEmpwelfares();
		if(ews!=null&&!ews.isEmpty()){
			Integer[] wids=new Integer[ews.size()];
			int i=0;//数组下标的索引位置
			for (Empwelfare ewf : ews) {
				wids[i]=ewf.getWelfare().getWid();
				i++;//索引累加
			}
			emp.setWids(wids);
		}
		/*********************************************************/
		
		return emp;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Emp> findAll(int page, int rows) {
		if(page<1)page=1;
		if(rows<1)rows=5;
		return daos.getEdao().findPageAll(page, rows);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int findMaxPage(int rows) {
		if(rows<1)rows=5;
		
		return daos.getEdao().findMaxPage(rows);
	}

}
