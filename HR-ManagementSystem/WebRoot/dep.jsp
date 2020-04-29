<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<s:if test="#session.lsdep==null">
	<c:redirect url="findAll_Dep.action?t=<%=new Date().getTime() %>"></c:redirect>
</s:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
<p align="center">部门列表</p>
<table width="500" border="1" align="center" cellpadding="1" cellspacing="0">
  <tr align="center" bgcolor="#CCFF99">
    <td>编号</td>
    <td>部门名称</td>
    <td>员工数量</td>
  </tr>
  <s:iterator value="#session.lsdep" var="dep">
  <tr align="center">
    <td>${dep.depid }</td>
    <td>${dep.depname }</td>
    <td><s:property value="#dep.enums"/></td>
  </tr>
  </s:iterator>
</table>
<hr width="550" />
<form id="form1" name="form1" method="post" action="save_Dep.action">
  <table width="400" border="1" align="center" cellpadding="1" cellspacing="0">
    <tr>
      <td colspan="2" align="center" bgcolor="#CCFF99">部门添加</td>
    </tr>
    <tr>
      <td width="91">部门名称 </td>
      <td width="299">
      <input type="text" name="dep.depname" id="depname" /></td>
    </tr>
    <tr>
      <td colspan="2" align="center" bgcolor="#CCFF99"><input type="submit" name="button" id="button" value="确定" /></td>
    </tr>
  </table>
</form>
</body>
</html>