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
        <meta charset="utf-8">
        <title>Education Master Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="fe/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="fe/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="fe/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="fe/css/style.css" rel="stylesheet">
        
        
         <!--MDB--> 
        <script
          type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"
        ></script>
       
         <!--Font Awesome--> 
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"
        />
         <!--Google Fonts--> 
        <link
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"
        />
         <!--MDB--> 
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
          rel="stylesheet"
        />
        
        <style>
            .gradient-custom-2 {
                /* fallback for old browsers */
                background: #fccb90;

                /* Chrome 10-25, Safari 5.1-6 */
                background: -webkit-linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);

                /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
                }

                @media (min-width: 768px) {
                .gradient-form {
                height: 100vh !important;
                }
                }
                @media (min-width: 769px) {
                .gradient-custom-2 {
                border-top-right-radius: .3rem;
                border-bottom-right-radius: .3rem;
                }
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
                                                <h3>Elective</h3>
                                            </div>
                                        </li>
                                        <jsp:useBean class="DAL.DAO" id="dao" />
                                        <c:set var="elec" value="${dao.getElectiveByTag(subject.subjectCode)}"/>
                                        <li>
                                            <h4>${elec.tag}: ${elec.ename} <span></span></h4>
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
                                                    <c:forEach items="${elec.subject}" var="subject">
                                                        <tr>
                                                            <td><a href="subjectDetails?subjectCode=${subject.subjectCode}&curid=${curid}"></a>${subject.subjectCode}</td>
                                                            <td>${subject.subjectName_EN} - ${subject.subjectName_VI}</td>
                                                            <td>${subject.semester}</td>
                                                            <td>${subject.noCredit}</td>
                                                            <td>
                                                                <c:if test="${!subject.isCombo && !subject.isElective && !subject.syllabus.isEmpty()}">
                                                                    <c:set var="pre" value="${dao.getPreRequisiteBySlbid(subject.syllabus.get(0).slbid)}" />
                                                                    <c:forEach begin="0" end="${pre.size() - 1}" var="i">
                                                                        ${pre.get(i).preReqCode}
                                                                        <c:if test="${i < pre.size() - 1}">
                                                                            ,
                                                                        </c:if>
                                                                    </c:forEach>

                                                                </c:if>
                                                            </td>
                                                            <td>${subject.note}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
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