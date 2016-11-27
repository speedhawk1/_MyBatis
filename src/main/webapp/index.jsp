<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/27
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/inc.jsp"%>
<html>
<head>
    <title>index page</title>
</head>
<body>
<h1>index page</h1>
<form action="${ctx}/user" method="post">
    <input type="hidden" name="action" value="login">
    <input type="text" name="username" placeholder="USERNAME" value="tester"><br>
    <input type="password" name="password" placeholder="PASSWORD" value="123"><br>
    <input type="submit" value="LOGIN">
</form>
${requestScope.message}
<hr>
<a href="signup.jsp">SIGN UP</a>
</body>
</html>
