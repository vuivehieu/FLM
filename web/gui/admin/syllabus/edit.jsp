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

       <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,400;0,500;0,600;0,700;1,400&amp;display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
<!--        <link rel="stylesheet" href="admin-template/css/vendors.bundle.min.css" />
        <link rel="stylesheet" href="admin-template/vendors/datatables.net-bs4/css/dataTables.bootstrap4.min.css" />
        <link rel="stylesheet" href="admin-template/css/main.bundle.min.css"/>-->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js" defer="defer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
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
        <%--<jsp:include page="../common/heading/heading.jsp"/>--%>


        <!--== BODY CONTNAINER ==-->
        <div class="container-fluid sb2">
            <div class="row">
                <jsp:include page="../common/sidebar/sidebarLeft.jsp"/>


                <!--== BODY INNER CONTAINER ==-->
                <div class="sb2-2">
                    <!--== breadcrumbs ==-->
                    <div class="sb2-2-2">
                        <ul>
                            <li><a href="syllabusList"><i class="fa fa-home" aria-hidden="true"></i> Syllabus List</a></li>
                            <li class="active-bre"><a > Syllabus Update</a></li>
                        </ul>
                    </div>

                    <!--== User Details ==-->
                    <div class="sb2-2-3">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-inn-sp admin-form">
                                    <div class="sb2-2-add-blog sb2-2-1">
                                        <h2>Syllabus Update</h2>

                                        <ul class="nav nav-tabs tab-list">
                                            <li class="active"><a data-toggle="tab" href="#detail" aria-expanded="true"><i class="fa fa-info" aria-hidden="true"></i> <span>Detail</span></a></li>
                                        </ul>
                                        <jsp:useBean class="Custom.ForJSP" id="custom" />
                                        <jsp:useBean class="DAL.DAO" id="dao" />
                                        <c:set var="allSubject" value="${dao.allSubject}"/>
                                        <c:set var="allDecision" value="${dao.allDecision}"/>


                                        <div class="tab-content">
                                            <div id="detail" class="tab-pane fade in active">
                                                <div class="box-inn-sp">

                                                    <div class="bor">
                                                        <form action="syllabusUpdate" method="post">               
                                                            <input type="text" name="slbid" value="${syllabus.slbid}" hidden>
                                                            <input type="text" name="type" value="${type eq 'add' ? 'add' : 'update'}" hidden>

                                                            <div class="row" style="margin-bottom: 2rem;">
                                                                <div class="input-field col s6">
                                                                    <select name="subjectCode">
                                                                        <c:forEach items="${allSubject}" var="item">
                                                                            <option value="${item.subjectCode}" ${item.subjectCode == syllabus.subjectCode ? 'selected' : ''}>
                                                                                ${item.subjectCode} 
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>

                                                                    <label for="tag" class="" style=" font-weight: bold; color: black; ">Subject Code: ${syllabus.subjectCode}<span class="text-danger">*</span></label>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <select name="decisionNo">
                                                                        <c:forEach items="${allDecision}" var="item">
                                                                            <option value="${item.decisionNo}" ${item.decisionNo == syllabus.decision.decisionNo ? 'selected' : ''}>
                                                                                ${item.decisionNo} 
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>

                                                                    <label for="tag" class="" style=" font-weight: bold; color: black; ">Decision No: ${syllabus.decision.decisionNo}<span class="text-danger">*</span></label>
                                                                </div>
                                                            </div>

                                                            <div class="row" style="margin-bottom: 2rem;">
                                                                <div class="input-field col s6">
                                                                    <input id="tag" name="slbName_EN" type="text" value="${syllabus.slbName_EN}" class="validate" required>
                                                                    <label style=" font-weight: bold; color: black; " for="tag" class="">Syllabus Name EN <span class="text-danger">*</span></label>
                                                                </div>

                                                                <div class="input-field col s6">
                                                                    <input id="name" name="slbName_VI" type="text" value="${syllabus.slbName_VI}" class="validate" required>
                                                                    <label style=" font-weight: bold; color: black; " for="name">Syllabus Name VI <span class="text-danger">*</span></label>
                                                                </div>
                                                            </div>

                                                            <div class="row" style="margin-bottom: 2rem;">
                                                                <div class="input-field col s6">
                                                                    <input id="subjectNameEN" name="degreeLevel" type="text" value="${syllabus.degreeLevel}" class="validate" required>
                                                                    <label style=" font-weight: bold; color: black; " for="subjectNameEN">Degree Level</label>
                                                                </div>
                                                                <div class="input-field col s6">
                                                                    <input id="subjectNameVI" type="text" name="timeAllocation" value="${syllabus.timeAllocation}" class="validate"  required>
                                                                    <label style=" font-weight: bold; color: black; " for="subjectNameVI" class="">Time Allocation </label>
                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-top: 2rem">
                                                                <div class="input-field col s6">
                                                                    <input type="radio" id="isActive" name="isActive" ${syllabus.isActive ? 'checked' : ''} value="true" class="validate">
                                                                    <label style=" font-weight: bold; color: black; " for="isActive">Active</label>
                                                                    <input type="radio" id="unActive" name="isActive" ${syllabus.isActive ? '' : 'checked'} value="false" class="validate" >
                                                                    <label style=" font-weight: bold; color: black; " for="unActive">unActive</label>
                                                                </div>
                                                                <div class="input-field col s6">
                                                                    <input type="radio" id="isApproved" name="isApproved" ${syllabus.isApproved ? 'checked' : ''} value="true" class="validate">
                                                                    <label style=" font-weight: bold; color: black; " for="isApproved">Approved</label>
                                                                    <input type="radio" id="unApproved" name="isApproved" ${syllabus.isApproved ? '' : 'checked'} value="false" class="validate" >
                                                                    <label style=" font-weight: bold; color: black; " for="unApproved">unApproved</label>
                                                                </div>

                                                            </div>
                                                            <div class="row" style="margin-bottom: 2rem;">

                                                                <div class="input-field col s6">
                                                                    <input type="number" id="minToPass" name="minToPass" value="${syllabus.minAvgMarkToPass}">
                                                                    <label style=" font-weight: bold; color: black; " class="">MinToPass</label>

                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-bottom: 2rem;">

                                                                <div class="input-field col s12" style="margin-bottom: 2rem;">
                                                                    <textarea cols="20" id="studentTask" name="studentTask" rows="20">${syllabus.studentTask}</textarea>
                                                                    <label style=" font-weight: bold; color: black; " class="">Student Task</label>

                                                                </div>

                                                                <div class="input-field col s12" style="margin-bottom: 2rem;">
                                                                    <textarea cols="10" id="tool" name="tool" rows="10">${syllabus.tool}</textarea>
                                                                    <label style=" font-weight: bold; color: black; " class="">Tool</label>
                                                                </div>

                                                                <div class="input-field col s12" style="margin-bottom: 2rem;">
                                                                    <textarea cols="10" id="note" name="note" rows="10">${syllabus.note}</textarea>
                                                                    <label style=" font-weight: bold; color: black; " class="">Note</label>
                                                                </div>

                                                                <div class="input-field col s12" style="margin-bottom: 2rem;">
                                                                    <textarea cols="10" id="description" name="description" rows="10">${syllabus.description}</textarea>
                                                                    <label style=" font-weight: bold; color: black; " class="">Description</label>
                                                                    <script>
                                                                        CKEDITOR.replace('description');
                                                                    </script>
                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-bottom: 2rem;">
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
         <script src="admin-template/js/vendors.bundle.min.js"></script>
                    <script src="admin-template/js/main.bundle.min.js"></script>
                    <script src="admin-template/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
                    <script src="admin-template/vendors/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
                    <script src="admin-template/js/pages/datatables/basicDatatable.min.js"></script>
</body>


</html>