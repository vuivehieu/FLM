           
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
                      placeholder="User Name"  id="userName" onFocus="focusUserName()" />
                    <label class="form-label" for="userName">User Name</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled
                      placeholder="Full Name"  id="fullName" />
                    <label class="form-label" for="fullName">Full Name</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;"
                      placeholder="Email" id="email" onFocus="focusEmail()"/>
                    <label class="form-label" for="email">Email</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="password" onFocus="focusPass()" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled  id="password"  />
                    <label class="form-label" for="password">Password</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                     <input type="password" onFocus="focusConfirmPass()" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled id="comfirm-password"  />
                      <label class="form-label" for="comfirm-password">Confirm password</label>
                  </div>
                       
                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                         <input type="text" class="form-control validate" onFocus="focusInputCode()"
                           id="verifyCode" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;"/>
                       <label class="form-label" for="verifyCode">Input Code</label>
                    </div>
                  </div>
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                        <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button" style="font-size: 15px;" 
                                id="verifyCodeBtn" onclick="verifyEmail()">Verify Email</button>
                    </div>
                  </div>
                </div>
                       
                        
                <div class="text-center pt-1 pb-1">
                  <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3 disabled" type="button" style="font-size: 15px;" id="register" onclick="register()">Register</button>
                </div>
                   
                   <p id="messageRegister" style="color: blue; text-align: center;"></p>
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
    let codeSendMail= '';

    function verifyEmail(){
        // mail phai co dang @gmail.com, @fpt.edu.vn
        codeSendMail = '';
        let email = $('#email').val();
        
        if(email.includes("@gmail.com") || email.includes("@fpt.edu.vn")){
            let data = {
            email : email
            };

            $.ajax({
                url: '/SWP391-G2/comfirmEmail',
                type: "POST",
                contentType: "application/json", // NOT dataType!
                data: JSON.stringify(data),
                success: function(response) {
                    // handle success

                    if(response.code != null){
                        codeSendMail = response.code;
                        console.log("response.code", response.code);
                        $('#email').prop('disabled', true);
                        $('#userName').prop('disabled', false);
                        $('#fullName').prop('disabled', false);
                        $('#password').prop('disabled', false);
                        $('#comfirm-password').prop('disabled', false);
                        $('#register').removeClass('disabled');
                        $('#verifyCodeBtn').addClass('disabled');
                    }else {
                        document.getElementById("errorMessage").innerHTML = response.error;
                    }

                },
                error: function (xhr, status, error) {
                     // handle error
                    console.log("error: ", error);
                }
            });
        }else {
            document.getElementById("errorMessage").innerHTML = "Email must be in the form @gmail.com and @fpt.edu.vn";
        }
    }

    function register(){
        console.log("codeSendMail", codeSendMail);
        let userName = $('#userName').val();
        let fullName = $('#fullName').val();
        let email = $('#email').val();
        let password = $('#password').val();
//                            let confirmPass = $('#comfirm-password').val();
        let verifyCode = $('#verifyCode').val();
        let data = {
            userName : userName,
            fullName : fullName,
            email : email,
            password : password
        };

        if(checkConfirmPass()){
            if(verifyCode != codeSendMail){
            document.getElementById("errorMessage").innerHTML = "The code is not correct";
            }

            $.ajax({
                url: '/SWP391-G2/register',
                type: "POST",
                contentType: "application/json", // NOT dataType!
                data: JSON.stringify(data),
                success: function(response) {
                    // handle success

                    if(response.messageRegister != null){
                        console.log("response.messageRegister", response.messageRegister);
                        document.getElementById("messageRegister").innerHTML = response.messageRegister;

                        setTimeout(function() {
                            let btnClose = document.getElementById("btn-close-2");
                            btnClose.click();
                        }, 3000);


                    }else {
                        console.log("response.error", response.error);
                        document.getElementById("errorMessage").innerHTML = response.error;
                    }

                },
                error: function (xhr, status, error) {
                     // handle error
                    console.log("error: ", error);
                }
            });
        }
    }


    function checkConfirmPass() {
        var password = document.getElementById('password');
        var confirm_password = document.getElementById('comfirm-password');

        if (password.value !== confirm_password.value) {
//                                event.preventDefault();
            document.getElementById("errorMessage").innerHTML = "Password don't match";
            return false;
        }else {
            return true;
        }
    }

    function closeModal2() {
        document.getElementById('modal2').style.display = 'none';
        document.getElementById('modal2').classList.remove('in');

    }


    function focusUserName(){
        document.getElementById("errorMessage").innerHTML = '';
    }
    function focusEmail(){
        document.getElementById("errorMessage").innerHTML = '';
    }
    function focusPass(){
        document.getElementById("errorMessage").innerHTML = '';
    }
    function focusConfirmPass(){
        document.getElementById("errorMessage").innerHTML = '';
    }
    function focusInputCode(){
        document.getElementById("errorMessage").innerHTML = '';
    }

</script>  
