<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="account" value="${cookie.userRole.value}" />    
<div class="sidebar-panel">
    <div class="brand"><img src="images/Logo_FPT.png"/></div>
    <!-- Start:: user-->
    <div class="scroll-nav" data-perfect-scrollbar="" data-suppress-scroll-x="true">
        <div class="app-user text-center">
            <div class="app-user-photo"><img src="images/avatar/default.png" alt="" /></div>
            <div class="app-user-info"><span class="app-user-name">ADMIN</span>
                <div class="app-user-control"><a class="control-item" href="/admin/user/profile"><i class="material-icons">person</i></a><a class="control-item" href="logout"><i class="material-icons"> exit_to_app</i></a></div>
            </div>
        </div>
        <!-- End:: user-->
        <!-- Start:: side-nav-->
        <div class="side-nav">
            <div class="main-menu">
                <nav class="sidebar-nav">
                    <ul class="metismenu" id="ul-menu">
                        <c:if test="${account == 6}">
                            <li><a href="admin-alluser"><i class="material-icons nav-icon">group</i>User Management</a></li>
                            <li><a href="admin-settings"><i class="material-icons nav-icon">group</i>Settings Management</a></li>
                        </c:if>
                        <c:if test="${(account == 6) or (account == 5) or (account == 7)}">
                            <li><a href="curriculumList"><i class="material-icons nav-icon">table_view</i>Curriculum Management</a></li>
                        </c:if>
                        <li><a href="syllabusList"><i class="material-icons nav-icon">collections_bookmark</i>Syllabus Management</a></li>
                        <c:if test="${(account == 6) or (account == 5) or (account == 7)}">
                        <li><a href="comboList"><i class="material-icons nav-icon">add_chart</i>Combo Management</a></li>
                        </c:if>
                        <c:if test="${(account == 6) or (account == 5) or (account == 7)}">
                        <li><a href="ploList"><i class="material-icons nav-icon">view_stream</i>PLO-PO</a></li>
                        </c:if>
                        <c:if test="${(account == 6) or (account == 5) or (account == 7)}">
                        <li><a href="electiveList"><i class="material-icons nav-icon">dynamic_form</i>Elective Management</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<script>
    // get the current URL
    var url = window.location.href;

    // get all menu items
    var menuItems = document.querySelectorAll('#ul-menu li');

    // loop through menu items
    menuItems.forEach(function (item) {
        // get the link in the menu item
        var link = item.querySelector('a').href;

        // if the current URL contains the link, add the active class
        if (url.indexOf(link) !== -1) {
            item.classList.add('mm-active');
        }
    });
</script>

