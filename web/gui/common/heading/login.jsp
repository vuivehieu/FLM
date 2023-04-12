<!--<section>
     LOGIN SECTION 
    <div id="modal1" class="modal" role="dialog">
        <div class="log-in-pop">
            <div class="log-in-pop-left">
                <h1>Welcome to FPT Learning Material</h1>
                <p>Don't have an account? Create your account. It's take less then a minutes</p>
                <h4>Login with social media</h4>
                <ul>
                    <li>
                        <a 
                            href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&openid.realm&include_granted_scopes=true&redirect_uri=http://localhost:9999/SWP391-G2/loginGoogle&response_type=code&client_id=42744789831-8329f665c93uc2p4eo6kept7fihshgq8.apps.googleusercontent.com&approval_prompt=force">
                            <i class="fa fa-google"></i> Google+</a> 
                    </li>
                </ul>
            </div>
            <div class="log-in-pop-right">
                <a href="#" id="btn-close" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" />
                </a>
                <h4>Login</h4>
                <p>Don't have an account? Create your account. It's take less then a minutes</p>
                <form class="s12" action="login" method="post">
                    <div>
                        <div class="input-field s12">
                            <input type="text" data-ng-model="name" class="validate" name="userName">
                            <label>User name</label>
                        </div>
                    </div>
                    <div>
                        <div class="input-field s12">
                            <input type="password" class="validate" name="password">
                            <label>Password</label>
                        </div>
                    </div>
                    <div>
                        <div class="s12 log-ch-bx">
                            <p>
                                <input type="checkbox" id="test5" />
                                <label for="test5">Remember me</label>
                            </p>
                        </div>
                    </div>
                    <div>
                        <div class="input-field s4">
                            <p id="messageLogin">${error != null ? error : ''}</p>
                            <input type="submit" value="Login" class="waves-effect waves-light log-in-btn"> </div>
                    </div>
                    <div>
                        <div class="input-field s12"> <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal3">Forgot password</a> | <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal2" onclick="closeModal1()">Create a new account</a> </div>
                    </div>
                </form>
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
                            
                        }
                </script>
            </div>
        </div>
    </div>

</section>-->
                            
<section class="h-100 " style="background-color: #eee;">
     <!--LOGIN SECTION--> 
<div id="modal1" class="modal" role="dialog">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-10">
        <div class="card rounded-3 text-black">
          <div class="row g-0">
            <div class="col-lg-6">
                <div class="card-body p-md-5 mx-md-4">
                
                    <div class="text-center">
<!--                      <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp" 
                        style="width: 185px;" alt="logo">-->
                      <h4 class="mt-1 mb-5 pb-1">FPT Login</h4>
                    </div>

                    <form class="s12" action="login" method="post">
                      <p>Please login to your account</p>

                      <div class="form-outline mb-4">
                        <input type="text" id="form2Example11" class="form-control" style="font-size: 15px;"
                          placeholder="User Name" name="userName" />
                        <label class="form-label" for="form2Example11">Username</label>
                      </div>

                      <div class="form-outline mb-4">
                        <input type="password" id="form2Example22" class="form-control" style="font-size: 15px;" name="password" />
                        <label class="form-label" for="form2Example22">Password</label>
                      </div>

                      <div class="text-center pt-1 mb-5 pb-1">
                          <p id="messageLogin">${error != null ? error : ''}</p>
                        <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit">Log
                          in</button>
                        <a class="text-muted" href="#!" data-dismiss="modal" data-toggle="modal" data-target="#modal3">Forgot password?</a>
                      </div>

                      <div class="d-flex align-items-center justify-content-center pb-4">
                        <p class="mb-0 me-2">Don't have an account?</p>
                        <button type="button" style="font-size: 15px;" class="btn btn-outline-danger" data-dismiss="modal" data-toggle="modal" data-target="#modal2" onclick="closeModal1()">Create new</button>
                      </div>

                    </form>
                
                </div>
                <a href="#" id="btn-close" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" />
                </a>
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

        }
</script>
