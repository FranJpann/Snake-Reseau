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
        width: 170px;
        padding: 10px;
      }
      .containerMain {
      	width: 300px;
      	padding: 10px;
      }
      .content {
        background-color: #D3D3D3;
        border-radius: 5px;
        padding: 10px;
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
      }
      .dropdown-content{
      	display: none;
  		position: absolute;
  		text-align: center;
  		background-color: #f1f1f1;
  		min-width: 150px;
  		box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  		z-index: 1;
      }
      .containerLogin:hover .dropdown-content {display: block;}
      
    </style>
  </head>
  <body>
      	<c:choose>
      		<c:when test="${ !empty sessionScope.currentUser }">
      		
      			<div class="containerLogin">
      				<div class="content" style="text-align: center;">
      					<p> ${ sessionScope.currentUser.pseudo } &#9660;</p>
      				</div>
      				<div class="dropdown-content">
                    	<p><a href="/Snake-Reseau/User?id=${ sessionScope.currentUser.id }">Voir mon profil</a></p>
                    	<p><a href="Deconnexion">Se déconnecter</a></p>
                    </div>
      			</div>
      			
      		</c:when>
      		<c:otherwise>
      		
      			<div class="containerLogin">
      				<div class="content" style="text-align: center;">
		      			<div class="links">
		          			<p><a href="Login">Se connecter</a></p>
		          			<p><a href="Register">S'enregistrer</a></p>
		        		</div>
        		    </div>
    			</div>
    			
      		</c:otherwise>
      	</c:choose>

    <div class="containerMain">
      	<div class="content">
      		<p style="text-decoration:underline; font-weight:bold; text-align: center;"> Derniers inscrits </p>
    		<c:forEach items="${ lastUsers }" var="user">
      			<p><a href="/Snake-Reseau/User?id=${ user.id }" style="font-size: 20px; font-weight: lighter;">ID${ user.id } - ${ user.pseudo } </a></p>
      		</c:forEach>
      	</div>
    </div>
    
    <!--<div class="containerMain">
      	<div class="content">
      		<p style="text-decoration:underline; font-weight:bold;"> Classement </p>
    		<c:forEach items="${ bestUsers }" var="user">
      			<a href="/Snake-Reseau/User?id=${ user.id }" style="font-size: 20px; font-weight: lighter;"> ${ user.id } - ${ user.pseudo } </a>
      		</c:forEach>
      	</div>
    </div>-->
    
  </body>
</html>