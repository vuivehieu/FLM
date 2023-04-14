<%-- 
    Document   : resetPass
    Created on : 19-03-2023, 07:43:25
    Author     : phanh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>

    <body>

        <section>
            <div class="ad-log-main">
                <div class="ad-log-in">
                    <div class="ad-log-in-logo">
                        <a href="home"><img src="images/Logo_Đại_học_FPT.png" alt="" style="width: 100px"></a>
                    </div>
                    <div class="ad-log-in-con">
                        <div class="">
                            <h4>Reset Password</h4>

                            Hello, UserName: ${requestScope.userName}

                            <form class="s12" action="resetPass" method="post">
                                <div>
                                    <div class="input-field s12">
                                        <input type="text" name="password" onChange="checkValidation()" id="newPassword" data-ng-model="name" class="validate" required="">
                                        <label class="">New Password</label>
                                    </div>
                                </div>
                                
                                <input type="text" name="userName" value="${userName}" hidden="">

                                <div>
                                    <div class="input-field s4">
                                        <i class="waves-effect waves-light log-in-btn waves-input-wrapper" style=""><input type="submit" value="Reset" class="waves-button-input"></i> </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script>
            let newPassword = document.getElementById('newPassword');
            let confimPassword = document.getElementById('confimPassword');

            function checkValidation() {
                if (newPassword.value != confimPassword.value) {
                    newPassword.style.borderColor = 'red';
                    confimPassword.style.borderColor = 'red';
                    return false;
                } else {
                    return true;
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
