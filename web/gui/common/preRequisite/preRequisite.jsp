<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


    <head>
        <meta charset="utf-8">
        <title>Education Master Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="fe/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="fe/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="fe/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="fe/css/style.css" rel="stylesheet">
        
        
         <!--MDB--> 
        <script
          type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"
        ></script>
       
         <!--Font Awesome--> 
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"
        />
         <!--Google Fonts--> 
        <link
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"
        />
         <!--MDB--> 
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
          rel="stylesheet"
        />
        
        <style>
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

        
        <script>
             $('#search').keyup(function (e) {
                if (e.keyCode === 13) {
                    searchPreRequisite();
                }
            });
        </script>
        
         <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="fe/lib/easing/easing.min.js"></script>
        <script src="fe/lib/waypoints/waypoints.min.js"></script>
        <script src="fe/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="fe/lib/isotope/isotope.pkgd.min.js"></script>
        <script src="fe/lib/lightbox/js/lightbox.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="fe/mail/jqBootstrapValidation.min.js"></script>
        <script src="fe/mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="fe/js/main.js"></script>
     
    </body>


</html>
