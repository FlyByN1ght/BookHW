<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Даниил
  Date: 16.01.2024
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File List</title>
</head>
<body>
<h2>File List</h2>
<ul>
    <%
        List<File> fileList = (List<File>)request.getAttribute("fileList");
        if (fileList != null) {
            for (File file : fileList) {
    %>
    <li>
        <%= file.getName() %>
        <a href="book?action=download&fileName=<%= file.getName() %>">Download</a>
    </li>
    <%
            }
        }
    %>
</ul>
</body>
</html>
