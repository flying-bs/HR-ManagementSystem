package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Empwelfare entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "empwelfare", catalog = "empdb")
public class Empwelfare implements java.io.Serializable {

	// Fields

	private Integer ewid;
	private Emp emp;
	private Welfare welfare;

	// Constructors

	/** default constructor */
	public Empwelfare() {
	}

	/** full constructor */
	public Empwelfare(Emp emp, Welfare welfare) {
		this.emp = emp;
		this.welfare = welfare;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ewid", unique = true, nullable = false)
	public Integer getEwid() {
		return this.ewid;
	}

	public void setEwid(Integer ewid) {
		this.ewid = ewid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eid", nullable = false)
	public Emp getEmp() {
		return this.emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wid", nullable = false)
	public Welfare getWelfare() {
		return this.welfare;
	}

	public void setWelfare(Welfare welfare) {
		this.welfare = welfare;
	}

}