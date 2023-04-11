<%-- 
    Document   : subject
    Created on : 16-01-2023, 16:41:44
    Author     : phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            .pro-user-act-study {
                background: #2dc3b4;
                color: #fff;
                padding: 1px 4px;
                border-radius: 2px;
                font-size: 13px;
            }

            .pro-user-act-notpass {
                background: #f92543;
                color: #fff;
                padding: 1px 4px;
                border-radius: 2px;
                font-size: 13px;
            }

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
                            <li><a href="editprofile">Edit Profile</a></li>
                            <li><a href="changePassword">Change Password</a></li>
                            <li><a href="#">Notifications</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="stu-db">
                <div class="container pg-inn">
                    <div class="col-md-12">
                        <div class="udb">
                            <div class="udb-sec udb-cour">
                                <h4><img src="images/icon/db2.png" alt="" />Curriculum</h4>
                                <p>${account.curriculum.curName_EN}</p>
                                <p>${account.curriculum.curName_VI}</p>

                                <div class="sdb-cours">
                                    <ul>
                                        <li>
                                            <a href="#">
                                                <div class="list-mig-like-com com-mar-bot-30">
                                                    <div class="list-mig-lc-img"> <img src="images/course/3.jpg" alt=""> <span class="home-list-pop-rat list-mi-pr">Duration:20 Days</span> </div>
                                                    <div class="list-mig-lc-con">
                                                        <h5>Master of Science</h5>
                                                        <p>Illinois City,</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="udb-sec udb-cour-stat">
                                <h4><img src="images/icon/db3.png" alt="" /> Subject Status</h4>
                                <p>View Tiến Trình Học</p>
                                <div class="pro-con-table">
                                    <table class="bordered responsive-table">
                                        <thead>
                                            <tr>
                                                <th>No</th>
                                                <th>Subject Code</th>
                                                <th>Subject Name</th>
                                                <th>Semester</th>
                                                <th>Learn</th>
                                                <th width="10%">Status</th>
                                                <th>Grade</th>
                                            </tr>
                                        </thead>
                                        <jsp:useBean class="DAL.GradeDAO" id="gradeDAO"/>
                                        <jsp:useBean class="DAL.StudentDAO" id="studentDAO" />
                                        <jsp:useBean class="DAL.DAO" id="dao" />
                                        <jsp:useBean class="Custom.ForJSP" id="custom" />
                                        <tbody>
                                            <c:set var="subject" value="${account.curriculum.subject}"/>

                                            <c:forEach begin="0" end="${subject.size() - 1}" var="i">
                                                <c:set var="learn" value="${studentDAO.getStatusOfSubjectByStidAndSubjectCode(account.stid, subject.get(i).subjectCode)}"/>
                                                <c:set var="status" value="${gradeDAO.checkPreRequisite(subject.get(i).subjectCode, account.stid)}" />
                                                <tr>
                                                    <td>${(i + 1)}</td>
                                                    <td>${subject.get(i).subjectCode}</td>
                                                    <td>${subject.get(i).subjectName_EN}_${subject.get(i).subjectName_VI}</td>
                                                    <td>${subject.get(i).semester}</td>
                                                    <td>
                                                        <span class="${learn.isLearning == 0 ? 'pro-user-act-study' : learn.isLearning == 1 ? 'pro-user-act' : ''}">${learn.isLearning == 0 ? 'Studying' : learn.isLearning == 1 ? 'Studied' : 'Not Started'}</span>
                                                    </td>
                                                    <td>
                                                        <c:if test="${learn.isLearning != -1}">
                                                            <span class="${status ? 'pro-user-act' : 'pro-user-act-notpass'}">${status ? 'Passed' : 'Not Passed'}</span>

                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${learn.isLearning != -1}">
                                                            <a href="grade?subjectCode=${subject.get(i).subjectCode}&stid=${account.stid}" class="pro-edit" style="background: #999">${custom.formarNumberFloat(gradeDAO.getAverageGradeOfSubjectBySubjectCodeAndStid(subject.get(i).subjectCode, account.stid))}</a>
                                                        </c:if>
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--SECTION END-->

        <jsp:include page="../common/footer/footer.jsp"/>

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>
