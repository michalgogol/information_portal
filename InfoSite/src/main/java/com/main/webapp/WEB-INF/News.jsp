
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>


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
                        <div class="embed-responsive embed-responsive-16by9">
                            <iframe id="<c:out value='${theNews.title}'/>" src="/newcontent?url=<c:out value='${theNews.article_href}'/>" height="500" width="700"  ></iframe>
                        </div>

                            <form>
                           <div class="form-group">
                               <label>Komentarz: </label>
                               <textarea class="form-control" rows="4" id="comment<c:out value='${theNews.article_id}'/>" name="comment"></textarea>
                               <button type="button" class="btn btn-default"  onclick="ajaxFunction(<c:out value='${theNews.article_id}'/>)" >Wyślij</button>
                           </div>
                          </form>
        <c:forEach items="${theNews.comments}" var="comment">
            <div class="form-group">
                <textarea disabled class="form-control" rows="3" >${comment}</textarea>
            </div>

        </c:forEach>

                        </div>

                    <div class="modal-footer">
                        <div id="ajax<c:out value='${theNews.article_id}'/>"></div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>




</div>

<script>

function ajaxFunction(id) {

    var comment  = $("#comment"+id).val();
    var login = $.cookie('userLogin');

    var sendComment ={
        comment: comment,
        login: login,
        articleId: id
    };

    $.post("testajax", sendComment, function(responseText) {

        $("#ajax"+id).append(' <div class="form-group"> <textarea disabled class="form-control" rows="3" >' + responseText + '</textarea> </div>');
    });

}

</script>


<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/resources/js/masonry.pkgd.min.js"></script>
<script src="/resources/js/imagesloaded.pkgd.min.js"></script>
<script src="/resources/js/classie.js"></script>
<script src="/resources/js/colorfinder-1.1.js"></script>
<script src="/resources/js/gridScrollFx.js"></script>
    <script type="text/javascript" SRC="/resources/js/jquery.cookie.js"></script>
<script>
    new GridScrollFx( document.getElementById( 'grid' ), {
        viewportFactor : 0.4
    } );
</script>
</body>
</html>