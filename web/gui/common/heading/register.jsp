<!--<style>
    .log-in-pop-right form label {
        left: 10px;
        top: -10px;
    }
</style>-->

<!--<section>
     REGISTER SECTION 
    <div id="modal2" class="modal" role="dialog">
        <div class="log-in-pop">
            <div class="log-in-pop-left">
                <h1>Hello...</h1>
                <p>Don't have an account? Create your account. It's take less then a minutes</p>
                <h4>Login with social media</h4>
                <ul>
                    <li><a href="#"><i class="fa fa-google"></i> Google+</a></li>
                </ul>
            </div>
            <div class="log-in-pop-right">
                <a href="#" id="btn-close-2" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" />
                </a>
                <h4>Create an Account</h4>
                <p>Don't have an account? Create your account. It's take less then a minutes</p>
                <form class="s12" action="register" method="post" onsubmit="checkData()">
                    <div id="data"></div>
                    <div>
                        <div class="input-field s12">
                            <input type="text" data-ng-model="name1" class="validate" name="userName" value="${userName != null ? userName : ''}" id="userName">
                            <label>User name</label>
                        </div>
                    </div>
                    <div>
                        <div class="input-field s12">
                            <input type="text" data-ng-model="name1" class="validate" value="${fullName != null ? fullName : ''}" name="fullName" id="fullName">
                            <label>Full Name</label>
                        </div>
                    </div>
                    <div>
                        <div class="input-field s12">
                            <input type="email" class="validate" name="email" value="${email != null ? email : ''}" id="email">
                            <label>Email</label>
                        </div>
                    </div>
                    <div>
                        <div class="input-field s12">
                            <input type="password" class="validate" name="password" value="${password != null ? password : ''}" id="password" onchange="checkPassword()">
                            <label>Password</label>
                        </div>
                    </div>
                    <div>
                        <div class="input-field s12">
                            <input type="password" class="validate" name="comfirm-password" value="${password != null ? password : ''}" id="comfirm-password" onchange="checkPassword()">
                            <label>Confirm password</label>
                        </div>
                    </div>

                    <div>
                        <div class="input-field s4">
                            <input type="submit" value="Register" class="waves-effect waves-light log-in-btn"> </div>
                    </div>
                    <div>
                        <div class="input-field s12"> <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal1" onclick="closeModal2()">Are you a already member ? Login</a> </div>
                    </div>
                    <span id="messageRegister" style="display: none">${messageRegister != null ? messageRegister : 'false'}</span>
                   <span id="errorMessage" style="color: red"></span>
                    <script>
                       
                        let messageRegister = document.getElementById('messageRegister');

                        if (messageRegister.innerHTML !== 'OK' && messageRegister.innerHTML !== 'false') {

                            if (messageRegister.innerHTML === 'userNameemail') {
                                document.getElementById('userName').style.border = '1px solid red';
                                document.getElementById('email').style.border = '1px solid red';
                            } else if (messageRegister.innerHTML === 'userName') {
                                document.getElementById('userName').style.border = '1px solid red';
                                document.getElementById('userName').focus();
                            } else if (messageRegister.innerHTML === 'email') {
                                document.getElementById('email').style.border = '1px solid red';
                            }
                            document.getElementById('modal2').style.display = 'block';
                            document.getElementById('modal2').classList.add('in');
                            document.getElementById('btn-close-2').addEventListener('click', function () {
                                document.getElementById('modal2').style.display = 'none';
                                document.getElementById('modal2').classList.remove('in');
                            });
                        }

                        function checkPassword() {
                            var password = document.getElementById('password');
                            var confirm_password = document.getElementById('comfirm-password');
                            
                            if (password.value !== confirm_password.value || password.length < 6 || password.length > 20) {
                                password.style.border = '1px solid red';
                                confirm_password.style.border = '1px solid red';
                            } else {
                                password.style.border = '1px solid green';
                                confirm_password.style.border = '1px solid green';
                            }
                        }

                        function checkData() {
                            var password = document.getElementById('password');
                            var confirm_password = document.getElementById('comfirm-password');
                            
                            if (password.value !== confirm_password.value || password.length < 6 || password.length > 20) {
                                event.preventDefault();
                                 document.getElementById("errorMessage").innerHTML = "Password don't match";
                            }
                            
//                            if (checkPassword()) {
//                                return true;
//                            }
//                            return false;
                        }

                        function closeModal2() {
                            document.getElementById('modal2').style.display = 'none';
                            document.getElementById('modal2').classList.remove('in');

                        }

                    </script>
                </form>
            </div>
        </div>
    </div>

