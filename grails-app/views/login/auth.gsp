<g:set var='securityConfig' value='${applicationContext.springSecurityService.securityConfig}'/>
<html>
	<head>
		<s2ui:title messageCode='spring.security.ui.login.title'/>
		<asset:stylesheet src='spring-security-ui-auth.css'/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	</head>
	<body>
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
				<button class="btn btn-default" href="submit">Register</button>
				<button class="btn btn-primary" type="submit">Sign in</button>
			</s2ui:form>

		</div>

	</body>
</html>
