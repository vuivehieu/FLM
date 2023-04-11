
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- MOBILE MENU -->
<section>
    <div class="ed-mob-menu">
        <div class="ed-mob-menu-con">
            <div class="ed-mm-left">
                <div class="wed-logo">
                    <a href="http://localhost:9999/SWP391-G2/home">
                        <img style="height: calc(100px-12px)" src="images/Logo_Đại_học_FPT.png" alt="" />
                    </a>
                </div>
            </div>
            <div class="ed-mm-right">
                <div class="ed-mm-menu">
                    <a href="#!" class="ed-micon"><i class="fa fa-bars"></i></a>
                    <div class="ed-mm-inn">
                        <a href="#!" class="ed-mi-close"><i class="fa fa-times"></i></a>
                        <h4>Search</h4>
                        <ul>
                            <li><a href="search?type=curriculum">All Curriculum</a></li>
                            <li><a href="search?type=syllabus">All Syllabus</a></li>
                            <li><a href="search?type=preRequisite">All PreRequisite</a></li>
                            <li><a href="search?type=corollary">All CorollarySubject</a></li>
                        </ul>
                        <h4>User Account</h4>
                        <ul>
                            <c:if test="${account == null}">
                                <li><a href="#!" data-toggle="modal" data-target="#modal1">Sign In</a></li>
                                <li><a href="#!" data-toggle="modal" data-target="#modal2">Register</a></li>
                                </c:if>
                                <c:if test="${account != null}">
                                <li><a href="profile">Profile</a></li>
                                <li><a href="profile">Change Password</a></li>
                                <li><a href="profile">Change Avatar</a></li>

                                <li><a href="logout">Logout</a></li>
                                </c:if>  
                        </ul>
                        <h4>All Pages</h4>
                        <ul>
                            <li><a href="index-2.html">Home</a></li>
                            <li><a href="about.html">About us</a></li>
                            <li><a href="admission.html">Admission</a></li>
                            <li><a href="all-courses.html">All courses</a></li>
                            <li><a href="course-details.html">Course details</a></li>
                            <li><a href="awards.html">Awards</a></li>
                            <li><a href="seminar.html">Seminar</a></li>
                            <li><a href="events.html">Events</a></li>
                            <li><a href="event-details.html">Event details</a></li>
                            <li><a href="event-register.html">Event register</a></li>
                            <li><a href="contact-us.html">Contact us</a></li>
                        </ul>
                        <h4>User Profile</h4>
                        <ul>
                            <li><a href="dashboard.html">User profile</a></li>
                            <li><a href="db-courses.html">Courses</a></li>
                            <li><a href="db-exams.html">Exams</a></li>
                            <li><a href="db-profile.html">Prfile</a></li>
                            <li><a href="db-time-line.html">Time line</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!--HEADER SECTION-->
<section>
    <!-- TOP BAR -->
    <style>
        .chip {
            margin-top: 0.5rem;
        }

    </style>
<!--    <div class="ed-top">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="wed-logo">
                        <a href="home">
                            <img style="height: calc(100px-12px)" src="images/Logo_Đại_học_FPT.png" alt="" />
                        </a>
                    </div>
                    <div class="main-menu">
                        <ul>
                            <li><a href="search?type=curriculum">All Curriculum</a></li>

                            <c:if test="${account != null}">
                                <li><a href="search?type=syllabus">All Syllabus</a></li>
                                </c:if>
                            <li><a href="search?type=preRequisite">All PreRequisite</a></li>
                            <li><a href="search?type=corollary">All Corollary</a></li>
                        </ul>
                    </div>
                    <div class="${account == null ? 'ed-com-t1-right' : ''}" style="${account == null ? '' : 'float: right;'}">
                        <ul>
                            <c:if test="${account == null}">
                                <li><a href="#!" data-toggle="modal" data-target="#modal1">Sign In</a>
                                </li>
                                <li><a href="#!" data-toggle="modal" data-target="#modal2">Sign Up</a>
                                </li>
                            </c:if>

                        </ul>
                        <c:if test="${account != null}">
                            <div class="dropdown">
                                <div class="chip" data-toggle="dropdown">
                                    <img src="${account.avatar}" alt="Person" width="96" height="96">
                                    ${account.displayName}
                                </div>

                                <div class="dropdown-menu dropdown-menu-right ">
                                    <ul class="d-flex flex-column">
                                        <c:if test="${!(account.role.rid == 1) && !(account.role.rid == 2) && !(account.role.rid == 3)}">
                                            <li class="nav-item" style="padding-left: 1.5rem"><a class="dropdown-item" href="dashboard">Dashboard</a></li>
                                            </c:if>
                                        <li class="nav-item" style="padding-left: 1.5rem"><a class="dropdown-item" href="profile">Profile</a></li>
                                        <li style="padding-left: 1.5rem"><a class="dropdown-item" href="changePassword">Change Password</a></li>
                                        <li style="padding-left: 1.5rem"><a class="dropdown-item" href="changeAvatar">Change Avatar</a></li>
                                        <li style="padding-left: 1.5rem"><a class="dropdown-item" href="logout">Logout</a></li>

                                    </ul>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>-->


