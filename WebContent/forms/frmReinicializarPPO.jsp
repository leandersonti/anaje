<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">
	
	
	<div class="card">
		<div class="card-header text-white bg-dark">
			<b>Reinicializar PPO</b>
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
$(document).ready(function() {
	
var select = $('#codZonaMunic');
select.find('option').remove();
	$.getJSON('../elo/listarJsonZonaEleitoralCBX', 
		function(jsonResponse) {
			$('<option>').val(0).text("Informe a Zona").appendTo(select);
			$('<option>').val('9999;9999').text("Reinicializar Todos").appendTo(select);
			$.each(jsonResponse, function(key, value) {				
				$('<option>').val(value.id.zona + ";" + value.id.codmunic).text(
					value.fzona + " - " + value.municipio)
						.appendTo(select);
			});
		}); 

	$('#codZonaMunic').change(function(event) {	
		$('#codZonaMunic').removeClass("is-invalid");
		carregaPontoTransmissao();	     
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
 							// console.log(frm);
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
 
function carregaPontoTransmissao(){
	var codZonaMunic = $("#codZonaMunic").val();
    var cbxpt = $('#idus');
        cbxpt.find('option').remove();
   	 if(codZonaMunic != -1){	    		 
		     $.getJSON('../pontotrans/listarJson?codZonaMunic='+codZonaMunic,function(jsonResponse) {
		   	  $('<option>').val(0).text("Informe o ponto de transmissao").appendTo(cbxpt);
		   	$('<option>').val(999999).text("Todos os pontos").appendTo(cbxpt);
		             $.each(jsonResponse, function(key, value) {             
		            	 $('<option>').val(value.id.id).text( ("0000" + value.codLocal).slice(-4) + " " + value.descricao).appendTo(cbxpt);
		      		 });
		     });
    }else{
   	 $('<option>').val(-1).text("Informe o Ponto de Transmissao").appendTo(cbxpt);
    }
}

</script>

<jsp:include page="/mainfooter.inc.jsp" />