<%-- 
    Document   : syllabus
    Created on : 20-01-2023, 14:47:42
    Author     : phanh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                                                <h4 style="margin-bottom: 1rem">Syllabus Details </h4>
                                                <p>Syllabus ID: <span>${syllabus.slbid}</span></p>
                                                <p>Syllabus Name: <span>${syllabus.slbName_EN} - ${syllabus.slbName_VI}</span></p>
                                                <p>Subject Code: <span>${syllabus.subjectCode}</span></p>
                                                <p>No Credit: <span>${subject.noCredit}</span></p>
                                                <p>DegreeLevel: <span>${syllabus.degreeLevel}</span></p>
                                                <p>Time Allocation: <span>${syllabus.timeAllocation}</span></p>
                                                <p>Pre-Requisite: 
                                                    <span>
                                                        <c:forEach items="${syllabus.preReq}" var="i">
                                                            ${i.preReqCode}
                                                        </c:forEach>
                                                    </span>
                                                </p>
                                                <p>Description: <span>${syllabus.description}</span></p>
                                                <p>StudentTasks: <span>${syllabus.studentTask}</span></p>
                                                <p>Tools: <span>${syllabus.tool}</span></p>
                                                <p>IsApproved:  <span>${syllabus.isApproved}</span></p>
                                                <p>Note: <span>${syllabus.note}</span></p>
                                                <p>MinAvgMarkToPass: <span>${syllabus.minAvgMarkToPass}</span></p>
                                                <p>IsActive: <span>${syllabus.isActive}</span></p>
                                                <p>ApprovedDate: <span>${syllabus.approvedDate}</span></p>
                                            </div>
                                        </li>

                                        <li>
                                            <c:if test="${syllabus.material.isEmpty()}">
                                                <h3 class="text-danger">Not Found Material ! </h3>

                                            </c:if>
                                            <c:if test="${!syllabus.material.isEmpty()}">
                                                <h3>Material <span>(${syllabus.material.size()})</span></h3>
                                                <div class="table-responsive table-desi">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>Description</th>
                                                                <th>Author</th>
                                                                <th>Publisher</th>
                                                                <th>isMain</th>
                                                                <th>isHardCopy</th>
                                                                <th>isOnline</th>
                                                                <th>Note</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${syllabus.material}" var="material">
                                                                <tr>
                                                                    <td>
                                                                        <span class="list-enq-name">${material.description}</span>
                                                                    </td>
                                                                    <td>${material.author}</td>
                                                                    <td>${material.publisher}</td>
                                                                    <td class="text-center"> <span class="fa  ${material.isMainMaterial ? 'fa-check text-success' : 'fa-times text-danger'}"></span></td>
                                                                    <td><span class="fa  ${material.isHardCopy ? 'fa-check text-success' : 'fa-times text-danger'}"></span></td>
                                                                    <td><span class="fa  ${material.isOnline ? 'fa-check text-success' : 'fa-times text-danger'}"></span></td>
                                                                    <td>${material.note}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:if>
                                        </li>

                                        <li>
                                            <c:if test="${syllabus.lo.size() == 0}">
                                                <h3 class="text-danger">Not Found LO !</h3>
                                            </c:if>
                                            <c:if test="${!syllabus.lo.isEmpty()}">
                                                <h3>LO <span>(${syllabus.lo.size()})</span></h3>
                                                <div class="table-responsive table-desi">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>CLO Name</th>
                                                                <th>CLO Details</th>
                                                                <th>LO Details</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${syllabus.lo}" var="lo">
                                                                <tr>
                                                                    <td>
                                                                        <span class="list-enq-name">${lo.cloName}</span>
                                                                    </td>
                                                                    <td><span class="list-enq-name">${lo.cloDetail}</span></td>
                                                                    <td><span class="list-enq-name">${lo.loDetail}</span></td>

                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:if>
                                        </li>


                                        <li>
                                            <h3>Assessment <span>(${syllabus.assessment.size()})</span></h3>
                                            <c:if test="${!syllabus.assessment.isEmpty()}">
                                                <div class="table-responsive table-desi">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th width="20%">Category</th>
                                                                <th>Type</th>
                                                                <th>Part</th>
                                                                <th>Duration</th>
                                                                <th>Quetion Type</th>
                                                                <th>Knowledge & Skill</th>
                                                                <th>Note</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${syllabus.assessment}" var="assessment">
                                                                <tr >
                                                                    <td>
                                                                        <span class="list-enq-name">${assessment.category}</span>
                                                                        <span class="list-enq-city">Weight: ${Math.floor(assessment.weight * 100)} %</span><br>
                                                                        <span class="list-enq-city">Completion Criteria: ${assessment.completionCriteria}</span>
                                                                    </td>
                                                                    <td class="text-center">${assessment.type}</td>
                                                                    <td class="text-center">${assessment.part}</td>
                                                                    <td>${assessment.duration}</td>
                                                                    <td>${assessment.questionType}</td>
                                                                    <td>${assessment.knowledgeSkill}</td>
                                                                    <td>${assessment.note}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:if>

                                        </li>


                                        <li>
                                            <h3>Session <span>(${syllabus.session.size()})</span></h3>
                                            <c:if test="${!syllabus.session.isEmpty()}">
                                                <div class="table-responsive table-desi">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>Topic</th>
                                                                <th>Session No</th>
                                                                <th>Learning Type</th>
                                                                <th>ITU</th>
                                                                <th>Student Material</th>
                                                                <th>Dowload</th>
                                                                <th>Urls</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${syllabus.session}" var="session">
                                                                <tr>
                                                                    <td>
                                                                        <span class="list-enq-name">${session.topic}</span>
                                                                        <a href="#!" data-toggle="modal" data-target="#modal_session${session.sesNo}"><span class="list-enq-city">${session.question.size()} question</a></span>
                                                                    </td>
                                                                    <td>${session.sesNo}</td>
                                                                    <td>${session.learningTeachingType}</td>
                                                                    <td>${session.ITU}</td>
                                                                    <td>${session.studentMaterial}</td>
                                                                    <td class="text-center">
                                                                        <c:if test="${session.dowload != null && session.dowload != ''}">
                                                                            <c:if test="${account != null && account.role.rid != 1}">
                                                                                <a href="${session.dowload}" download=""><span class="fa fa-download" aria-hidden="true"></span></a>

                                                                            </c:if>
                                                                        </c:if>

                                                                    </td>
                                                                    <td>${session.urls}</td>
                                                            <div id="modal_session${session.sesNo}" class="modal fade" role="dialog">
                                                                <div class="log-in-pop">

                                                                    <div class="question-pop">
                                                                        <a href="#" id="btn-close" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" /></a>

                                                                        <div class="cor-side-com">
                                                                            <div class="">
                                                                                <div class="de-left-tit">
                                                                                    <h4>${session.topic}</h4>
                                                                                </div>
                                                                            </div>
                                                                            <div class="ho-event">
                                                                                <ul>
                                                                                    <c:if test="${session.question.isEmpty()}">
                                                                                        <li>
                                                                                            <div class="ho-ev-link ho-ev-link-full">
                                                                                                <h4 style="color:red">No Have Question For This Session !</h4>  
                                                                                            </div>
                                                                                        </li>
                                                                                    </c:if>
                                                                                    <c:if test="${!session.question.isEmpty()}">
                                                                                        <c:forEach items="${session.question}" var="question">
                                                                                            <li>
                                                                                                <div class=" ho-ev-link-full">
                                                                                                    <a href="#!">
                                                                                                        <h4>${question.qname}</h4>
                                                                                                    </a>
                                                                                                    <p>${question.details}</p>
                                                                                                    <c:if test="${account.role.rname != 'Student' && account.role.rname != 'Guest' && account != null}">
                                                                                                        <ul class="collapsible" data-collapsible="accordion">
                                                                                                            <li>
                                                                                                                <div class="collapsible-header">
                                                                                                                    <i style="width: 5%;" class="fa fa-comments-o" aria-hidden="true"></i> Answer
                                                                                                                </div>
                                                                                                                <div class="collapsible-body cor-tim-tab">
                                                                                                                    Answer: <p>${question.answer}</p>
                                                                                                                </div>
                                                                                                            </li>
                                                                                                        </ul>
                                                                                                    </c:if>
                                                                                                </div>
                                                                                            </li>
                                                                                        </c:forEach>
                                                                                    </c:if>
                                                                                </ul>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
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


        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>
