<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<style type="text/css">
#apDiv1 {
	position: absolute;
	width: 200px;
	height: 115px;
	z-index: 1;
	left: 258px;
	top: 224px;
}
#apDiv2 {
	position: absolute;
	width: 763px;
	height: 115px;
	z-index: 2;
	left: 44px;
	top: 85px;
}
</style>
</head>

<body>

  <div id="apDiv1">
    <form id="form1" name="form1" method="post" action="check_Admin.action">
      <table width="350" border="1" cellpadding="1" cellspacing="0">
        <tr>
          <td colspan="2" align="center" bgcolor="#CCFF99">用户登录</td>
        </tr>
        <tr>
          <td>账号:</td>
          <td><label for="admin.aname"></label>
          <input type="text" name="admin.aname" id="admin.aname" /></td>
        </tr>
        <tr>
          <td>密码:</td>
          <td><label for="admin.passwd"></label>
          <input type="password" name="admin.passwd" id="admin.passwd" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center" bgcolor="#CCFF99"><input type="submit" name="button" id="button" value="提交" />
          <input type="reset" name="button2" id="button2" value="重置" /></td>
        </tr>
      </table>
    </form>
    <p>
    	<%
    		String error=request.getParameter("error");
        	if(error!=null&&!error.equals("")){
        		
        		  
    	%>
    		<font color="red">用户名或者密码错误</font>
    	<%
    	}
    	%>
    </p>
  </div>
  <div id="apDiv2">
    <table width="550" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><p align="center"><font color="#0000FF" size="36px">人力资源管理系统</font></p></td>
      </tr>
    </table>
  </div>
	
</body>
</html>