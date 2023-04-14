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
        
        <!--Start Content-->
        <div class="container-fluid" >
            <h1 style="text-align: center; margin-top: 2rem;">Curriculum Management</h1>
                <div class="row" style="margin-top: 30px;">
                    <div class="col-md-6"></div>
                    <div class="col-md-6">
                        <form class="form-inline" style="position: relative;display: flex;">
                            <select class="form-control" style="border-radius: unset;" id="sel">
                                <option value="curCode" selected>Curriculum Code</option>
                                <option value="curName">Name</option>
                            </select>
                            <div class="form-group" style="flex-grow: 1;">
                                <input style="width: 100%; border-radius: unset;" type="text" class="form-control" id="search" value="${key}"
                                    placeholder="Search">
                            </div>
                            <button style="position: absolute; right: 0; border-radius: unset;" type="submit" onclick="searchCurriculum()"
                                class="btn btn-default"><i class="fa fa-search"></i></button>
                        </form>
                    </div>
                </div>
                                    
                <c:if test="${!list.isEmpty() && list != null}">
                    <div class="table-responsive" style="margin-top: 30px;" id="root">
                        <table class="table table-bordered cart_summary">
                            <thead>
                                <tr style="background-color: rgb(185, 182, 182);">
                                    <th style="vertical-align: middle;">No</th>
                                    <th style="vertical-align: middle;">
                                        Curriculum Code
                                    </th>
                                    <th style="vertical-align: middle;">Name</th>
                                    <th style="vertical-align: middle;">Description</th>
                                    <th style="vertical-align: middle;">
                                        Decision No MM/dd/yyy
                                    </th>
                                    <th style="vertical-align: middle;">Total Credit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="index" value="${start + 1}"/>
                                <c:forEach items="${list}" var="item">
                                    <tr>
                                        <td>${index}</td>
                                        <td>
                                            <span>${item.curCode}</span>
                                        </td>
                                        <td>
                                            <a href="curriculumDetails?curid=${item.curid}">
                                                <p style="font-size: 18px; font-weight: 500; color: #ff6634" class="product-name">
                                                    ${item.curName_EN}
                                                </p>
                                                <small class="cart_ref">${item.curName_VI}</small>
                                            </a>
                                            
                                        </td>
                                        <td>
                                            <p>
                                                ${item.description}
                                            </p>
                                        </td>
                                        <td class="qty">
                                            <a href="decision?decisionNo=${item.decision.decisionNo}" >
                                                <p style="font-size: 18px; font-weight: 500; color: #ff6634" class="product-name">
                                                    ${item.decision.decisionNo}
                                                </p>
                                                <small class="cart_ref">${custom.getDateFormat('MM/dd/yyyy', item.decision.approvedDate)}</small>
                                            </a>
                                        </td>
                                        <td class="price">
                                            <span>${item.totalCredit}</span>
                                        </td>
                                    </tr>                       
                                <c:set var="index" value="${index + 1}"/>
                                </c:forEach>
                            </tbody>
                        </table>
                        <ul class="pagination" style="display: flex; justify-content: center;">
<!--                            <li style="border: 1px solid #dee2e6; margin-right: 5px;padding:5px; width: 30px;text-align: center;"><a style="color: #ff6634;" href="#">1</a></li>
                            <li style="border: 1px solid #dee2e6; margin-right: 5px;padding:5px; width: 30px;text-align: center;" onclick="changePage(2)">2</li>
                            <li style="border: 1px solid #dee2e6; margin-right: 5px;padding:5px; width: 30px;text-align: center;"><a style="color: #ff6634;" href="#">3</a></li>
                            <li style="border: 1px solid #dee2e6; margin-right: 5px;padding:5px; width: 30px;text-align: center;"><a style="color: #ff6634;" href="#">4</a></li>
                            <li style="border: 1px solid #dee2e6; margin-right: 5px;padding:5px; width: 30px;text-align: center;"><a style="color: #ff6634;" href="#">5</a></li>-->
                            
                            <li class="${(page == 1 ? 'disabled' : 'waves-effect')}"> 
                                <a onclick="changePage('${page - 1}')">
                                    <i class='fa fa-angle-left' aria-hidden='true'></i>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${numberOfPage}" var="i">
                                <li class=" ${page == i ? 'active' : 'waves-effect'}">
                                    <a onclick="changePage('${i}')">${i} </a>
                                </li>
                            </c:forEach>
                            <li class="${(page == numberOfPage ? 'disabled' : 'waves-effect')}">
                                <a onclick="changePage('${page + 1}')">
                                    <i class='fa fa-angle-right' aria-hidden='true'></i>
                                </a>
                            </li>
                            
                        </ul>
                    </div>
                </c:if>
            </div>
        <!--End Content-->
        
        <jsp:include page="../footer/footer.jsp"/>

        <script>
            let request;
            function searchCurriculum() {
//                start select
                let select = document.getElementById('sel');
                let selectValue = select.value;
                select.addEventListener('change',function(){
                    selectValue = select.value;
                });
//                end select
                let key = document.getElementById("search").value;
                let url = './search?type=curriculum&keysearch=' + key + '&filter=' +selectValue;


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
                    alert("Unable to connect server");
                }
            }
            
            function changePage(page) {
                let key = document.getElementById("search").value;
                let selectValue = document.getElementById('sel').value;
                let url = './search?type=curriculum&keysearch=' + key + '&filter=' +selectValue + '&page=' + page;
//                let url = './search?type=curriculum&keysearch=&filter=&page=' + page;


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
                    alert("Unable to connect server");
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
                    searchCurriculum();
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