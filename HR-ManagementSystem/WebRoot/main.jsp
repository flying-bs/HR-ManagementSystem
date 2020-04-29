<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${ad==null}">
	<c:redirect url="login.jsp?error=1"></c:redirect>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人力资源管理系统</title>
</head>

<frameset rows="*" cols="185,*" framespacing="5" border="5">
  <frame src="left.jsp" name="leftFrame" scrolling="yes" noresize="noresize" id="leftFrame" title="leftFrame" />
  <frame src="welcome.jsp" name="mainFrame" scrolling="yes" id="mainFrame" title="mainFrame" />
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
