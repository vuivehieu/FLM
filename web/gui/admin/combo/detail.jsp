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
                        <a href="comboAdd"><button class="btn btn-warning">Add Combo</button></a>
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
                                        </ul>

                                        <div class="tab-content">
                                            <div id="detail" class="tab-pane fade  active in">
                                                <div class="box-inn-sp">
                                                    <div class="inn-title">
                                                        <h4>Combo Detail</h4>
                                                    </div>
                                                    <div class="bor">
                                                        <form action="" method="post">               
                                                            <input type="text" name="cid" value="${combo.cid}" hidden>
                                                            <input type="text" name="type" value="update" hidden>
                                                            <div class="row">
                                                                <div class="input-field col s6">
                                                                    <input id="id" type="text" value="${combo.cid}" class="validate" disabled="">
                                                                    <label for="id" class="">Combo ID</label>
                                                                </div>
                                                                <div class="input-field col s6">
                                                                    <input id="tag" name="tag" type="text" value="${combo.tag}" class="validate" required>
                                                                    <label for="tag" class="">Tag <span class="text-danger">*</span></label>
                                                                    <span class="text-danger">${error}</span>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s6">
                                                                    <input id="comboNameEN" name="comboNameEN" type="text" value="${combo.comboName_EN}" class="validate" required>
                                                                    <label for="comboNameEN">Combo Name - EN <span class="text-danger">*</span></label>
                                                                </div>
                                                                <div class="input-field col s6">
                                                                    <input type="text" name="comboNameVI" class="validate" value="${combo.comboName_VI}" required>
                                                                    <label for="comboNameVI" class="">Combo Name - VI <span class="text-danger">*</span></label>
                                                                </div>
                                                            </div>
                                                            <div class="row" style="margin-top: 2rem">
                                                                <div class="input-field col s12">
                                                                    <input type="radio" id="isActive" name="isActive" ${combo.isActive ? 'checked': ''} value="true" class="validate">
                                                                    <label for="isActive">isActive</label>
                                                                    <input type="radio" id="deActive" name="isActive" ${combo.isActive ? '': 'checked'} value="false" class="validate">
                                                                    <label for="deActive">deActive</label>
                                                                </div>

                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <textarea cols="10" id="note" name="note" rows="10">${combo.note}</textarea>
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
                                                    <form action="" onsubmit="return comfirm('Add Subject')" method="post">
                                                        <input type="text" name="cid" value="${combo.cid}" hidden>
                                                        <input type="text" name="type" value="add" hidden="">

                                                        <h4>List Subject In Combo:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <div class="table-responsive table-desi">
                                                                    <table class="table table-hover">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>#</th>
                                                                                <th>SubjectCode</th>
                                                                                <th>SubjectName</th>
                                                                                <th>Semester</th>
                                                                                <th>NoCredit</th>
                                                                                <th>Status</th>
                                                                                <th>Edit</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <c:if test="${!combo.subject.isEmpty()}">
                                                                                <c:forEach begin="0" end="${combo.subject.size() - 1}" var="i">
                                                                                    <tr>
                                                                                        <td>${i + 1}</td>
                                                                                        <td>${combo.subject.get(i).subjectCode}</td>
                                                                                        <td>${combo.subject.get(i).subjectName_EN}</td>
                                                                                        <td>${combo.subject.get(i).semester}</td>
                                                                                        <td>${combo.subject.get(i).noCredit}</td>

                                                                                        <td>
                                                                                            <span class="label label-success">Active</span>
                                                                                        </td>
                                                                                        <td><a href="#!" target="_blank" class="ad-st-view">View</a></td>
                                                                                        <td onclick="comfirm('Delete Subject')"><a href="comboDetail?type=delete&cid=${combo.cid}&subjectCode=${combo.subject.get(i).subjectCode}&semester=${combo.subject.get(i).semester}" class="ad-st-view">Delete</a></td>
                                                                                    </tr>
                                                                                </c:forEach>
                                                                            </c:if>

                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div> 
                                                        <jsp:useBean class="DAL.DAO" id="dao" />
                                                        <c:set var="subject" value="${dao.getSubjectNotInCombo(combo.cid)}"/>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <h4>Add Subject To Combo: ${subject.size()}</h4>
                                                                <input type="submit"  class="" value="ADD Subject To Combo">
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
                                                        <!--                                                        <div class="row">
                                                                                                                    <div class="input-field col s12">
                                                                                                                        <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input" value="Submit"></i>
                                                                                                                    </div>
                                                                                                                </div>										-->
                                                    </form>
                                                </div>
                                            </div>
                                            <div id="menu2" class="tab-pane fade">
                                                <div class="inn-title">
                                                    <h4>Photo Gallery</h4>
                                                    <p>Airtport Hotels The Right Way To Start A Short Break Holiday</p>
                                                </div>
                                                <div class="bor">
                                                    <form action="#">
                                                        <div class="file-field input-field">
                                                            <div class="btn admin-upload-btn">
                                                                <span>File</span>
                                                                <input type="file" multiple="">
                                                            </div>
                                                            <div class="file-path-wrapper">
                                                                <input class="file-path validate" type="text" placeholder="Upload course banner image">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input" value="Upload"></i>
                                                            </div>
                                                        </div>

                                                    </form>
                                                </div>
                                            </div>
                                            <div id="menu3" class="tab-pane fade">
                                                <div class="inn-title">
                                                    <h4>Time table</h4>
                                                    <p>Airtport Hotels The Right Way To Start A Short Break Holiday</p>
                                                </div>
                                                <div class="bor ad-cou-deta-h4">
                                                    <form>
                                                        <h4>1st semester:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Title:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Descriptions:</label>
                                                            </div>
                                                        </div> 
                                                        <h4>2nd semester:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Title:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Descriptions:</label>
                                                            </div>
                                                        </div> 
                                                        <h4>3rd semester:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Title:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Descriptions:</label>
                                                            </div>
                                                        </div> 
                                                        <h4>4th semester:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Title:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Descriptions:</label>
                                                            </div>
                                                        </div> 

                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input" value="Submit"></i>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div id="menu4" class="tab-pane fade">
                                                <div class="inn-title">
                                                    <h4>Contact Info</h4>
                                                    <p>Airtport Hotels The Right Way To Start A Short Break Holiday</p>
                                                </div>
                                                <div class="bor">
                                                    <form>
                                                        <div class="row">
                                                            <div class="input-field col s6">
                                                                <input id="t5-n1" type="text" class="validate">
                                                                <label for="t5-n1">Contact Name</label>
                                                            </div>
                                                            <div class="input-field col s6">
                                                                <input id="t5-n2" type="text" class="validate">
                                                                <label for="t5-n2">Alter Contact Name</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s6">
                                                                <input id="t5-n3" type="number" class="validate">
                                                                <label for="t5-n3">Phone</label>
                                                            </div>
                                                            <div class="input-field col s6">
                                                                <input id="t5-n4" type="number" class="validate">
                                                                <label for="t5-n4">Mobile</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input id="t5-n5" type="email" class="validate">
                                                                <label for="t5-n5">Email</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <textarea id="t5-n6" class="materialize-textarea"></textarea>
                                                                <label for="t5-n6">Listing Descriptions:</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input" value="Upload"></i>
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

        <script>
            function comfirm(typeComfirm) {
                let comfirm = confirm('You Want ' + typeComfirm + ' !'); 
                        return comfirm;
            }

        </script>
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