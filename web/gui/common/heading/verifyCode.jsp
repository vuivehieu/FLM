                 
<section class="h-100 " style="background-color: #eee;">
      <!-- REGISTER SECTION -->
<div id="modal4" class="modal" role="dialog">
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
                  <h4 class="mt-1 mb-5 pb-1">Verify Code</h4>
                </div>

                <form action="verifyCode" method="post" >
                  <div class="form-outline mb-4">
                    <input type="text" class="form-control validate"
                      placeholder="Full Name"  value="${verifyCode != null ? verifyCode : ''}" name="verifyCode" style="font-size: 15px;"/>
                    <label class="form-label" for="verifyCode">Input Code</label>
                  </div>
                  <div class="text-center pt-1 mb-5 pb-1">
                    <button class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" >Verify Code</button>
                   
                  </div>
                   
                   <span id="messageVerifyCode" style="display: none">${messageVerifyCode != null ? messageVerifyCode : 'false'}</span>
                   <span id="successRegisterMessage" style="color: blue"></span>
                </form>
                <a href="#" id="btn-close-4" class="pop-close" data-dismiss="modal"><img src="images/cancel.png" alt="" />
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
                       
//                        let messageVerifyCode = document.getElementById('messageVerifyCode');
//
//                        console.log("value messageVerifyCode.innerHTML", messageVerifyCode.innerHTML.value );
//                        console.log("messageVerifyCode.innerHTML", messageVerifyCode.innerHTML);
//                        console.log("messageVerifyCode.innerHTML === 'OK'", messageVerifyCode.innerHTML === 'OK');
//                        console.log("messageVerifyCode.value === 'OK'", messageVerifyCode.value === 'OK');
//                        if (messageVerifyCode.innerHTML === 'OK') {
//                            document.getElementById('modal4').style.display = 'block';
//                            document.getElementById('modal4').classList.add('in');
//                            
//                            document.getElementById("successRegisterMessage").innerHTML = "Dang ky thanh cong";
//                            document.getElementById('btn-close-4').addEventListener('click', function () {
//                                document.getElementById('modal4').style.display = 'none';
//                                document.getElementById('modal4').classList.remove('in');
//                            });
//                            
//                        }
//
//                        function closeModal4() {
//                            document.getElementById('modal4').style.display = 'none';
//                            document.getElementById('modal4').classList.remove('in');
//
//                        }

</script>
