package com.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Dep entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dep", catalog = "empdb")
public class Dep implements java.io.Serializable {

	// Fields

	private Integer depid;
	private String depname;
	private int  enums;
	private Set<Emp> emps = new HashSet<Emp>(0);

	// Constructors

	/** default constructor */
	public Dep() {
	}

	/** minimal constructor */
	public Dep(String depname) {
		this.depname = depname;
	}

	/** full constructor */
	public Dep(String depname, Set<Emp> emps) {
		this.depname = depname;
		this.emps = emps;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "depid", unique = true, nullable = false)
	public Integer getDepid() {
		return this.depid;
	}

	public void setDepid(Integer depid) {
		this.depid = depid;
	}

	@Column(name = "depname", nullable = false, length = 50)
	public String getDepname() {
		return this.depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dep")
	public Set<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	@Transient
	public int getEnums() {
		return enums;
	}

	public void setEnums(int enums) {
		this.enums = enums;
	}

}