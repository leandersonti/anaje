<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/harpia/images/favicon.ico">

    <title>PPO</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
   
  </head>

  <body>

  <div class="container">
    <div class="card">
		  <div class="card-header">PPO - Procedimento Padrão Obrigatório</div>
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
				           <a href="#" class="btn btn-outline-danger" id="checkincluir1" data-record-id="1" data-record-descricao="Cheguei no cartório">
				             <span class="glyphicon glyphicon-home" aria-hidden="true"></span><br>Cheguei no Cartório
				           </a>
				    </div>
				    <div class="text-center col">
				       		<a href="#" class="btn btn-outline-warning" id="checkincluir2" data-record-id="2" data-record-descricao="Cheguei no ponto">
				               <span class="glyphicon glyphicon-star" aria-hidden="true"></span><br>Cheguei no Ponto
				            </a>
				    </div>
				    <div class="text-center col">
				      		<a href="#" class="btn btn-outline-success" id="checkincluir3" data-record-id="3" data-record-descricao="Oficializar sistema">
				                <span class="glyphicon glyphicon-time" aria-hidden="true"></span><br>Oficializei o Sistema
				            </a> 
				    </div>
				    <div class="text-center col">
				     		<a href="#" class="btn btn-outline-primary" id="checkincluir4" data-record-id="4" data-record-descricao="Encerramento">
				               <span class="glyphicon glyphicon-ok" aria-hidden="true"></span><br>Encerramento
				           </a>
				    </div>
				</div>
				  
  	    <!--     <a href="#" class="btn btn-primary">Consultar</a>  -->
		  </div>
		  <div class="card-footer text-muted">
		    	<table class="table table-striped table-sm" id="constab" width="100%" style="display:none">
		    	
		    	
		    	</table>
		  </div>
		</div>

      
      <br/>
      <footer class="footer">
        <p>&copy; STI/CDES/SAWEB 2019  <i class="glyphicon glyphicon-user" aria-hidden="true"></i><a href="${pageContext.request.contextPath}/login/frmLogin">Login</a>&nbsp;&nbsp; 
          <i class="glyphicon glyphicon-tags" aria-hidden="true"></i>
          <a href="http://intranet.tre-am.jus.br:8080/Mura/">Mura</a>&nbsp;&nbsp;
          <i class="glyphicon glyphicon-info-sign" aria-hidden="true"></i>
          <a href="http://intranet.tre-am.jus.br:8080/acompelei/">AcompElei</a>&nbsp;&nbsp;
          <i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>
          <!--  <a href="homologaPonto/frmSetupHomologaPonto">Homologar Ponto Transmissão</a>  -->
        </p>
      </footer>

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
				if (titulo == ""){
					$('#result').attr("class","alert alert-danger");
					$('#result').html("Informe por favor o título de eleitor");		   
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
				       				vClass = "alert alert-info";
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
				   		     });
		          }
			  }
		   });
		   
		$( "#consreg" ).click(function(event) {
		    var data = $(event.delegateTarget).data();
			var id = data.recordId;  
			var descricao = data.recordDescricao;
			var titulo =  $('#tituloEleitor').val();
			$('#result').html("");
				if (titulo == ""){
					$('#result').attr("class","alert alert-danger");
					$('#result').html("Informe por favor o título de eleitor");		   
					
				} else {
				$("#constab").html("carregando...")	
				$.getJSON('ppo/listarJsonByTitulo?tituloEleitor=' + titulo,
		       	     function(jsonResponse) {       		       
		       		     var tr;    		   
		       			 $("#constab").html("<thead><th> Data </th><th> Descrição </th><th> Código </th></thead>");
			       		    	for (var i = 0; i < jsonResponse.length; i++) {
			       		    		var dtf = new Date(jsonResponse[i].dataCad);
				       	            tr = $('<tr/>');		       	         			       	         
				       	            tr.append("<td>" + dtf.toLocaleString('pt-BR') + "</td>");
				       	            tr.append("<td>" + jsonResponse[i].ppoTipo.descricao + "</td>");	
				       	         	tr.append("<td>" + jsonResponse[i].codigo + "</td>");	
				       	            $("#constab").append(tr);	 	       	            
				       	        }  
		       			if(jsonResponse.length){      
		       				 $("#result").hide();
		       				 $("#constab").show();       				
		       		       }else{
			       		    	$("#constab").hide();
			       		    	$('#result').attr("class","alert alert-info");
			       				$('#result').html("Não existe nenhum registro.");
			       				$("#result").show();  
		       		       }
		   		       });
			 }
		   });
	});
	
  </script>
     
  </body>
</html>
