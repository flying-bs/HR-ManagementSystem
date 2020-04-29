package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Salary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "salary", catalog = "empdb", uniqueConstraints = @UniqueConstraint(columnNames = "eid"))
public class Salary implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Emp emp;
	private Float emoney;

	// Constructors

	/** default constructor */
	public Salary() {
	}

	/** minimal constructor */
	public Salary(Emp emp) {
		this.emp = emp;
	}

	/** full constructor */
	public Salary(Emp emp, Float emoney) {
		this.emp = emp;
		this.emoney = emoney;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "sid", unique = true, nullable = false)
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eid", unique = true, nullable = false)
	public Emp getEmp() {
		return this.emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@Column(name = "emoney", precision = 12, scale = 0)
	public Float getEmoney() {
		return this.emoney;
	}

	public void setEmoney(Float emoney) {
		this.emoney = emoney;
	}

}