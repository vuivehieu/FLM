
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Topbar Start -->
    <div class="py-3 container-fluid d-none d-md-block" style="background: #ff6634;">
    </div>
    <!-- Topbar End -->

    <!-- Navbar Start -->
    <div class="p-0 container-fluid position-relative nav-bar">
        <div class="p-0 container-lg position-relative px-lg-3" style="z-index: 9;">
            <nav class="bg-white shadow navbar navbar-expand-lg navbar-light p-lg-0">
                <a href="#" class="navbar-brand d-block d-lg-none">
                    <!--<h1 class="m-0 display-4 text-primary"><span class="text-secondary">i</span>CREAM</h1>-->
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="py-0 ml-auto navbar-nav">
                        <a href="search?type=curriculum&keysearch=&filter=curCode" class="nav-item nav-link" id="curriculum" onclick="activeNav()">All Curriculum</a>
                        <c:if test="${account != null && account.role.rid != 1}">
                            <a href="search?type=syllabus&keysearch=&filter=subjectcode" class="nav-item nav-link" id="syllabus" onclick="activeNav()">All Syllabus</a>
                        </c:if>
                        <a href="search?type=preRequisite&keysearch=&filter=subjectName" class="nav-item nav-link" id="preRequisite" onclick="activeNav()">All PreRequisite</a>
                    </div>
                    <a href="home" class="mx-5 navbar-brand d-none d-lg-block"
                        style="width:100px; height: 100%; overflow: hidden;">
                        <img style="width: 100%; height: 100%;" src="./fe/img/Logo_Đại_học_FPT.png" alt="">
                    </a>
                    <div class="py-0 mr-auto navbar-nav">
                        <a href="search?type=corollary&keysearch=&filter=subjectcode" class="nav-item nav-link" id="corollary" onclick="activeNav()">All Corollary</a>
                        <c:if test="${account == null}">
                            <a href="#!" data-toggle="modal" data-target="#modal1" class="nav-item nav-link">Sign In</a>
                            <a href="#!" data-toggle="modal" data-target="#modal2" class="nav-item nav-link">Sign Up</a>
                        </c:if>
                        <c:if test="${account != null}">
                              <div class="dropdown" style="display: flex; align-items: center; margin-left: 2rem;">
                                  <a
                                      class="dropdown-toggle d-flex align-items-center hidden-arrow"
                                      href="#"
                                      id="navbarDropdownMenuAvatar"
                                      role="button"
                                      data-mdb-toggle="dropdown"
                                      aria-expanded="false"
                                      style ="background-color: #f26838;border-radius: 50%;width: 50px;height: 50px;"
                                      >
                                      <c:if test="${account.avatar != ''}">
                                          <img
                                          src="${account.avatar}"
                                          class="rounded-circle"
                                          height="25"
                                          loading="lazy" style="width: 50px;height: 50px;"
                                          />
                                      </c:if>
                                      <c:if test="${account.avatar == ''}">
                                          <p style="font-size: 2rem;color: #fff;margin: auto;">${account.displayName.charAt(0)}</p>
                                      </c:if>

                                  </a>

                                  <ul
                                    class="dropdown-menu dropdown-menu-end"
                                    aria-labelledby="navbarDropdownMenuAvatar" style="margin-top: 1rem;width: 17rem;"
                                  >
                                    <c:if test="${!(account.role.rid == 1) && !(account.role.rid == 2) && !(account.role.rid == 3)}">
                                    <li style="display: block;width: 100%; margin-bottom: 0.5rem;">
                                      <a class="dropdown-item" href="dashboard" style="font-size: 15px;"><span style="font-weight: bolder;color: black;">Dashboard</span></a>
                                    </li>
                                    </c:if>
                                    <li style="display: block;width: 100%; margin-bottom: 0.5rem;">
                                        <a class="dropdown-item" href="#" style="font-size: 15px;">Name: <span style="font-weight: bolder;color: black;">${account.displayName}</span></a>
                                    </li>
                                    <li style="display: block;width: 100%; margin-bottom: 0.5rem;">
                                      <a class="dropdown-item" href="#" style="font-size: 15px;">Email: <span style="font-weight: bolder;color: black;">${account.email}</span></a>
                                    </li>
                                    <li style="display: block;width: 100%; margin-bottom: 0.5rem;">
                                      <a class="dropdown-item" href="#" style="font-size: 15px;">User's role: <span style="font-weight: bolder;color: black;">${account.role.rname}</span></a>
                                    </li>
                                    <li style="display: block;width: 100%; margin-bottom: 0.5rem;">
                                      <a class="dropdown-item" href="logout" style="font-size: 15px; color: #0388f9;">Sign out FLM</a>
                                    </li>
                                  </ul>
                                </div>
                              </div>
                        </c:if>
                        
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- Navbar End -->


<!--SECTION LOGIN, REGISTER AND FORGOT PASSWORD-->
<jsp:include page="login.jsp"/>
<jsp:include page="register.jsp"/>
<jsp:include page="forgot.jsp"/>

<script>
    
    function activeNav() {
        let type = new URLSearchParams(window.location.search).get('type');
        console.log("type", type);
        if (type === 'curriculum') {
          document.getElementById("curriculum").classList.add("active");
        } else if (type === 'syllabus') {
          document.getElementById("syllabus").classList.add("active");
        } else if (type === 'preRequisite') {
          document.getElementById("preRequisite").classList.add("active");
        } else if (type === 'corollary') {
          document.getElementById("corollary").classList.add("active");
        }

        // Thay đổi đường dẫn URL
        let url = new URL(window.location.href);
        url.searchParams.set('type', type);
        window.history.pushState({ path: url.href }, '', url.href);
      }

      // Gọi hàm activeNav() khi tải trang
      activeNav();

      // Gọi hàm activeNav() khi click vào nav item
      let navItems = document.querySelectorAll('ul li a');
      navItems.forEach(navItem => {
        navItem.addEventListener('click', function(event) {
          event.preventDefault();
          let href = navItem.getAttribute('href');
          window.location.href = href;
          activeNav();
        });
    });

    console.log("account", account);
    let signUpBtn = document.getElementById("signUpBtn");
    signUpBtn.addEventListener("click", function() {
        document.getElementById('email').value = '';
        document.getElementById('userName').value = '';
        document.getElementById('fullName').value = '';
        document.getElementById('password').value = '';
        document.getElementById('comfirm-password').value = '';
        document.getElementById('verifyCode').value = '';
        document.getElementById("errorMessage").innerHTML = '';
        document.getElementById("messageRegister").innerHTML = '';
    });
</script>
