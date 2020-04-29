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
		/****����н������*******/
		Set<Salary> ss=new HashSet<Salary>();
		Salary sa=new Salary(emp,emp.getEmoney());
		ss.add(sa);
		emp.setSalaries(ss);//��н�ʱ����¼
		/********************/
		
		/****����Ա����������***************************/
		Integer[] wids=emp.getWids();
		Set<Empwelfare> ews=new HashSet<Empwelfare>();
		if(wids!=null&&wids.length>0){//�ж��Ƿ�ӽ����ȡ�������
			for (int i = 0; i < wids.length; i++) {
				//��ȡѡ��ĸ�������
				Welfare wf=daos.getWdao().findById(wids[i]);
				Empwelfare ewf=new Empwelfare(emp, wf);//ΪԱ��������ϵ��׼������
				ews.add(ewf);//��Ա�������������õ�Ա������������
			}
		}
		emp.setEmpwelfares(ews);//��Ա���������������
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
		//��ȡԭ����Ա������
		Emp oldemp=daos.getEdao().findById(emp.getEid());
		
		/****����н��***************/
		Set<Salary> oldss=oldemp.getSalaries();
		if(oldss!=null&&!oldss.isEmpty()){//�ж��Ƿ����н��
			for (Salary sa : oldss) {
				sa.setEmoney(emp.getEmoney());
			}
			emp.setSalaries(oldss);
		}else{
			Set<Salary> ss=new HashSet<Salary>();
			Salary sa=new Salary(emp,emp.getEmoney());
			ss.add(sa);
			emp.setSalaries(ss);//��н�ʱ����¼
		}		
		/*************************/
		
		/****����Ա������������*********************/
		//��ȡԭ����Ա����������
		Set<Empwelfare> oldews=oldemp.getEmpwelfares();
		if(oldews!=null&&!oldews.isEmpty()){//�ж�ԭ����Ա���Ƿ���ڸ���
			//ɾ��ԭ����Ա������
			for (Empwelfare empwelfare : oldews) {
				daos.getEwdao().delete(empwelfare);
			}
		}
		
		//�����µ�Ա������
		Integer[] wids=emp.getWids();
		Set<Empwelfare> ews=new HashSet<Empwelfare>();
		if(wids!=null&&wids.length>0){//�ж��Ƿ�ӽ����ȡ�������
			for (int i = 0; i < wids.length; i++) {
				//��ȡѡ��ĸ�������
				Welfare wf=daos.getWdao().findById(wids[i]);
				Empwelfare ewf=new Empwelfare(emp, wf);//ΪԱ��������ϵ��׼������
				ews.add(ewf);//��Ա�������������õ�Ա������������
			}
		}
		emp.setEmpwelfares(ews);//��Ա���������������
		
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
		
		/***��ȡԱ����н�ʣ�Ϊ�޸�ҳ���н���ı���׼������**********/
		Set<Salary> ss=emp.getSalaries();
		for (Salary salary : ss) {
			emp.setEmoney(salary.getEmoney());
		}
		/**********************************************/
		
		/**********��ȡԱ������������飬Ϊ�޸Ľ���ĸ�����ѡ��ѡ��ֵ׼������*****/
		Set<Empwelfare> ews=emp.getEmpwelfares();
		if(ews!=null&&!ews.isEmpty()){
			Integer[] wids=new Integer[ews.size()];
			int i=0;//�����±������λ��
			for (Empwelfare ewf : ews) {
				wids[i]=ewf.getWelfare().getWid();
				i++;//�����ۼ�
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
