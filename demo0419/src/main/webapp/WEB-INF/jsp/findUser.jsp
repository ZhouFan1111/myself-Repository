<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/19
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> hello world</h1>
    <form action="${ctx}/test/findUserByName" method="post">
       请输入用户名： <input type="text" name="userName" value="">
        <input type="submit" value="查找">
    </form>
</body>
</html>
