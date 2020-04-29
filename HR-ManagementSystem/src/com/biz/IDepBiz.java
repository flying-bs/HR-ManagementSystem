package com.biz;
import java.util.*;
import com.po.*;
public interface IDepBiz {
	public boolean save(Dep dep);
	public List<Dep> findAll();
}
