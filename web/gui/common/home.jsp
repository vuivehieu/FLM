<%-- 
    Document   : index
    Created on : 14-01-2023, 06:15:40
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>

    <body>
        <jsp:include page="heading/heading.jsp"/>

        <!-- SLIDER -->
        <section>
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item slider1 active">
                        <img src="images/slider/1.jpg" alt="">
                        <div class="carousel-caption slider-con">
                            <h2>Welcome to <span> FPT University</span></h2>
                            <p>:> đốt trường</p>
                            <a href="#" class="bann-btn-1">All Courses</a><a href="#" class="bann-btn-2">Read More</a>
                        </div>
                    </div>
                    <div class="item">
                        <img src="images/slider/2.jpg" alt="">
                        <div class="carousel-caption slider-con">
                            <h2>Phan Hiếu <span>  2023</span></h2>
                            <p>:> Đốt trường</p>
                            <a href="#" class="bann-btn-1">Curriculum</a><a href="#" class="bann-btn-2">Read More</a>
                        </div>
                    </div>
                    <div class="item">
                        <img src="images/slider/3.jpg" alt="">
                        <div class="carousel-caption slider-con">
                            <h2>Syllabus <span>Master</span></h2>
                            <p>:> đốt trường</p>
                            <a href="#" class="bann-btn-1">All Syllabus</a><a href="#" class="bann-btn-2">Read More</a>
                        </div>
                    </div>
                </div>

                <!-- Left and right controls -->
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <i class="fa fa-chevron-left slider-arr"></i>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <i class="fa fa-chevron-right slider-arr"></i>
                </a>
            </div>
        </section>

        <!-- QUICK LINKS -->
        <section>
            <div class="container">
                <div class="row">
                    <div class="wed-hom-ser">
                        <ul>
                            <li>
                                <a href="#" class="waves-effect waves-light btn-large wed-pop-ser-btn"><img src="images/icon/h-ic1.png" alt=""> Academy</a>
                            </li>
                            <li>
                                <a href="#" class="waves-effect waves-light btn-large wed-pop-ser-btn"><img src="images/icon/h-ic2.png" alt=""> Admission</a>
                            </li>
                            <li>
                                <a href="#" class="waves-effect waves-light btn-large wed-pop-ser-btn"><img src="images/icon/h-ic4.png" alt=""> Courses</a>
                            </li>
                            <li>
                                <a href="#" class="waves-effect waves-light btn-large wed-pop-ser-btn"><img src="images/icon/h-ic3.png" alt=""> Seminar</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <!-- DISCOVER MORE -->
        <section>
            <div class="container com-sp pad-bot-70">
                <div class="row">
                    <div class="con-title">
                        <h2>Chuyên Ngành <span>FPT University</span></h2>
                        <p>:> đốt trường</p>
                    </div>
                </div>
                <div class="row">
                    <div class="ed-course">
                        <c:forEach items="${listMajor}" var="major">
                            <div class="col-md-3 col-sm-4 col-xs-12">
                                <div class="ed-course-in">
                                    <a class="course-overlay" href="curriculum?majorID=${major.majorID}">
                                        <img src="${major.image}" alt="ảnh major">
                                        <span>${major.majorName_EN}</span>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <!-- POPULAR COURSES -->
        <section class="pop-cour">
            <div class="container com-sp pad-bot-70">
                <div class="row">
                    <div class="con-title">
                        <h2>Khóa học <span>Nổi Bật</span></h2>
                        <p>Đốt Trường :></p>
                    </div>
                </div>
                <div class="row">

                    <c:forEach begin="0" end="5" var="i">
                        <div class="col-md-6">
                            <!--POPULAR COURSES-->
                            <div class="home-top-cour">
                                <!--POPULAR COURSES IMAGE-->
                                <div class="col-md-3"> <img src="images/course/sm-4.jpg" alt=""> </div>
                                <!--POPULAR COURSES: CONTENT-->
                                <div class="col-md-9 home-top-cour-desc">
                                    <a href="subjectDetails?subjectCode=${subject.get(i).subjectCode}">
                                        <h3>${subject.get(i).subjectCode}</h3>
                                    </a>
                                    <h4>${subject.get(i)} / Construction / Building</h4>
                                    <p>Classes started from coming friday(21 jun 2017),total seats 72 and available seats 10</p> <span class="home-top-cour-rat">4.2</span>
                                    <div class="hom-list-share">
                                        <ul>
                                            <li><a href="course-details.html"><i class="fa fa-bar-chart" aria-hidden="true"></i> Book Now</a> </li>
                                            <li><a href="course-details.html"><i class="fa fa-eye" aria-hidden="true"></i>32 Aavailable</a> </li>
                                            <li><a href="course-details.html"><i class="fa fa-share-alt" aria-hidden="true"></i> 124</a> </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <strong class="mr-auto">Thông báo</strong>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Đóng">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                Đăng ký thành công!
            </div>
        </div>
        <div id="message" hidden="">${sessionScope.message}</div>
        <%
              session.removeAttribute("message");
        %>

        <!-- FOOTER -->
        <jsp:include page="footer/footer.jsp"/>


        <!-- Thêm link tới các thư viện jQuery và Popper.js -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                if (document.getElementById('messageRegister').innerHTML !== "false") {
                    $('.toast').toast('show');
                }

            });
        </script>
        <script>
            $(document).ready(function () {
                if (document.getElementById('message').innerHTML !== '') {
                    $('.toast').toast('show');
                    $('.toast').addClass('in');

                    setTimeout(function () {
                        $('.toast').removeClass('in');

                    }, 2900);
                }

            });
        </script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>

</html>
