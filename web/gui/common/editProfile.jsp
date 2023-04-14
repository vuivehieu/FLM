<%-- 
    Document   : profile
    Created on : 14-01-2023, 23:45:23
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@include file="/common/heading/heading.jsp" %>--%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Education Master Template</title>
        <!-- META TAGS -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Education master is one of the best educational html template, it's suitable for all education websites like university,college,school,online education,tution center,distance education,computer education">
        <meta name="keyword" content="education html template, university template, college template, school template, online education template, tution center template">
        <!-- FAV ICON(BROWSER TAB ICON) -->
        <link rel="shortcut icon" href="images/fav.ico" type="image/x-icon">
        <!-- GOOGLE FONT -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700%7CJosefin+Sans:600,700" rel="stylesheet">
        <!-- FONTAWESOME ICONS -->
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <!-- ALL CSS FILES -->
        <link href="css/materialize.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet" />
        <link href="css/style.css" rel="stylesheet" />
        <!-- RESPONSIVE.CSS ONLY FOR MOBILE AND TABLET VIEWS -->
        <link href="css/style-mob.css" rel="stylesheet" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
            <![endif]-->
         <!-- MDB -->
        <script
          type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"
        ></script>
       
        <!-- Font Awesome -->
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"
        />
        <!-- Google Fonts -->
        <link
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"
        />
        <!-- MDB -->
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
          rel="stylesheet"
        />

        <style>
            .input-field label:not(.label-icon).active {
                opacity: 1;
                font-size: 1.2rem;
                top: 22px!important;
            }
            
            .tab-inn,
            .udb {
                padding: 0;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../common/heading/heading.jsp"/>

        <!--SECTION START-->
        <section>
            <div class="pro-cover">
            </div>
            <div class="pro-menu">
                <div class="container">
                    <div class="col-md-9 col-md-offset-3">
                        <ul>
                            <li>
                                <a href="profile" >Profile</a>
                            </li>


                            <c:if test="${account.role.rname == 'Student'}">
                                <li><a href="courses">Courses</a></li>
                                <li><a href="db-time-line.html">Time Line</a></li>
                                </c:if>
                                <li><a class="pro-act" href="editprofile">Edit Profile</a></li>
                            <li><a href="changePassword">Change Password</a></li>
                            <li><a href="#">Notifications</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="stu-db">
                <div class="container pg-inn">
                    <div class="col-md-3">
                        <div class="pro-user">
                            <img src="${account.avatar != '' ? account.avatar : 'images/user.jpg'}" alt="user">
                        </div>

                    </div>
                    <div class="col-md-9">
                        <div class="udb">
                            <div class="udb-sec udb-prof">
                                <div class="sb2-2-3">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-inn-sp admin-form">
                                                <div class="tab-inn">
                                                    <form action="" method="post">
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" value="${account.userName}" name="userName" class="validate" required>
                                                                <label class="">User Name</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" value="${account.displayName}" name="fullName" class="validate" required>
                                                                <label class="">Full Name</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="email" value="${account.email}" name="email" class="validate" required >
                                                                <label class="">Email</label>
                                                            </div>
                                                        </div>
<!--                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input id="male" type="radio" value="1" name="gender" ${account.gender == 1 ? 'checked' : ''} class="validate" >
                                                                <label for="male" class="">Male</label>
                                                                <input id="female" class="validate ml-4" type="radio" value="0" name="gender" ${account.gender == 0 ? 'checked' : ''}  >
                                                                <label for="female" class="">Female</label>
                                                            </div>

                                                        </div>-->

                                                       

                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <i class="waves-effect waves-light btn-large waves-input-wrapper" style="">
                                                                    <input type="submit" class="waves-button-input" value="Change">
                                                                </i>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--SECTION END-->


        <!--END HEADER SECTION-->
        <%@include file="../common/footer/footer.jsp" %>

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>
