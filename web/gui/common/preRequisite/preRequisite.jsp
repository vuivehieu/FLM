<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <!-- Font Awesome -->
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"
        />
           <!-- MDB -->
        <script
          type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"
        ></script>
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
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
            <![endif]-->

        <style>
/*            .descripCur {
                overflow: hidden;
                text-overflow: ellipsis;
                line-height: 25px;
                -webkit-line-clamp: 3;
                height: 75px;
                display: -webkit-box;
                -webkit-box-orient: vertical;
            }*/

            .pad-bot-70 {
                padding-left: 5rem;
                padding-right: 5rem;
            }

            ul.ul-custom > li { 
                padding-left: 2rem;
            }
            .gradient-custom-2 {
        /* fallback for old browsers */
        background: #fccb90;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
        }

        @media (min-width: 768px) {
        .gradient-form {
        height: 100vh !important;
        }
        }
        @media (min-width: 769px) {
        .gradient-custom-2 {
        border-top-right-radius: .3rem;
        border-bottom-right-radius: .3rem;
        }
        }
        </style>
    </head>

    <body>

        <jsp:include page="../heading/heading.jsp"/>
        <section class="pop-cour">
            <div class="container com-sp pad-bot-70">
                <div class="row">
                    <div class="con-title col-12">
                        <h2>Show Learning Path <span>of a Subject</span></h2>
                    </div>


                </div>
                <div class="col-12" style="display: flex;align-items: center;">
                    <!-- start select -->
                        <select id="preRequisite" name="select" class="browser-default" style="font-size: 16px;width: 17rem;">
                          <option value='subjectName' selected>Subject Name</option>
                          <option value='syllabusName'>Syllabus Name</option>
                        </select>
                    <!--end select-->
                    <form class="form-inline" style="width: 100%;padding-left: 3rem;">
                        <div style="position: relative">
                            <input type="text" name="type" value="preRequisite" hidden="">
                            <input class="" id="search" style="height: 5rem;font-size: unset" type="text" placeholder="Search">
                            <span style="position: absolute; top:20px; right:10px" class="fa fa-search" onclick="searchPreRequisite()"></span>
                        </div>
                    </form>
                </div>
            </div>

            <div class="container pad-bot-70" id="root">
                
            </div>
        </section> 

        <jsp:include page="../footer/footer.jsp"/>

        <script>
            let request;
            function searchPreRequisite() {
                //                start select
                let select = document.getElementById('preRequisite');
                let selectValue = select.value;
                select.addEventListener('change',function(){
                    selectValue = select.value;
                });
//                end select
                let key = document.getElementById("search").value;
                let url = './search?type=preRequisite&keysearch=' + key + '&filter=' +selectValue;


                if (window.XMLHttpRequest) {
                    request = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    request = new ActiveXObject("Microsoft.XMLHTTP");
                }

                try {
                    request.onreadystatechange = getInfo;
                    request.open("POST", url, true);
                    request.send("POST");
                } catch (e) {
                    alert("Unable to connect server")
                }
            }

            function getInfo() {
                if (request.readyState === 4) {
                    var val = request.responseText;
                    document.getElementById("root").innerHTML = val;
                }
            }
        </script>

        <!--Import jQuery before materialize.js-->
        <script src="js/main.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
             $('#search').keyup(function (e) {
                if (e.keyCode === 13) {
                    searchPreRequisite();
                }
            });
        </script>
        <script src="js/materialize.min.js"></script>
        <script src="js/custom.js"></script>
     
    </body>


</html>
