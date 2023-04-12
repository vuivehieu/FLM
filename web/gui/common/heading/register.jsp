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

                <form action="register" method="post" onsubmit="checkData()">
                  <!--<p>Please login to your account</p>-->

                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px;"
                      placeholder="User Name"  name="userName" value="${userName != null ? userName : ''}" id="userName" />
                    <label class="form-label" for="userName">User Name</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px;"
                      placeholder="Full Name"  value="${fullName != null ? fullName : ''}" name="fullName" id="fullName" />
                    <label class="form-label" for="fullName">Full Name</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px;"
                      placeholder="Email"  name="email" value="${email != null ? email : ''}" id="email" />
                    <label class="form-label" for="email">Email</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="password" class="form-control validate" style="font-size: 15px;" name="password" value="${password != null ? password : ''}" id="password" onchange="checkPassword()" />
                    <label class="form-label" for="password">Password</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                    <input type="password" class="form-control validate" style="font-size: 15px;" name="comfirm-password" value="${password != null ? password : ''}" id="comfirm-password" onchange="checkPassword()" />
                    <label class="form-label" for="password">Confirm password</label>
                  </div>

                  <div class="text-center pt-1 mb-5 pb-1">
                    <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" >Login</button>
                    <div>
                        <div class="input-field s12"> <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal1" onclick="closeModal2()">Are you a already member ? Login</a> </div>
                    </div>
                  </div>
                   
                   <span id="messageRegister" style="display: none">${messageRegister != null ? messageRegister : 'false'}</span>
                   <span id="errorMessage" style="color: red"></span>
                </form>
                <a href="#" id="btn-close-2" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" />
                </a>
              </div>
            </div>
            <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
              <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                <h4 class="mb-4">Welcome to FPT Learning Material</h4>
                <p class="small mb-0">Don't have an account? Create your account. It's take less then a minutes</p>
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

                        if (messageRegister.innerHTML !== 'OK' && messageRegister.innerHTML !== 'false') {

//                            if (messageRegister.innerHTML === 'userNameemail') {
//                                document.getElementById('userName').style.border = '1px solid red';
//                                document.getElementById('email').style.border = '1px solid red';
//                            } else if (messageRegister.innerHTML === 'userName') {
//                                document.getElementById('userName').style.border = '1px solid red';
//                                document.getElementById('userName').focus();
//                            } else if (messageRegister.innerHTML === 'email') {
//                                document.getElementById('email').style.border = '1px solid red';
//                            }
                            
                            document.getElementById('modal2').style.display = 'block';
                            document.getElementById('modal2').classList.add('in');
                            document.getElementById("errorMessage").innerHTML = "User Name hoac email da ton tai";
                            document.getElementById('btn-close-2').addEventListener('click', function () {
                                document.getElementById('modal2').style.display = 'none';
                                document.getElementById('modal2').classList.remove('in');
                            });
                        }
                       
                        
//                        if (messageRegister.innerHTML === 'OK') {
//                            document.getElementById('modal2').style.display = 'block';
//                            document.getElementById('modal2').classList.add('in');
//                            
//                            // tao o input nhap ma code dang ky
//                            
//                            document.getElementById('btn-close-2').addEventListener('click', function () {
//                                document.getElementById('modal2').style.display = 'none';
//                                document.getElementById('modal2').classList.remove('in');
//                            });
//                            
//                        }

//                        function checkPassword() {
//                            var password = document.getElementById('password');
//                            var confirm_password = document.getElementById('comfirm-password');
//                            
//                            if (password.value !== confirm_password.value || password.length < 6 || password.length > 20) {
//                                password.style.border = '1px solid red';
//                                confirm_password.style.border = '1px solid red';
//                            } else {
//                                password.style.border = '1px solid green';
//                                confirm_password.style.border = '1px solid green';
//                            }
//                        }

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
