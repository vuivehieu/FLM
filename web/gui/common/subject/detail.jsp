
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            .question-pop {
                float: left;
                width: 100%;
                padding: 50px;
            }
            .question-pop a{
                color: #333;
            }
            .question-pop h4 {
                font-size: 24px;
                margin-bottom: 20px;
            }

            .subject-details p{
                /*padding-left: 2rem;*/
            }

            .subject-details span {
                color: #000000;
            }

            .subject-details a {
                color: #f26838;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../heading/heading.jsp"/>

        <!--SECTION START-->
        <section style=" margin-top: 2rem; ">
            <div class="container com-sp">
                <div class="row">
                    <div class="cor ">
                        <div class="ed-about-tit">
                            <div class="con-title">
                                <h2>${subject.subjectName_EN} - <span> ${subject.subjectCode}</span></h2>
                                <p>${subject.subjectName_VI}</p>
                            </div>
                            <div>
                                <div class="ho-event pg-eve-main ">
                                    <ul>
                                        <li>

                                            <div class="pg-eve-desc subject-details">

                                                <h4 style="margin-bottom: 1rem">Subject Details </h4>
                                                <p>Subject Name: <span>${subject.subjectName_EN} - ${subject.subjectName_VI}</span></p>
                                                <p>Subject Code: <span>${subject.subjectCode}</span></p>
                                                <p>Semester: <span>${subject.semester}</span></p>
                                                <p>No Credit: <span>${subject.noCredit}</span></p>
                                            </div>
                                        </li>
                                        <li>
                                            <c:if test="${subject.syllabus.isEmpty()}">
                                                <h3 class="text-danger text-center">Not Found Syllabus For This Subject</h3>
                                            </c:if>

                                            <c:if test="${!subject.syllabus.isEmpty()}">
                                                <h3>Syllabus <span>(${subject.syllabus.size()})</span></h3>
                                                <div class="table-responsive table-desi">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr >
                                                                <th>ID</th>
                                                                <th>Subject Code</th>
                                                                <th class="text-center">Syllabus Name</th>
                                                                <th class="text-center">isActive</th>
                                                                <th class="text-center">isApproved</th>
                                                                <th class="text-center">Decision</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${subject.syllabus}" var="syllabus">
                                                                <tr>
                                                                    <td>${syllabus.slbid}</td>
                                                                    <td>${syllabus.subjectCode}</td>
                                                                    <td>
                                                                        <a href="syllabusDetails?subjectCode=${syllabus.subjectCode}&slbid=${syllabus.slbid}">
                                                                            <span class="list-enq-name">${syllabus.slbName_EN}</span>
                                                                        </a>
                                                                        <a href="syllabusDetails?subjectCode=${syllabus.subjectCode}&slbid=${syllabus.slbid}">
                                                                            <span class="list-enq-city">${syllabus.slbName_VI}</span>
                                                                        </a>
                                                                    </td>
                                                                    <td class="text-center"><span class="fa ${syllabus.isActive ? 'fa-check text-success' : 'fa-times text-danger'}"></span></td>
                                                                    <td class="text-center"><span class="fa ${syllabus.isApproved ? 'fa-check text-success' : 'fa-times text-danger'}"></span></td>
                                                                    <td>
                                                                        <a href="decision?decisionNo=${syllabus.decision.decisionNo}"><span class="list-enq-name">${syllabus.decision.decisionNo} ${syllabus.decision.approvedDate}</span></a>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:if>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--SECTION END-->

        <jsp:include page="../footer/footer.jsp"/>

      <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="fe/lib/easing/easing.min.js"></script>
        <script src="fe/lib/waypoints/waypoints.min.js"></script>
        <script src="fe/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="fe/lib/isotope/isotope.pkgd.min.js"></script>
        <script src="fe/lib/lightbox/js/lightbox.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="fe/mail/jqBootstrapValidation.min.js"></script>
        <script src="fe/mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="fe/js/main.js"></script>
    </body>


</html>