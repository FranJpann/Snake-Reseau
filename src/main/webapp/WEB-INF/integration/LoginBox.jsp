	<c:choose>
      		<c:when test="${ !empty sessionScope.currentUser }">
      		
      			<div class="containerLogin">
      				<div class="contentLogin">
      					<p> ${ sessionScope.currentUser.pseudo } &#9660;</p>
      				</div>
      				<div class="dropdown-content">
                    	<p><a href="/Snake-Reseau/User?id=${ sessionScope.currentUser.id }">Voir mon profil</a></p>
                    	<p><a href="/Snake-Reseau/Shop">Shop</a></p>
                    	<p><a href="Deconnexion">Déconnexion</a></p>
                    </div>
      			</div>
      			
      		</c:when>
      		<c:otherwise>
      		
      			<div class="containerLogin">
      				<div class="contentLogin">
		      			<div class="links">
		          			<p><a href="Login">Se connecter</a></p>
		          			<p><a href="Register">S'enregistrer</a></p>
		        		</div>
        		    </div>
    			</div>
    			
      		</c:otherwise>
      	</c:choose>
