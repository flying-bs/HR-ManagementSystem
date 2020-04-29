package com.biz;
import com.po.*;
import java.util.*;
public interface IAdminBiz {
	public boolean save(Admin admin);
	public boolean update(Admin admin);
	public boolean delById(Integer aid);
	public Admin findById(Integer aid);
	public List<Admin> findAll();
	public Admin check(Admin admin);
	
}
