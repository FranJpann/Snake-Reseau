<!DOCTYPE html>
<html lang="fr">
 <head>
    <title>Snake</title>
    <link href="https://fonts.cdnfonts.com/css/games" rel="stylesheet">
    <link href="https://fonts.cdnfonts.com/css/aureola" rel="stylesheet">
    
    <style type="text/css">
      body {
        font-family: Games;
        background-color: #28634f;
        padding: 5px;
      }
      
      div{
      	padding: 5px;
      }
      .containerProfil {
        font-family: Aureola;
        width: fit-content;
      	display: flex;
      	align-items: center;
      	border: 1px solid #d5ddc6;
      	background-color: #D3D3D3;
      }
      .contentHistory{
      	margin-top: 10px;
      	background-color: #207d5e;
      	border: 2px solid #d5ddc6;
      	width: 300px;
      }
      .history{
      	color: white;
      }
      
      <%@include file="/WEB-INF/css/main.css"%>
    </style>
    
  </head>
  <body>
  
  	<%@ include file="integration/LoginBox.jsp" %>
			
	  <div class="containerProfil">
	  		<img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png" style="width: 130px; margin: 2px;">
	  		<div style="font-size: 20px; margin-left: 10px;">
	  			<p style="color:black;"> ${ user.pseudo } </p>
	  			<p style="color:black;"> Games: ${ numberOfGames } </p>
	  			<p style="color:black;"> Best: ${ userBestScore }pts</p>
	  		</div>
	  </div>
	  
      <div class="history">
      	<p style="font-size:45px; margin:20px;"> HISTORIQUE </p>
      	
      	<c:forEach items="${ history }" var="gameHistory">
      		<div class="contentHistory">
      			<c:choose>
      				<c:when test="${ gameHistory.winner.pseudo == user.pseudo }">
      					<p style="font-size: 40px; margin:5px;"> ${ gameHistory.bestScore }.pts </p>
      				</c:when>
      				<c:otherwise>
      					<p style="font-size: 40px; margin:5px;"> ${ gameHistory.looserScore }.pts </p>
      				</c:otherwise>
      			</c:choose>
      			<div style="text-align:center;">
      				<c:choose>
      					<c:when test="${ gameHistory.winner.pseudo == user.pseudo }">
      						<img src="media/web-media/victory.png">
      					</c:when>
      					<c:otherwise>
      						<img src="media/web-media/defeat.png">
      					</c:otherwise>
      				</c:choose>
      				<div>
      						<c:choose>
      							<c:when test="${ gameHistory.winner.pseudo != user.pseudo }">
      								<p style="font-size: 22px; margin:5px;">
      									VS <a href="/Snake-Reseau/User?id=${ gameHistory.winner.id }">${ gameHistory.winner.pseudo } </a>
   									</p>
								</c:when>
								<c:otherwise>
      								<p style="font-size: 22px; margin:5px;"> 
      									VS <a href="/Snake-Reseau/User?id=${ gameHistory.looser.id }">${ gameHistory.looser.pseudo } </a>
      								</p>
      							</c:otherwise>
							</c:choose>
      				</div>
      			</div>
      		</div>
      	</c:forEach>
      </div>

  </body>
</html>