<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/view/common/tagPage.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
</head>
<body>


<h2>
   Hello NikoBelic
</h2>

<br>

<a href="/userController/showAllUsers" target="_blank">查询用户信息并跳转到一个JSP页面</a>

<form action="/userController/addUser">
    姓名:<input name="name" type="text"/>
    年龄:<input name="age" type="text">
    <input type="submit" value="提交">
</form>
<br>


</body>
</html>
