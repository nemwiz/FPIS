<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<title>Fizicko projektovanje informacionih sistema</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/pnotify.custom.min.css" media="all" rel="stylesheet" type="text/css" />
		<link href="css/styles.css" rel="stylesheet">
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
</head>
<body>

<form name="dobavljacforma" id="dobavljacforma" method=POST action=dobavljackontrolor>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.jsp">FPIS</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="porudzbina.jsp">Porudzbina</a></li>
            <li><a href="listaporudzbina.jsp">Lista sa porudzbinama</a></li>
            <li><a href="#">Dobavljac</a></li>
          </ul>
        </div>
      </div>
</nav>
<!-- Small modal -->

<div class="container-fluid">
      
      <div class="row row-offcanvas row-offcanvas-left">
        
         <div class="col-sm-3 col-md-2 sidebar-offcanvas text-center" id="sidebar" role="navigation">
           <h4>Rad sa dobavljacem</h4>
           <ul class="nav nav-sidebar">
             </ul>
           <ul class="nav nav-sidebar">
            <li> <input type=text class="form-control" placeholder="Unesite sifru dobavljaca" id="pretraga" name="sifraDobavljacaPretraga" value=""></li>
             </ul>
           <ul class="nav nav-sidebar">
              <li><button type=submit class="btn btn-warning btn-danger btn-block" id="nadji" name="action" value="Trazi">Nadji dobavljaca</button></li>
            </ul>
            <ul class="nav nav-sidebar">
             <li><button type=button class="btn btn-warning btn-danger btn-block" data-toggle="modal" data-target=".bs-example-modal-sm" >Nov dobavljac</button></li>
            </ul>
             <ul class="nav nav-sidebar">
              <li><button type=submit class="btn btn-warning btn-danger btn-block" name="action" value="Izmeni dobavljaca">Izmeni dobavljaca</button></li>
            </ul>
            <ul class="nav nav-sidebar">
             <li><button type=submit class="btn btn-warning btn-danger btn-block" name="action" value="Obrisi dobavljaca">Obrisi dobavljaca</button></li>
            </ul>
            
          
        </div><!--/span-->
        
        <div class="col-sm-9 col-md-10 main">
          
          <!--toggle sidebar button-->
          <p class="visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-left"></i></button>
          </p>

					<!--forma za unos novog dobavljaca-->
		
		
			<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  			<div class="modal-dialog modal-sm">
   			<div class="modal-content">
   			
   			<div class="modal-header text-center">
   			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
   			<h4 class="modal-title" id="myModalLabel">Unos novog dobavljaca</h4>
     		</div>
     		
     		<div class="modal-body text-center">
     		
     		<form id="dodajdobavljacaforma" name="dodajdobavljacaforma" method="POST" action=dobavljackontrolor>
     		<h4><span class="label label-primary">Naziv dobavljaca</span></h4>
     		<input type=text class="form-control" name="nazivDobavljacaUnos" value="">
       		<br /> 

			<input type="submit" class="btn btn-warning btn-danger btn-block" name="action" value="Dodaj novog dobavljaca">
     	 	</form>
     	 	
     	 	</div>
   			</div>
  			</div>
			</div>
			
			<!--Kraj forme za unos nove porudzbine-->
			
		
          <div class="row placeholders">
            <div class="col-xs-6 col-sm-4 placeholder text-center">
              <h4><span class="label label-primary">Sifra dobavljaca</span></h4>
              <input type=text class="form-control" name="sifraDobavljaca" value="${sifraDobavljaca}" readonly>
            </div>
            <div class="col-xs-6 col-sm-4 placeholder text-center">
              <h4><span class="label label-primary">Naziv dobavljaca</span></h4>
              <input type=text class="form-control" name="nazivDobavljaca" value="${nazivDobavljaca}">
              <input type="hidden" name="message" id="message" value="${message}">  
               <input type="hidden" name="rezultat" id="rezultat" value="${rezultat}"> 
            </div>
          </div>
          
          
      </div><!--/row-->
	</div>
</div><!--/.container-->

</form>

<!-- script references -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/pnotify.custom.min.js"></script>
		<script src="js/scripts.js"></script>
		
		<script>
			$("#nadji").click(function() {
			var brojPorudzbine = 0;
			if(document.getElementById("pretraga").value==""){
			$("#pretraga").val("0");
			}
		});
		</script>
		<script type="text/javascript">
		$(function() {
			var message = document.getElementById("message").value;
			var rezultat = document.getElementById("rezultat").value;
			var stack_bar_top = {"dir1": "down", "dir2": "right", "push": "top", "spacing1": 0, "spacing2": 0, "firstpos1": 52, "firstpos2": 0};
			 if(rezultat==0){
			 
			 } else if(rezultat==1){
			 
			  $(function(){
       
			new PNotify({
							type : 'success',
							text : message,
							styling: "bootstrap3",
							addclass: "stack_bar_top",
							stack: stack_bar_top,
							cornerclass: "",
							hide: true,
							delay: 4000,
							remove: true,
        					width: "400px",
							animation : {
								effect_in : 'show',
								effect_out : 'slide'
							}
						});
					});
				} else if(rezultat==2){
				
				 $(function(){
       
			new PNotify({
							title : 'Greska',
							type : 'error',
							text : message,
							styling: "bootstrap3",
							addclass: "stack_bar_top",
							stack: stack_bar_top,
							cornerclass: "",
							hide: true,
							delay: 4000,
							remove: true,
        					width: "400px",
							animation : {
								effect_in : 'show',
								effect_out : 'slide'
							}
						});
					});
				} 
			});
		</script>

</body>
</html>