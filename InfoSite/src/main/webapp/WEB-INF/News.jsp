
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/news.css">

    <title>Title</title>

</head>
<body>

<div id="loader"></div>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">InfoSite</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/Account">Home</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/end"><span class="glyphicon glyphicon-log-in"></span> Wyloguj</li>
        </ul>
    </div>
</nav>


<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">

        </div>
        <div class="col-sm-8 text-left">

        <c:forEach items="${news}" var="theNews">

    <div class="panel panel-default">
        <a data-toggle="collapse" data-parent="#accordion" href= "#<c:out value='${theNews.article_id}'/>" >
        <div class="panel-heading" style="height: 250px; background: url('${theNews.img_Href}'); background-size: cover;background-position: center; ">


            <h2 class="panel-title">
              ${theNews.title}
            </h2>

        </div></a>
        <div id=${theNews.article_id} class="panel-collapse collapse">
            <div class="panel-body">${theNews.article_content}</div>
        </div>
    </div>

</c:forEach>
        <div class="col-sm-2 sidenav">

        </div>
    </div>
</div>
<script>
    var loader = document.getElementById("loader");
    window.addEventListener('load',function () {
        loader.style.display = 'none';
    })
</script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
