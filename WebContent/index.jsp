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
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <style type="text/css">
	   body {
		  padding-top: 1rem;
		}
	</style>
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
				             <span class="glyphicon glyphicon-home" aria-hidden="true"></span><br>1-Cheguei no Cartório
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
		
	        <p>&copy; STI/CDES/SAWEB 2019  <i class="fa fa-lock" aria-hidden="true"></i> <a href="${pageContext.request.contextPath}/login/frmLogin">Login</a>&nbsp;&nbsp; 
	          <i class="glyphicon glyphicon-tags" aria-hidden="true"></i>
	          <!-- 
	          <a href="http://intranet.tre-am.jus.br:8080/Mura/">Mura</a>&nbsp;&nbsp;
	          <a href="http://intranet.tre-am.jus.br:8080/acompelei/">AcompElei</a>&nbsp;&nbsp;
	          <!--  <a href="homologaPonto/frmSetupHomologaPonto">Homologar Ponto Transmissão</a>  -->
	        </p>
	    
	</div>
  </div>

  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>  
  <script src="js/ppofront.js" charset="utf-8"></script>
     
  </body>
</html>
