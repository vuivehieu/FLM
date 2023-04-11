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

        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css"/>
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
                top: -12px;
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
                            <li><a href="dashboard"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="active-bre"><a href="#"> Dashboard</a></li>
                            <li class="page-back"><a href="#!" onclick="history.back()"><i class="fa fa-backward" aria-hidden="true"></i> Back</a></li>
                        </ul>

                    </div>
                    <div class="float-right">
                        <a href=""><button class="btn btn-warning">Add Combo</button></a>
                    </div>

                    <!--== User Details ==-->
                    <div class="sb2-2-3">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-inn-sp admin-form">
                                    <div class="sb2-2-add-blog sb2-2-1">
                                        <h2>Combo Detail</h2>

                                        <ul class="nav nav-tabs tab-list">
                                            <li class="active"><a data-toggle="tab" href="#detail" aria-expanded="true"><i class="fa fa-info" aria-hidden="true"></i> <span>Detail</span></a>
                                            </li>
                                            <li class=""><a data-toggle="tab" href="#listSubject" aria-expanded="false"><i class="fa fa-bed" aria-hidden="true"></i> <span>Subject</span></a>
                                            </li>
                                            <!--                                            <li class=""><a data-toggle="tab" href="#menu2" aria-expanded="false"><i class="fa fa-picture-o" aria-hidden="true"></i> <span>Banner Image</span></a>
                                                                                        </li>
                                                                                        <li class=""><a data-toggle="tab" href="#menu3" aria-expanded="false"><i class="fa fa-facebook" aria-hidden="true"></i> <span>Time table</span></a>
                                                                                        </li>
                                                                                        <li class=""><a data-toggle="tab" href="#menu4" aria-expanded="false"><i class="fa fa-phone" aria-hidden="true"></i> <span>Contact Info</span></a>
                                                                                        </li>-->
                                        </ul>

                                        <div class="tab-content">
                                            <div id="detail" class="tab-pane fade  active in">
                                                <div class="box-inn-sp">
                                                    <div class="inn-title">
                                                        <h4>Combo Detail</h4>
                                                    </div>
                                                    <div class="bor">
                                                        <form action="" method="post">               
                                                            <input type="text" name="cid" value="" hidden>
                                                            <input type="text" name="type" value="update" hidden>
                                                            <div class="row">

                                                                <div class="input-field col s6">
                                                                    <input id="tag" name="tag" type="text" value="${tag}" class="validate" required>
                                                                    <label for="tag" class="">Tag <span class="text-danger">*</span></label>
                                                                    <span class="text-danger">${error}</span>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s6">
                                                                    <input id="comboNameEN" name="comboNameEN" type="text" value="${comboNameEN}" class="validate" required>
                                                                    <label for="comboNameEN">Combo Name - EN <span class="text-danger">*</span></label>
                                                                </div>
                                                                <div class="input-field col s6">
                                                                    <input type="text" name="comboNameVI" class="validate" value="${comboNameVI}" required>
                                                                    <label for="comboNameVI" class="">Combo Name - VI <span class="text-danger">*</span></label>
                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-top: 2rem">
                                                                <div class="input-field col s12">
                                                                    <input type="radio" id="isActive" name="isActive" ${isActive ? 'checked' : ''} value="true" class="validate">
                                                                    <label for="isActive">isActive</label>
                                                                    <input type="radio" id="deActive" name="isActive" ${isActive ? '' : 'checked'} value="false" class="validate">
                                                                    <label for="deActive">deActive</label>
                                                                </div>

                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <textarea cols="10" id="note" name="note" rows="10">${note}</textarea>
                                                                    <label class="">Note</label>
                                                                    <script>
                                                                        CKEDITOR.replace('note');
                                                                    </script>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input"></i>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="listSubject" class="tab-pane fade">
                                                <div class="inn-title">
                                                    <h4>Add, Delete Subject Of Combo</h4>
                                                </div>
                                                <div class="bor ad-cou-deta-h4">
                                                    <form action="" method="post">
                                                        <input type="text" name="cid" value="" hidden>
                                                        <input type="text" name="type" value="add" hidden="">
                                                        <jsp:useBean class="DAL.DAO" id="dao" />
                                                        <c:set var="subject" value="${dao.getSubjectNotInCombo(combo.cid)}"/>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <h4>Add Subject To Combo: ${subject.size()}</h4>
                                                                <input type="submit" class="" value="ADD Subject To Combo">
                                                            </div>
                                                        </div> 
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <div class="">
                                                                    <table class="table table-hover table-responsive table-desi" id="tableSubject">
                                                                        <thead>
                                                                            <tr>
                                                                                <th></th>
                                                                                <th>#</th>
                                                                                <th>SubjectCode</th>
                                                                                <th>SubjectName</th>
                                                                                <th>Semester</th>
                                                                                <th>NoCredit</th>

                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <c:forEach begin="0" end="${subject.size() - 1}" var="i">
                                                                                <tr>
                                                                                    <td><input type="checkbox" name="selectSubject" value="${subject.get(i).subjectCode}"></td>
                                                                                    <td>${i + 1}</td>
                                                                                    <td>${subject.get(i).subjectCode}</td>
                                                                                    <td>${subject.get(i).subjectName_EN}</td>
                                                                                    <td>${subject.get(i).semester}</td>
                                                                                    <td>${subject.get(i).noCredit}</td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
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
