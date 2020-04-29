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

/**
 * Welfare entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "welfare", catalog = "empdb")
public class Welfare implements java.io.Serializable {

	// Fields

	private Integer wid;
	private String wname;
	private Set<Empwelfare> empwelfares = new HashSet<Empwelfare>(0);

	// Constructors

	/** default constructor */
	public Welfare() {
	}

	/** minimal constructor */
	public Welfare(String wname) {
		this.wname = wname;
	}

	/** full constructor */
	public Welfare(String wname, Set<Empwelfare> empwelfares) {
		this.wname = wname;
		this.empwelfares = empwelfares;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "wid", unique = true, nullable = false)
	public Integer getWid() {
		return this.wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	@Column(name = "wname", nullable = false)
	public String getWname() {
		return this.wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "welfare")
	public Set<Empwelfare> getEmpwelfares() {
		return this.empwelfares;
	}

	public void setEmpwelfares(Set<Empwelfare> empwelfares) {
		this.empwelfares = empwelfares;
	}

	@Override
	public String toString() {
		return "Welfare [empwelfares=" + empwelfares + ", wid=" + wid
				+ ", wname=" + wname + "]";
	}

}