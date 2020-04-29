package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin", catalog = "empdb")
public class Admin implements java.io.Serializable {

	// Fields

	private Integer aid;
	private String aname;
	private String passwd;
	private Integer levels;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String aname, String passwd) {
		this.aname = aname;
		this.passwd = passwd;
	}

	/** full constructor */
	public Admin(String aname, String passwd, Integer levels) {
		this.aname = aname;
		this.passwd = passwd;
		this.levels = levels;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "aid", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Column(name = "aname", nullable = false, length = 50)
	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	@Column(name = "passwd", nullable = false, length = 50)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "levels")
	public Integer getLevels() {
		return this.levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

}