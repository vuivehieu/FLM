
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
                <a href="#" id="btn-close" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" /></a>
                <div class="text-center">
                  <h4 class="mt-1 mb-5 pb-1">Forgot password</h4>
                </div>
                    
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate" style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;"
                      placeholder="Email" id="emailForgot" onFocus="focusEmailForgot()"/>
                    <label class="form-label" for="emailForgot">Email</label>
                  </div>

                  <div class="form-outline mb-4">
                    <input type="password" onFocus="focusPassForgot()" class="form-control validate" 
                           style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled  id="passwordForgot"  />
                    <label class="form-label" for="passwordForgot">New Password</label>
                  </div>
                    
                  <div class="form-outline mb-4">
                     <input type="password" onFocus="focusConfirmPassForgot()" class="form-control validate" 
                            style="font-size: 15px; background-color: unset !important; border-bottom: unset !important;" disabled id="comfirm-passwordForgot"  />
                      <label class="form-label" for="comfirm-passwordForgot">Confirm password</label>
                  </div>
                       
                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                         <input type="text" class="form-control validate" onFocus="focusInputCodeForgot()"
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
                        $('#emailForgot').prop('disabled', true);
                        
                        $('#passwordForgot').prop('disabled', false);
                        $('#comfirm-passwordForgot').prop('disabled', false);
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
            document.getElementById("errorMessageForgot").innerHTML = "Email must be in the form @gmail.com and @fpt.edu.vn";
        }
    }
    
    function saveNewPassword(){
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
            }else {
                $.ajax({
                    url: '/SWP391-G2/updatePassForgot',
                    type: "POST",
                    contentType: "application/json", // NOT dataType!
                    data: JSON.stringify(data),
                    success: function(response) {
                        // handle success

                        if(response.messageChangPassSuccess != null){
                            document.getElementById("messageChangPassSuccess").innerHTML = response.messageChangPassSuccess;
                            document.getElementById("errorMessageForgot").innerHTML = "";
                           setTimeout(function() {
                                let btnClose = document.getElementById("changeLoginPage");
                                btnClose.click();
                            }, 3000);


                        }else {
                            document.getElementById("errorMessageForgot").innerHTML = response.error;
                        }

                    },
                    error: function (xhr, status, error) {
                         // handle error
                        console.log("error: ", error);
                    }
                });
            }

            
        } else {
            document.getElementById("errorMessageForgot").innerHTML = "Password don't match";
        }
    }
    
    function checkConfirmPassForgot() {
        var password = document.getElementById('passwordForgot');
        var confirm_password = document.getElementById('comfirm-passwordForgot');

        if (password.value !== confirm_password.value) {
            
            return false;
        }else {
            return true;
        }
    }

    function closeModal3() {
        document.getElementById('modal3').style.display = 'none';
        document.getElementById('modal3').classList.remove('in');

    }
    
    function focusEmailForgot(){
        document.getElementById("errorMessageForgot").innerHTML = '';
    }
    function focusPassForgot(){
        document.getElementById("errorMessageForgot").innerHTML = '';
    }
    function focusConfirmPassForgot(){
        document.getElementById("errorMessageForgot").innerHTML = '';
    }
    function focusInputCodeForgot(){
        document.getElementById("errorMessageForgot").innerHTML = '';
    }
</script>

