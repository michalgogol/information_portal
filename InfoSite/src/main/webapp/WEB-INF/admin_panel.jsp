<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Title</title>

</head>
<body>




<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index">InfoSite</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Projects</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">

        </div>
        <div class="col-sm-8 text-left">


            <form method="POST" action="TestResults">

                Category:<br>
                <input list="categories" name="category">
                <datalist id="categories">
                    <option value="Sport">
                    <option value="Culture">
                    <option value="Politic">
                    <option value="Business">
                    <option value="Science">
                </datalist><br>

                Source domain:<br>
                <input type="text"  name="domain" value="" ><br>


                Title:<br>
                <input type="text" name="title"><br>


                Article Href:<br>
                <input type="text" name ="article_href"><br>


                Article Content:<br>
                <input type="text" name="article_content"><br>


                Article Header:<br>
                <input type="text"  name="article_header"><br>

                Image href:<br>
                <input type="text"  name="img_href"><br>

                <input type="checkbox" name="Save" value="YES"> Save <br>

                <input type="submit" value="Send">

            </form>


        </div>
        <div class="col-sm-2 sidenav">

        </div>
    </div>
</div>



<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
