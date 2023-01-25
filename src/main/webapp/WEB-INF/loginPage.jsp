<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style type="text/css">
      body {
        font-family: sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
      }
      .container {
      	margin: 0 auto;
        width: 400px;
        padding: 10px;
      }
      .content {
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
	<%@ include file="background-video.jsp" %>
	<div class="container">
      <div class="content">
      
      <p> CONNEXION </p>
      
      <c:if test="${ !empty errorConnectionForm }">
      	<p style="color:red;">${ errorConnectionForm }</p>
      </c:if>
      
		<form method="post" action="Login">
			<p>
				<label for="log">Login : </label>
				<input type="text" name="log" id="log"/>
			</p>
			<p>
				<label for="passwd">Mot de passe : </label>
				<input type="password" name="passwd" id="passwd"/>
			</p>
			<input type="submit" value="Connexion" />
		</form>
		
	  </div>
	</div>
</body>
</html>