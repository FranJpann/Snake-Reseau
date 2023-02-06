<!DOCTYPE html>
<html lang="fr">
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

      
    </style>
  </head>
  <body>
  
      	<c:choose>
      		<c:when test="${ !empty sessionScope.currentUser }">
      		
      			<div class="containerLogin">
      				<div class="content" style="text-align: center;">
      					<p> ${ sessionScope.currentUser.pseudo } &#9660;</p>
      				</div>
      				
      			</div>
      			
      		</c:when>
      		<c:otherwise>
      		

    			
      		</c:otherwise>
      	</c:choose>

    <div class="containerMain">
      	
    </div>
    
  </body>
</html>