package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.po.*;
import com.service.DaoService;
import com.biz.IWelfareBiz;
@Service("WelfareBiz")
@Transactional
public class WelfareBiz implements IWelfareBiz{
	@Resource(name="DaoService")
	private DaoService daos;

	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Welfare> findAll() {
		return daos.getWdao().findAll();
	}
	public boolean save(Welfare wf) {
		try {
			daos.getWdao().save(wf);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
