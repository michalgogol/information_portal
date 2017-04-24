
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>News</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/news.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="/resources/ss/demo.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/component.css" />

    <script src="/resources/js/modernizr.custom.js"></script>
</head>
<body>
<div class="container">

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/index">InfoSite</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/Account">Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <div><a href="/end"><span class="glyphicon glyphicon-log-in"></span> Wyloguj</a></div>
            </ul>
        </div>
    </nav>

    <section class="grid-wrap">
        <ul class="grid swipe-right" id="grid">
            <c:forEach items="${news}" var="theNews">

            <li><a href="#<c:out value='${theNews.article_id}'/>" data-toggle="modal"><img src="${theNews.img_Href}" ><h3> ${theNews.title}</h3></a></li>


            </c:forEach>
        </ul>
    </section>

    <!-- modal-->
    <c:forEach items="${news}" var="theNews">
        <div class="modal fade" id=${theNews.article_id} role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">${theNews.title}</h4>
                    </div>
                    <div class="modal-body" style="display: block">
                        <iframe id="<c:out value='${theNews.title}'/>" src="/newcontent?url=<c:out value='${theNews.article_href}'/>" height="500" width="700"  ></iframe>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>


</div>

<script>



</script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/resources/js/masonry.pkgd.min.js"></script>
<script src="/resources/js/imagesloaded.pkgd.min.js"></script>
<script src="/resources/js/classie.js"></script>
<script src="/resources/js/colorfinder-1.1.js"></script>
<script src="/resources/js/gridScrollFx.js"></script>
<script>
    new GridScrollFx( document.getElementById( 'grid' ), {
        viewportFactor : 0.4
    } );
</script>
</body>
</html>