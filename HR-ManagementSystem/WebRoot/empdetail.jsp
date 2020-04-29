<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<s:if test="#session.demp==null">
	<c:redirect url="findAll_Emp.action"></c:redirect>
</s:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工详细信息</title>
</head>

<body>
<table width="500" border="1" align="center" cellpadding="1" cellspacing="0">
  <tr>
    <td colspan="3" align="center" bgcolor="#CCFFCC">员工详细信息</td>
  </tr>
  <tr>
    <td width="51">姓名</td>
    <td width="344">${demp.ename}</td>
    <td width="91" rowspan="7" align="left" valign="top"><img src="uppic/${demp.photo}" width="91" height="160" alt="" /></td>
  </tr>
  <tr>
    <td>性别</td>
    <td>${demp.sex}</td>
  </tr>
  <tr>
    <td>地址</td>
    <td>${demp.address}</td>
  </tr>
  <tr>
    <td>生日</td>
    <td><s:date name="#session.demp.birthday" format="yyyy-MM-dd"/></td>
  </tr>
  <tr>
    <td>照片</td>
    <td>${demp.photo}</td>
  </tr>
  <tr>
    <td>部门</td>
    <td>${demp.dep.depname}</td>
  </tr>
  <tr>
    <td>薪资</td>
    <td>${demp.emoney}</td>
  </tr>
  <tr>
    <td>福利</td>
    <td colspan="2">
    	<s:iterator value="#session.demp.empwelfares" var="ewf">
    		${ewf.welfare.wname}<br>
    	</s:iterator>
    </td>
  </tr>
  <tr>
    <td colspan="3" align="center" bgcolor="#CCFFCC">&nbsp;</td>
  </tr>
</table>
<p align="center"><a href="findAll_Emp.action">查看列表</a></p>
</body>
</html>