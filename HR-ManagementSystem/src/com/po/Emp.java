package com.po;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Emp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "emp", catalog = "empdb")
public class Emp implements java.io.Serializable {

	// Fields

	private Integer eid;
	private Dep dep;
	private String ename;
	private String sex;
	private String address;
	private Date birthday;
	private String photo;
	private Set<Salary> salaries = new HashSet<Salary>(0);
	private Set<Empwelfare> empwelfares = new HashSet<Empwelfare>(0);

	/*** 与界面关联属性 ************/
	private String sdate;
	// 与界面关联属性
	// 1与薪资关联属性
	private float emoney;

	// 2于福利关联属性
	private Integer[] wids;// 接受复选框的数据

	// 3与照片上传关联的属性
	private File pic;
	private String picContentType;
	private String picFileName;

	// Constructors

	/** default constructor */
	public Emp() {
	}

	/** minimal constructor */
	public Emp(Dep dep, String ename) {
		this.dep = dep;
		this.ename = ename;
	}

	/** full constructor */
	public Emp(Dep dep, String ename, String sex, String address,
			Date birthday, String photo, Set<Salary> salaries,
			Set<Empwelfare> empwelfares) {
		this.dep = dep;
		this.ename = ename;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.photo = photo;
		this.salaries = salaries;
		this.empwelfares = empwelfares;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "eid", unique = true, nullable = false)
	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depid", nullable = false)
	public Dep getDep() {
		return this.dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	@Column(name = "ename", nullable = false)
	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "sex")
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	public Set<Salary> getSalaries() {
		return this.salaries;
	}

	public void setSalaries(Set<Salary> salaries) {
		this.salaries = salaries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	public Set<Empwelfare> getEmpwelfares() {
		return this.empwelfares;
	}

	public void setEmpwelfares(Set<Empwelfare> empwelfares) {
		this.empwelfares = empwelfares;
	}
	@Transient
	public String getSdate() {
		if(birthday!=null){
			sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		}
		return sdate;
	}

	public void setSdate(String sdate) {
		if(sdate!=null&&!sdate.trim().equals("")){
			try {
				birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.sdate = sdate;
	}
	
	@Transient
	public float getEmoney() {
		return emoney;
	}

	public void setEmoney(float emoney) {
		this.emoney = emoney;
	}
	@Transient
	public Integer[] getWids() {
		return wids;
	}

	public void setWids(Integer[] wids) {
		this.wids = wids;
	}
	@Transient
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}
	@Transient
	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	@Transient
	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

}