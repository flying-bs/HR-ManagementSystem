package com.biz;
import java.util.*;
import com.po.*;
public interface IEmpBiz {
	public boolean save(Emp emp);
	public boolean update(Emp emp);
	public boolean delById(Integer eid);
	public Emp findById(Integer eid);
	/**
	 * @param page 当前页数
	 * @param rows 每页记录数
	 * */
	public List<Emp> findAll(int page,int rows);
	public int findMaxPage(int rows);
	
}
