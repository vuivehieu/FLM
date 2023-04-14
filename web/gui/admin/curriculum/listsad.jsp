<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">


    <head>
        <title>FLM</title>
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
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var table = $('#example').DataTable({
                    dom: 'lr<"table-filter-container">itp',
                    initComplete: function (settings) {
                        var api = new $.fn.dataTable.Api(settings);
                    },
                    "lengthChange": false
//                    buttons: ['copy', 'excel', 'pdf', 'colvis'],
                });
                table.buttons().container()
                        .appendTo('#example_wrapper .col-md-6:eq(0)');
                $('#editModal').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var status = button.data('status'); // Extract value from data-* attributes
                    var role = button.data('role');
                    var name = button.data('name');
                    var username = button.data('username');
                    var email = button.data('email');
                    console.log(status);
                    console.log(role);
                    // Set the selected value of the "statusRadio" radio buttons based on the "data-status" attribute
                    if (status === 1) {
                        $('#activeStatus').prop('checked', 'checked');
                    } else {
                        $('#inactiveStatus').prop('checked', 'checked');
                    }
                    $('#studentName').attr('value', name);
                    $('#studentUsername').attr('value', username);
                    $('#studentEmail').attr('value', email);
                    // Set the selected value of the "roleEdit" select box based on the "data-role" attribute
                    $('#roleSelect').val(role);
                });
                // Set the selected value of the "roleEdit" select box based on the "data-role" attribute
            });
            function submitForm() {
                $('#updateForm').submit();
            }
        </script>
        <style type="text/css">
            .table-filter-container{
                align-items: center
            }
            .filterDiv .row {
                display: flex;
                align-items: center;
                justify-content: flex-start;
                margin-left: 20px;

            }
            .dataTables_paginate{
                width: 100%;
                text-align: center;
            }
            .dataTables_info{
                width: 100%;
                text-align: center;
                padding: 18px;
            }
            .userSearchTxt{
                width: 200px !important;
                height: 50px !important;
                font-size: 14px !important;
                margin-left: 450px !important;
                color: black !important;
            }
        </style>
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
                                <li class="active-bre"><a>Curriculum List</a></li>
                            </ul>
                        </div>
                        <div class="float-right">
                            <a href="admin-adduser"><button class="btn btn-warning">Add Curriculum</button></a>
                        </div>
                    </div>


                    <!--== User Details ==-->
                    <div class="sb2-2-3">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-inn-sp">
                                    <div class="inn-title">
                                        <h4>List Curriculum</h4>
                                        <!--<p>All about students like name, student id, phone, email, country, city and more</p>-->    
                                    </div>

                                    <div class="tab-inn">

                                        <div class="table-responsive table-desi">
                                            <div class="row">
                                                <div class="col-md-6 col-sm-6"></div>
                                                <!--                                                <div class="col-md-6 col-sm-6 mob-hide">
                                                                                                    <form class="app-search">
                                                                                                        <input type="text" placeholder="Search..." class="form-control">
                                                                                                        <a href="#"><i class="fa fa-search"></i></a>
                                                                                                    </form>
                                                                                                </div>-->
