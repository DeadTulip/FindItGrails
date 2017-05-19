<g:applyLayout name="main">
<g:set var='securityConfig' value='${applicationContext.springSecurityService.securityConfig}'/>

<div class="container">
	<s2ui:form type='login' focus='username' class="form-signin">
		<h2 class="form-signin-heading">Sign in</h2>
		<input type="text" name="${securityConfig.apf.usernameParameter}"
			   placeholder="Username" id="username" class='formLogin form-control' />
		<input type="password" name="${securityConfig.apf.passwordParameter}"
			   placeholder="Password" id="password" class='formLogin form-control' />

		<div class="checkbox">
			<label>
				<input type="checkbox" value="remember-me"> Remember me
			</label>
		</div>
		<button class="btn btn-default" name="btnRegister" type="submit">Register</button>
		<button class="btn btn-primary" name="btnSignIn" type="submit">Sign in</button>
	</s2ui:form>

</div>

</g:applyLayout>