</section>-->
                   
                   
<section class="h-100 " style="background-color: #eee;">
      <!-- REGISTER SECTION -->
<div id="modal2" class="modal" role="dialog">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-10">
        <div class="card rounded-3 text-black">
          <div class="row g-0">
            <div class="col-lg-6">
              <div class="card-body p-md-5 mx-md-4">

                <div class="text-center">
<!--                  <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
                    style="width: 185px;" alt="logo">-->
                  <h4 class="mt-1 mb-5 pb-1">Create an Account</h4>
                </div>

                <!--<form action="register" method="post" onsubmit="checkData()">-->
                  <!--<p>Please login to your account</p>-->

                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled
                      placeholder="User Name"  name="userName" value="${userName != null ? userName : ''}" id="userName" />
                    <label class="form-label" for="userName">User Name</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled
                      placeholder="Full Name"  value="${fullName != null ? fullName : ''}" name="fullName" id="fullName" />
                    <label class="form-label" for="fullName">Full Name</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;"
                      placeholder="Email" id="email" />
                    <label class="form-label" for="email">Email</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="password" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled name="password" value="${password != null ? password : ''}" id="password"  />
                    <label class="form-label" for="password">Password</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                     <input type="password" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled name="comfirm-password" value="${password != null ? password : ''}" id="comfirm-password"  />
                      <label class="form-label" for="password">Confirm password</label>
                  </div>
                       
                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                         <input type="text" class="form-control validate"
                          value="${verifyCode != null ? verifyCode : ''}" name="verifyCode" id="verifyCode" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;"/>
                       <label class="form-label" for="verifyCode">Input Code</label>
                    </div>
                  </div>
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                        <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button" style="font-size: 15px;" id="verifyCodeBtn" onclick="verifyEnail()">Verify Email</button>
                    </div>
                  </div>
                </div>
                       
                        
                <div class="text-center pt-1 pb-1">
                  <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3 disabled" type="button" style="font-size: 15px;" id="register">Register</button>
                </div>
                   
                   <span id="messageRegister" style="display: none">${messageRegister != null ? messageRegister : 'false'}</span>
                   <p id="errorMessage" style="color: red; text-align: center; margin-bottom: 30px;"></p>

                <div class="input-field s12" style="text-align: center;"> <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal1" onclick="closeModal2()">Are you a already member ? Login</a> </div>

                  <!--end form xac nhan ma code-->  
                <a href="#" id="btn-close-2" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" />
                </a>
              </div>
            </div>
            <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
              <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                <h4 class="mb-4" style="font-size: 15px;">Welcome to FPT Learning Material</h4>
                <p class="small mb-0" style="font-size: 15px;">Don't have an account? Create your account. It's take less then a minutes</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</section>

