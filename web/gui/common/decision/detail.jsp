<%-- 
    Document   : detail
    Created on : 20-01-2023, 10:16:50
    Author     : phanh
--%>

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

        <jsp:useBean class="Custom.ForJSP" id="custom"/>
        <!--SECTION START-->
        <section>
            <div class="container com-sp">
                <div class="row">
                    <div class="cor ">
                        <div class="ed-about-tit">
                            <div class="con-title">
                                <h2>Decision: <span> ${decision.decisionNo}</span></h2>
                                <p>${subject.subjectName_VI}</p>
                            </div>
                            <div>
                                <div class="ho-event pg-eve-main ">
                                    <ul>
                                        <li>

                                            <div class="pg-eve-desc subject-details">

                                                <h4 style="margin-bottom: 1rem">Decision Details </h4>
                                                <p>DecisionNo: <span>${decision.decisionNo}</span></p>
                                                <p>DecisionName: <span>${decision.decisionName}</span></p>
                                                <p>ApprovedDate (MM/dd/yyyy): <span>${custom.getDateFormat('MM/dd/yyyy', decision.approvedDate)}</span></p>
                                                <p>Note	: <span>${decision.note}</span></p>
                                                <p>CreateDate (MM/dd/yyyy): <span>${custom.getDateFormat('MM/dd/yyyy', decision.createDate)}</span></p>
                                                <p>FileName: <span>${decision.fileName}</span></p>
                                            </div>
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

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>
