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
        <link rel="stylesheet" href="admin-template/css/vendors.bundle.min.css" />
        <link rel="stylesheet" href="admin-template/vendors/datatables.net-bs4/css/dataTables.bootstrap4.min.css" />
        <link rel="stylesheet" href="admin-template/css/main.bundle.min.css"/>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js" defer="defer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
            <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <title>User Management</title>

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
                                            <select class="form-control js-basic-example2" id="filterRole" style="margin-left:-2%;background: #cfcfcf;border-radius: 20px;padding: 10px 20px;" onchange="filter()">
                                                <option value="0">All</option>
                                                <c:forEach items="${roles}" var="role">
                                                    <option value="${role.rid}" ${filterRole == role.rid? 'selected' : ''}><c:out value="${role.rname}"/></option>
                                                </c:forEach>
                                            </select>
                                            <div class="input-group-append" style="position: absolute;right: 0;z-index: 10;">
                                            </div>
                                        </div>
                                        <div class="input-group" style="position: relative;width: 30%;margin-left: 25px;margin-bottom: 15px;float: left">
                                            <select class="form-control js-basic-example2" id="filterStatus" style="margin-left:-2%;background: #cfcfcf;border-radius: 20px;padding: 10px 20px;" onchange="filter()">
                                                <option value="3">All</option>
                                                <option value="0" ${filterStatus == 0? 'selected' : ''}>Inactive</option>
                                                <option value="1" ${filterStatus == 1? 'selected' : ''}>Active</option>
                                                <option value="2" ${filterStatus == 2? 'selected' : ''}>Not Verify Email</option>
                                            </select>
                                            <div class="input-group-append" style="position: absolute;right: 0;z-index: 10;">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-6">
                                        <div class="input-group" style="position: relative;width: 75%;margin-left: auto;margin-bottom: 15px;">
                                            <input class="form-control" id="textSearch" value="${search eq ''? '' : search}" type="text" placeholder="Search for result" style="margin-left:-2%;background: #cfcfcf;border-radius: 20px;padding: 10px 20px;">
                                                <div class="input-group-append" style="position: absolute;right: 0;z-index: 10;">
                                                    <button class="btn btn-secondary"  type="button" onclick="search()">
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
                                                    <th class="sort-handler cursor-pointer">Name</th>
                                                    <th class="sort-handler cursor-pointer">UserName</th>
                                                    <th class="sort-handler cursor-pointer">Email</th>
                                                    <th class="sort-handler cursor-pointer">Role</th>
                                                    <th >Status</th>
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
                                                            <button id="btnDetail"
                                                                    data-status="${user.status}" data-role="${user.role.rid}" data-name="${user.displayName}" data-username="${user.userName}" data-email="${user.email}" data-avatar="${user.avatar}" data-userid="${user.accountID}" data-password="${user.password}"
                                                                    data-target="#editModal"
                                                                    data-toggle="modal"
                                                                    aria-hidden="true"
                                                                    class="btn text-primary rounded-circle m-0 btn-sm btn-icon"
                                                                    style="height: 0px !important;"><i
                                                                    class="material-icons">edit</i>
                                                                <div class="ripple-container"></div>
                                                            </button>
                                                            <button
                                                                class="btn text-danger rounded-circle m-0 btn-sm btn-icon"
                                                                style="height: 0px !important;" onclick="document.getElementById('deleteLink').href = 'admin-deleteuser?id=${user.accountID}&pageNo=${pagination.pageNo}&search=${search}&filter=${filter}';
                                                                        openModal(${result.id})"><i
                                                                    class="material-icons">delete</i></button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <c:if test="${totalPages == 0}">
                                            <h4 style="text-align: center"> Record not found !</h4>
                                        </c:if>
                                        <ul class="pagination justify-content-center font-weight-bold">
                                            <li class="page-item">
                                                <c:if test="${pagination.pageNo > 1}">
                                                    <button class="page-link" ><i class="material-icons " onclick="onPage(1,${pagination.pageSize}, '${search}', '${filterRole}', '${filterStatus}')">keyboard_double_arrow_left</i>
                                                    </c:if>
                                            </li>
                                            <li class="page-item">
                                                <c:if test="${pagination.pageNo > 1}">
                                                    <button class="page-link" ><i class="material-icons " onclick="onPage(${pagination.pageNo - 1},${pagination.pageSize}, '${search}', '${filterRole}', '${filterStatus}')">keyboard_arrow_left</i>
                                                    </c:if>
                                            </li>
                                            <c:forEach var="page" begin="1" end="${totalPages}">
                                                <li aria-current="page" class="page-item ${pagination.pageNo == page? 'active' :''}">
                                                    <c:choose>
                                                        <c:when test="${page == pagination.pageNo}">
                                                            <button class="page-link page-number">${page}</button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button class="page-link page-number" onclick="onPage(${page},${pagination.pageSize}, '${search}', '${filterRole}', '${filterStatus}')">${page}</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item">
                                                <c:if test="${not empty list && pagination.pageNo != totalPages}">
                                                    <button class="page-link" onclick="onPage(${pagination.pageNo+1},${pagination.pageSize}, '${search}', '${filterRole}', '${filterStatus}')"><i class="material-icons">keyboard_arrow_right</i>
                                                    </button>
                                                </c:if>
                                            </li>
                                            <li class="page-item">
                                                <c:if test="${not empty list && pagination.pageNo != totalPages}">
                                                    <button class="page-link" onclick="onPage(${totalPages},${pagination.pageSize}, '${search}', '${filterRole}', '${filterStatus}')"><i class="material-icons">keyboard_double_arrow_right</i>
                                                    </c:if>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div aria-hidden="true" aria-labelledby="exampleModalLabel" class="modal fade" id="ModalDelete" role="dialog"
                         tabindex="-1">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header" style="background: #304156;padding: 10px;">
                                    <h5 class="modal-title" id="exampleModalLabel" style="color: white">
                                        Delete User</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true" style="color: white">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <h4>Are you sure want to delete?</h4>
                                </div>
                                <div class="modal-footer" style="margin: 0 auto; display: flex ; justify-content: space-between">
                                    <a id="deleteLink" class="btn btn-opacity-danger" href="#">
                                        <%--              <button class="btn btn-opacity-danger " style="margin-left: 12px;" type="button">--%>
                                        Delete
                                        <%--              </button>--%>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="ModalAdd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <form id="formAdd" class="needs-validation" method="POST" action="admin-adduser">
                                    <div class="modal-header" style="background: #304156;padding: 10px;">
                                        <h5 class="modal-title" id="exampleModalAdd" style="color: white">Add User</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true" style="color: white">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="form-group col-md-12">
                                                <label for="inputUsername" class="ml-2" style="font-size: 15px; color:black">Username</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control mt-2" id="inputUsername" placeholder="Input Username" name="inputUsername" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">
                                                </div>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label for="inputPassword" class="ml-2" style="font-size: 15px; color:black">Password</label>
                                                <div class="input-group">
                                                    <input type="password" class="form-control mt-2" id="inputPassword" placeholder="Input Password" name="inputPassword" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">
                                                </div>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label for="inputFullName" class="ml-2" style="font-size: 15px; color:black">Fullname</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control mt-2" id="inputFullName" placeholder="Input Fullname" name="inputFullName" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">

                                                </div>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label for="inputEmail" class="ml-2" style="font-size: 15px; color:black">Email</label>
                                                <div class="input-group">
                                                    <input type="email" class="form-control mt-2" id="inputEmail" placeholder="Input Email" name="inputEmail" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">

                                                </div>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label for="inputAvatar" class="ml-2" style="font-size: 15px; color:black">Avatar</label>
                                                <div class="input-group">
                                                    <!--<input accept="image/*" type="file" class="form-control mt-2" id="inputAvatar" placeholder="Input Avatar" 
                                                    name="inputAvatar" aria-describedby="inputGroupPrepend" style="font-size: 14px;"></input>-->
                                                    <input accept="image/*" type="file" class="form-control mt-2" id="inputAvatar" placeholder="Input Avatar" 
                                                    name="inputAvatar" aria-describedby="inputGroupPrepend" style="font-size: 14px;"
                                                           onchange="updatePreview(this, 'image-preview')" ></input>
                                                </div>
                                            </div>
                                            <label class="ml-2" style="font-size: 15px;margin-left:.5rem!important;">Role</label>
                                            <div class="form-group col-md-12">
                                                <select class="form-control js-basic-example2" id="inputRole" name="inputRole" style="width: 100%" required>
                                                    <c:forEach items="${roles}" var="role">
                                                        <c:if test="${role.rid != 6}">
                                                            <option value="${role.rid}"><c:out value="${role.rname}"/></option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="col-md-12 form-group">
                                                <label class="ml-2" style="font-size: 15px">Status</label>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <div class="input-group">
                                                    <label for="inputStatus1" class="ml-2" style="font-size: 15px">Active</label>
                                                    <input type="radio" value="1" class="form-control mt-2" id="inputStatus1" name="inputStatus" style="font-size: 2px;" aria-describedby="inputGroupPrepend">
                                                        <label for="inputStatus2" class="ml-2" style="font-size: 15px">Inactive</label>
                                                        <input type="radio" value="0" class="form-control mt-2" id="inputStatus2" name="inputStatus" style="font-size: 2px;" aria-describedby="inputGroupPrepend">
                                                            </div>                                    
                                                            </div>
                                                            </div>
                                                            </div>
                                                            <div class="modal-footer" style="margin: 0 auto; display: flex; justify-content: center" >
                                                                <button class="btn btn-opacity-success" type="submit">Save</button>
                                                            </div>
                                                            </form>
                                                            </div>
                                                            </div>
                                                            </div>
                                                            <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

                                                                <div class="modal-dialog" role="document">
                                                                    <div class="modal-content">
                                                                        <form id="formEdit" class="needs-validation" method="POST" action="admin-updateuser">
                                                                            <div class="modal-header" style="background: #304156;padding: 10px;">
                                                                                <h5 class="modal-title" id="exampleModalAdd" style="color: white">Edit User</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true" style="color: white">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="row">
                                                                                    <div class="form-group col-md-12">
                                                                                        <label for="updateUsername" class="ml-2" style="font-size: 15px; color:black">Username</label>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control mt-2" id="updateUsername" disabled placeholder="Input Username" value="" name="updateUsername4" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">
                                                                                                <input type="text" class="form-control mt-2" id="updateUsername3" placeholder="Input Username" value="" name="updateUsername" aria-describedby="inputGroupPrepend" style="font-size: 14px;display: none">
                                                                                                    <input type="text" class="form-control mt-2" id="updateId" placeholder="Input Username" value="" name="updateId" aria-describedby="inputGroupPrepend" style="font-size: 14px;display: none">
                                                                                                        </div>
                                                                                                        </div>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <label for="updateFullName" class="ml-2" style="font-size: 15px; color:black">Password</label>
                                                                                                            <div class="input-group">
                                                                                                                <input type="password" class="form-control mt-2" id="updateFullName" disabled placeholder="Input Fullname" value="" name="updateFullName" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">

                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <label for="updateFullName" class="ml-2" style="font-size: 15px; color:black">Fullname</label>
                                                                                                            <div class="input-group">
                                                                                                                <input type="text" class="form-control mt-2" id="updateFullName" disabled placeholder="Input Fullname" value="" name="updateFullName" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">

                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <label for="updateEmail" class="ml-2" style="font-size: 15px; color:black">Email</label>
                                                                                                            <div class="input-group">
                                                                                                                <input type="text" class="form-control mt-2" id="updateEmail" disabled placeholder="Input Fullname" value="" name="updateEmail" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">

                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <label for="updateAvatar" class="ml-2" style="font-size: 15px; color:black">Avatar</label>  
                                                                                                            <div class="input-group">
                                                                                                                <input type="file" class="form-control mt-2" id="updateAvatar" disabled placeholder="Input Fullname" value="" name="updateAvatar" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <label class="ml-2" style="font-size: 15px;margin-left:.5rem!important;">Role</label>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <select class="form-control js-basic-example2" id="updateRole" name="updateRole" style="width: 100%" required>
                                                                                                                <c:forEach items="${roles}" var="role">
                                                                                                                    <c:if test="${role.rid != 6}">
                                                                                                                        <option value="${role.rid}"><c:out value="${role.rname}"/></option>
                                                                                                                    </c:if>
                                                                                                                </c:forEach>
                                                                                                            </select>
                                                                                                        </div>

                                                                                                        <div class="col-md-12 form-group">
                                                                                                            <label class="ml-2" style="font-size: 15px">Status</label>
                                                                                                        </div>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <div class="input-group">
                                                                                                                <label for="updateStatus1" class="ml-2" style="font-size: 15px">Active</label>
                                                                                                                <input type="radio" value="1" class="form-control mt-2" id="updateStatus1" name="updateStatus" style="font-size: 2px;" aria-describedby="inputGroupPrepend">
                                                                                                                    <label for="updateStatus2" class="ml-2" style="font-size: 15px">Inactive</label>
                                                                                                                    <input type="radio" value="0" class="form-control mt-2" id="updateStatus2" name="updateStatus" style="font-size: 2px;" aria-describedby="inputGroupPrepend">
                                                                                                                        <label for="updateStatus3" class="ml-2" style="font-size: 15px">Not Verify Email</label>
                                                                                                                        <input type="radio" value="2" class="form-control mt-2" id="updateStatus3" name="updateStatus" style="font-size: 2px;" aria-describedby="inputGroupPrepend">
                                                                                                                            </div>                                    
                                                                                                                            </div>
                                                                                                                            </div>
                                                                                                                            </div>
                                                                                                                            <div class="modal-footer" style="margin: 0 auto; display: flex; justify-content: center" >
                                                                                                                                <button class="btn btn-opacity-success" type="submit">Save</button>
                                                                                                                            </div>
                                                                                                                            </form>
                                                                                                                            </div>
                                                                                                                            </div>
                                                                                                                            </div>
                                                                                                                            <!---->
                                                                                                                            <!-- End:: Footer-->
                                                                                                                            </div>
                                                                                                                            <!-- End::Main header-->
                                                                                                                            </div>
                                                                                                                            </div>

                                                                                                                            <script src="admin-template/js/vendors.bundle.min.js"></script>
                                                                                                                            <script src="admin-template/js/main.bundle.min.js"></script>
                                                                                                                            <script src="admin-template/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
                                                                                                                            <script src="admin-template/vendors/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
                                                                                                                            <script src="admin-template/js/pages/datatables/basicDatatable.min.js"></script>
                                                                                                                            <script type="text/javascript">
                                                        let currentIndex;
                                                        let oldText;
                                                        $(".sort-handler").click(function (e) {
                                                            const index = $(this).index();
                                                            if (index != currentIndex || !currentIndex) {
                                                                if (oldText) {
                                                                    $($('.sort-handler').get(currentIndex)).html(oldText);
                                                                }
                                                                currentIndex = index;
                                                            }
                                                            if (!$(this).text().includes(oldText) || !oldText) {
                                                                oldText = $(this).text();
                                                            }
                                                            // if (oldText.include('&#8593;')){
                                                            //     oldText.replace('&#8593;', '');
                                                            // } else if (oldText.include('&#8595;')){
                                                            //     oldText.replace('&#8595;', '');
                                                            // }
                                                            $(this).html(oldText + ' &#8593;');
                                                            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                                                            table = document.getElementById("example");
                                                            switching = true;
                                                            //Set the sorting direction to ascending:
                                                            dir = "asc";
                                                            /*Make a loop that will continue until
                                                             no switching has been done:*/
                                                            while (switching) {
                                                                //start by saying: no switching is done:
                                                                switching = false;
                                                                rows = table.rows;
                                                                /*Loop through all table rows (except the
                                                                 first, which contains table headers):*/
                                                                for (i = 1; i < (rows.length - 1); i++) {
                                                                    //start by saying there should be no switching:
                                                                    shouldSwitch = false;
                                                                    /*Get the two elements you want to compare,
                                                                     one from current row and one from the next:*/
                                                                    x = rows[i].getElementsByTagName("TD")[index];
                                                                    y = rows[i + 1].getElementsByTagName("TD")[index];
                                                                    /*check if the two rows should switch place,
                                                                     based on the direction, asc or desc:*/
                                                                    if (dir == "asc") {
                                                                        if (new Intl.Collator().compare(x.innerHTML.toLowerCase(), y.innerHTML.toLowerCase()) == 1) {
                                                                            //if so, mark as a switch and break the loop:
                                                                            shouldSwitch = true;
                                                                            break;
                                                                        }
                                                                    } else if (dir == "desc") {
                                                                        if (new Intl.Collator().compare(x.innerHTML.toLowerCase(), y.innerHTML.toLowerCase()) == -1) {
                                                                            //if so, mark as a switch and break the loop:
                                                                            shouldSwitch = true;
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                                if (shouldSwitch) {
                                                                    /*If a switch has been marked, make the switch
                                                                     and mark that a switch has been done:*/
                                                                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                                                                    switching = true;
                                                                    //Each time a switch is done, increase this count by 1:
                                                                    switchcount++;
                                                                } else {
                                                                    /*If no switching has been done AND the direction is "asc",
                                                                     set the direction to "desc" and run the while loop again.*/
                                                                    if (switchcount == 0 && dir == "asc") {
                                                                        $(this).html(oldText + ' &#8595;');
                                                                        dir = "desc";
                                                                        switching = true;
                                                                    }
                                                                }
                                                            }
                                                        });
                                                        $(document).ready(function () {
                                                            $(".js-basic-example2").select2({
                                                                theme: "classic"
                                                            });
                                                        });
                                                        function onPage(pageNo, pageSie, search, filterRole, filterStatus) {
                                                            if (search === '') {
                                                                if (filterRole === 0) {
                                                                    window.location.href = `admin-alluser?pageNo=` + pageNo + `&filterStatus=` + filterStatus;
                                                                }
                                                                if (filterStatus === 0) {
                                                                    window.location.href = `admin-alluser?pageNo=` + pageNo + `&filterRole=` + filterRole;
                                                                }
                                                                if (filterRole === 0 && filterStatus === 0) {
                                                                    window.location.href = `admin-alluser?pageNo=` + pageNo;
                                                                }
                                                                window.location.href = `admin-alluser?pageNo=` + pageNo + `&filterRole=` + filterRole + `&filterStatus=` + filterStatus;
                                                            } else {
                                                                window.location.href = `admin-alluser?pageNo=` + pageNo + `&filterRole=` + filterRole + `&filterStatus=` + filterStatus + `&search=` + search;
                                                            }
                                                        }
                                                        function filter() {
                                                            const searchValue = "${search}";
                                                            const filterRole = document.getElementById("filterRole").value;
                                                            const filterStatus = document.getElementById("filterStatus").value;
                                                            if (searchValue === '') {
                                                                if (filterRole !== 0 && filterStatus !== 3) {
                                                                    window.location.href = `admin-alluser?pageNo=1&filterRole=` + filterRole + `&filterStatus=` + filterStatus;
                                                                } else if (filterRole !== 0) {
                                                                    window.location.href = `admin-alluser?pageNo=1&filterRole=` + filterRole;
                                                                } else if (filterStatus !== 3) {
                                                                    window.location.href = `admin-alluser?pageNo=1&filterStatus=` + filterStatus;
                                                                }
                                                            } else {
                                                                if (filterRole !== 0 && filterStatus !== 3) {
                                                                    window.location.href = `admin-alluser?pageNo=1&filterRole=` + filterRole + `&filterStatus=` + filterStatus + '&search=' + searchValue;
                                                                } else if (filterRole !== 0) {
                                                                    window.location.href = `admin-alluser?pageNo=1&filterRole=` + filterRole + '&search=' + searchValue;
                                                                } else if (filterStatus !== 3) {
                                                                    window.location.href = `admin-alluser?pageNo=1&filterStatus=` + filterStatus + '&search=' + searchValue;
                                                                }
                                                            }
                                                        }
                                                        function search() {
                                                            const filterStatus = "${filterStatus}";
                                                            const filterRole = "${filterRole}";
                                                            const searchValue = document.getElementById("textSearch").value;
                                                            if (searchValue === '') {
                                                                window.location.href = `admin-alluser?filterStatus=` + filterStatus + `&filterRole=` + filterRole;
                                                            } else
                                                                window.location.href = `admin-alluser?filterStatus=` + filterStatus + `&filterRole=` + filterRole + `&search=` + searchValue;
                                                        }
                                                        $('#editModal').on('show.bs.modal', function (event) {
                                                            var button = $(event.relatedTarget); // Button that triggered the modal
                                                            var status = button.data('status'); // Extract value from data-* attributes
                                                            var role = button.data('role');
                                                            var id = button.data('userid');
                                                            var name = button.data('name');
                                                            var avatar = button.data('avatar');
                                                            var username = button.data('username');
                                                            var email = button.data('email');
                                                            console.log(status);
                                                            console.log(role);
                                                            console.log(id);
                                                            // Set the selected value of the "statusRadio" radio buttons based on the "data-status" attribute
                                                            if (status === 1) {
                                                                $('#updateStatus1').prop('checked', 'checked');
                                                            } else if (status === 2) {
                                                                $('#updateStatus3').prop('checked', 'checked');
                                                            } else {
                                                                $('#updateStatus2').prop('checked', 'checked');
                                                            }
                                                            $('#updateId').attr('value', id);
                                                            $('#updateAvatar').attr('value', avatar);
                                                            $('#updateFullName').attr('value', name);
                                                            $('#updateUsername3').attr('value', username);
                                                            $('#updateUsername').attr('value', username);
                                                            $('#updateEmail').attr('value', email);
                                                            $('#updateRole').val(role);
                                                            $('#updateRole').val(role).trigger('change');

                                                        });
                                                        function openModal(id) {
                                                            $("#ModalDelete").modal('show');
                                                        }
                                                        ;
                                                        
                                                         function updatePreview(input, target) {
                                   let file = input.files[0];
                                   let reader = new FileReader();

                                   reader.readAsDataURL(file);
                                   reader.onload = function () {
                                       let img = document.getElementById(target);
                                       // can also use "this.result"
                                       img.src = reader.result;
                                   }
                               }
                                                                                                                            </script>
                                                                                                                            </body>


                                                                                                                            </html>