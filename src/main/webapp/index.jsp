<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <link rel="stylesheet" href="style/style.css">
</head>

<body>
<h1>Task Working!</h1>
<hr>
<h3><a href='<s:url action="generateData" />'>Generate data (takes time) and show table</a></h3>
<hr>
<h3><a href="success.jsp">Show table</a></h3>
</body>
</html>
