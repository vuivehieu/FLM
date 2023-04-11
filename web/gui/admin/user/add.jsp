<%-- 
    Document   : add
    Created on : 21-01-2023, 15:45:16
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


    <head>
        <title>EEducation Master Template</title>
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
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
            <![endif]-->
    </head>

    <body>
        <!--== MAIN CONTRAINER ==-->
        <jsp:include page="../common/heading/heading.jsp"/>

        <!--== BODY CONTNAINER ==-->
        <div class="container-fluid sb2">
            <div class="row">
                <!--== USER INFO ==-->

                <!--== LEFT MENU ==-->
                <jsp:include page="../common/sidebar/sidebarLeft.jsp"/>


                <!--== BODY INNER CONTAINER ==-->
                <div class="sb2-2">
                    <!--== breadcrumbs ==-->
                    <div class="sb2-2-2">
                        <ul>
                            <li><a href="admin-home"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="active-bre"><a href="#"> Add New Account</a></li>
                        </ul>
                    </div>

                    <!--== User Details ==-->
                    <div class="sb2-2-3">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-inn-sp admin-form">
                                    <div class="sb2-2-add-blog sb2-2-1">
                                        <h2>Add New Course</h2>
                                        <!--<p>The .table class adds basic styling (light padding and only horizontal dividers) to a table:</p>-->

                                        <ul class="nav nav-tabs tab-list">
                                            <li class="active"><a data-toggle="tab" href="#home" aria-expanded="true"><i class="fa fa-info" aria-hidden="true"></i> <span>Detail</span></a></li>
                                            <li class=""><a data-toggle="tab" href="#menu1" aria-expanded="false"><i class="fa fa-bed" aria-hidden="true"></i> <span>ADMIN</span></a></li>
                                            <li class=""><a data-toggle="tab" href="#menu2" aria-expanded="false"><i class="fa fa-picture-o" aria-hidden="true"></i> <span>Student</span></a></li>
                                            <li class=""><a data-toggle="tab" href="#menu3" aria-expanded="false"><i class="fa fa-facebook" aria-hidden="true"></i> <span>Lecturer (Teacher)</span></a></li>
                                            <li class=""><a data-toggle="tab" href="#menu4" aria-expanded="false"><i class="fa fa-phone" aria-hidden="true"></i> <span>CRDD</span></a></li>
                                        </ul>

                                        <div class="tab-content">
                                            <div id="home" class="tab-pane fade active in">
                                                <div class="box-inn-sp">
                                                    <div class="inn-title">
                                                        <h4>Account Information</h4>
                                                        <p></p>
                                                    </div>
                                                    <div class="bor">
                                                        <form action="ádasd" method="get">
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <input id="list-title" type="text" class="validate" name="userName">
                                                                    <label for="list-title" class="">User Name</label>
                                                                </div>
                                                                <div class="input-field col s12">
                                                                    <input id="list-name" type="password" class="validate" name="password">
                                                                    <label for="list-name">Password</label>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <input id="list-name" type="text" class="validate" name="email">
                                                                    <label>Email</label>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <select nmae="role">
                                                                        <option value="" disabled selected>Role</option>
                                                                        <c:forEach begin="0" end="5" var="i">
                                                                            <option value="${i+1}" >Role ${i+1}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <i class="waves-effect waves-light btn-large waves-input-wrapper" style="">
                                                                        <input type="submit" class="waves-button-input" value="Submit"></i>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="menu1" class="tab-pane fade">
                                                <div class="inn-title">
                                                    <h4>ADMIN</h4>
                                                    <!--<p>Airtport Hotels The Right Way To Start A Short Break Holiday</p>-->
                                                </div>
                                                <div class="bor ad-cou-deta-h4">
                                                    <form action="" method="">
                                                        <h4>Requirements:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Course Descriptions:</label>
                                                            </div>
                                                        </div> 
                                                        <h4>Feese:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="number" class="validate" required>
                                                                <label>1'st terms feese</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <input type="number" class="validate" required>
                                                                <label>2'nd terms feese</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <input type="number" class="validate" required>
                                                                <label>3'rd terms feese</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Price Descriptions:</label>
                                                            </div>
                                                        </div>
                                                        <h4>Student Profile:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Student Profile Descriptions:</label>
                                                            </div>
                                                        </div>	
                                                        <h4>How to apply this course:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Step 1 Descriptions:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Step 2 Descriptions:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Step 3 Descriptions:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Step 4 Descriptions:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Step 5 Descriptions:</label>
                                                            </div>
                                                        </div>	
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input" value="Submit"></i>
                                                            </div>
                                                        </div>										
                                                    </form>
                                                </div>
                                            </div>
                                            <div id="menu2" class="tab-pane fade">
                                                <div class="inn-title">
                                                    <h4>Photo Gallery</h4>
                                                    <p>Airtport Hotels The Right Way To Start A Short Break Holiday</p>
                                                </div>
                                                <div class="bor">
                                                    <form action="asdasdsa">
                                                        <div class="file-field input-field">
                                                            <div class="btn admin-upload-btn">
                                                                <span>File</span>
                                                                <input type="file" multiple="" >
                                                            </div>
                                                            <div class="file-path-wrapper">
                                                                <input class="file-path validate" type="text" placeholder="Upload course banner image">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input" value="Upload"></i>
                                                            </div>
                                                        </div>

                                                    </form>
                                                </div>
                                            </div>
                                            <div id="menu3" class="tab-pane fade">
                                                <div class="inn-title">
                                                    <h4>Time table</h4>
                                                    <p>Airtport Hotels The Right Way To Start A Short Break Holiday</p>
                                                </div>
                                                <div class="bor ad-cou-deta-h4">
                                                    <form>
                                                        <h4>1st semester:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Title:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Descriptions:</label>
                                                            </div>
                                                        </div> 
                                                        <h4>2nd semester:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Title:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Descriptions:</label>
                                                            </div>
                                                        </div> 
                                                        <h4>3rd semester:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Title:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Descriptions:</label>
                                                            </div>
                                                        </div> 
                                                        <h4>4th semester:</h4>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input type="text" class="validate" required>
                                                                <label>Title:</label>
                                                            </div>
                                                            <div class="input-field col s12">
                                                                <textarea class="materialize-textarea"></textarea>
                                                                <label>Descriptions:</label>
                                                            </div>
                                                        </div> 

                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input" value="Submit"></i>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div id="menu4" class="tab-pane fade">
                                                <div class="inn-title">
                                                    <h4>Contact Info</h4>
                                                    <p>Airtport Hotels The Right Way To Start A Short Break Holiday</p>
                                                </div>
                                                <div class="bor">
                                                    <form>
                                                        <div class="row">
                                                            <div class="input-field col s6">
                                                                <input id="t5-n1" type="text" class="validate">
                                                                <label for="t5-n1">Contact Name</label>
                                                            </div>
                                                            <div class="input-field col s6">
                                                                <input id="t5-n2" type="text" class="validate">
                                                                <label for="t5-n2">Alter Contact Name</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s6">
                                                                <input id="t5-n3" type="number" class="validate">
                                                                <label for="t5-n3">Phone</label>
                                                            </div>
                                                            <div class="input-field col s6">
                                                                <input id="t5-n4" type="number" class="validate">
                                                                <label for="t5-n4">Mobile</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <input id="t5-n5" type="email" class="validate">
                                                                <label for="t5-n5">Email</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <textarea id="t5-n6" class="materialize-textarea"></textarea>
                                                                <label for="t5-n6">Listing Descriptions:</label>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="input-field col s12">
                                                                <i class="waves-effect waves-light btn-large waves-input-wrapper" style=""><input type="submit" class="waves-button-input" value="Upload"></i>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>
