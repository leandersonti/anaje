<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container">
	
		<div class="card">
		<div class="card-header text-black bg-warning">
			<b>Reinicializar Encerramento</b>
		</div>
		<div class="card-body">

			<form action="" method="post" name="frmReiniciarPPO" id="frmReiniciarPPO">

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="inputSolicitante">Zona: </label>
						<select class="form-control" name="codZonaMunic" id="codZonaMunic" required>
					  			<option value="0">Informe a Zona Eleitoral</option>
						</select>
						<div class="invalid-feedback">Informe a Zona Eleitoral.</div>						
					</div>
												
					<div class="col-md-6 mb-3">
					     <label for="inputSolicitante">*Ponto Transmissão: </label>		
						 <select class="form-control" name="pkPonto.id" id="idus" required>
					  			<option value="9999">Todos</option>
						</select>					
						<div class="invalid-feedback">Informe o ponto de transmissão.</div>	
					</div>
				</div>  
				
				<button class="btn btn-primary" id="btnSave" type="button">Reinicializar</button>
			</form>
		</div>
	</div> 
	
</div>

<jsp:include page="/javascripts.jsp" />
<script type="text/javascript">
var URLSIS = "${pageContext.request.contextPath}";
$(document).ready(function() {
	carregaZonaEleitoralCBX();
	
	$('#codZonaMunic').change(function(event) {	
		$('#codZonaMunic').removeClass("is-invalid");
		carregarPontoTransmissao();	     
	});
	
   $("#btnSave").click(function() {
 	 var idus =  $("#idus").val(); 	 		
 	 var URL = "reiniciarJson"; 	
 		if (verificaDados()){
 			 swal({
 		         title: "Confirma ?",
 		         text: "Confirma Reinicialização do PPO?",
 		         icon: "warning",
 		          buttons: [true, "Sim"]
 	         }).then((result) => {
 				if (result) {
 					var frm = $("#frmReiniciarPPO").serialize();	
 					$.getJSON({
 						url: URL,
 						data: frm
 				    }).done(function( data ) {					    	
 				    	if(data.ret==1){
 					    		swal("Reinicialização", data.mensagem, "success");
 				    	}
 				    	else 
 				    		swal(URL, data.mensagem, "error");
 					}).fail(function() {
 							swal("Reinicialização", "Ocorreu um erro ao reinicializar PPO", "error");
 					});
 			      } 
 			   }); // -- FIM SWAL --
 		 }else{
 			   swal("Dados", "Verifique os campos obrigatórios ", "error");
 	     }
	 }); // -- FIM btnSave -- */
	 	
 });	

function verificaDados(){
	if ($("#codZonaMunic").val()==0){
  	  $("#codZonaMunic")[0].classList.add('is-invalid');
  		return false;
  	}

	if ($("#idus").val()==0){
    	  $("#idus")[0].classList.add('is-invalid');
    	return false;
    }else {
   		$("#idus")[0].classList.remove('is-invalid');
	   return true;
    } 
 }

</script>
<script src="${pageContext.request.contextPath}/js/commonutils.js" charset="utf-8"></script>
<jsp:include page="/mainfooter.inc.jsp" />