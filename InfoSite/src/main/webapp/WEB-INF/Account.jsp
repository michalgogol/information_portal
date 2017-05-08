<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/account.css">
    <meta charset="utf-8">
    <title>InfoPortal</title>


</head>
<body>


<div class="crossfade">
    <figure></figure>
    <figure></figure>
    <figure></figure>
    <figure></figure>
    <figure></figure>
</div>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">InfoSite</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/end"><span class="glyphicon glyphicon-log-in"></span><button type="button" class="btn-link" data-toggle="modal" data-target="#register">Wyloguj</button></a></li>
        </ul>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">

        </div>
        <div class="col-sm-8 text-left">


<div class="img1">
    <section id="sec">

        <div class="test">

            <a href="News" style="text-decoration: none">  <div id="circle1" class="circle" onclick="category('Sport')"><p>Sport</p></div></a>
            <a href="News" style="text-decoration: none">  <div id="circle2" class="circle" onclick="category('Culture')"><p>Kultura</p></div></a>
            <a href="News" style="text-decoration: none"><div id="circle3" class="circle" onclick="category('Politic')"><p>Polityka</p></div></a>
            <a href="News" style="text-decoration: none"><div id="circle4" class="circle" onclick="category('Business')"><p>Biznes</p></div></a>
            <a href="News" style="text-decoration: none"><div id="circle5" class="circle" onclick="category('Science')"><p>Nauka</p></div></a>


        </div>
    </section>
</div>
            <div class="col-sm-2 sidenav">

            </div>
        </div>
    </div>
</div>

<script>
    $('#circle1').hide();
    $('#circle2').hide();
    $('#circle3').hide();
    $('#circle4').hide();
    $('#circle5').hide();

    function category(cat) {

        document.cookie = "category="+cat;
    }

    var Sport = '<c:out value="${Sport}"/>';
    var Culture = '<c:out value="${Culture}"/>';
    var Politic = '<c:out value="${Politic}"/>';
    var Science = '<c:out value="${Science}"/>';
    var Business = '<c:out value="${Business}"/>';
    if(Sport == "true")
        $('#circle1').show();

    if(Culture == "true")
        $('#circle2').show();

    if(Politic == "true")
        $('#circle3').show();

    if(Business == "true")
        $('#circle4').show();

    if(Science == "true")
        $('#circle5').show();
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
