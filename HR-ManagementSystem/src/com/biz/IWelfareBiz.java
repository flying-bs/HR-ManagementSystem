package com.biz;
import java.util.*;
import com.po.*;
public interface IWelfareBiz {
	public boolean save(Welfare wf);
	public List<Welfare> findAll();
}
