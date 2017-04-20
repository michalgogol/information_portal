<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<!doctype html>
<head>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/index.css">


    <meta charset="utf-8">
    <title>InfoSite</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index">InfoSite</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><span class="glyphicon glyphicon-log-in"></span><button type="button" class="btn-link" data-toggle="modal" data-target="#register">Kliknij</button></li>
        </ul>
    </div>
</nav>




<div class="loginpart">


    <div class="container-fluid">




                <form action="userDB" method="POST">
                    <div class="container">
                   <div class="form-group">
                       <label  class="whiteLabel" for="login">Login: </label>
                       <input type="text" name="login" value="" id="login" placeholder="login" ><br>
                   </div>
                    <div class="form-group">
                        <label class="whiteLabel" for="password">Hasło: </label>
                    <input type ="password" name ="password" value="" id="password" placeholder="hasło"><br>
                        <p id="forgotten"> <br>Niepamiętasz hasła - <button type="button" class="btn-link" data-toggle="modal" data-target="#noPassword">Kliknij</button> </p>
                    </div>
                    <input type ="submit" value="Wyślij" class="btn btn-default" />

                    </div>
                </form>
    </div>



    <!--forgotten password modal -->
    <div class="modal fade" id="noPassword" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Odzyskaj hasło</h3>
                </div>
                <div class="modal-body">

                    <form action="forgottenPasswordControll" method="POST">

                    <div class="form-group">
                        <input type="text" name="email" value="" placeholder="Email" ><br>
                    </div>

        <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" >Wyślij</button>
                </div>
                </form>
                </div>
            </div>
        </div>
    </div>


    <!--registration modal -->
    <div class="modal fade" id="register" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Utwórz nowe konto</h3>
                </div>
                <div class="modal-body">

                    <form action="CreateAccountControll" method="POST">

                        <div class="form-group">
                            <input type="text" name="login" value="" placeholder="Login" ><br>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" value="" placeholder="Hasło" ><br>
                        </div>
                        <div class="form-group">
                            <input type="text" name="email" value="" placeholder="Email" ><br>
                        </div>
                        <p>Wybierz kategorie informacji </p>
                        <div class="checkbox">
                            <label> <input type="checkbox" name="sport" value="YES"  >Sport <br></label>
                        </div>
                        <div class="checkbox">
                            <label> <input type="checkbox" name="culture" value="YES"  >Kultura <br></label>
                        </div>
                        <div class="checkbox">
                            <label> <input type="checkbox" name="politic" value="YES"  >Polityka <br></label>
                        </div>
                        <div class="checkbox">
                            <label> <input type="checkbox" name="business" value="YES"  >Biznes <br></label>
                        </div>
                        <div class="checkbox">
                            <label> <input type="checkbox" name="science" value="YES"  >Nauka <br></label>
                        </div>

                        <div class="modal-footer">

                            <button type="submit" class="btn btn-primary " >Rejestruj</button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


            </div>





    </div>

</div>
<div class="container-fluid bg-green">
    <div class="row">
        <div class="col-sm-8" id="about">
            <h1>Spersonalizowane informacje z całego świata w jednym miejscu</h1><br>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
                in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt
                in culpa qui officia deserunt mollit anim id est laborum.
            </p>
        </div>

        <div class="col-sm-4">
            <span class="glyphicon glyphicon-paperclip logo slideanim"></span>
        </div>
    </div>
</div>

<div class="container">

    <h2>Nasze sprawdzone źródła informacji</h2>

    <br>
    <div id="myCarousel" class="carousel slide" data-ride="carousel" style="max-width: 900px; margin: 0 auto">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
            <li data-target="#myCarousel" data-slide-to="4"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">

            <div class="item active " >
                <img src="/resources/images/wpolityce.jpg"  >

            </div>

            <div class="item" >
                <img src="/resources/images/money.jpg"   >

            </div>
            <div class="item" >
                <img src="/resources/images/forbes.jpg"   >

            </div>

            <div class="item" >
                <img src="/resources/images/o.jpg" >

            </div>

            <div class="item" >
                <img id="test" src="/resources/images/gw.jpg"  >

            </div>

        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function(){
        // Add smooth scrolling to all links in navbar + footer link
        $(".navbar a, footer a[href='#index']").on('click', function(event) {
            // Make sure this.hash has a value before overriding default behavior
            if (this.hash !== "") {
                event.preventDefault();
                var hash = this.hash;

                $('html, body').animate({
                    scrollTop: $(hash).offset().top
                }, 700, function(){

                    window.location.hash = hash;
                });
            }
        });

        $(window).scroll(function() {
            $(".slideanim").each(function(){
                var pos = $(this).offset().top;

                var winTop = $(window).scrollTop();
                if (pos < winTop + 700) {
                    $(this).addClass("slide");
                }
            });
        });
    })

</script>

</body>
</html>
