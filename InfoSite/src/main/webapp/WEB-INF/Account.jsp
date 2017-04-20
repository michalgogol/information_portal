<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/acc.css">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Categories</title>
</head>
<body>

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

<main>
    <section>
        <article>
            <h1>Panel Title</h1>
        </article>
    </section>

    <section id="sport" onclick="category('Sport')">
        <article onclick="category('Sport')">
            <h1>Sport</h1>
        </article>
    </section>
    <section id="culture" onclick="category('culture')">
        <article>
            <h1>Kultura</h1>
        </article>
    </section>
    <section id="politic" onclick="category('politic')">
        <article>
            <h1>Polityka</h1>
        </article>
    </section>
    <section id="business" onclick="category('business')">
        <article>
            <h1>Biznes</h1>
        </article>
    </section>
    <section id="science" onclick="category('science')">
        <article>
            <h1>Nauka</h1>
        </article>
    </section>
    <section>
        <article>
            <h2>Panel Title</h2>
        </article>
    </section>
</main>


<script>
    $('#sport').hide();
    $('#culture').hide();
    $('#politic').hide();
    $('#science').hide();
    $('#business').hide();

    function category(cat) {

        document.cookie = "category="+cat;
    }

    var Sport = '<c:out value="${Sport}"/>';
    var Culture = '<c:out value="${Culture}"/>';
    var Politic = '<c:out value="${Politic}"/>';
    var Science = '<c:out value="${Science}"/>';
    var Business = '<c:out value="${Business}"/>';
    if(Sport == "true")
        $('#sport').show();

    if(Culture == "true")
        $('#culture').show();

    if(Politic == "true")
        $('#politic').show();

    if(Business == "true")
        $('#science').show();

    if(Science == "true")
        $('#business').show();
</script>

<script>
    $(document).ready(function() {
        $("#sport").click(function() {
            window.location.href="newsloader"

        });
        $("#culture").click(function() {
            window.location.href="newsloader"

        });
        $("#business").click(function() {
            window.location.href="newsloader"

        });
        $("#politic").click(function() {
            window.location.href="newsloader"

        });
        $("#science").click(function() {
            window.location.href="newsloader"

        });
    });
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
