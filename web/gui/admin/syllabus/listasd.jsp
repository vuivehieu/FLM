<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">


    <head>
        <title>ADMIN - Combo List</title>
        <!-- META TAGS -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="EEducation master is one of the best eEducational html template, it's suitable for all eEducation websites like university,college,school,online eEducation,tution center,distance eEducation,computer eEducation">
        <meta name="keyword" content="eEducation html template, university template, college template, school template, online eEducation template, tution center template">
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
        <!--        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
                <link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/dataTables.bootstrap4.min.css"/>
                <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.3.2/css/buttons.bootstrap4.min.css"/>-->

        <style>
            .app-search a {
                top: 4px;
                right: 4px
            }
            .app-search input {
                color: unset;
            }
        </style>
    </head>

    <body>
        <!--== MAIN CONTRAINER ==-->
        <jsp:include page="../common/heading/heading.jsp"/>

        <!--== BODY CONTNAINER ==-->
        <div class="container-fluid sb2">
            <div class="row">
                <jsp:include page="../common/sidebar/sidebarLeft.jsp"/>

                <!--== BODY INNER CONTAINER ==-->
                <div class="sb2-2">
                    <!--== breadcrumbs ==-->
                    <div class="d-flex">
                        <div class="sb2-2-2">
                            <ul>
                                <li><a href="dashboard"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                                <li class="active-bre"><a> Syllabus List</a></li>
                            </ul>
                        </div>
                        <div class="float-right">
                            <a href="syllabusAdd"><button class="btn btn-warning">Add Syllabus</button></a>
                        </div>
                    </div>


                    <!--== User Details ==-->
                    <div class="sb2-2-3">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-inn-sp">
                                    <div class="inn-title">
                                        <h4>Syllabus List</h4>   
                                    </div>
                                    <div class="tab-inn" >
                                        <div class="table-responsive table-desi">
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6"></div>
                                                <div class="col-md-6 col-sm-6 mob-hide" style="padding-right: 3rem">
                                                    <form class="app-search">
                                                        <input type="text" placeholder="Search..." id="search" name="keySearch" value="${key}" oninput="searchSyllabus()">
                                                        <a href="#"><i class="fa fa-search"></i></a>
                                                    </form>
                                                </div>
                                            </div>
                                            <jsp:useBean class="Custom.ForJSP" id="custom" />
                                            <div id="root">
                                                <table id="" class="table table-hover" style="width:100%">
                                                    <thead>
                                                        <tr>
                                                            <th onclick="sortByType('${sort == "" ? 'id_down' : sort eq 'id_up' ? 'id_down': 'id_up'}')" width="5%" >ID <span class="fa ${sort == "" ? 'fa-angle-up' : sort eq 'id_up' ? 'fa-angle-up' : 'fa-angle-down'}"></span></th>
                                                            <th onclick="sortByType('${sort == "" ? 'code_down' : sort eq 'code_up' ? 'code_down': 'code_up'}')">SubjectCode <span class="fa ${sort == "" ? 'fa-angle-up' : sort eq 'code_up' ? 'fa-angle-up' : 'fa-angle-down'}"></span></th>
                                                            <th onclick="sortByType('${sort == "" ? 'name_down' : sort eq 'name_up' ? 'name_down': 'name_up'}')">Syllabus Name <span class="fa ${sort == "" ? 'fa-angle-up' : sort eq 'name_up' ? 'fa-angle-up' : 'fa-angle-down'}"></span></th>
                                                            <!--<th onclick="sortByType('${sort == "" ? 'date_down' : sort eq 'date_up' ? 'date_down': 'date_up'}')" width="10%" > <span class="fa ${sort == "" ? 'fa-angle-up' : sort eq 'date_up' ? 'fa-angle-up' : 'fa-angle-down'}"></span></th>-->
                                                            <th >DecisionNo</th>
                                                            <th>Active</th>
                                                            <th>Approved</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${list}" var="item">
                                                            <tr>
                                                                <td>${item.slbid}</td>
                                                                <td>${item.subjectCode}</td>
                                                                <td>
                                                                    <a href="#">
                                                                        <span class="list-enq-name">${item.slbName_EN}</span>
                                                                        <span class="list-enq-city">${item.slbName_VI}</span>
                                                                    </a>
                                                                </td>
                                                                <td>${item.decision.decisionNo}</td>
                                                                <td><span class="fa ${item.isActive ? 'fa-check text-success' : 'fa-times text-danger'}"></span></td>
                                                                <td><span class="fa ${item.isApproved ? 'fa-check text-success' : 'fa-times text-danger'}"></span></td>
                                                                <td><a href="syllabusDetail?slbid=${item.slbid}">Edit</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>                                                

                                                <div class="pg-pagina">
                                                    <ul class="pagination">
                                                        <li class="${page == 1 ? 'disabled' : 'waves-effect'}">
                                                            <a onclick="changePage('${page - 1}', '${sort}')">Pre</a>
                                                        </li>
                                                        <c:forEach begin="1" end="${numberOfPage}" var="i">
                                                            <li class=" ${page == i ? 'active' : 'waves-effect'}">
                                                                <a onclick="changePage('${i}', '${sort}')">${i}</a>
                                                            </li>
                                                        </c:forEach>
                                                        <li class="${page == numberOfPage ? 'disabled' : 'waves-effect'}">
                                                            <a onclick="changePage('${page + 1}', '${sort}')">Next</a>
                                                        </li>
                                                    </ul>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div id="message" hidden="">${sessionScope.message}</div>
        <%
              session.removeAttribute("message");
        %>

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                                                                let request;
                                                                function searchSyllabus() {
                                                                    let key = document.getElementById("search").value;
                                                                    let url = './syllabusList?keySearch=' + key;


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

                                                                function changePage(page, sort) {
                                                                    let key = document.getElementById("search").value;
                                                                    let url = './syllabusList?keySearch=' + key + "&sort=" + sort + "&page=" + page;

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


                                                                function sortByType(type) {
                                                                    let key = document.getElementById("search").value;
                                                                    let url = './syllabusList?keySearch=' + key + "&sort=" + type;


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


                                                                $(document).ready(function () {
                                                                    if (document.getElementById('message').innerHTML !== '') {
                                                                        $('.toast').toast('show');
                                                                        $('.toast').addClass('in');

                                                                        setTimeout(function () {
                                                                            $('.toast').removeClass('in');

                                                                        }, 2900);
                                                                    }

                                                                });


                                                                $('#search').keyup(function (e) {
                                                                    if (e.keyCode === 13) {
                                                                        console.log("enter");
                                                                        e.preventDefault();
                                                                        searchCombo();
                                                                    }
                                                                });
        </script>

        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>

    </body>


</html>