<%-- 
    Document   : profile
    Created on : 14-01-2023, 23:45:23
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@include file="/common/heading/heading.jsp" %>--%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Education Master Template</title>
        <!-- META TAGS -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Education master is one of the best educational html template, it's suitable for all education websites like university,college,school,online education,tution center,distance education,computer education">
        <meta name="keyword" content="education html template, university template, college template, school template, online education template, tution center template">
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
         <!-- MDB -->
        <script
          type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"
        ></script>
       
        <!-- Font Awesome -->
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"
        />
        <!-- Google Fonts -->
        <link
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"
        />
        <!-- MDB -->
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
          rel="stylesheet"
        />
        <style>
            input[type=password] {
                font-size: inherit;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../common/heading/heading.jsp"/>

        <!--SECTION START-->
        <section>
            <div class="pro-cover">
            </div>
            <div class="pro-menu">
                <div class="container">
                    <div class="col-md-9 col-md-offset-3">
                        <ul>
                            <li>
                                <a href="profile">Profile</a>
                            </li>
                            <li>
                                <a href="#!">Change Avatar</a>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
            <div class="stu-db">
                <div class="container pg-inn">
                    <div class="col-md-3">

                    </div>
                    <div class="col-md-12">
                        <div class="udb">
                            <div class="udb-sec udb-prof">

                                <form action="changeAvatar" method="post" enctype="multipart/form-data">
                                    <div class="row" style="display: flex; justify-content: center"> 
                                        <div class="col" style="margin-right: 2rem">
                                            <div class="p-4 border border-primary">
                                                <h2 class="mb-5 border-bottom pb-3">Upload Avatar</h2>
                                                <form>
                                                    <input type="file" name="image" class="form-control" accept="image/*"
                                                           onchange="updatePreview(this, 'image-preview')" >
                                                    <div class="text-end" style="margin-top: 2rem">
                                                        <button class="btn btn-primary mt-3 ">Save</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                        <div class="col">
                                            <div class="p-4 border border-secondary">
                                                <h2 class="border-bottom pb-3 mb-4">Avatar Preview</h2>
                                                <div class="text-center">
                                                    <img id="image-preview" 
                                                         src="https://via.placeholder.com/400"
                                                         style="width:400px"
                                                         class="rounded rounded-circle" alt="placeholder">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!--SECTION END-->


        <!--END HEADER SECTION-->
        <%@include file="../common/footer/footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
        <script type="text/javascript">
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

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
    </body>


</html>
