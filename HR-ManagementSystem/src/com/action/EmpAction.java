package com.action;
import com.po.*;
import com.service.BizService;
import com.bean.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
@Controller	
@Namespace("/")
@ParentPackage("struts-default")
public class EmpAction implements IEmpAction {
	private Emp emp;
	private Integer eid;
	
	private int page;
	private int rows;
	
	private String path;
	@Resource(name="BizService")
	private BizService bizs;
	
	
	public BizService getBizs() {
		return bizs;
	}

	public void setBizs(BizService bizs) {
		this.bizs = bizs;
	}

	
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	@Action(value="save_Emp",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String save() {
		/************处理文件上传*****************/
		//获取服务器的物理路径
		String rpath=ServletActionContext.getRequest().getRealPath("/");
		
		//判断是否存在文件上传
		if(emp.getPic()!=null&&emp.getPic().length()>0){
			//获取文件名称
			String fname=emp.getPicFileName();
			
			//判断是否存在后缀
			if(fname.lastIndexOf(".")!=-1){
				//获取后缀名称
				String ext=fname.substring(fname.lastIndexOf("."));
				//判断文件后缀是否为jpg或者png
				if(ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".png")){
					//创建新的文件名称
					String newfname=new Date().getTime()+ext;
					//创建文件对象，指定上传文件存放的路径
					File destFile=new File(rpath+"/uppic/"+newfname);
					
					try {
						//开始上传
						FileUtils.copyFile(emp.getPic(), destFile);
						emp.setPhoto(newfname);//保存新的文件名称到数据库
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		
		/*********************************************************/
		boolean flag=bizs.getEbiz().save(emp);
		if(flag){
			path="findAll_Emp.action";
			return "ok";
		}
		return null;
	}
	@Action(value="update_Emp",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String update() {
		/************处理文件上传*****************/
		//获取原来的员工照片(emp.getEid()是更新界面的隐藏域传递上来的编号)
		String oldfname=bizs.getEbiz().findById(emp.getEid()).getPhoto();
		//获取服务器的物理路径
		String rpath=ServletActionContext.getRequest().getRealPath("/");
		
		//判断是否存在文件上传
		if(emp.getPic()!=null&&emp.getPic().length()>0){
			//获取文件名称
			String fname=emp.getPicFileName();
			
			//判断是否存在后缀
			if(fname.lastIndexOf(".")!=-1){
				//获取后缀名称
				String ext=fname.substring(fname.lastIndexOf("."));
				//判断文件后缀是否为jpg或者png
				if(ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".png")){
					//创建新的文件名称
					String newfname=new Date().getTime()+ext;
					//创建文件对象，指定上传文件存放的路径
					File destFile=new File(rpath+"/uppic/"+newfname);
					
					try {
						//开始上传
						FileUtils.copyFile(emp.getPic(), destFile);
						emp.setPhoto(newfname);//保存新的文件名称到数据库
						
						//删除原来的员工照片
						File oldfile=new File(rpath+"/uppic/"+oldfname);
						if(oldfile.exists()&&!oldfname.equals("default.jpg")){
							oldfile.delete();//删除
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}else{
			emp.setPhoto(oldfname);//不修改照片时，继续保留原来的照片
		}
		
		/*********************************************************/
		
		boolean flag=bizs.getEbiz().update(emp);
		if(flag){
			path="findAll_Emp.action";
			return "ok";
		}
		return null;
	}
	@Action(value="delById_Emp",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String delById() {
		//获取原来的员工照片(emp.getEid()是更新界面的隐藏域传递上来的编号)			
		String oldfname=bizs.getEbiz().findById(eid).getPhoto();
		//获取服务器的物理路径
		String rpath=ServletActionContext.getRequest().getRealPath("/");
				
		
		boolean flag=bizs.getEbiz().delById(eid);
		if(flag){
			//删除原来的员工照片
			File oldfile=new File(rpath+"/uppic/"+oldfname);
			if(oldfile.exists()&&!oldfname.equals("default.jpg")){
				oldfile.delete();//删除员工照片
			}
			
			path="findAll_Emp.action";
			return "ok";
		}
		return null;
	}
	@Action(value="findById_Emp",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String findById() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		Emp oldemp=bizs.getEbiz().findById(eid);
		session.setAttribute("oldemp",oldemp);
		path="empupdate.jsp";
	
		return "ok";
	}
	@Action(value="findDetail_Emp",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String findDetail() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		Emp demp=bizs.getEbiz().findById(eid);
		session.setAttribute("demp",demp);
		path="empdetail.jsp";
	
		return "ok";
	}
	@Action(value="findAll_Emp",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String findAll() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		/*******************为修改界面准备数据****************************/
		List<Dep> lsdep=bizs.getDepbiz().findAll();//下拉列表框准备
		List<Welfare> lswf=bizs.getWbiz().findAll();//复选框准备数据
		session.setAttribute("lsdep", lsdep);
		session.setAttribute("lswf", lswf);
		/***********************************************************/
		
		//获取分页的实体对象
		PageBean pb=(PageBean) session.getAttribute("pb");
		pb=pb==null?new PageBean():pb;
		
		//判断传递的当前页数和每页记录数
		page=page==0?pb.getPage():page;
		rows=rows==0?pb.getRows():rows;
		
		//获取最大页数
		int maxpage=bizs.getEbiz().findMaxPage(rows);
		if(page>maxpage)page=maxpage;
		
		//获取当前页记录的集合
		List<Emp> lsemp=bizs.getEbiz().findAll(page, rows);
		
		//封装到分页实体中
		pb.setMaxpage(maxpage);
		pb.setPage(page);
		pb.setRows(rows);
		pb.setPagelist(lsemp);
		
		session.setAttribute("pb",pb);
		
		path="emplist.jsp";
		
		return "ok";
	}
	@Action(value="doinit_Emp",results={
			@Result(name="ok",location="${path}",type="redirect")
	})
	public String doinit() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		List<Dep> lsdep=bizs.getDepbiz().findAll();//下拉列表框准备
		List<Welfare> lswf=bizs.getWbiz().findAll();//复选框准备数据
		session.setAttribute("lsdep", lsdep);
		session.setAttribute("lswf", lswf);
		path="empadd.jsp";
		return "ok";
	}

}
