<%-- 
    Document   : detail
    Created on : 18-01-2023, 11:16:09
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com"%>
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

        <style>
            .table-custom th {
                background-color: #fd642d;
                color: white;
            }

            .curriculum-review {
                width: 10%;
                float: left;
            }

            .curriculum-review img{
                padding-left: auto;
                width: 80px;
                height: 80px;
                border-radius: 50px;
            }

            .curriculum-review-right i{
                float: right;
                top: -9rem;
                position: relative;
                padding-right: 5rem;
            }

            .color-warning-custom {
                background-color: #ff9600;
            }


        </style>


        <script src="https://cdn.ckeditor.com/4.20.1/standard/ckeditor.js"></script>
    </head>

    <body>

        <jsp:include page="../heading/heading.jsp"/>
        <!--SECTION START-->
        <section>
            <div class="container com-sp pad-bot-70 pg-inn">
                <div class="row">
                    <div class="cor">
                        <div class="col-md-12">
                            <div class="cor-mid-img">
                                <img src="images/course.jpg" alt="">
                            </div>
                            <div class="cor-con-mid">
                                <div class="cor-p1">
                                    <h2>${cur.curCode}</h2>
                                    <span>Curriculum Name: ${cur.curName_EN}</span> <br>
                                    <span>Major: ${cur.major.majorName_EN} - ${cur.major.majorName_VI}</span><br>
                                </div>
                                <div class="cor-p4">
                                    <h3>Curriculum Description:</h3>
                                    <h5>1. Training Objectives<br>1.1 General objective:</h5>
                                    <p>${cur.description}</p>
                                    <h5>1.2 Specific objectives:<br>Graduates of the IT training program/SE specialty must demonstrate the following:</h5>
                                    <div>
                                        <c:if test="${!cur.po.isEmpty()}">
                                            <c:forEach items="${cur.po}" var="po">
                                                <p>
                                                    ${po.poName}: ${po.poDescription}
                                                </p>

                                            </c:forEach>
                                        </c:if>
                                    </div>
                                    <h5>1.3. Job positions after graduation:<br>Graduates of Software Engineering can choose for themselves the following jobs:</h5>
                                    <p>Lười :></p>

                                    <div>
                                        <a class="btn btn-warning color-warning-custom" href="poDetailView?id=${cur.curid}" role="button">View PO</a>
                                        <a class="btn btn-warning color-warning-custom" href="viewCombo?id=${cur.curid}" role="button">View Combo</a>
                                        <a class="btn btn-warning color-warning-custom" href="viewElective?id=${cur.curid}" role="button">View Elective</a>

                                    </div>
                                </div>
                                <div class="cor-p5">
                                    <h3>Chưa Nghĩ Ra Nó Là Gì :></h3>
                                    <ul class="nav nav-tabs">
                                        <li class="active">
                                            <a data-toggle="tab" href="#subject"><img src="images/icon/cor4.png" alt=""> <span>Subject</span></a>
                                        </li>
                                        <li>
                                            <a data-toggle="tab" href="#plo"><img src="images/icon/cor1.png" alt=""><span>PLO</span></a>
                                        </li>
                                        <li class="">
                                            <a data-toggle="tab" href="#mapping"><img src="images/icon/cor5.png" alt=""><span>Mapping</span></a>
                                        </li>
                                    </ul>

                                    <div class="tab-content">
                                        <div id="subject" class="tab-pane fade in active">
                                            <h4>${cur.subject.size()} Subjects, ${cur.totalCredit} Credits</h4>
                                            <div class="">
                                                <c:if test="${!cur.subject.isEmpty()}">
                                                    <div class="box-inn-sp">
                                                        <div class="tab-inn">
                                                            <div class="table-responsive table-desi">
                                                                <table class="table table-hover">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>Subject Code</th>
                                                                            <th>Subject Name</th>
                                                                            <th>Semester</th>
                                                                            <th>NoCredit</th>
                                                                            <th width="20%">PreRequisite</th>
                                                                            <th></th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <jsp:useBean class="DAL.DAO" id="dao"/>
                                                                        <c:forEach items="${cur.subject}" var="subject">
                                                                            <tr>
                                                                                <td>
                                                                                    <a href="subjectDetails?subjectCode=${subject.subjectCode}&curid=${cur.curid}">
                                                                                        <span class="list-enq-name">${subject.subjectCode}</span>
                                                                                        <c:if test="${subject.isElective}">
                                                                                            Elective: ${subject.electiveName}
                                                                                        </c:if>
                                                                                        <c:if test="${subject.isCombo}">
                                                                                            Combo: ${subject.comboName}
                                                                                        </c:if>
                                                                                        <c:if test="${!subject.isElective && !subject.isCombo}">
                                                                                            <span class="list-enq-city"></span>
                                                                                        </c:if>
                                                                                    </a>
                                                                                </td>
                                                                                <td>
                                                                                    <a href="subjectDetails?subjectCode=${subject.subjectCode}&curid=${cur.curid}">
                                                                                        <span class="list-enq-name">${subject.subjectName_EN}</span>
                                                                                        <span class="list-enq-city">${subject.subjectName_VI}</span>
                                                                                    </a></td>
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
                                                                            </tr>
                                                                        </c:forEach> 

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div id="plo" class="tab-pane fade">
                                            <h4>Program Learning Outcomes - ${cur.plo.size()}</h4>
                                            <c:if test="${!cur.plo.isEmpty()}">
                                                <div class="box-inn-sp">
                                                    <div class="tab-inn">
                                                        <div class="table-responsive table-desi">
                                                            <table class="table table-hover">
                                                                <thead>
                                                                    <tr>
                                                                        <th></th>
                                                                        <th width="20%">PLO Name</th>
                                                                        <th>PLO Description</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach begin="0" end="${cur.plo.size() - 1}" var="i">
                                                                        <tr>
                                                                            <td>${(i+1)}</td>
                                                                            <td>${cur.plo.get(i).ploName}</td>
                                                                            <td>
                                                                                <span class="list-enq-name">${cur.plo.get(i).ploDescription}</span>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>

                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                        <div id="mapping" class="tab-pane fade">

                                            <div class="cor-p6 ">
                                                <c:if test="${length > 0}">
                                                    <table class="table table-hover table-striped table-bordered table-custom">
                                                        <thead class="flex-top">
                                                            <tr >
                                                                <th class="text-center" colspan="${cur.plo.size() + 1}">Mapping subjects of the Curriculum ${cur.curCode} to program learning outcomes</th>
                                                            </tr>
                                                            <tr>
                                                                <th>Subject Code</th>
                                                                    <c:forEach items="${cur.plo}" var="i">
                                                                    <th>${i.ploName}</th>
                                                                    </c:forEach>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach begin="0" end="${cur.subject.size() - 1}" var="i">
                                                                <tr>
                                                                    <td class="text-center"><a href="subjectDetails?subjectCode=${cur.subject.get(i).subjectCode}&curid=${cur.curid}">
                                                                            <span class="list-enq-name">${cur.subject.get(i).subjectCode}</span></a></td>
                                                                            <c:if test="${cur.plo.size() > 0}">
                                                                                <c:forEach begin="0" end="${cur.plo.size() - 1}" var="j">
                                                                                    <c:if test="${map[i][j].isMapping}">
                                                                                <td class="text-center"><i class="fa fa-check text-success" style="text-align: center;" aria-hidden="true"></i></td>
                                                                                </c:if>
                                                                                <c:if test="${!map[i][j].isMapping}">
                                                                                <td> </td>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:if>

                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:if>

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

        <jsp:include page="../footer/footer.jsp"/>  

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>
