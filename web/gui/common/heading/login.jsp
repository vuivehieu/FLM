
<section class="h-100 " style="background-color: #eee;">
     <!--LOGIN SECTION--> 
<div id="modal1" class="modal" role="dialog">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-10">
        <div class="card rounded-3 text-black">
          <div class="row g-0">
            <div class="col-lg-6">
                <div class="card-body p-md-5 mx-md-4" style="position: relative;">
                    <a href="#" id="btn-close" style="position: absolute; top:10px; right:10px;" class="pop-close" data-dismiss="modal"><img style="width: 12px;height: 12px;" src="images/cancel.png" alt="" /></a>
                    <div class="text-center">
                      <h4 class="mt-1 mb-5 pb-1">FPT Login</h4>
                    </div>

                    <form class="s12" action="login" method="post">
                      <p>Please login to your account</p>

                      <div class="form-group mb-4">
                           <label class="form-label" for="form2Example11">Username</label>
                        <input type="text" id="form2Example11" class="form-control" style="font-size: 15px;" name="userName" />
                      </div>

                      <div class="form-group mb-4">
                          <label class="form-label" for="form2Example22">Password</label>
                        <input type="password" id="form2Example22" class="form-control" style="font-size: 15px;" name="password" />
                      </div>

                      <div class="text-center pt-1 mb-5 pb-1">
                          <p id="messageLogin">${error != null ? error : ''}</p>
                        <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" style="font-size: 15px;">Log
                          in</button>
                        <a class="text-muted" href="#!" data-dismiss="modal" data-toggle="modal" data-target="#modal3" onclick="closeModal1()">Forgot password?</a>
                      </div>
                            <p class="mb-2 me-2" style="text-align: center;">Don't have an account?</p>
                      <div class="d-flex align-items-center justify-content-center pb-4">
<!--                        <p class="mb-0 me-2">Don't have an account?</p>-->
                        
                        <a class="" href="#!" data-dismiss="modal" data-toggle="modal" data-target="#modal2" onclick="closeModal1()">Create new</a>
                        
                        <span style="margin: 0px 1rem;"> Or </span> 
                        <a 
                            href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&openid.realm&include_granted_scopes=true&redirect_uri=http://localhost:8080/SWP391-G2/loginGoogle&response_type=code&client_id=85314650649-qt98rm1tth046spr9j93ka3qo1mjcuns.apps.googleusercontent.com&approval_prompt=force">
                            Login Google
                        </a> 
                      </div>
                    </form>
                </div>
            </div>
                
            <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
              <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                <h4 class="mb-4" style="font-size: 15px;">Welcome to FPT Learning Material</h4>
                <p class=" mb-0" style="font-size: 15px;">Don't have an account? Create your account. It's take less then a minutes</p>
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
    let messageLogin = document.getElementById('messageLogin');
    if (messageLogin.innerHTML !== '') {
        document.getElementById('modal1').style.display = 'block';
        document.getElementById('modal1').classList.add('in');
        document.getElementById('btn-close').addEventListener('click', function () {
            document.getElementById('modal1').style.display = 'none';
            document.getElementById('modal1').classList.remove('in');
        });
    }

    function closeModal1(){
            document.getElementById('modal1').style.display = 'none';
            document.getElementById('modal1').classList.remove('in');
            
            document.getElementById('emailForgot').value = '';
            document.getElementById('passwordForgot').value = '';
            document.getElementById('comfirm-passwordForgot').value = '';
            document.getElementById('verifyCodeForgot').value = '';
    }
    
</script>
