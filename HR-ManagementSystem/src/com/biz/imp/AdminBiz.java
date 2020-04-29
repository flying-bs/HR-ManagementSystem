package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biz.*;
import com.po.*;
import com.service.DaoService;
@Service("AdminBiz")
@Transactional
public class AdminBiz implements IAdminBiz {
	@Resource(name="DaoService")
	private DaoService daos;
	
	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}

	public boolean save(Admin admin) {
		try {
			daos.getAdao().save(admin);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Admin admin) {
		try {
			daos.getAdao().merge(admin);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delById(Integer aid) {
		Admin admin=daos.getAdao().findById(aid);
		try {
			daos.getAdao().delete(admin);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Admin findById(Integer aid) {
		
		return daos.getAdao().findById(aid);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Admin> findAll() {
		
		return daos.getAdao().findAll();
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Admin check(Admin admin) {
		
		return daos.getAdao().check(admin);
	}

}