<!-- Navbar -->
<div class="ed-top">
<nav class="navbar navbar-expand-lg navbar-light bg-white">
   <!--Container wrapper--> 
  <div class="container-fluid">
     <!--Toggle button--> 
    <button
      class="navbar-toggler"
      type="button"
      data-mdb-toggle="collapse"
      data-mdb-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <i class="fas fa-bars"></i>
    </button>

     <!--Collapsible wrapper--> 
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
       <!--Navbar brand--> 
      <a class="navbar-brand mt-2 mt-lg-0" href="home">
        <img
          src="images/Logo_Đại_học_FPT.png"
          height="100%"
          alt="FPT Logo"
          loading="lazy"
        />
      </a>
       <!--Left links--> 
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item" style="font-weight: bold;">
          <a class="nav-link" href="search?type=curriculum">All Curriculum</a>
        </li>
        <li class="nav-item" style="font-weight: bold;">
          <a class="nav-link" href="search?type=preRequisite">All PreRequisite</a>
        </li>
        <c:if test="${account != null}">
        <li class="nav-item" style="font-weight: bold;">
          <a class="nav-link" href="search?type=syllabus">All Syllabus</a>
        </li>
        </c:if>
        <li class="nav-item" style="font-weight: bold;">
          <a class="nav-link" href="search?type=corollary">All Corollary</a>
        </li>
      </ul>
  
       <!--Left links--> 
    </div>
     <!--Collapsible wrapper--> 

     <!--Right elements--> 
    <c:if test="${account == null}">
    <div class="d-flex align-items-center">
        <button type="button" class="btn btn-dark me-3">
          <a href="#!" data-toggle="modal" data-target="#modal1">Sign In</a>
        </button>
        <button type="button" class="btn btn-primary me-3">
           <a href="#!" data-toggle="modal" data-target="#modal2">Sign Up</a>
        </button>
    </div>
    </c:if>
    <c:if test="${account != null}">
          <!--Avatar--> 
      <div class="dropdown">
        <a
          class="dropdown-toggle d-flex align-items-center hidden-arrow"
          href="#"
          id="navbarDropdownMenuAvatar"
          role="button"
          data-mdb-toggle="dropdown"
          aria-expanded="false"
        >
          <img
            src="${account.avatar}"
            class="rounded-circle"
            height="25"
            alt="Black and White Portrait of a Man"
            loading="lazy"
          />
        </a>
        <ul
          class="dropdown-menu dropdown-menu-end"
          aria-labelledby="navbarDropdownMenuAvatar"
        >
          <c:if test="${!(account.role.rid == 1) && !(account.role.rid == 2) && !(account.role.rid == 3)}">
          <li>
            <a class="dropdown-item" href="dashboard">Dashboard</a>
          </li>
          </c:if>
          <li>
            <a class="dropdown-item" href="profile">My profile</a>
          </li>
          <li>
            <a class="dropdown-item" href="changePassword">Change Password</a>
          </li>
          <li>
            <a class="dropdown-item" href="changeAvatar">Change Avatar</a>
          </li>
          <li>
            <a class="dropdown-item" href="logout">Logout</a>
          </li>
        </ul>
      </div>
    </div>
    </c:if>
     <!--Right elements--> 
  </div>
   <!--Container wrapper--> 
</nav>
</div>
<!-- Navbar -->

</section>
<div style="position: fixed; top: 0; right: 0;" id="toastMessage">


</div>
<!--END HEADER SECTION-->

<!--SECTION LOGIN, REGISTER AND FORGOT PASSWORD-->
<jsp:include page="login.jsp"/>
<jsp:include page="register.jsp"/>
<jsp:include page="forgot.jsp"/>
