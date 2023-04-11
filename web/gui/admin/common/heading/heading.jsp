<%-- 
    Document   : headnig
    Created on : 21-01-2023, 11:02:48
    Author     : phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>

    .admin-heading {
        /*background-color: white;*/
    }

    .admin-heading .logo img {
        max-height: 5rem;
        max-width: 10rem;
        padding-top: 1rem;
    }

    .sb1 {
        background: #fff;
    }
    
</style>
<!--== MAIN CONTRAINER ==-->
<div class="container-fluid sb1 admin-heading">
    <div class="row">
        <!--== LOGO ==-->
        <div class="col-md-2 col-sm-3 col-xs-6 sb1-1 logo">
            <a href="#" class="btn-close-menu"><i class="fa fa-times" aria-hidden="true"></i></a>
            <a href="#" class="atab-menu"><i class="fa fa-bars tab-menu" aria-hidden="true"></i></a>
            <a href="dashboard" class="logo"><img src="images/Logo_Đại_học_FPT.png" alt="" />
            </a>
        </div>
        <!--== SEARCH ==-->
        <div class="col-md-6 col-sm-6 mob-hide">
            <!--                                <form class="app-search">
                                                <input type="text" placeholder="Search..." class="form-control">
                                                <a href="#"><i class="fa fa-search"></i></a>
                                            </form>-->
        </div>  
        <!--== NOTIFICATION ==-->
        <div class="col-md-2 tab-hide">
            <div class="top-not-cen"></div>
        </div>
        <!--== MY ACCCOUNT ==-->
        <div class="col-md-2 col-sm-3 col-xs-6" style="padding-right: 0;">
            <!-- Dropdown Trigger -->
            <a class='waves-effect dropdown-button top-user-pro' href='#' data-activates='top-menu'>
                <img src="${account.avatar}" alt=""/>My Account <i class="fa fa-angle-down" aria-hidden="true"></i>
            </a>

            <!-- Dropdown Structure -->
            <ul id='top-menu' class='dropdown-content top-menu-sty' style="top: 62px!important">
                <li><a href="admin-panel-setting.html" class="waves-effect"><i class="fa fa-cogs" aria-hidden="true"></i>Admin Setting</a>
                </li>
                <li class="divider"></li>
                <li><a href="logout" class="ho-dr-con-last waves-effect"><i class="fa fa-sign-in" aria-hidden="true"></i> Logout</a>
                </li>
            </ul>
        </div>
    </div>

    <!--<div aria-live="polite" aria-atomic="true" style="position: relative;">-->
        <!-- Position it -->
        <div style="position: fixed; top: 0; right: 0;" id="toastMessage">

            <!-- Then put toasts within -->
            ${sessionScope.message}
<!--            <div class="toast " role="alert" aria-live="assertive" aria-atomic="true" data-delay="3000">
                <div class="toast-header">
                    <strong class="mr-auto">Bootstrap Toast</strong>
                    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="toast-body">
                    This is a simple toast message.
                </div>
            </div>-->
        </div>
    <!--</div>-->
</div>


