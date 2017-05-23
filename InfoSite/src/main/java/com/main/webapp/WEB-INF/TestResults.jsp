<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Results</title>
</head>
<body>


<div class="container">

    <table class="table">
        <thead>
        <tr>
            <th>Title</th>
            <th>Article href</th>
            <th>Article Content</th>
            <th>Article Header </th>
            <th>Image Href</th>
        </tr>
        </thead>
        <tbody>
<c:forEach items="${testResults}" var="results">
        <tr>
            <td>${results.title}</td>
            <td>${results.article_href}</td>
            <td>${results.article_content}</td>
            <td>${results.article_header}</td>
            <td>${results.img_Href}</td>
        </tr>
</c:forEach>
        </tbody>
    </table>
</div>




<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
