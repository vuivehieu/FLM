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
            <title>Settings</title>

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
                                        <li class="breadcrumb-item active"><a href="#">Settings</a></li>
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
                                            <select class="form-control js-basic-example2" id="filterType" style="margin-left:-2%;background: #cfcfcf;border-radius: 20px;padding: 10px 20px;">
                                                <option value="User Role" selected>User Role</option>
                                            </select>
                                            <div class="input-group-append" style="position: absolute;right: 0;z-index: 10;">
                                            </div>
                                        </div>
                                        <div class="input-group" style="position: relative;width: 30%;margin-left: 25px;margin-bottom: 15px;float: left">
                                            <select class="form-control js-basic-example2" id="filterStatus" style="margin-left:-2%;background: #cfcfcf;border-radius: 20px;padding: 10px 20px;" onchange="filter()">
                                                <option value="3">All</option>
                                                <option value="0" ${filterStatus == 0? 'selected' : ''}>Inactive</option>
                                                <option value="1" ${filterStatus == 1? 'selected' : ''}>Active</option>
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
                                    <h2 class="p-1 m-0 text-16 font-weight-semi " style="color: white">Settings</h2>
                                    <div style="color: white" class="p-1 m-0 text-16">
                                        <i class="fa fa-plus" aria-hidden="true" data-toggle="modal" data-target="#ModalAdd" style="cursor: pointer"></i>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body table-responsive">
                                        <table id="example" style="text-align: center" class="table">
                                            <tbody>
                                                <tr>
                                                    <th class="sort-handler cursor-pointer">ID</th>
                                                    <th class="sort-handler cursor-pointer">Name</th>
                                                    <th class="sort-handler cursor-pointer">Type</th>
                                                    <th class="sort-handler cursor-pointer">Display Order</th>
                                                    <th>Status</th>
                                                    <th></th>
                                                </tr>
                                                <c:forEach items="${list}" var="item">
                                                    <tr>
                                                        <td>${item.rid}</td>
                                                        <td>
                                                            ${item.rname}
                                                        </td>
                                                        <td>${item.type}</td>
                                                        <td>${item.displayOrder}</td>
                                                        <td>
                                                            <c:if test="${item.status == 1}">
                                                                <span class="badge badge-success">Active</span>
                                                            </c:if>
                                                            <c:if test="${item.status == 0}">
                                                                <span class="badge badge-danger">Inactive</span>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <button id="btnDetail"
                                                                    data-status="${item.status}" data-type="${item.type}" data-name="${item.rname}" data-display="${item.displayOrder}" data-itemid="${item.rid}"
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
                                                                style="height: 0px !important;" onclick="document.getElementById('deleteLink').href = 'admin-change-setting-status?id=${item.rid}&status=${item.status==0? 1 : 0}';
                                                                        openModal(${result.id})">
                                                                <c:if test="${item.status== 1}">
                                                                    <i class="material-icons">toggle_off</i>
                                                                </c:if>
                                                                <c:if test="${item.status== 0}">
                                                                    <i class="material-icons">toggle_on</i>
                                                                </c:if>
                                                            </button>

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
                                                    <button class="page-link" ><i class="material-icons " onclick="onPage(1,${pagination.pageSize}, '${search}', '${filterType}', '${filterStatus}')">keyboard_double_arrow_left</i>
                                                    </c:if>
                                            </li>
                                            <li class="page-item">
                                                <c:if test="${pagination.pageNo > 1}">
                                                    <button class="page-link" ><i class="material-icons " onclick="onPage(${pagination.pageNo - 1},${pagination.pageSize}, '${search}', '${filterType}', '${filterStatus}')">keyboard_arrow_left</i>
                                                    </c:if>
                                            </li>
                                            <c:forEach var="page" begin="1" end="${totalPages}">
                                                <li aria-current="page" class="page-item ${pagination.pageNo == page? 'active' :''}">
                                                    <c:choose>
                                                        <c:when test="${page == pagination.pageNo}">
                                                            <button class="page-link page-number">${page}</button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button class="page-link page-number" onclick="onPage(${page},${pagination.pageSize}, '${search}', '${filterType}', '${filterStatus}')">${page}</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item">
                                                <c:if test="${not empty list && pagination.pageNo != totalPages}">
                                                    <button class="page-link" onclick="onPage(${pagination.pageNo+1},${pagination.pageSize}, '${search}', '${filterType}', '${filterStatus}')"><i class="material-icons">keyboard_arrow_right</i>
                                                    </button>
                                                </c:if>
                                            </li>
                                            <li class="page-item">
                                                <c:if test="${not empty list && pagination.pageNo != totalPages}">
                                                    <button class="page-link" onclick="onPage(${totalPages},${pagination.pageSize}, '${search}', '${filterType}', '${filterStatus}')"><i class="material-icons">keyboard_double_arrow_right</i>
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
                                    <h4>Are you sure want to change status?</h4>
                                </div>
                                <div class="modal-footer" style="margin: 0 auto; display: flex ; justify-content: space-between">
                                    <a id="deleteLink" class="btn btn-opacity-danger" href="#">
                                        <%--              <button class="btn btn-opacity-danger " style="margin-left: 12px;" type="button">--%>
                                        Change Status
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
                                        <h5 class="modal-title" id="exampleModalAdd" style="color: white">Add Setting</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true" style="color: white">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="form-group col-md-12">
                                                <label for="inputName" class="ml-2" style="font-size: 15px; color:black">Name</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control mt-2" id="inputName" placeholder="Input Username" name="inputName" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">
                                                </div>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label for="inputDisplay" class="ml-2" style="font-size: 15px; color:black">Display Order</label>
                                                <div class="input-group">
                                                    <input type="password" class="form-control mt-2" id="inputDisplay" placeholder="Input Username" name="inputDisplay" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">
                                                </div>
                                            </div>
                                            <div class="form-group col-md-12">
                                                <label for="inputDescription" class="ml-2" style="font-size: 15px; color:black">Description</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control mt-2" id="inputFullName" placeholder="Input Fullname" name="inputFullName" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">

                                                </div>
                                            </div>
                                            <label class="ml-2" style="font-size: 15px;margin-left:.5rem!important;">Type</label>
                                            <div class="form-group col-md-12">
                                                <select class="form-control js-basic-example2" id="inputRole" name="inputRole" style="width: 100%" required>
                                                    <option value="0">User Role</option>
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
                                                                                <h5 class="modal-title" id="exampleModalAdd" style="color: white">Edit Setting</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true" style="color: white">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="row">
                                                                                    <div class="form-group col-md-12">
                                                                                        <label for="updateName" class="ml-2" style="font-size: 15px; color:black">Name</label>
                                                                                        <div class="input-group">
                                                                                            <input type="text" class="form-control mt-2" id="updateName" disabled placeholder="Input Username" value="" name="updateName" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">
                                                                                                <input type="text" class="form-control mt-2" id="updateName2" placeholder="Input Username" value="" name="updateName2" aria-describedby="inputGroupPrepend" style="font-size: 14px;display: none">
                                                                                                    <input type="text" class="form-control mt-2" id="updateId" placeholder="Input Username" value="" name="updateId" aria-describedby="inputGroupPrepend" style="font-size: 14px;display: none">
                                                                                                        </div>
                                                                                                        </div>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <label for="updateDisplay" class="ml-2" style="font-size: 15px; color:black">Display Order</label>
                                                                                                            <div class="input-group">
                                                                                                                <input type="text" class="form-control mt-2" id="updateDisplay" disabled placeholder="Input Fullname" value="" name="updateDisplay" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">

                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <label for="updateDescription" class="ml-2" style="font-size: 15px; color:black">Description</label>
                                                                                                            <div class="input-group">
                                                                                                                <input type="text" class="form-control mt-2" id="updateDescription" disabled placeholder="Input Fullname" value="" name="updateDescription" aria-describedby="inputGroupPrepend" required style="font-size: 14px;">

                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <label class="ml-2" style="font-size: 15px;margin-left:.5rem!important;">Type</label>
                                                                                                        <div class="form-group col-md-12">
                                                                                                            <select class="form-control js-basic-example2" id="updateRole" name="updateRole" style="width: 100%" required>
                                                                                                                <option value="0">User Role</option>
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
                                                        function onPage(pageNo, pageSize, search, filterType, filterStatus) {
                                                            if (search === '') {
                                                                if (filterType === 'User Role') {
                                                                    window.location.href = `admin-settings?pageNo=` + pageNo + `&filterStatus=` + filterStatus;
                                                                }
                                                                if (filterType === 'User Role') {
                                                                    window.location.href = `admin-settings?pageNo=` + pageNo + `&filterType=` + filterType;
                                                                }
                                                                if (filterType === 'User Role' && filterStatus === 0) {
                                                                    window.location.href = `admin-settings?pageNo=` + pageNo;
                                                                }
                                                                window.location.href = `admin-settings?pageNo=` + pageNo + `&filterType=` + filterType + `&filterStatus=` + filterStatus;
                                                            } else {
                                                                window.location.href = `admin-settings?pageNo=` + pageNo + `&filterType=` + filterType + `&filterStatus=` + filterStatus + `&search=` + search;
                                                            }
                                                        }
                                                        function filter() {
                                                            const searchValue = "${search}";
                                                            const filterType = document.getElementById("filterType").value;
                                                            const filterStatus = document.getElementById("filterStatus").value;
                                                            if (searchValue === '') {
                                                                if (filterType !== 'User Role' && filterStatus !== 3) {
                                                                    window.location.href = `admin-settings?pageNo=1&filterType=` + filterType + `&filterStatus=` + filterStatus;
                                                                } else if (filterType !== 'User Role') {
                                                                    window.location.href = `admin-settings?pageNo=1&filterType=` + filterType;
                                                                } else if (filterStatus !== 3) {
                                                                    window.location.href = `admin-settings?pageNo=1&filterStatus=` + filterStatus;
                                                                }
                                                            } else {
                                                                if (filterType !== 'User Role' && filterStatus !== 3) {
                                                                    window.location.href = `admin-settings?pageNo=1&filterType=` + filterType + `&filterStatus=` + filterStatus + '&search=' + searchValue;
                                                                } else if (filterType !== 'User Role') {
                                                                    window.location.href = `admin-settings?pageNo=1&filterType=` + filterType + '&search=' + searchValue;
                                                                } else if (filterStatus !== 3) {
                                                                    window.location.href = `admin-settings?pageNo=1&filterStatus=` + filterStatus + '&search=' + searchValue;
                                                                }
                                                            }
                                                        }
                                                        function search() {
                                                            const filterType = "${filterType}";
                                                            const filterStatus = "${filterStatus}";
                                                            const searchValue = document.getElementById("textSearch").value;
                                                            if (searchValue === '') {
                                                                window.location.href = `admin-settings?filterType=` + filterType + `&filterStatus=` + filterStatus;
                                                            } else
                                                                window.location.href = `admin-settings?filterType=` + filterType + `&filterStatus=` + filterStatus + `&search=` + searchValue;
                                                        }
                                                        $('#editModal').on('show.bs.modal', function (event) {
                                                            var button = $(event.relatedTarget); // Button that triggered the modal
                                                            var status = button.data('status'); // Extract value from data-* attributes
                                                            var type = button.data('type');
                                                            var itemid = button.data('itemid');
                                                            var name = button.data('name');
                                                            var display = button.data('display');
                                                            // Set the selected value of the "statusRadio" radio buttons based on the "data-status" attribute
                                                            if (status === 1) {
                                                                $('#updateStatus1').prop('checked', 'checked');
                                                            } else if (status === 2) {
                                                                $('#updateStatus3').prop('checked', 'checked');
                                                            } else {
                                                                $('#updateStatus2').prop('checked', 'checked');
                                                            }
                                                            $('#updateName').attr('value', name);
                                                            $('#updateDisplay').attr('value', display);
                                                            $('#updateId').attr('value', itemid);
                                                            $('#updateType').val(type)
                                                            $('#updateType').val(type).trigger('change');

                                                        });
                                                        function openModal(id) {
                                                            $("#ModalDelete").modal('show');
                                                        }
                                                        ;
                                                                                                                        </script>
                                                                                                                        </body>


                                                                                                                        </html>