<style>
    .log-in-pop-right form label {
        left: 10px;
        top: -10px;
    }
</style>

<section>
    <!-- REGISTER SECTION -->
    <div id="modal2" class="modal fade" role="dialog">
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
                <form class="s12" action="register" method="post">
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
                            <input type="submit" onsubmit="return checkData();" value="Register" class="waves-effect waves-light log-in-btn"> </div>
                    </div>
                    <div>
                        <div class="input-field s12"> <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#modal1" onclick="closeModal2()">Are you a already member ? Login</a> </div>
                    </div>
                    <span id="messageRegister" style="display: none">${messageRegister != null ? messageRegister : 'false'}</span>
                   
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
                            if (checkPassword()) {
                                return true;
                            }
                            return false;
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

</section>
