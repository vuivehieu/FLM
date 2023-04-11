<%-- 
    Document   : detail
    Created on : 19-02-2023, 12:28:34
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


    <head>
        <title>EEducation Master Template</title>
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

        <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">-->

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
            <![endif]-->
        <style>
            .input-field label:not(.label-icon).active {
                opacity: 1;
                font-size: 1.5rem;
                top: 22px !important;
            }

            .admin-form form label {
                left: 20px;
                font-size: 1.5rem;
                top: -20px;
            }
            admin-form form [type="submit"] {
                width: auto;
            }


        </style>

        <script src="https://cdn.ckeditor.com/4.20.1/standard/ckeditor.js"></script>
    </head>

    <body>
        <jsp:include page="../common/heading/heading.jsp"/>


        <!--== BODY CONTNAINER ==-->
        <div class="container-fluid sb2">
            <div class="row">
                <jsp:include page="../common/sidebar/sidebarLeft.jsp"/>


                <!--== BODY INNER CONTAINER ==-->
                <div class="sb2-2">
                    <!--== breadcrumbs ==-->
                    <div class="sb2-2-2">
                        <ul>
                            <li><a href="materialList"><i class="fa fa-home" aria-hidden="true"></i> Material List</a></li>
                            <li class="active-bre"><a > Material Details</a></li>
                        </ul>
                    </div>

                    <!--== User Details ==-->
                    <div class="sb2-2-3">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-inn-sp admin-form">
                                    <div class="sb2-2-add-blog sb2-2-1">
                                        <h2>Material ${type != null ? type eq 'add' ? 'Add' : 'Detail' : 'Detail' }</h2>

                                        <ul class="nav nav-tabs tab-list">
                                            <li class="active"><a data-toggle="tab" href="#detail" aria-expanded="true"><i class="fa fa-info" aria-hidden="true"></i> <span>Detail</span></a></li>
                                        </ul>
                                        <jsp:useBean class="Custom.ForJSP" id="custom" />
                                        <jsp:useBean class="DAL.DAO" id="dao" />
                                        <c:set var="allSyllabusOwn" value="${dao.getSyllabusByAccountID(account.accountID)}"/>


                                        <div class="tab-content">
                                            <div id="detail" class="tab-pane fade in active">
                                                <div class="box-inn-sp">

                                                    <div class="bor">
                                                        <form action="" method="post">               
                                                            <input type="text" name="mid" value="${material.mid}" hidden >
                                                            <input type="text" name="type" value="${type eq 'add' ? 'add' : 'update'}" hidden>

                                                            <div class="row" style="margin-top: 2rem; margin-bottom: 2rem">
                                                                <div class="input-field col s12" style="margin-bottom: 2rem">
                                                                    <select name="slbid">
                                                                        <c:forEach items="${allSyllabusOwn}" var="item">
                                                                            <option value="${item.slbid}" ${item.slbid == material.slbid ? 'selected' : ''}${slbid != null ? slbid == item.slbid ? 'selected' : '' : ''}>
                                                                                Syllabus ID: ${item.slbid} - SubjectCode: ${item.subjectCode} 
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>

                                                                    <label for="tag" class="">Syllabus ID <span class="text-danger">*</span></label>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input id="publishedDate" name="publishedDate" type="date" value="${material.publishedDate}${publishedDate != null ? publishedDate : ''}" class="validate" required>
                                                                    <label for="publishedDate" class="">PublishedDate <span class="text-danger">*</span></label> 
                                                                </div>


                                                            </div>

                                                            <div class="row" style="margin-top: 2rem">

                                                                <div class="input-field col s6">
                                                                    <input id="author" name="author" type="text" value="${material.author}${author != null ? author : ''}" class="validate" >
                                                                    <label for="author" class="">Author </label> 
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input id="publisher" name="publisher" type="text" value="${material.publisher}${publisher != null ? publisher : ''}" class="validate" >
                                                                    <label for="publisher" class="">Publisher </label>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input id="edition" name="edition" type="text" value="${material.edition}${edtion != null ? edtion : ''}" class="validate" >
                                                                    <label for="edition" class="">Edition </label>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input id="isbn" name="isbn" type="text" value="${material.ISBN}${ISBN != null ? ISBN : ''}" class="validate" >
                                                                    <label for="isbn" class="">ISBN </label>
                                                                </div>
                                                            </div>


                                                            <div class="row" style="margin-top: 2rem">
                                                                <div class="input-field col s12">
                                                                    <textarea cols="10" id="description" name="description" rows="10" required="">${material.description}${description != null ? description : ''}</textarea>
                                                                    <label class="">Description <span class="text-danger">*</span></label>
                                                                </div>
                                                                <div class="input-field col s6" style="margin-top: 2rem">
                                                                    <input id="note" name="note" type="text" value="${material.note}${note != null ? note : ''}" class="validate" required="">
                                                                    <label for="note">Name<span class="text-danger">*</span> </label>
                                                                </div>
                                                            </div>


                                                            <div class="row" style="margin-top: 2rem">
                                                                <div class="input-field col s6">
                                                                    <input type="radio" id="isActive" name="isActive" ${material.isActive ? 'checked' : ''}  value="true" class="validate">
                                                                    <label for="isActive">Active</label>
                                                                    <input type="radio" id="unActive" name="isActive" ${material.isActive ? '' : 'checked'}  value="false" class="validate">
                                                                    <label for="unActive">unActive</label>
                                                                </div>
                                                            </div>

                                                            <div class="row" style="margin-top: 2rem">
                                                                
                                                                <div class="input-field col s6">
                                                                    <input type="radio" id="isMainMaterial" name="isMainMaterial" ${material.isMainMaterial ? 'checked' : ''}  value="true" class="validate">
                                                                    <label for="isMainMaterial">MainMaterial</label>
                                                                    <input type="radio" id="unMainMaterial" name="isMainMaterial" ${material.isMainMaterial ? '' : 'checked'}  value="false" class="validate">
                                                                    <label for="unMainMaterial">Not MainMaterial</label>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input type="radio" id="isHardCopy" name="isHardCopy" ${material.isHardCopy ? 'checked' : ''}  value="true" class="validate">
                                                                    <label for="isHardCopy">HardCopy</label>
                                                                    <input type="radio" id="unHardCopy" name="isHardCopy" ${material.isHardCopy ? '' : 'checked'}  value="false" class="validate">
                                                                    <label for="unHardCopy">Not HardCopy</label>
                                                                </div>

                                                                

                                                                <div class="input-field col s6">
                                                                    <input type="radio" id="isOnline" name="isOnline" ${material.isOnline ? 'checked' : ''}  value="true" class="validate">
                                                                    <label for="isOnline">Online</label>
                                                                    <input type="radio" id="unOnline" name="isOnline" ${material.isOnline ? '' : 'checked'}  value="false" class="validate">
                                                                    <label for="unOnline">Not Online</label>
                                                                </div>
                                                            </div>

                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" value="${type == null ? 'Save' : 'Add'}" class="waves-button-input"></i>
                                                                </div> 
                                                            </div>
                                                        </form>
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
        </div>

        <div id="message" hidden="">${sessionScope.message}</div>
        <%
              session.removeAttribute("message");
        %>
        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <!--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>-->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                if (document.getElementById('message').innerHTML !== '') {
                    $('.toast').toast('show');
                    $('.toast').addClass('in');

                    setTimeout(function () {
                        $('.toast').removeClass('in');

                    }, 2900);
                }

            });
        </script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>