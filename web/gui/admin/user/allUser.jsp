<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width,initial-scale=1" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Archivo:ital,wght@0,400;0,500;0,600;0,700;1,400&amp;display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
        <link rel="stylesheet" href="<c:url value="/admin-template/css/vendors.bundle.min.css"/>"/>
        <link rel="stylesheet" href="admin-template/vendors/datatables.net-bs4/css/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" href="admin-template/css/main.bundle.min.css"/>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js" defer="defer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
            <title>User Management</title>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script type="text/javascript">
                $(document).ready(function () {
                    var table = $('#example').DataTable({
                        dom: 'lr<"table-filter-container">itp',
                        initComplete: function (settings) {
                            var api = new $.fn.dataTable.Api(settings);
                            $('.table-filter-container', api.table().container()).append(
                                    $('#filterDiv').detach().show(),
                                    );
                            $('#nameOrderSelect').on('change', function () {
                                table.order([2, this.value]).draw();
                                if (this.value === '') {
                                    table.order().draw();
                                }
                            });
                            $('#userSearchTxt').on('keyup', function () {
                                table.search(this.value).draw();
                            });
                            $('#roleFilter').on('change', function () {
                                table.columns(4).search(this.value).draw();
                            });
                            $('#statusFilter').on('change', function () {
                                table.columns(5).search(this.value).draw();
                            });
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
        <div class="app-admin-wrap-layout-1 sidebar-full sidebar-theme-slate">
            <!--== MAIN CONTRAINER ==-->
            <jsp:include page="../common/heading/heading.jsp"/>

            <!--== BODY CONTNAINER ==-->
            <div class="main-content-wrap">
                <!-- Start::Main header  -->
                <header class="main-header bg-card d-flex flex-row justify-content-between align-items-center px-lg">
                    <!-- Start::Header menu-->
                    <!-- End::Header menu-->
                </header>
                <div class="main-content-body">
                    <!-- Start:: content (Your custom content)  -->
                    <div class="subheader px-lg" style="padding: 0">
                        <div class="subheader-container">
                            <div class="subheader-main">
                                <nav aria-label="breadcrumb" class="ul-breadcrumb">
                                    <ol class="ul-breadcrumb-items">
                                        <li class="breadcrumb-home"><a href="#"> <i class="material-icons"
                                                                                    >home</i></a></li>
                                        <li class="breadcrumb-item"><a href="#">FLM</a></li>
                                        <li class="breadcrumb-item active"><a href="#">User Management</a></li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="row align-items-center">
                                    <div class="col-sm-12 col-md-6">
                                        <div class="input-group" style="position: relative;width: 25%;margin-left: auto;margin-bottom: 15px;float: left">
                                            <!--                                    <select class="form-control js-basic-example2" id="filterType" style="margin-left:-2%;background: #cfcfcf;border-radius: 20px;padding: 10px 20px;" onchange="filter()">-->
                                            <!--                                        <option value="application" ${filter == 'application' ? 'selected' : ''}><spring:message code="file.media"/></option>-->
                                            <!--                                        <option value="image" ${filter == 'image' ? 'selected' : ''}><spring:message code="file.file"/></option>-->
                                            <!--                                    </select>-->
                                            <div class="input-group-append" style="position: absolute;right: 0;z-index: 10;">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-6">
                                        <div class="input-group" style="position: relative;width: 75%;margin-left: auto;margin-bottom: 15px;">
                                            <input class="form-control" id="textSearch" value="" type="text" placeholder="Search for result" style="margin-left:-2%;background: #cfcfcf;border-radius: 20px;padding: 10px 20px;">
                                                <div class="input-group-append" style="position: absolute;right: 0;z-index: 10;">
                                                    <button class="btn btn-secondary"  type="button">
                                                        <i class="fa fa-search"></i>
                                                    </button>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-header" style="background: #242939 ;display: flex;justify-content: space-between;">
                                    <h2 class="p-1 m-0 text-16 font-weight-semi " style="color: white">User Management</h2>
                                    <div style="color: white" class="p-1 m-0 text-16">
                                                                        <i class="fa fa-plus" aria-hidden="true" data-toggle="modal" data-target="#ModalAdd" style="cursor: pointer"></i>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body table-responsive">
                                        <table id="example" style="text-align: center" class="table">
                                            <tbody>
                                                <tr>
                                                    <th style="width:10%;height: auto;">Avatar</th>
                                                    <th>Name</th>
                                                    <th>UserName</th>
                                                    <th>Email</th>
                                                    <th>Role</th>
                                                    <th>Status</th>
                                                    <th></th>
                                                </tr>
                                                <c:forEach items="${list}" var="user">
                                                    <tr>
                                                        <td><span class="list-img"><img src="${user.avatar}"></span></td>
                                                        <td>
                                                            <a href="#">
                                                                <span class="list-enq-name">${user.displayName}</span>
                                                            </a>
                                                        </td>
                                                        <td>${user.userName}</td>
                                                        <td>${user.email}</td>
                                                        <td>${user.role.rname}</td>
                                                        <td>
                                                            <c:if test="${user.status == 1}">
                                                                <span class="badge badge-success">Active</span>
                                                            </c:if>
                                                            <c:if test="${user.status == 0}">
                                                                <span class="badge badge-danger">Inactive</span>
                                                            </c:if>
                                                            <c:if test="${user.status == 2}">
                                                                <span class="badge badge-info">Not Verify Email</span>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <button id="btnDetail" onclick="handleUpdate(${item.id})"
                                                                    class="btn text-primary rounded-circle m-0 btn-sm btn-icon"
                                                                    style="height: 0px !important;"><i
                                                                    class="material-icons">edit</i>
                                                                <div class="ripple-container"></div>
                                                            </button>
                                                            <button onclick="handleDelete(${item.id})"
                                                                    class="btn text-danger rounded-circle m-0 btn-sm btn-icon"
                                                                    style="height: 0px !important;"><i
                                                                    class="material-icons">delete</i></button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!---->
                    <!-- End:: Footer-->
                </div>
                <!-- End::Main header-->
            </div>
        </div>
    </body>
    <!--        <div class="container-fluid sb2">
                <div class="row">
    <%--<jsp:include page="../common/sidebar/sidebarLeft.jsp"/>--%>

    == BODY INNER CONTAINER ==
    <div class="sb2-2">
        == breadcrumbs ==
        <div class="d-flex">
            <div class="sb2-2-2">
                <ul>
                    <li><a href="dashboard"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                    <li class="active-bre"><a>Users</a></li>
                </ul>
            </div>
            <div class="float-right">
                <a href="admin-adduser"><button class="btn btn-warning">Add User</button></a>
            </div>
        </div>


        == User Details ==
        <div class="sb2-2-3">
            <div class="row">
                <div class="col-md-12">
                    <div class="box-inn-sp">
                        <div class="inn-title">
                            <h4>List User</h4>
                            <p>All about students like name, student id, phone, email, country, city and more</p>    
                        </div>

                        <div class="tab-inn">

                            <div class="table-responsive table-desi">
                                <div class="row">
                                    <div class="col-md-6 col-sm-6"></div>
                                                                                    <div class="col-md-6 col-sm-6 mob-hide">
                                                                                        <form class="app-search">
                                                                                            <input type="text" placeholder="Search..." class="form-control">
                                                                                            <a href="#"><i class="fa fa-search"></i></a>
                                                                                        </form>
                                                                                    </div>
                                    <div id="filterDiv" class="filterDiv" style="display: none">
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
</div>
</div>

<table id="example" class="table table-hover" style="width:100%">
<thead>
<tr>
<th></th>
<th>Name</th>
<th>UserName</th>
<th>Email</th>
<th>Role</th>
<th>Status</th>
<th></th>
</tr>
</thead>
<tbody>
    <c:forEach items="${list}" var="user">
        <tr>
            <td><span class="list-img"><img src="${user.avatar}" alt=""></span></td>
            <td>
                <a href="#">
                    <span class="list-enq-name">${user.displayName}</span>
                </a>
            </td>
            <td>${user.userName}</td>
            <td>${user.email}</td>
            <td>${user.role.rname}</td>
            <td>
                <span class="label ${user.status == 0 ? 'label-danger' : user.status == 1 ? 'label-success' : user.status ==2 ? 'label-warning' : ''}">
        ${user.status == 0 ? 'Block' : user.status == 1 ? 'Active' : user.status == 2 ? 'Not Verify Email' : '' }</span>
</td>
<td>
    <button type="button" class="ad-st-view" data-toggle="modal" data-target="#editModal" data-status="${user.status}" data-role="${user.role.rid}" data-name="${user.displayName}" data-username="${user.userName}" data-email="${user.email}">Edit</button>
    <a href="admin-student-details.html" class="ad-">Block</a>
</td>
</tr>
    </c:forEach>

</tbody>
</table>


</div>
                                <div class="pg-pagina">
                                    <ul class="pagination">
                                        <li class="disabled"><a href="#!">Pre</a></li>
                                        <li class="active"><a href="#!">1</a></li>
                                        <li class="waves-effect"><a href="#!">2</a></li>
                                        <li class="waves-effect"><a href="#!">3</a></li>
                                        <li class="waves-effect"><a href="#!">4</a></li>
                                        <li class="waves-effect"><a href="#!">5</a></li>
                                        <li class="waves-effect"><a href="#!">Next</a></li>
                                    </ul>

                                </div>
</div>
</div>
</div>
</div>


<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="margin-top: 100px;">
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
    <%--<c:forEach items="${roles}" var="role">--%>
        <option value="${role.rid}"><c:out value="${role.rname}"></c:out></option>
    <%--</c:forEach>--%>
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
</div>
</div>
</div>

</div>
</div>

Import jQuery before materialize.js
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
</body>-->


</html>