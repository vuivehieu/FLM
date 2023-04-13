
<!--<section class="h-100 " style="background-color: #eee;">
     FORGOT SECTION 
    <div id="modal3" class="modal" role="dialog">
        <div class="log-in-pop">
            <div class="log-in-pop-left">
                <h1>Hello... </h1>
                <p>Don't have an account? Create your account. It's take less then a minutes</p>
                <h4>Login with social media</h4>
                <ul>
                    <li><a href="#"><i class="fa fa-facebook"></i> Facebook</a>
                    </li>
                    <li><a href="#"><i class="fa fa-google"></i> Google+</a>
                    </li>
                    <li><a href="#"><i class="fa fa-twitter"></i> Twitter</a>
                    </li>
                </ul>
            </div>
            <div class="log-in-pop-right">
                <a href="#" id="btn-close" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" />
                </a>
                <h4>Forgot password</h4>
                <p>Don't have an account? Create your account. It's take less then a minutes</p>
                <form class="s12" action="forgotPassword" method="post">
                    <div>
                        <div class="input-field s12">
                            <input type="email"  name="email" class="validate">
                            <label>Enter Your Gmail</label>
                            <p id="messageForgot" class="text-danger">${errorForgot != null ? errorForgot : ''}</p>
                        </div>
                    </div>
                    <div>
                        <div class="input-field s4">
                            <input type="submit" value="Submit" class="waves-effect waves-light log-in-btn"> </div>
                    </div>
                    <div>
                        <div class="input-field s12"> <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal1">Are you a already member ? Login</a> | <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal2">Create a new account</a> </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>-->
                        

<section class="h-100 " style="background-color: #eee;">
<!-- REGISTER SECTION -->
<div id="modal3" class="modal" role="dialog">
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
                  <h4 class="mt-1 mb-5 pb-1">Forgot password</h4>
                </div>
                    
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;"
                      placeholder="Email" id="emailForgot" onFocus="focusEmail()"/>
                    <label class="form-label" for="emailForgot">Email</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="password" onFocus="focusPass()" class="form-control validate" 
                           style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled  id="passwordForgot"  />
                    <label class="form-label" for="passwordForgot">Password</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                     <input type="password" onFocus="focusConfirmPass()" class="form-control validate" 
                            style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled id="comfirm-passwordForgot"  />
                      <label class="form-label" for="comfirm-passwordForgot">Confirm password</label>
                  </div>
                       
                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                         <input type="text" class="form-control validate" onFocus="focusInputCode()"
                           id="verifyCodeForgot" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;"/>
                       <label class="form-label" for="verifyCodeForgot">Input Code</label>
                    </div>
                  </div>
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                        <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button" style="font-size: 15px;" 
                                id="verifyCodeBtnForgot" onclick="verifyEmailForgot()">Verify Email</button>
                    </div>
                  </div>
                </div>
                       
                        
                <div class="text-center pt-1 pb-1">
                  <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3 disabled" type="button" style="font-size: 15px;" id="saveNewPass" onclick="saveNewPassword()">Save new password</button>
                </div>
                   
                   <p id="messageChangPassSuccess" style="color: blue; text-align: center;"></p>
                   <p id="errorMessageForgot" style="color: red; text-align: center; margin-bottom: 30px;"></p>

                <div class="input-field s12" style="text-align: center;"> <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal1" onclick="closeModal3()" id="changeLoginPage">Are you a already member ? Login</a> </div>

                <a href="#" id="btn-close-3" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" />
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
//    let messageForgot = document.getElementById('messageForgot');
//    if (messageForgot.innerHTML !== '') {
//        document.getElementById('modal3').style.display = 'block';
//        document.getElementById('modal3').classList.add('in');
//        document.getElementById('btn-close').addEventListener('click', function () {
//            document.getElementById('modal3').style.display = 'none';
//            document.getElementById('modal3').classList.remove('in');
//        });
//    }
    
    let codeSendMailForgot= '';
    function verifyEmailForgot(){
        // mail phai co dang @gmail.com, @fpt.edu.vn
        codeSendMailForgot = '';
        let email = $('#emailForgot').val();
        
        
        if(email.includes("@gmail.com") || email.includes("@fpt.edu.vn")){
            let data = {
            emailForgot : email
            };

            $.ajax({
                url: '/SWP391-G2/forgotPassword',
                type: "POST",
                contentType: "application/json", // NOT dataType!
                data: JSON.stringify(data),
                success: function(response) {
                    // handle success

                    if(response.code != null){
                        codeSendMailForgot = response.code;
                        console.log("response.code", response.code);
                        $('#emailForgot').prop('disabled', true);
                        
                        $('#password').prop('disabled', false);
                        $('#comfirm-password').prop('disabled', false);
                        $('#saveNewPass').removeClass('disabled');
                        $('#verifyCodeBtnForgot').addClass('disabled');
                    }else {
                        document.getElementById("errorMessageForgot").innerHTML = response.error;
                    }

                },
                error: function (xhr, status, error) {
                     // handle error
                    console.log("error: ", error);
                }
            });
        }else {
            console.log('email', email)
            document.getElementById("errorMessageForgot").innerHTML = "Email must be in the form @gmail.com and @fpt.edu.vn";
        }
    }
    
    function saveNewPassword(){
        console.log("codeSendMailForgot", codeSendMailForgot);
        let email = $('#emailForgot').val();
        let password = $('#passwordForgot').val();
        let confirmPass = $('#comfirm-passwordForgot').val();
        let verifyCode = $('#verifyCodeForgot').val();
        let data = {
            email : email,
            password : password
        };

        if(checkConfirmPassForgot()){
            if(verifyCode != codeSendMailForgot){
                document.getElementById("errorMessageForgot").innerHTML = "The code is not correct";
            }

            $.ajax({
                url: '/SWP391-G2/update-pass-forgot',
                type: "POST",
                contentType: "application/json", // NOT dataType!
                data: JSON.stringify(data),
                success: function(response) {
                    // handle success

                    if(response.messageChangPassSuccess != null){
                        console.log("response.messageChangPassSuccess", response.messageChangPassSuccess);
                        document.getElementById("messageChangPassSuccess").innerHTML = response.messageChangPassSuccess;

                       setTimeout(function() {
                            let btnClose = document.getElementById("changeLoginPage");
                            btnClose.click();
                        }, 3000);


                    }else {
                        console.log("response.error", response.error);
                        document.getElementById("errorMessageForgot").innerHTML = response.error;
                    }

                },
                error: function (xhr, status, error) {
                     // handle error
                    console.log("error: ", error);
                }
            });
        }
    }
    
    function checkConfirmPassForgot() {
        var password = document.getElementById('passwordForgot');
        var confirm_password = document.getElementById('comfirm-passwordForgot');

        if (password.value !== confirm_password.value) {
            document.getElementById("errorMessageForgot").innerHTML = "Password don't match";
            return false;
        }else {
            return true;
        }
    }

    function closeModal3() {
        document.getElementById('modal3').style.display = 'none';
        document.getElementById('modal3').classList.remove('in');

    }
</script>

