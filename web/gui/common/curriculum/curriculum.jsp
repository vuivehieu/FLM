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
            pad-bot-70 {
                padding-left: 5rem;
                padding-right: 5rem;
            }
            .descripCur {
                overflow: hidden;
                text-overflow: ellipsis;
                line-height: 25px;
                -webkit-line-clamp: 4;
                height: 90px;
                display: -webkit-box;
                -webkit-box-orient: vertical;
            }
        </style>
    </head>

    <body>

        <jsp:include page="../heading/heading.jsp"/>
        <section class="pop-cour">
            <div class="container com-sp pad-bot-70">
                <div class="row">
                    <div class="con-title col-12">
                        <h2>Curriculum <span>Management</span></h2>
                    </div>
                </div>

                <div class="col-12">
                    <form class="form-inline">
                        <div style="position: relative">
                            <input type="text" name="type" value="curriculum" hidden="">
                            <input class="" name="keysearch" id="search" value="${key}" style="height: 5rem;font-size: unset" type="text" placeholder="Search" oninput="searchCurriculum()">
                            <span style="position: absolute; top:20px; right:10px" class="fa fa-search"></span>
                        </div>
                    </form>

                </div>

            </div>

            <div class="container-fluid pad-bot-70 p-custom" id="root">
                <c:if test="${!list.isEmpty() && list != null}">
                    <div class='row'>
                        <h5 style='margin-left: 5rem;'>${size} Curriculum(es) found</h5>
                        <table class='table table-hover'>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th width="10%">CurriculumCode</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>DecisionNo<br>MM/dd/yyyy</th>
                                    <th>Total Credit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <jsp:useBean class="DAL.DAO" id="dao"/>
                                <jsp:useBean class="Custom.ForJSP" id="custom"/>
                                <c:set var="index" value="${start + 1}"/>
                                <c:forEach items="${list}" var="item">
                                    <tr>
                                        <td>${index}</td>
                                        <td>${item.curCode}</td>
                                        <td>
                                            <a href="curriculumDetails?curid=${item.curid}">
                                                <span class='list-enq-name'>${item.curName_EN} ( ${item.curName_VI} )</span>
                                            </a>
                                        </td>
                                        <td class="descripCur">${item.description}</td>
                                        <td>
                                            <a href="decision?decisionNo=${item.decision.decisionNo}" >
                                                <span class="list-enq-name">${item.decision.decisionNo}</span>
                                                <span class="list-enq-city">${custom.getDateFormat('MM/dd/yyyy', item.decision.approvedDate)}</span>
                                            </a>
                                        </td>
                                        <td>${item.totalCredit}</td>
                                    </tr>
                                    <c:set var="index" value="${index + 1}"/>

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
            function searchCurriculum() {
                let key = document.getElementById("search").value;
                let url = './search?type=curriculum&keysearch=' + key;


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
                let url = './search?type=curriculum&keysearch=' + key + '&page=' + page;


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
                    searchCurriculum();
                }
            });
        </script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>