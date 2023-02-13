<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style type="text/css">
      body {
        font-family: sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
      }
      .containerReg {
      	margin: 0 auto;
        width: 400px;
        padding: 10px;
      }
      .contentReg {
      	text-align: center;
        background-color: #D3D3D3;
        border-radius: 5px;
        padding: 10px;
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
      }
      label
	  {	
	  		display: block;
			width: 150px;
			float: left;
	  }
</style>
</head>
<body>
	<%@ include file="integration/background-video.jsp" %>
	
	<div class="containerReg">
      <div class="contentReg">
      
      <p> NOUVEAU COMPTE </p>
      
      <c:if test="${ !empty errorRegisterForm }">
      	<p style="color:red;">${ errorRegisterForm }</p>
      </c:if>
      
		<form method="post" action="Register">
			<p>
				<label for="login">Pseudo : </label>
				<input type="text" name="login" id="login"/>
			</p>
			<p>
				<label for="password">Mot de passe : </label>
				<input type="password" name="password" id="password"/>
			</p>
			<p>
				<label for="passwordConfirmation">Confirmation mot de passe : </label>
				<input type="password" name="passwordConfirmation" id="passwordConfirmation"/>
			</p>
			<input type="submit" value="Enregistrer" />
		</form>
		
	  </div>
	</div>
</body>
</html>