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
                            <li><a href="sessionList"><i class="fa fa-home" aria-hidden="true"></i> Assessment List</a></li>
                            <li class="active-bre"><a > Assessment Details</a></li>
                        </ul>
                    </div>

                    <!--== User Details ==-->
                    <div class="sb2-2-3">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-inn-sp admin-form">
                                    <div class="sb2-2-add-blog sb2-2-1">
                                        <h2>Assessment ${type != null ? type eq 'add' ? 'Add' : 'Detail' : 'Detail' }</h2>

                                        <ul class="nav nav-tabs tab-list">
                                            <li class="active"><a data-toggle="tab" href="#detail" aria-expanded="true"><i class="fa fa-info" aria-hidden="true"></i> <span>Detail</span></a></li>
                                        </ul>
                                        <jsp:useBean class="Custom.ForJSP" id="custom" />
                                        <jsp:useBean class="DAL.DAO" id="dao" />
                                        <c:set var="allSyllabusOwn" value="${dao.getSyllabusByAccountID(account.accountID)}"/>
                                        <c:set var="allDecision" value="${dao.allDecision}"/>


                                        <div class="tab-content">
                                            <div id="detail" class="tab-pane fade in active">
                                                <div class="box-inn-sp">

                                                    <div class="bor">
                                                        <form action="" method="post" enctype="multipart/form-data">               
                                                            <input type="text" name="assid" value="${ass.assid}" hidden>
                                                            <input type="text" name="type" value="${type eq 'add' ? 'add' : 'update'}" hidden>

                                                            <div class="row">
                                                                <div class="input-field col s12" style="margin-bottom: 12px">
                                                                    <select name="slbid">
                                                                        <c:forEach items="${allSyllabusOwn}" var="item">
                                                                            <option value="${item.slbid}" ${item.slbid == ass.assid ? 'selected' : ''} ${slbid != nulll ? assid == item.slbid ? 'selected' : '' : ''}>
                                                                                ${item.slbid} - ${item.subjectCode} 
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>

                                                                    <label for="tag" class="">Syllabus <span class="text-danger">*</span></label>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input id="category" name="category" type="text" value="${ass.category}${category != null ? category : ''}" class="validate" required>
                                                                    <label for="category" class="">Category <span class="text-danger">*</span></label>
                                                                    <c:if test="${error != null}">
                                                                        ${error}
                                                                    </c:if>
                                                                </div>

                                                                <div class="input-field col s6" style="margin-bottom: 12px">
                                                                    <select name="typeAss">
                                                                        <option value="on-going" >On-Going</option>
                                                                        <option value="online">Online</option>
                                                                        <option value="offline">Offline</option>
                                                                        <label for="type" class="">Type <span class="text-danger">*</span></label>
                                                                    </select>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input id="weight" name="weight" type="text" value="${ass.weight}" class="validate" required>
                                                                    <label for="weight" class="">Weight <span class="text-danger">*</span></label>
                                                                    <c:if test="${error != null}">
                                                                        ${error}
                                                                    </c:if>
                                                                </div>

                                                                <div class="input-field col s6" style="margin-bottom: 12px">
                                                                    <select name="part">
                                                                        <option value="1">1</option>
                                                                        <option value="2">2</option>
                                                                        <option value="3">3</option>
                                                                        <option value="4">4</option>
                                                                        <option value="5">5</option>
                                                                        <option value="6">6</option>
                                                                        <option value="7">7</option>
                                                                        <option value="8">8</option>
                                                                        <option value="9">9</option>
                                                                        <option value="10">10</option>
                                                                        <label for="part" class="">Part <span class="text-danger">*</span></label>
                                                                    </select>
                                                                </div>
                                                            </div>

                                                            <div class="row" style="margin-bottom: 12px">

                                                                <div class="input-field col s12">
                                                                    <textarea cols="10" id="duration" name="duration" rows="10" required="">${ass.duration}${duration != null ? duration : ''}</textarea>
                                                                    <label class="">Duration </label>
                                                                </div>

                                                                <div class="input-field col s12">
                                                                    <textarea cols="10" id="questionType" name="questionType" rows="10" required="">${ass.questionType}${questionType != null ? questionType : ''}</textarea>
                                                                    <label class="">Question Type </label>
                                                                </div>

                                                                <div class="input-field col s12">
                                                                    <textarea cols="10" id="noQuestion" name="noQuestion" rows="10" required="">${ass.noQuestion}${noQuestion != null ? noQuestion : ''}</textarea>
                                                                    <label class="">No Question </label>
                                                                </div>



                                                            </div>


                                                            <div class="row" style="margin-top: 2rem">
                                                                <div class="input-field col s6">
                                                                    <input id="knowledge_Skill" name="knowledge_Skill" type="text" value="${ass.knowledgeSkill}${knowledgeSkill != null ? knowledgeSkill : ''}" class="validate" required>
                                                                    <label for="knowledge_Skill">Knowledge_Skill <span class="text-danger">*</span></label>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input id="gradingGuide" name="gradingGuide" type="text" value="${ass.gradingGuide}${gradingGuide != null ? gradingGuide : ''}" class="validate" required>
                                                                    <label for="gradingGuide">Grading Guide</label>
                                                                </div>



                                                            </div>
                                                            <div class="row">
                                                                
                                                                <div class="input-field col s6" style="margin-top: 12px">
                                                                    <input type="radio" id="isActive" name="isActive" ${ass.isActive ? 'checked' : ''} ${isActive != null ? isActive ? 'checked' : '' : ''} value="true" class="validate">
                                                                    <label for="isActive">Active</label>
                                                                    <input type="radio" id="unActive" name="isActive" ${ass.isActive ? '' : 'checked'} ${isActive != null ? isActive ? '' : 'checked' : ''} value="false" class="validate" >
                                                                    <label for="unActive">unActive</label>
                                                                </div>

                                                                <div class="input-field col s12">
                                                                    <textarea cols="10" id="note" name="note" rows="10" required="">${ass.note}${note != null ? note : ''}</textarea>
                                                                    <label class="">Note </label>
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