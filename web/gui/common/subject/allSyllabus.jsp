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

        <style>
            .descripCur {
                overflow: hidden;
                text-overflow: ellipsis;
                line-height: 25px;
                -webkit-line-clamp: 3;
                height: 75px;
                display: -webkit-box;
                -webkit-box-orient: vertical;
            }
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
        <section class="pop-cour">
            <div class="container com-sp pad-bot-70">
                <div class="row">
                    <div class="con-title col-12">
                        <h2>Syllabus <span>Management</span></h2>
                    </div>


                </div>
                <div class="col-12">
                    <form class="form-inline">
                        <div style="position: relative">
                            <input type="text" name="type" value="syllabus" hidden="">
                            <input class="" name="keysearch" id="search" value="${key}" style="height: 5rem;font-size: unset" type="text" placeholder="Search" oninput="searchSyllabus()">
                            <span style="position: absolute; top:20px; right:10px" class="fa fa-search"></span>
                        </div>
                    </form>

                </div>
            </div>

            <div class="container pad-bot-70" id="root">
                <c:if test="${!list.isEmpty() && list != null}">
                    <div class='row'>
                        <h5>${size} Syllabus(es) found</h5>
                        <table class='table table-hover'>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th width="10%">Subject Code</th>
                                    <th>Subject Name</th>
                                    <th>Syllabus Name</th>
                                    <th>IsActive</th>
                                    <th>IsApproved</th>
                                    <th>DecisionNo MM/dd/yyyy</th>
                                </tr>
                            </thead>
                            <tbody>
                                <jsp:useBean class="DAL.DAO" id="dao"/>
                                <jsp:useBean class="Custom.ForJSP" id="custom"/>
                                <c:forEach items="${list}" var="item">
                                    <tr>
                                        <td>${item.slbid}</td>
                                        <td>${item.subjectCode}</td>
                                        <td>${dao.getSubjectNameENBySubjectCode(item.subjectCode)}</td>
                                        <td>
                                            <a href="syllabusDetails?subjectCode=${item.subjectCode}&slbid=${item.slbid}" >
                                                <span class='list-enq-name'>${item.slbName_EN}</span>
                                                <span class="list-enq-city">${item.slbName_VI}</span>
                                            </a>
                                        </td>
                                        <td class="text-center "><i class="fa ${item.isActive ? 'fa-check text-success' : 'fa-times text-danger'}"></i></td>
                                        <td class="text-center"><i class="fa ${item.isApproved ? 'fa-check text-success' : 'fa-times text-danger'}"></i></td>
                                        <td>
                                            <a href="decision?decisionNo=${item.decision.decisionNo}" >
                                                <span class="list-enq-name">${item.decision.decisionNo}</span>
                                                <span class="list-enq-city">${custom.getDateFormat('MM/dd/yyyy', item.decision.approvedDate)}</span>

                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class='pg-pagina'>
                        <ul class='pagination'>
                            <li class="${(page == 1 ? 'disabled' : 'waves-effect')}"> 
                                <a onclick="changePage('${page - 1}')">
                                    <i class='fa fa-angle-left' aria-hidden='true'></i>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${numberOfPage}" var="i">
                                <li class=" ${page == i ? 'active' : 'waves-effect'}">
                                    <a onclick="changePage('${i}')">${i} </a>
                                </li>
                            </c:forEach>
                            <li class="${(page == numberOfPage ? 'disabled' : 'waves-effect')}">
                                <a onclick="changePage('${page + 1}')">
                                    <i class='fa fa-angle-right' aria-hidden='true'></i>
                                </a>
                            </li>
                        </ul>
                    </div>


                </c:if>
            </div>
        </section> 

        <jsp:include page="../footer/footer.jsp"/>

        <script>

            let request;
            function searchSyllabus() {
                let key = document.getElementById("search").value;
                let url = './search?type=syllabus&keysearch=' + key;


                if (window.XMLHttpRequest) {
                    request = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    request = new ActiveXObject("Microsoft.XMLHTTP");
                }

                try {
                    request.onreadystatechange = getInfo;
                    request.open("POST", url, true);
                    request.send("POST");
                } catch (e) {
                    alert("Unable to connect server");
                }
            }

            function changePage(page) {
                let key = document.getElementById("search").value;
                let url = './search?type=syllabus&keysearch=' + key + '&page=' + page;


                if (window.XMLHttpRequest) {
                    request = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    request = new ActiveXObject("Microsoft.XMLHTTP");
                }

                try {
                    request.onreadystatechange = getInfo;
                    request.open("POST", url, true);
                    request.send("POST");
                } catch (e) {
                    alert("Unable to connect server");
                }
            }

            function getInfo() {
                if (request.readyState === 4) {
                    var val = request.responseText;
                    document.getElementById("root").innerHTML = val;
                }
            }
        </script>

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $('#search').keyup(function (e) {
                if (e.keyCode === 13) {
                    searchSyllabus();
                }
            });
        </script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>
