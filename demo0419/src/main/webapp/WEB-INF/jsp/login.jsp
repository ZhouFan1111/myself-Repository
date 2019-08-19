<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/19
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <style>
        body{
            background:url("${ctxStatic}/image/background.jpg") no-repeat;
            background-size:auto 130% ;
            background-position:center;
        }
        .form-signin-heading{ text-align: center;width:540px;margin:16% auto;font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#fff;}
        .form-signin-heading:before{
            content: " ";
            width:480px;
            height: 95px;
            display:block;
            margin-top:-130px;
            /* padding-right:5px;
            background:url("${ctxStatic}/images/logo2.png") no-repeat;*/
            background-size:260px auto ;
            background-position:center;
        }
        .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#f5dceba6;border:1px solid #e5e5e5;
            -webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
        .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#a53014;}
        .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
        .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:25px;}
        .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
        .header{height:80px;padding-top:5%;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
        label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
    </style>

</head>
<body>
    <%--<form action="${ctx}/test/login" method="post">--%>
        <%--用户名： <input type="text" placeholder="请输入用户名" name="userName" value=""><br/>--%>
        <%--密  码： <input type="password" placeholder="请输入密码" name="password" value="">--%>
        <%--<input type="submit" value="登录">--%>

        <h2 class="form-signin-heading"><span>周帆登录页面</span></h2>
        <form id="loginForm" class="form-signin" action="${ctx}/test/login" method="post">
            <label class="input-label" for="username">登录名</label><br/>
            <input type="text" id="username" name="username" class="input-block-level required" value="${username}"><br/>
            <label class="input-label" for="password">密码</label><br/>
            <input type="password" id="password" name="password" class="input-block-level required">
            <div class="form-group">
                <input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="btn btn-large btn-info" type="submit" value="注 册"/>
            </div>
        </form>

    <%--</form>--%>
    <button id="test1">测试jQuery</button>

    <script>
        var a = "${zfTest}";
        layer.alert(a);
        $(function () {
            $("#test1").click(function () {
                alert("测试成功");
            });
        });
    </script>
</body>
</html>
