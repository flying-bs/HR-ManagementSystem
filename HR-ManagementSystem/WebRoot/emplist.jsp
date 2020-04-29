<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<s:if test="#session.pb==null">
	<c:redirect url="findAll_Emp.action"></c:redirect>
</s:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工列表</title>
<script>
	/**
	更改每页记录数
	*/
	function doChangeRows(){
		var rows=document.form1.rows.value;
		if(isNaN(rows)){
			alert('请输入正确的数字！');
			document.form1.rows.value=${pb.rows};
			return;
		}
		window.location="findAll_Emp.action?rows="+rows;
	}
	/**
	更改当前页数
	*/
	function doChangePage(){
		var page=document.form1.page.value;
		if(isNaN(page)){
			alert('请输入正确的数字！');
			document.form1.rows.value=${pb.page};
			return;
		}
		window.location="findAll_Emp.action?page="+page;
	}
	
	//删除提示
	function dodel(){
		return window.confirm('是否真的删除!');
	}
	
	
	
	


</script>
</head>

<body>
<p align="center">员工列表</p>
<hr align="center" width="770" />
<table width="770" border="1" align="center" cellpadding="1" cellspacing="0">
  <tr align="center" bgcolor="#CCFF99">
    <td width="67">编号</td>
    <td width="117">姓名</td>
    <td width="54">性别</td>
    <td width="154">地址</td>
    <td width="106">生日</td>
    <td width="95">部门</td>
    <td width="147">操作</td>
  </tr>
  <s:iterator value="#session.pb.pagelist" var="emp">
  <tr align="center">
    <td>${emp.eid}</td>
    <td><a href="findDetail_Emp.action?eid=${emp.eid}">${emp.ename}</a></td>
    <td>${emp.sex}</td>
    <td>${emp.address}</td>
    <td>${emp.sdate }</td>
    <td>${emp.dep.depname}</td>
    <td><a href="delById_Emp.action?eid=${emp.eid}" onclick="return dodel()">删除</a> <a href="findById_Emp.action?eid=${emp.eid}">修改</a></td>
  </tr>
  </s:iterator>
</table>
<form id="form1" name="form1" method="post" action="">
  <table width="770" border="1" align="center" cellpadding="1" cellspacing="0">
    <tr align="center" bgcolor="#CCFF99">
      <td width="52">
      	<s:if test="#session.pb.page>1">
      		<a href="findAll_Emp.action?page=1">首页</a>
      	</s:if>
      	<s:else>
      		首页
      	</s:else>	
      </td>
      <td width="52">
      	<s:if test="#session.pb.page>1">
      		<a href="findAll_Emp.action?page=${pb.page-1}">上页</a>
      	</s:if>
      	<s:else>
      		上页
      	</s:else>
      </td>
      <td width="52">
      	<s:if test="#session.pb.page<#session.pb.maxpage">
      		<a href="findAll_Emp.action?page=${pb.page+1}">下页</a>
      	</s:if>
      	<s:else>
      		下页
      	</s:else>	
      </td>		
      <td width="52">
      	<s:if test="#session.pb.page<#session.pb.maxpage">
      		<a href="findAll_Emp.action?page=${pb.maxpage}">末页</a>
      	</s:if>
      	<s:else>
      		末页
      	</s:else>	
      </td>
      <td width="224">每页
        <label for="rows"></label>
      <input name="rows" value="${pb.rows }" type="text" id="rows" size="2" />
      条记录
      <input type="button" name="button" id="button" value="确定" onclick="doChangeRows()"/></td>
      <td width="237">跳转到第
        <label for="page"></label>
        <input name="page" value="${pb.page }" type="text" id="page" size="2" />
        页
<input type="button" name="button2" id="button2" value="确定" onclick="doChangePage()" /></td>
      <td width="71">${pb.page}/${pb.maxpage}页</td>
    </tr>
  </table>
 
</form>
 <hr align="center" width="770" />
 <p align="center"><a href="empadd.jsp">返回添加</a></p>
</body>
</html>