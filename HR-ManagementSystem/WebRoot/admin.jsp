<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<s:if test="#session.lsad==null">
	<c:redirect url="findAll_Admin.action"></c:redirect>
</s:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员列表</title>
</head>

<body>
<p align="center">管理员列表</p>
<table width="550" border="1" align="center" cellpadding="1" cellspacing="0">
  <tr align="center" bgcolor="#CCFF99">
    <td width="92">编号</td>
    <td width="138">姓名</td>
    <td width="134">密码</td>
    <td width="82">级别</td>
    <td width="82">操作</td>
  </tr>
  <s:iterator value="#session.lsad" var="ad">
  <tr align="center">
    <td>${ad.aid}</td>
    <td>${ad.aname}</td>
    <td>${ad.passwd}</td>
    <td>
    	<s:if test="#ad.levels==0">
    		员工
    	</s:if>
    	<s:else>
    		经理
    	</s:else>
    </td>
    <td><a href="delById_Admin.action?aid=${ad.aid}">删除</a> <a href="findById_Admin.action?aid=${ad.aid}">修改</a></td>
  </tr>
  </s:iterator>
</table>
<hr width="550" />
<form id="form1" name="form1" method="post" action="save_Admin.action">
  <table width="400" border="1" align="center" cellpadding="1" cellspacing="0">
    <tr>
      <td colspan="2" align="center" bgcolor="#CCFF99">管理员添加</td>
    </tr>
    <tr>
      <td width="91">账号</td>
      <td width="299"><s:textfield name="admin.aname"/></td>
    </tr>
    <tr>
      <td>密码</td>
      <td><s:password name="admin.passwd"/></td>
    </tr>
    <tr>
      <td>级别</td>
      <td><s:radio name="admin.levels" list="#{0:'员工',1:'经理'}" listKey="key" listValue="value" value="0"/></td>
    </tr>
    <tr>
      <td colspan="2" align="center" bgcolor="#CCFF99"><input type="submit" name="button" id="button" value="保存" /></td>
    </tr>
  </table>
</form>
</body>
</html>