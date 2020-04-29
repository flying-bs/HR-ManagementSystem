##创建数据库
CREATE DATABASE empdb;
##
USE empdb
##部门表
CREATE TABLE dep(
depid INT PRIMARY KEY AUTO_INCREMENT,##主键自增
depname VARCHAR(50) NOT NULL
);
##福利表
CREATE TABLE welfare(
wid INT PRIMARY KEY AUTO_INCREMENT,
wname Varchar(50) NOT NULL
);
##员工表
CREATE TABLE emp(
eid INT PRIMARY KEY AUTO_INCREMENT,
ename Varchar (50) NOT NULL,
sex Varchar(4) DEFAULT '男',
address Varchar(120),
birthday DATE,
photo Varchar(50),
depid INT NOT NULL,
CONSTRAINT fk_depid FOREIGN KEY(depid) REFERENCES dep(depid)
);
##薪资表
CREATE TABLE salary(
sid INT PRIMARY KEY AUTO_INCREMENT,
  emoney FLOAT CHECK (emoney>=1200),
  eid INT NOT NULL UNIQUE,
  CONSTRAINT fk_salary_eid FOREIGN KEY (eid)
  REFERENCES emp(eid)   
);
##员工福利关系表
CREATE TABLE empwelfare(
  ewid INT PRIMARY KEY AUTO_INCREMENT,
  eid INT NOT NULL,
  wid INT NOT NULL,
  CONSTRAINT fk_ew_eid FOREIGN KEY (eid)
  REFERENCES emp(eid) ,
  CONSTRAINT fk_ew_wid FOREIGN KEY (wid)
  REFERENCES welfare(wid) 
);

#创建用户表

CREATE TABLE Admin(
aid INT  PRIMARY KEY AUTO_INCREMENT,
aname VARCHAR(50) NOT NULL,
passwd VARCHAR(20) NOT NULL,
levels INT NOT NULL
);

##插入初始化数据
##部门数据
INSERT INTO dep(depname) VALUES('技术部');
INSERT INTO dep(depname) VALUES('财务部');
INSERT INTO dep(depname) VALUES('市场部');
INSERT INTO dep(depname) VALUES('行政部');
INSERT INTO dep(depname) VALUES('人事部');

##福利数据
INSERT INTO welfare(wname)VALUES('三金');
INSERT INTO welfare(wname) VALUES('误餐费');
INSERT INTO welfare(wname) VALUES('电话费');
INSERT INTO welfare(wname) VALUES('住房补助');
INSERT INTO welfare(wname) VALUES('降温费');
INSERT INTO welfare(wname) VALUES('取暖费');
INSERT INTO welfare(wname) VALUES('交通补助');

INSERT INTO Admin (aname,passwd,levels) VALUES('admin','123456',1);
INSERT INTO Admin (aname,passwd,levels) VALUES('employee','123456',0);

##建立视图 在列表页面显示员工部门名称
CREATE VIEW vwemp
AS
SELECT e.*,d.depname FROM emp e,dep d WHERE e.depid=d.depid;


SELECT * FROM dep;
SELECT * FROM welfare;

SELECT * FROM emp;
SELECT * FROM vwemp;
SELECT * FROM salary;
SELECT * FROM empwelfare;