<!DOCTYPE html>
<html lang="fr">
 <head>
 	<meta charset="UTF-8">
    <title>Snake</title>
    <link href="https://fonts.cdnfonts.com/css/games" rel="stylesheet">
    
    <style type="text/css">
      body {
        font-family: sans-serif;
        background-color: #28634f;
        margin: 0;
        padding: 0;
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
      <%@include file="/WEB-INF/css/main.css"%>
    </style>
  </head>
  <body>
  
  	<%@ include file="integration/LoginBox.jsp" %>

    <div class="containerMain">
      	<div class="content">
      		<p style="text-decoration:underline; font-weight:bold; text-align: center;"> Derniers inscrits </p>
    		<c:forEach items="${ lastUsers }" var="user">
      			<p><a href="/Snake-Reseau/User?id=${ user.id }" style="font-size: 20px; font-weight: lighter;">ID${ user.id } - ${ user.pseudo } </a></p>
      		</c:forEach>
      	</div>
    </div>
   	
   	<div class="containerMain">
      	<div class="content" style="background-color: #D3D3D3;">
      		<p style="text-decoration:underline; font-weight:bold; text-align: center;"> CLASSEMENT </p>
    		<c:forEach items="${ bestUsers }" var="user" varStatus="count">
    			<c:choose>
	    			<c:when test="${ count.count == 1 }">
	      				<p style="font-size: 25px; color: rgb(205, 176, 16); font-family: Games;">
	      					1. <a href="/Snake-Reseau/User?id=${ user.id }"> ${ user.pseudo } </a>
	      				</p>
	      			</c:when>
	      			<c:when test="${ count.count == 2 }">
	      				<p style="font-size: 25px; color: rgb(129, 124, 124); font-family: Games;">
	      					2. <a href="/Snake-Reseau/User?id=${ user.id }"> ${ user.pseudo } </a>
	      				</p>
	      			</c:when>
	      			<c:when test="${ count.count == 3 }">
	      				<p style="font-size: 25px; color: brown; font-family: Games;">
	      					3. <a href="/Snake-Reseau/User?id=${ user.id }">${ user.pseudo } </a>
	      				</p>
	      			</c:when>
	      			<c:otherwise>
	      				<p style="font-size: 25px; color: black; font-family: Games;">
	      					... <a href="/Snake-Reseau/User?id=${ user.id }">${ user.pseudo } </a>
	      				</p>
	      			</c:otherwise>
	      		</c:choose>
      		</c:forEach>
      	</div>
    </div>
    
  </body>
</html>