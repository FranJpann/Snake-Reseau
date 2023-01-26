<html>
  <head>
    <title>Snake</title>
    <style type="text/css">
      body {
        font-family: sans-serif;
        background-color: #28634f;
        margin: 0;
        padding: 0;
      }
      .containerLogin {
      	float: right;
        width: 300px;
        padding: 10px;
      }
      .containerMain {
      	margin: 0 auto;
      	width: 400px;
      	padding: 10px;
      }
      .content {
      	text-align: center;
      	font-size: 16px;
        background-color: #D3D3D3;
        border-radius: 5px;
        padding: 10px;
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
      }
    </style>
  </head>
  <body>
  
    <div class="containerLogin">
      <div class="content">
      	<c:choose>
      		<c:when test="${ !empty sessionScope.currentUser }">
      			<p>${ sessionScope.currentUser.pseudo } !</p>
      			<p>Connecté !</p>
      		</c:when>
      		<c:otherwise>
      			<div class="links">
          			<p><a href="Login">Se connecter</a></p>
          			<p><a href="Register">S'enregistrer</a></p>
        		</div>
      		</c:otherwise>
      	</c:choose>
      </div>
    </div>
    
    <c:forEach items="${ lastUsers }" var="user">
    	<div class="containerMain">
      		<div class="content">
      			<p style="font-size: 25px;">${ user.id } - ${ user.pseudo }</p>
      			<a href="/Snake-Reseau/User?id=${ user.id }">PROFIL</a>
      		</div>
    	</div>
    </c:forEach>
    
  </body>
</html>