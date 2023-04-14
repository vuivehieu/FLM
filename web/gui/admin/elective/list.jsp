<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="Custom.ForJSP" id="custom" />
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
            <title>Elective Management</title>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
            <script type="text/javascript">
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
                                        <li class="breadcrumb-item active"><a href="#">Elective Management</a></li>
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
                                    <h2 class="p-1 m-0 text-16 font-weight-semi " style="color: white">Elective Management</h2>
                                    <div style="color: white" class="p-1 m-0 text-16">
                                        <i class="fa fa-plus" aria-hidden="true" data-toggle="modal" data-target="#ModalAdd" style="cursor: pointer"></i>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body table-responsive">
                                        <table id="example" style="text-align: center" class="table">
                                            <tbody>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Elective Tag</th>
                                                    <th>Name</th>
                                                    <th>Created Date</th>
                                                    <th>Status</th>
                                                    <th style="width: 9%"></th>
                                                </tr>
                                                <c:forEach items="${list}" var="elective">
                                                    <tr>
                                                        <td>${elective.eid}</td>
                                                        <td>${elective.tag}</td>
                                                        <td>
                                                            <a href="#">
                                                                <span class="list-enq-name">${elective.subjectName_EN}</span>
                                                                <span class="list-enq-city">${elective.subjectName_VI}</span>
                                                            </a>
                                                        </td>
                                                        <td>${custom.getDateFormat('dd/MM/yyyy', elective.createDate) }</td>
                                                        <td>
                                                            <span class="badge ${elective.isActive ? 'badge-success' : 'badge-danger'}">${combo.isActive ? 'Active': 'Inactive'}</span>
                                                        </td>
                                                        <td>
                                                            <button id="btnDetail" onclick="handleUpdate(${plo.ploid})"
                                                                    class="btn text-primary rounded-circle m-0 btn-sm btn-icon"
                                                                    style="height: 0px !important;"><i
                                                                    class="material-icons">edit</i>
                                                                <div class="ripple-container"></div>
                                                            </button>
                                                            <button onclick="handleDelete(${plo.ploid})"
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

</html>