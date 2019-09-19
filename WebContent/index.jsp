<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>PPO</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <style type="text/css">
	   body {
		  padding-top: 1rem;
		}
	</style>
  </head>

  <body>

  <div class="container">
    <div class="card">
		  <div class="card-header">PPO - Procedimento Padr�o Obrigat�rio</div>
		  <div class="card-body">
		     <form>
	      		  <input type="hidden" name="idTipoCheck"  id="idTipoCheck"/>
				  <input type="hidden" name="codigo_te" value="" />
				  <input type="hidden" name="codigo_us" value="" />
				 <div id="result" class="" role="alert"></div>
				 
				  <div class="form-row align-items-center">
				    <div class="col-sm-4 my-1">
				      <label class="sr-only" for="tituloEleitor">Tecnico</label>
				      <div class="input-group">
				        <div class="input-group-prepend">
				          <div class="input-group-text">Titulo</div>
				        </div>
				        <input type="text" class="form-control" id="tituloEleitor" name="tituloEleitor" placeholder="titulo eleitor">
				      </div>
				    </div>
				    
				    <div class="col-auto my-1">
				         <button id="consreg" type="button" class="btn btn-primary">Consultar meus registros</button>
				    </div>
				  </div>
				</form>
				<br>
				<div class="row">
				    <div class="text-center col">
				           <a href="#" class="btn btn-outline-danger" id="checkincluir1" data-record-id="1" data-record-descricao="Cheguei no cart�rio">
				             <span class="glyphicon glyphicon-home" aria-hidden="true"></span><br>1-Cheguei no Cart�rio
				           </a>
				    </div>
				    <div class="text-center col">
				       		<a href="#" class="btn btn-outline-warning" id="checkincluir2" data-record-id="2" data-record-descricao="Cheguei no ponto">
				               <span class="glyphicon glyphicon-star" aria-hidden="true"></span><br>2-Cheguei no Ponto
				            </a>
				    </div>
				    <div class="text-center col">
				      		<a href="#" class="btn btn-outline-success" id="checkincluir3" data-record-id="3" data-record-descricao="Oficializar sistema">
				                <span class="glyphicon glyphicon-time" aria-hidden="true"></span><br>3-Oficializei o Sistema
				            </a> 
				    </div>
				    <div class="text-center col">
				     		<a href="#" class="btn btn-outline-primary" id="checkincluir4" data-record-id="4" data-record-descricao="Encerramento">
				               <span class="glyphicon glyphicon-ok" aria-hidden="true"></span><br>4-Encerramento
				           </a>
				    </div>
				</div>
		  </div>
		   <div class="card-footer text-muted">
		    	<table class="table table-striped table-sm" id="constab" width="100%" style="display:none">
		    	</table>
		  </div>
	</div>
 	 
	<div id="stepprogress"></div>
	
	<br><br><br>
	<div class="row">
		<footer class="footer">
	        <p>&copy; STI/CDES/SAWEB 2019  <i class="glyphicon glyphicon-user" aria-hidden="true"></i><a href="${pageContext.request.contextPath}/login/frmLogin">Login</a>&nbsp;&nbsp; 
	          <i class="glyphicon glyphicon-tags" aria-hidden="true"></i>
	          <a href="http://intranet.tre-am.jus.br:8080/Mura/">Mura</a>&nbsp;&nbsp;
	          <i class="glyphicon glyphicon-info-sign" aria-hidden="true"></i>
	          <a href="http://intranet.tre-am.jus.br:8080/acompelei/">AcompElei</a>&nbsp;&nbsp;
	          <i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>
	          <!--  <a href="homologaPonto/frmSetupHomologaPonto">Homologar Ponto Transmiss�o</a>  -->
	        </p>
	      </footer>
	</div>
  </div>

  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>  
  <script>
	$(document).ready(function() {
					
		$( "[id*='checkincluir']" ).click(function(event) {
		    var data = $(event.delegateTarget).data();
			var id = data.recordId;  
			var descricao = data.recordDescricao;
			var titulo =  $('#tituloEleitor').val();
			$('#result').html("");
			$("#result").hide();
			$('#stepprogress').html("");
			$("#stepprogress").hide();
				if (titulo == ""){
					$('#result').attr("class","alert alert-danger");
					$('#result').html("Informe por favor o t�tulo de eleitor");		   
				} else	{
				    if (confirm("Confirma check Point: " + descricao + "?")){
				    	   var url = 'ppo/adicionar?tituloEleitor=' + titulo +"&ppo.ppoTipo.id="+id;
							$.getJSON(url,
				       	     function(jsonResponse) {
				       		       var ret = jsonResponse.ret;
				       		       var msg = jsonResponse.mensagem;
				       		       var vClass;
				       		       
				       		    switch (ret) {
				       		    case 1:
				       		    	vClass = "alert alert-success";
				       		        break; 
				       		    case 2:
				       		    	vClass = "alert alert-danger";
				       		        break; 
				       			case 5:
				       				vClass = "alert alert-warning";
				    		        break;
				       			case 9:
				       				vClass = "alert alert-info";
				    		        break;
				       		    default: 
				       		    	vClass = "alert alert-danger";
				       		 }       		              				        		
				       		     $('#result').attr("class",vClass);
				       		     $('#result').html(descricao + " - " + msg);
				       		   	 $("#result").show(); 
				   		     }).fail(function() {
				   		    	 $('#result').attr("class","alert alert-danger");
				       		     $('#result').html("Servidor n�o encontrado");
				       		   	 $("#result").show(); 
				   		     })
		          }
			  }
		   });
		   
		$("#consreg").click(function(event) {
			$("#consreg").attr("disabled", true);
			$("#stepprogress").hide();
		    var data = $(event.delegateTarget).data();
			var id = data.recordId;  
			var descricao = data.recordDescricao;
			var titulo =  $('#tituloEleitor').val();
			$('#result').html("");
				if (titulo == ""){
					$('#result').attr("class","alert alert-danger");
					$('#result').html("Informe por favor o t�tulo de eleitor");
					$("#consreg").attr("disabled", false);
				} else {
				$("#constab").html("carregando...")	
				$.getJSON('ppo/listarJsonByTitulo?tituloEleitor=' + titulo,
		       	     function(jsonResponse) {       		       
		       		     var tr;
		       		     var stepprogress = '<ul class="progressbar">';
		       		     
		       			 $("#constab").html("<thead><th> Data </th><th> Descri��o </th><th> C�digo </th></thead>");
			       		    	for (var i = 0; i < jsonResponse.length; i++) {
			       		    		var dtf = new Date(jsonResponse[i].dataCad);
				       	            tr = $('<tr/>');
				       	            stepprogress +='<li class="active">'+jsonResponse[i].ppoTipo.sigla+'</li>';
				       	            tr.append("<td>" + dtf.toLocaleString('pt-BR') + "</td>");
				       	            tr.append("<td>" + jsonResponse[i].ppoTipo.descricao + "</td>");	
				       	         	tr.append("<td>" + jsonResponse[i].codigo + "</td>");	
				       	            $("#constab").append(tr);	 	       	            
				       	        }
			       		    	for (var j = 1; j <= (4-i); j++) {
			       		    		stepprogress +='<li></li>';	
			       		    	} 	
		       			if(jsonResponse.length){      
		       				 $("#result").hide();
		       				 $("#constab").show();
		       				 stepprogress +='</ul>';
		       				$("#stepprogress").html(stepprogress);
		       				$("#stepprogress").show();
		       		       }else{
			       		    	$("#constab").hide();
			       		    	$("#stepprogress").hide();
			       		    	$('#result').attr("class","alert alert-info");
			       				$('#result').html("N�o existe nenhum registro.");
			       				$("#result").show();  
		       		       }
		   		   }).done(function() {
		   			  $('#consreg').attr("disabled", false);
		   		   })
			 }
		   });
	});
	
  </script>
     
  </body>
</html>
