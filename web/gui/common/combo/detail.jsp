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
            .table-custom {
                margin-top: 1.5rem;
            }

            .table-custom th {
                background-color: #fd642d;
                color: white;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../heading/heading.jsp"/>

        <!--SECTION START-->
        <section>
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
                                                <h3>Comboes</h3>
                                            </div>
                                        </li>
                                        <jsp:useBean class="DAL.DAO" id="dao" />
                                        <c:forEach items="${dao.getComboByClick(curid, subject.semester)}" var="combo">
                                            <li>
                                                <h4>${combo.tag}: ${combo.comboName_EN} ${combo.comboName_VI} <span></span></h4>
                                                <table class="table table-hover table-striped table-bordered table-custom">
                                                    <thead>
                                                        <tr>
                                                            <th style="">Subject Code</th>
                                                            <th>Subject Name</th>
                                                            <th>Semester</th>
                                                            <th>NoCredit</th>
                                                            <th>Pre-Requisite</th>
                                                            <th>Note</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:if test="${combo.subject.isEmpty()}">
                                                            lỗi
                                                        </c:if>
                                                        <c:forEach items="${combo.subject}" var="sb">
                                                            <tr>
                                                                <td><a href="subjectDetails?subjectCode=${sb.subjectCode}&curid=${curid}">${sb.subjectCode}</a></td>
                                                                <td><a href="subjectDetails?subjectCode=${sb.subjectCode}&curid=${curid}">${sb.subjectName_EN}</a></td>
                                                                <td>${sb.semester}</td>
                                                                <td>${sb.noCredit}</td>
                                                                <td>
                                                                    <c:if test="${!sb.isCombo && !sb.isElective && !sb.syllabus.isEmpty()}">
                                                                        <c:set var="pre" value="${dao.getPreRequisiteBySlbid(sb.syllabus.get(0).slbid)}" />
                                                                        <c:forEach begin="0" end="${pre.size() - 1}" var="i">
                                                                            ${pre.get(i).preReqCode}
                                                                            <c:if test="${i < pre.size() - 1}">
                                                                                ,
                                                                            </c:if>
                                                                        </c:forEach>

                                                                    </c:if>
                                                                </td>
                                                                <td>${sb.note}</td>
                                                            </tr>
                                                        </c:forEach>


                                                    </tbody>
                                                </table>
                                            </li>
                                        </c:forEach>



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