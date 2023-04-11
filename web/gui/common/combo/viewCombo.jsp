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
                                <p>Combo Management</p>
                            </div>
                            <div>
                                <div class="ho-event pg-eve-main ">
                                    <ul>
                                        <li>
                                            <div class="pg-eve-desc subject-details">
                                                <p>Curriculum ID: <span>${cur.curid}</span></p>
                                                <p>Curriculum Code: <span>${cur.curCode}</span></p>
                                                <p>Curriculum Name: <span>${cur.curName_EN} - ${cur.curName_VI}</span></p>
                                            </div>
                                        </li>

                                        <li>
                                            <c:if test="${!cur.combo.isEmpty()}">
                                                <div class="table-responsive table-desi">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th width="10%">Combo ID</th>
                                                                <th>Combo Name</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${cur.combo}" var="combo">
                                                                <tr>
                                                                    <td class="text-center"><span class="list-enq-name">${combo.cid}</span></td>
                                                                    <td><a href="comboDetailView?cid=${combo.cid}">${combo.tag}: ${combo.comboName_EN} ${combo.comboName_VI}</a> </td>
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