<script>
                       
                        let messageRegister = document.getElementById('messageRegister');
                        let messageVerifyCode = document.getElementById('messageVerifyCode');
                        let successRegisterMessage = document.getElementById('successRegisterMessage');

                        if (messageRegister.innerHTML !== 'OK' && messageRegister.innerHTML !== 'false') {
                            
                            document.getElementById('modal2').style.display = 'block';
                            document.getElementById('modal2').classList.add('in');
                            document.getElementById("errorMessage").innerHTML = "User Name hoac email da ton tai";
                            document.getElementById('btn-close-2').addEventListener('click', function () {
                                document.getElementById('modal2').style.display = 'none';
                                document.getElementById('modal2').classList.remove('in');
                            });
                        }
                        
                        if (messageVerifyCode.innerHTML === 'OK') {
                            document.getElementById('modal2').style.display = 'block';
                            document.getElementById('modal2').classList.add('in');
                            document.getElementById('btn-close-2').addEventListener('click', function () {
                                document.getElementById('modal2').style.display = 'none';
                                document.getElementById('modal2').classList.remove('in');
                            });
                            
                            //disable register
                            document.getElementById('register').classList.add("disabled");
                            // remove class disabled o nut gui ma code
                            document.getElementById('verifyCodeBtn').classList.remove("disabled");
                        }
                        
                        if(successRegisterMessage.innerHTML === 'OK'){
                            document.getElementById('modal2').style.display = 'block';
                            document.getElementById('modal2').classList.add('in');
                            document.getElementById('btn-close-2').addEventListener('click', function () {
                                document.getElementById('modal2').style.display = 'none';
                                document.getElementById('modal2').classList.remove('in');
                            });
                            document.getElementById("successRegisterMessageShow").innerHTML = "Dang ky thanh cong";
                            document.getElementById('register').classList.remove("disabled");
                            document.getElementById('verifyCodeBtn').classList.remove("disabled");
                        }
                        
                        function verifyEnail(){
//                            let email = document.getElementById('email').value
                           let email = $('#email').val();
//                            let formData = {
//                                emailUserRgister: email
//                            };
                            console.log("email", email);
                            
                            $.ajax({
                                url: '/SWP391-G2/comfirmEmail',
                                type: 'POST',
                                data: {
                                    emailUserRgister: email
                                },
                                processData: false,
                                contentType: false,
                                success: function (data) {
                                    // handle success
                                    console.log("formData", formData);
                                    console.log("data", data);
                                },
                                error: function (xhr, status, error) {
                                     // handle error
                                    console.log("error: ", error);
                                }
                            });
                        }
                        
                        //                            $.ajax({
//                                url: "/api/gallery/galleryId/"  + id, // url where to submit the request
//                                method: "GET", // type of action POST || GET
//                                success: function (data) {
//                                    console.log("data", data);
//
//                                },
//                                error: function (xhr, status, error) {
//                                    console.log("error: ", error);
//                                    return false;
//                                }
//                            });
                        
//                        $('#fileInput').on('change', function () {
//                            var file = this.files[0];
//                            var formData = new FormData();
//                            formData.append('file', file);
//                            $.ajax({
//                                url: '/api/file/upload',
//                                type: 'POST',
//                                data: formData,
//                                processData: false,
//                                contentType: false,
//                                success: function (data) {
//                                    // handle success
//                                    toastr.success(messageSuccess, {timeOut: 3000})
//                                    paginateMedia(currentPage, 5, currentSearch);
//                                },
//                                error: function (xhr, status, error) {
//                                    console.log(xhr.responseJSON);
//                                    if (xhr.responseJSON === "File already exists") {
//                                        toastr.error(messageFileExist, {timeOut: 3000})
//                                    } else {
//                                        toastr.error(messageError, {timeOut: 3000})
//                                    }
//                                    // handle error
//                                }
//                            });
//                        });
                        

                        function checkData() {
                            var password = document.getElementById('password');
                            var confirm_password = document.getElementById('comfirm-password');
                            
                            if (password.value !== confirm_password.value || password.length < 6 || password.length > 20) {
                                event.preventDefault();
                                 document.getElementById("errorMessage").innerHTML = "Password don't match";
                            }
                        }

                        function closeModal2() {
                            document.getElementById('modal2').style.display = 'none';
                            document.getElementById('modal2').classList.remove('in');

                        }

</script>
