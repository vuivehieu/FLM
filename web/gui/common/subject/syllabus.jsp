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
