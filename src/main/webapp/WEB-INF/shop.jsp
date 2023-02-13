<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Boutique </title>
<style type="text/css">

      body {
        font-family: sans-serif;
        background-color: #28634f;
        margin: 0;
        padding: 0;
      }
      
      <%@include file="/WEB-INF/css/main.css"%>
      
</style>
</head>
		<%@ include file="integration/LoginBox.jsp" %>

	    <p style="color:white; position: absolute"> CREDIT DISPONIBLE : ${ credit } </p>
	    
	    <div>
	    	<h1 style="text-align: center; color: white; margin-bottom: 40px;"> BOUTIQUE </h1>
	    	<div style="display: flex; align-items: center;">
	    		<c:forEach items="${ skinsBuyable }" var="map">
	    			<div style="border: solid; margin: 20px; background: #D3D3D3">
	    				<img src="media/skins/${ map.key }.png" style="width: 128px; height: 128px; margin: 10px;">
	    				<p style="text-align: center;"> ${ map.value } Cr.</p>
	    				
	    				<form action="" method="post" style="text-align: center;">
						    <button name="buttonBuySkin" value="${ map.key }">ACHETER</button>
						</form>
	    			</div>
	    		</c:forEach>
	    	</div>
	    	<div style="display: flex; align-items: center;">
	    		<c:forEach items="${ skinsUser }" var="list">
	    			<div style="border: solid; margin: 20px; background: #D3D3D3">
	    				<img src="media/skins/${ list }.png" style="width: 128px; height: 128px; margin: 10px;">
	    				
	    				<p style="text-align: center; white:color"> DÉJÀ ACHETÉ </p>
	    			</div>
	    		</c:forEach>
	    	</div>
	    </div>
	
</body>
</html>