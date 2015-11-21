<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
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
	
	
	
	<form name="porudzbinaforma" id="porudzbinaforma" method=POST action=porudzbinakontrolor>
	
	
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
            <li><a href="#">Porudzbina</a></li>
            <li><a href="listaporudzbina.jsp">Lista sa porudzbinama</a></li>
            <li><a href="dobavljac.jsp">Dobavljac</a></li>
          </ul>
        </div>
      </div>
</nav>
<!-- Small modal -->

<div class="container-fluid">
      
      <div class="row row-offcanvas row-offcanvas-left">
        
         <div class="col-sm-3 col-md-2 sidebar-offcanvas text-center" id="sidebar" role="navigation">
           <h4>Rad sa porudzbinom</h4>
           <ul class="nav nav-sidebar">
             </ul>
           <ul class="nav nav-sidebar">
            <li> <input type=text class="form-control" placeholder="Unesite broj porudzbine" id="pretraga" name="brojPorudzbinePretraga" value=""></li>
             </ul>
           <ul class="nav nav-sidebar">
              <li><button type=submit class="btn btn-warning btn-danger btn-block" id="nadji" name="action" value="Trazi">Nadji porudzbinu</button></li>
            </ul>
            <ul class="nav nav-sidebar">
             <li><button type=button class="btn btn-warning btn-danger btn-block" data-toggle="modal" data-target=".bs-example-modal-sm" >Nova porudzbina</button></li>
            </ul>
            <ul class="nav nav-sidebar">
             <li><button type=submit class="btn btn-warning btn-danger btn-block" name="action" value="Potvrdi novu porudzbinu">Potvrdi novu porudzbinu</button></li>
            </ul>
             <ul class="nav nav-sidebar">
              <li><button type=submit class="btn btn-warning btn-danger btn-block" name="action" value="Izmeni porudzbinu">Izmeni porudzbinu</button></li>
            </ul>
            <ul class="nav nav-sidebar">
             <li><button type=submit class="btn btn-warning btn-danger btn-block" name="action" value="Obrisi porudzbinu">Obrisi porudzbinu</button></li>
            </ul>
            
             <ul class="nav nav-sidebar">
             </ul>
             <h4>Rad sa stavkama</h4>
              <ul class="nav nav-sidebar">
             </ul>
            <ul class="nav nav-sidebar">
             <li><button type=submit class="btn btn-warning btn-danger btn-block" name="action" value="Dodaj stavku">Dodaj stavku</button></li>
            </ul>
            <ul class="nav nav-sidebar">
             <li><button type=submit class="btn btn-warning btn-danger btn-block" name="action" value="Izmeni stavku">Izmeni stavku</button></li>
            </ul>
             <ul class="nav nav-sidebar">
             <li><button type=submit class="btn btn-warning btn-danger btn-block" name="action" value="Obrisi stavku">Obrisi stavku</button></li>
            </ul>
          
        </div><!--/span-->
        
        <div class="col-sm-9 col-md-10 main">
          
          <!--toggle sidebar button-->
          <p class="visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-left"></i></button>
          </p>

					<!--forma za unos nove porudzbine-->
		
		
			<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  			<div class="modal-dialog modal-sm">
   			<div class="modal-content">
   			
   			<div class="modal-header text-center">
   			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
   			<h4 class="modal-title" id="myModalLabel">Unos nove porudzbine</h4>
     		</div>
     		
     		<div class="modal-body text-center">
     		
     		<form id="dodajporudzbinuforma" name="dodajporudzbinuforma" method="POST" action=porudzbinakontrolor>
     		<h4><span class="label label-primary">Sifra dobavljaca</span></h4>
     		<jsp:include page="/svidobavljaci"/>
       		<br /> 

			<input type="submit" class="btn btn-warning btn-danger btn-block" name="action" value="Dodaj novu porudzbinu">
     	 	</form>
     	 	
     	 	</div>
   			</div>
  			</div>
			</div>
			
			<!--Kraj forme za unos nove porudzbine-->
			
		
          <div class="row placeholders">
            <div class="col-xs-6 placeholder text-center">
              <h4><span class="label label-primary">Broj porudzbine</span></h4>
              <input type=text class="form-control" name="brojPorudzbine" value="<%=session.getAttribute("brojPorudzbine")%>" readonly>
            </div>
            
            <div class="col-xs-6 placeholder text-center">
              <h4><span class="label label-primary">Datum</span></h4>
              <input type=text class="form-control" name="datum" value="<%=session.getAttribute("datum")%>" readonly>
            </div>
            <div class="col-xs-6 col-sm-4 placeholder text-center">
              <h4><span class="label label-primary">Sifra dobavljaca</span></h4>
              <input type=text class="form-control" name="sifraDobavljaca" value="<%=session.getAttribute("sifraDobavljaca")%>">
            </div>
            <div class="col-xs-6 col-sm-4 placeholder text-center">
              <h4><span class="label label-primary">Naziv dobavljaca</span></h4>
              <input type=text class="form-control" name="nazivDobavljaca" value="<%=session.getAttribute("nazivDobavljaca")%>" readonly>
            </div>
            <div class="col-xs-6 col-sm-4 placeholder text-center">
              <h4><span class="label label-primary">Ukupna vrednost</span></h4>
              <input type=text class="form-control" name="ukupnaVrednost" value="<%=session.getAttribute("ukupnaVrednost")%>" readonly>
               <input type="hidden" name="tableindex" id="tableindex" value="${tableindex}"> 
               <input type="hidden" name="message" id="message" value="${message}">  
               <input type="hidden" name="rezultat" id="rezultat" value="${rezultat}">     
            </div>
          </div>
          
          <hr>

          <h2 class="sub-header text-center">Stavke</h2>
          <div class="row placeholders">
            <div class="col-xs-6 col-sm-4 placeholder text-center">
              <h4><span class="label label-warning">Redni broj</span></h4>
              <input type=text class="form-control" placeholder="Redni broj" name="redniBroj" value="${redniBroj}" readonly>
            </div>
            <div class="col-xs-6 col-sm-4 placeholder text-center">
              <h4><span class="label label-warning">Kolicina</span></h4>
              <input type=text class="form-control" placeholder="Unesite zeljenu kolicinu" name="kolicina" value="${kolicina}">
            </div>
            <div class="col-xs-6 col-sm-4 placeholder text-center">
              <h4><span class="label label-warning">Sifra proizvoda</span></h4>
              <input type=text class="form-control" placeholder="Unesite sifru proizvoda" name="sifraProizvoda" value="${sifraProizvoda}"> 
			</div>
          </div>
          <div class="table-responsive">
            <table class="table table-bordered text-center">
            <jsp:include page="/svestavke"/>
            </table>
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
		<script>
			$("tr").click(function() {
			// `this` is the DOM element that was clicked
			var index = $("tr").index(this);
			document.getElementById("tableindex").value = index;
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