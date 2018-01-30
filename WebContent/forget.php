   <div ng-controller="forgetController">
	<section class="bg-pattern">
        <!-- Container -->
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-4 offset-md-4 mt-5 pt-5">
                    <div class="card">
                        <div class="card-block">
                            <h2 class="box-title text-center">Forget password?</h2>
                            <form method="post" id="user-forget-password-form" class="form" novalidate ng-submit="EMAIL()">
                                <div class="form-group">
                                    <label class="form-label" for="phone">Email Address <span class="text-danger">*</span></label>
                                    <input class="form-control" type="email" id="phone" name="phone" ng-model="forget.email" ng-maxlength="255" required value="" autofocus>
                                </div>
                                <span class="text-danger" ng-show="forget.emailshow">Please enter your email address.</span>
                                <button type="button" ng-disabled="user-forget-form.$invalid" class="btn btn-danger btn-block" >Submit</button>
                            </form>
                            <form method="post" id="otp-verification" class="form" validate ng-show="forget.otpShow" ng-submit="OTP()">
                                <div class="form-group">
                                    <label class="form-label" for="otp">One Time Password <span class="text-danger">*</span></label>
                                    <input class="form-control text-center" type="password" id="otp" ng-model="forget.otp" name="otp" required value="" ng-maxlength="4" autofocus placeholder="xxxx" style="letter-spacing:1.2rem;">
                                </div>
                                <span class="text-danger" ng-show="forget.onetp">Please enter your otp.</span>
                                <button type="button" class="btn btn-danger btn-block">Verify</button>
                           	</form>
                        </div><!-- /.card-block -->
                    </div><!-- /.card -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container -->
	</section>
</div>
<!--
<script>
		jQuery(document).ready(function($) {
			
			$('#otp-verification').submit(function( event ){
				event.preventDefault();
				var element = this;
				var button = {};
				button.id = 'button[name=submit]';
				button.text = 'Verify';
				var data = {};
				data.op = 'user_reset_password';
			
				$( element ).find('.has-danger').removeClass('has-danger');
				$( element ).find('.form-control-feedback').remove();
			
				var otp = $('#otp').val();
				if ((otp == '') || (otp === undefined)) {
					$('#otp').focus();  
					$('#otp').parent().addClass('has-danger');
					$('#otp').parent().append('<div class="form-control-feedback">Please enter your otp.</div>');
					return false;  
				}data.otp = otp;
				
				$.ajax({
					type: 'POST',
					url: base_url + '/api/rest/account/verify',
					headers: {
						'Content-Type'	: 'application/json',
					},
					data: JSON.stringify(data),
					beforeSend: function() {
						//console.log(data);
						$(element).find(button.id).css('width', $(element).find(button.id).outerWidth()).html('<i class="fa fa-spinner fa-spin"></i>').text();
					},
					success: function(result) {
						//console.log(result);										
						if(result.status === 'success') {						
							window.location.href = base_url + '/user/password';
						}
						$(element).find(button.id).html(button.text);
						// Show message
						if(result.messages) {
							$(result.messages).each(function() {
								show_message(this.message, this.type);
							});
						}					
					},
					error: function(request, textStatus, errorThrown) {
						//console.log(request.responseText);
						$(element).find(button.id).html(button.text);
						show_message('ERROR: Unable to verify your account.', 'error');
					}
				});
			  return false;
			});

			$('#user-forget-password-form').submit(function( event ){
				event.preventDefault();
				var element = this;
				var button = {};
				button.id = 'button[name=submit]';
				button.text = 'Submit';
				var data = {};
				data.op = 'user_forget_password';
			
				$( element ).find('.has-danger').removeClass('has-danger');
				$( element ).find('.form-control-feedback').remove();

				var phone = $('#phone').val();
				if ((phone == '') || (phone === undefined)) {
					$('#phone').focus();  
					$('#phone').parent().addClass('has-danger');
					$('#phone').parent().append('<div class="message form-control-feedback">Please enter your email address.</div>');
					return false;  
				}data.email = phone;
				
				$.ajax({
					type: 'POST',
					url: base_url + '/api/rest/users/restorepassword',
					headers: {
						'Content-Type'	: 'application/json',
						'application-type': 'REST',
					},
					data: JSON.stringify(data),
					beforeSend: function() {
						//console.log(data);
						$(element).find(button.id).css('width', $(element).find(button.id).outerWidth()).html('<i class="fa fa-spinner fa-spin"></i>').text();
					},
					success: function(result) {
						console.log(result);										
						if(result.status === 'success') {						
							button.text = 'Verify';
							$('#inputbox').removeAttr('data-content');
							$('.box-title').html('Account Verification');
							$('#phone').val('').focus();
							$('#user-forget-password-form').hide();
							$('#otp-verification').show();
							
						}
						$(element).find(button.id).html(button.text);
						// Show message
						if(result.messages) {
							$(result.messages).each(function() {
								show_message(this.message, this.type);
							});
						}					
					},
					error: function(request, textStatus, errorThrown) {
						//console.log(request.responseText);
						$(element).find(button.id).html(button.text);
						show_message('ERROR: Unable to reset your password.', 'error');
					}
				});
			  return false;
			});
		});
	</script>
	-->