<!--                                                <div id="filterDiv" class="filterDiv" style="display: none">
                                                    <div class="row">
                                                        <div class="col">
                                                            <select name="roleFilter" class="browser-default" id="roleFilter">
                                                                <option selected value="">All role</option>
                                                                <c:forEach items="${roles}" var="role">
                                                                    <option value="${role.rname}"><c:out value="${role.rname}"></c:out></option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col">
                                                            <select name="statusFilter" class="browser-default" id="statusFilter">
                                                                <option selected value="">All status</option>
                                                                <option value="Block">Block</option>
                                                                <option value="Active">Active</option>
                                                                <option value="Not Verify Email">Not Verify Email</option>
                                                            </select>
                                                        </div>
                                                        <div class="col">
                                                            <p id="nameOrder">
                                                                Sort By Name: 
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <select name="nameOrderSelect" class="browser-default" id="nameOrderSelect">
                                                                <option selected value="">None</option>
                                                                <option value="asc">Ascending</option>
                                                                <option value="desc">Descending</option>
                                                            </select>
                                                        </div>
                                                        <div class="col">
                                                            <input type="text" name="userSearchTxt" class="userSearchTxt" id="userSearchTxt" value="" placeholder="Search for user..."/>
                                                        </div>
                                                    </div>
                                                </div>-->
                                            </div>

                                            <table id="example" class="table table-hover" style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th>Curriculum Code</th>
                                                        <th>Curriculum Name(EN)</th>
                                                        <th>Curriculum Name(VI)</th>
                                                        <th>Description</th>
                                                        <th>Approved</th>
                                                        <th>Status</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${curriculums}" var="item">
                                                        <tr>
                                                            <td><span class="list-img"><img src="${item.image}" alt=""></span></td>
                                                            <td>
                                                                <a href="#">
                                                                    <span class="list-enq-name">${item.curCode}</span>
                                                                </a>
                                                            </td>
                                                            <td>${item.curName_EN}</td>
                                                            <td>${item.curName_VI}</td>
                                                            <td>${item.description}</td>
                                                            <td>
                                                                <span class="label ${!item.isApproved ? 'label-danger' : 'label-success'}">
                                                                    ${!item.isApproved ? 'Not Approved' :  'Approved'}</span>
                                                            </td>
                                                            <td>
                                                                <span class="label ${!item.isActive ? 'label-danger' : 'label-success'}">
                                                                    ${!item.isActive ? 'Inactive' : 'Active'}</span>
                                                            </td>
                                                            <td>
                                                                <button type="button" class="ad-st-view" data-toggle="modal" data-target="#editModal">Edit</button>
                                                                <!--<a href="admin-student-details.html" class="ad-">Block</a>-->
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>
                                            </table>


                                        </div>
                                        <!--                                        <div class="pg-pagina">
                                                                                    <ul class="pagination">
                                                                                        <li class="disabled"><a href="#!">Pre</a></li>
                                                                                        <li class="active"><a href="#!">1</a></li>
                                                                                        <li class="waves-effect"><a href="#!">2</a></li>
                                                                                        <li class="waves-effect"><a href="#!">3</a></li>
                                                                                        <li class="waves-effect"><a href="#!">4</a></li>
                                                                                        <li class="waves-effect"><a href="#!">5</a></li>
                                                                                        <li class="waves-effect"><a href="#!">Next</a></li>
                                                                                    </ul>
                                        
                                                                                </div>-->
                                    </div>
                                </div>
                            </div>
                        </div>


<!--                        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="margin-top: 100px;">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Edit Student</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="admin-updateuser" id="updateForm" method="post">
                                            <div class="form-group">
                                                <label for="studentName" class="col-form-label">Name</label>
                                                <input type="text" id="studentName" name="studentName" value="" disabled="disabled" style="font-size: 15px; color: black"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="studentUsername" class="col-form-label">Username</label>
                                                <input type="text" value="" id="studentUsername" name="studentUsername" style="font-size: 15px; color: black; display: none"/>
                                                <input type="text" value="" id="studentUsername2" name="studentUsername" disabled="disabled" style="font-size: 15px; color: black"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="studentEmail" class="col-form-label">Email</label>
                                                <input type="text" value="" id="studentEmail" name="studentEmail" disabled="disabled" style="font-size: 15px; color: black"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="roleSelect" class="col-form-label">Role</label>
                                                <select name="roleEdit" class="browser-default" id="roleSelect" required="true">
                                                    <c:forEach items="${roles}" var="role">
                                                        <option value="${role.rid}"><c:out value="${role.rname}"></c:out></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <p>Status</p>
                                                <input type="radio" id="activeStatus" name="statusRadio" value="1">
                                                <label for="activeStatus">Active</label><br>
                                                <input type="radio" id="inactiveStatus" name="statusRadio" value="0">
                                                <label for="inactiveStatus">Inactive</label><br>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" onclick="submitForm()">Save</button>
                                    </div>
                                </div>
                            </div>
                        </div>-->
                    </div>
                </div>

            </div>
        </div>

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap4.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.2/js/dataTables.buttons.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.2/js/buttons.bootstrap4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.2/js/buttons.html5.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.2/js/buttons.print.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.2/js/buttons.colVis.min.js"></script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>