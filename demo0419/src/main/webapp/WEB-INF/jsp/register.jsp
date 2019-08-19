<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/19
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> hello world</h1>
    <form action="${ctx}/test/insertUser" method="post">
       用户名： <input type="text" name="userName" value="">
       密  码： <input type="password" name="password" value="">
       年  龄： <input type="number" name="age" value="">
        <input type="submit" value="添加">
    </form>
</body>
</html>
