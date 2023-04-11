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
                                <h2>Curriculum - <span>${cur.curCode}</span></h2>
                                <p>PO Management</p>
                            </div>
                            <div>
                                <div class="ho-event pg-eve-main ">
                                    <ul>
                                        <li>
                                            <div class="pg-eve-desc subject-details">
                                                <p>Curriculum ID: <span>${cur.curid}</span></p>
                                                <p>Curriculum Name: <span>${cur.curName_EN} - ${cur.curName_VI}</span></p>
                                            </div>
                                        </li>

                                        <li>
                                            <c:if test="${cur.po.isEmpty()}">
                                                <h3 class="text-danger">Not Found PO !</h3>
                                            </c:if>

                                            <c:if test="${!cur.po.isEmpty()}">
                                                <h3>PO <span>(${cur.po.size()})</span></h3>

                                                <div class="table-responsive table-desi">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th width="5%">PO ID</th>
                                                                <th width="10%">PO Name</th>
                                                                <th>PO Description</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${cur.po}" var="po">
                                                                <tr>
                                                                    <td><span class="list-enq-name">${po.poid}</span></td>
                                                                    <td>${po.poName}</td>
                                                                    <td>${po.poDescription}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:if>
                                        </li>
                                        <li>
                                            <c:if test="${cur.plo.isEmpty()}">
                                                <h3 class="text-danger">Not Found PLO !</h3>

                                            </c:if>
                                            <c:if test="${!cur.plo.isEmpty()}">
                                                <h3>PLO <span>(${cur.plo.size()})</span></h3>

                                                <div class="table-responsive table-desi">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>PLO ID</th>
                                                                <th>PLO Name</th>
                                                                <th>PLO Description</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${cur.plo}" var="plo">
                                                                <tr>
                                                                    <td class="text-center">${plo.ploid}</td>
                                                                    <td class="text-center">${plo.ploName}</td>
                                                                    <td class="">${plo.ploDescription}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:if>

                                        </li>
                                        <li>
                                            <c:if test="${length > 0}">
                                                <table class="table table-hover table-striped table-bordered table-custom">
                                                    <thead>
                                                        <tr >
                                                            <th class="text-center" colspan="8">Mapping POs to PLOs</th>
                                                        </tr>
                                                        <tr>
                                                            <th style="">PLO(s)</th>
                                                                <c:forEach items="${cur.po}" var="i">
                                                                <th>${i.poName}</th>
                                                                </c:forEach>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach begin="0" end="${cur.plo.size() - 1}" var="i">
                                                            <tr>
                                                                <td class="text-center">${cur.plo.get(i).ploName}</td>

                                                                <c:forEach begin="0" end="${cur.po.size() - 1}" var="j">
                                                                    <c:if test="${map[i][j].isMapping}">
                                                                        <td class="text-center"><i class="fa fa-check" style="text-align: center; color: #e25f30" aria-hidden="true"></i></td>
                                                                        </c:if>
                                                                        <c:if test="${!map[i][j].isMapping}">
                                                                        <td> </td>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>    
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
