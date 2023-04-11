
<section>
    <!-- FORGOT SECTION -->
    <div id="modal3" class="modal fade" role="dialog">
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
</section>
<script>
    let messageForgot = document.getElementById('messageForgot');
    if (messageForgot.innerHTML !== '') {
        document.getElementById('modal3').style.display = 'block';
        document.getElementById('modal3').classList.add('in');
        document.getElementById('btn-close').addEventListener('click', function () {
            document.getElementById('modal3').style.display = 'none';
            document.getElementById('modal3').classList.remove('in');
        });
    }

    function closeModal1() {
        document.getElementById('modal3').style.display = 'none';
        document.getElementById('modal3').classList.remove('in');

    }
</script>

