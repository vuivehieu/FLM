<section>
    <!-- LOGIN SECTION -->
    <div id="modal1" class="modal fade" role="dialog">
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
<!--                            <p>
                                <input type="checkbox" id="test5" />
                                <label for="test5">Remember me</label>
                            </p>-->
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

</section>
