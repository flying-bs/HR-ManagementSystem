package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biz.IDepBiz;
import com.po.*;
import com.service.DaoService;
@Service("DepBiz")
@Transactional
public class DepBiz implements IDepBiz {
	@Resource(name="DaoService")
	private DaoService daos;

	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Dep> findAll() {
		List<Dep> lsdep=daos.getDepdao().findAll();
		for (Dep dep : lsdep) {
			dep.setEnums(dep.getEmps().size());
		}
		return daos.getDepdao().findAll();
	}

	public boolean save(Dep dep) {
		try {
			daos.getDepdao().save(dep);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
