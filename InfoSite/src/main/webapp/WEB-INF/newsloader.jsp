
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/loader.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/loader2.css">
    <title>loader</title>

</head>
<body>

<div class="loader-wrapper" id="loader-1">
    <div id="loader"></div>
</div>

<script>
    loaders = document.getElementsByClassName('loader-wrapper');
    loaders[0].style.display = "inherit";

    $(document).ready(function() {
        window.location.href="News"

    });
</script>
</body>
</html>
