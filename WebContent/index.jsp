<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>FPIS - glavna</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <link href="css/cover.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="site-wrapper">
     	<div class="site-wrapper-inner">
    		<div class="cover-container">
    			<span><h1><p class="text-warning">FPIS - projekat</p></h1></span>
    			<br/>
    			<div class="inner cover">
    			<a href="porudzbina.jsp" class="btn btn-lg btn-danger">Porudzbina</a>
    			</div>
    			<br/>
    			<div class="inner cover">
    			<a href="listaporudzbina.jsp" class="btn btn-lg btn-danger">Lista svih porudzbina</a>
    			</div>
    			<br/>
    			<div class="inner cover">
    			<a href="dobavljac.jsp" class="btn btn-lg btn-danger">Dobavljac</a>
    			</div>
    			<br/>
    			<div class="inner cover">
    			<a href="http://localhost:8080/FPISWSClient/webservis.jsp" class="btn btn-lg btn-danger">Web servis</a>
    			</div>
    		</div>
    	</div>
    </div>
 
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>