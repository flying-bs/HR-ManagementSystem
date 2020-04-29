package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.po.Admin;

/**
 * A data access object (DAO) providing persistence and search support for Admin
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.po.Admin
 * @author MyEclipse Persistence Tools
 */
@Service("AdminDAO")
public class AdminDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(AdminDAO.class);
	// property constants
	public static final String ANAME = "aname";
	public static final String PASSWD = "passwd";
	public static final String LEVELS = "levels";

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(Admin transientInstance) {
		log.debug("saving Admin instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Admin persistentInstance) {
		log.debug("deleting Admin instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Admin findById(java.lang.Integer id) {
		log.debug("getting Admin instance with id: " + id);
		try {
			Admin instance = (Admin) getHibernateTemplate().get("com.po.Admin",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Admin> findByExample(Admin instance) {
		log.debug("finding Admin instance by example");
		try {
			List<Admin> results = getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Admin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Admin as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Admin> findByAname(Object aname) {
		return findByProperty(ANAME, aname);
	}

	public List<Admin> findByPasswd(Object passwd) {
		return findByProperty(PASSWD, passwd);
	}

	public List<Admin> findByLevels(Object levels) {
		return findByProperty(LEVELS, levels);
	}

	public List findAll() {
		log.debug("finding all Admin instances");
		try {
			String queryString = "from Admin";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Admin merge(Admin detachedInstance) {
		log.debug("merging Admin instance");
		try {
			Admin result = getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Admin instance) {
		log.debug("attaching dirty Admin instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Admin instance) {
		log.debug("attaching clean Admin instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdminDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AdminDAO) ctx.getBean("AdminDAO");
	}
	
	/**
	 * 验证管理员身份
	 * */
	public Admin check(Admin admin){
		String hql="from Admin where 1=1 and aname=? and passwd=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, admin.getAname());
		query.setParameter(1, admin.getPasswd());
		
		List<Admin> lsad=query.list();
		if(lsad!=null&&!lsad.isEmpty()){
			admin=lsad.get(0);
			return admin;
		}
		
		return null;
	}
}