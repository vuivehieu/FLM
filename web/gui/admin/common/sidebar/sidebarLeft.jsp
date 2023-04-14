<%-- 
    Document   : sidebarLeft
    Created on : 21-01-2023, 11:14:57
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="account" value="${cookie.userRole.value}" />
<div class="sb2-1">
    <!--== LEFT MENU ==-->
    <div class="sb2-13">
        <ul class="collapsible" data-collapsible="accordion">
            <c:if test="${account  != null && account eq '6'}">
                <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-users" aria-hidden="true"></i> Users</a>
                    <div class="collapsible-body left-sub-menu">
                        <ul>
                            <li><a href="admin-alluser">All Users</a></li>
                            <li><a href="#!">ADMIN</a></li>
                            <li><a href="#!">Add New Account</a></li>
                            <li><a href="#!">Reset Password</a></li>
                        </ul>
                    </div>
                </li>
            </c:if>
            <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-book" aria-hidden="true"></i>Curriculum</a>
                <div class="collapsible-body left-sub-menu">
                    <ul>
                        <li><a href="curriculumList"><i class="fa fa-list-ol" aria-hidden="true"></i>All Curriculuum</a></li>
                        <li><a href="#!"><i class="fa fa-plus" aria-hidden="true"></i>Add New Curriculum</a></li>
<!--                        <li><a href="#!"><i class="fa fa-check" aria-hidden="true"></i>Approve / Disapprove</a>-->
                        </li>
                    </ul>
                </div>
            </li>

            <c:if  test="${account != null && account eq '4' || account eq '5' || account eq '7' || account eq '6'}">
                <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-server" aria-hidden="true"></i>Syllabus</a>
                    <div class="collapsible-body left-sub-menu">
                        <ul>
                            <li><a href="syllabusList">All Syllabus</a></li>
                            <li><a href="syllabusDetail?type=add">Add New Syllabus</a></li>

<!--                            <li><a href="sessionList">All Session</a></li>
                            <li><a href="sessionDetail?type=add">Add New Session</a></li>

                            <li><a href="questionList">All Question</a></li>
                            <li><a href="questionDetail?type=add">Add New Question</a></li>


                            <li><a href="materialList">All Material</a></li>
                            <li><a href="materialDetail?type=add">Add New Material</a></li>

                            <li><a href="assessmentList">All Assessment</a></li>
                            <li><a href="assessmentDetail?type=add">Add New Assessment</a></li>-->
                        </ul>
                    </div>
                </li>
                <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-pencil" aria-hidden="true"></i> Combo</a>
                    <div class="collapsible-body left-sub-menu">
                        <ul>
                            <li><a href="comboList">All Combo</a></li>
                            <li><a href="comboAdd">Add New Combo</a></li>
                        </ul>
                    </div>
                </li>
                <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-pencil" aria-hidden="true"></i> PLO-PO</a>
                    <div class="collapsible-body left-sub-menu">
                        <ul>
                            <li><a href="ploList">All PLO</a></li>
                            <li><a href="ploAdd">Add New PLO</a></li>
                            <li><a href="poList">All PO</a></li>
                            <li><a href="poAdd">Add New PO</a></li>
                        </ul>
                    </div>
                </li>
                <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-pencil" aria-hidden="true"></i> Elective</a>
                    <div class="collapsible-body left-sub-menu">
                        <ul>
                            <li><a href="electiveList">All Elective</a></li>
                            <li><a href="electiveDetail?type=add">Add New Elective</a></li>
                        </ul>
                    </div>
                </li>
<!--                <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-pencil" aria-hidden="true"></i> CLO</a>
                    <div class="collapsible-body left-sub-menu">
                        <ul>
                            <li><a href="cloList">All CLO</a></li>
                        </ul>
                    </div>
                </li>-->


            </c:if>

            <%--<c:if test="${account != null && account eq '5' || account eq '7' || account eq '6'}">--%>
<!--                <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-graduation-cap" aria-hidden="true"></i> Subject</a>
                    <div class="collapsible-body left-sub-menu">
                        <ul>
                            <li><a href="subjectList">All Subject</a></li>
                            <li><a href="admin-about-menu.html">All Session</a></li>
                            <li><a href="admin-admission-menu.html">All Material</a></li>
                            <li><a href="admin-all-menu.html">Add New Subject</a></li>
                        </ul>
                    </div>
                </li>-->
            <%--</c:if>--%>

<!--            <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-pencil" aria-hidden="true"></i> Assessment</a>
                <div class="collapsible-body left-sub-menu">
                    <ul>
                        <li><a href="assessmentList">All Assessment</a></li>
                        <li><a href="assessmentDetail?type=add">Add New Assessment</a></li>
                    </ul>
                </div>
            </li>-->
            
<!--            <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-pencil" aria-hidden="true"></i> PreRequisite</a>
                <div class="collapsible-body left-sub-menu">
                    <ul>
                        <li><a href="preRequisiteList">All PreRequisite</a></li>
                        <li><a href="assessmentDetail?type=add">Add New PreRequisite</a></li>
                    </ul>
                </div>
            </li>-->
            
<!--            <li><a href="javascript:void(0)" class="collapsible-header"><i class="fa fa-pencil" aria-hidden="true"></i> Decision</a>
                <div class="collapsible-body left-sub-menu">
                    <ul>
                        <li><a href="decisionList">All Decision</a></li>
                        <li><a href="decisionDetail?type=add">Add New Decision</a></li>
                    </ul>
                </div>
            </li>-->



        </ul>
    </div>
</div>
