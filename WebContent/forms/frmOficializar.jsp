<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="pontotrans">active</s:set>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">
	<div class="card">
		<div class="card-header">
			<b><i>Oficializar Ponto de Transmissão: </i> </b>
		</div>
		<div class="card-body">

			<form action="adicionar" method="post" name="form1" id="form1">

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="inputSolicitante">Zona: </label>
						<s:select label="Zona" headerKey="-1"
							headerValue="Selecione a zona" tooltip="Informe a Zona"
							list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
							listValue="fzona +' - '+ municipio" name="codZonaMunic"
							id="codZonaMunic" theme="simple" cssClass="form-control" />
					</div>
												
					<div class="col-md-6 mb-3">
					     <label for="inputSolicitante">*Ponto Transmissão: </label>		
						 <select class="form-control" name="id.id" id="idus" required>
					  			<option value="0">Informe o Ponto</option>
						</select>					
						<div class="invalid-feedback">Informe o ponto de transmissõa.</div>	
					</div>
				</div>  
				
				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>
			</form>
			<div class="row">
			<div class="alert alert-warning alert-dismissible fade show" role="alert">
			  <strong>Holy guacamole!</strong> You should check in on some of those fields below.
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			</div>
		</div>
	</div> 

</div>

<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">
$(document).ready(function() {	
	
	$('#codZonaMunic').change(function(event) {	
		carregarPontosTransmissao();	     
	 });	
	
 	 $("#btnSave").click(function() {
 		 var idus =  $("#idus").val(); 	 		
 		 var URL = ""; 
 			//if ( $('#id').length ) { URL = "atualizar"; }
 			//else{ 
 			URL = "oficializar"; 	
 			if (verificaDados()){
 				 swal({
 			         title: "Confirma ?",
 			         text: "Confirma " + URL + "?",
 			         icon: "warning",
 			          buttons: [true, "Sim"]
 			         }).then((result) => {
 						if (result) {
 							var frm = $("#form1").serialize();	
 							console.log(frm);
 							$.getJSON({
 								url: URL,
 								data: frm
 						    }).done(function( data ) {					    	
 						    	if(data.ret==1){
 						    		carregarPontosTransmissao();
 						    		swal(URL, data.mensagem, "success");
 						    	}
 						    	else 
 						    		swal(URL, data.mensagem, "error");
 							}).fail(function() {
 									swal("Adicionar", "Ocorreu um erro ao incluir", "error");
 							});
 					      } 
 				   }); // -- FIM SWAL --
 			   }else{
 				   swal("Dados", "Verifique os campos obrigatórios ", "error");
 			   }
	 	}); // -- FIM btnSave -- */
	 
});

function carregarPontosTransmissao() {
	codZonaMunic = $("#codZonaMunic").val();
	$.getJSON('listarSemOficializar?codZonaMunic='+codZonaMunic, function(jsonResponse) {
		var cbxIUS = $('#idus');			
		cbxIUS.find('option').remove();
		$('<option>').val(0).text("Informe o ponto").appendTo(cbxIUS);
		if (jsonResponse.length>0){
			$('<option>').val(999999).text("Oficializar todos").appendTo(cbxIUS);
			$.each(jsonResponse, function(key, value) {		
				$('<option>').val(value.id.id).text(value.descricao).appendTo(cbxIUS);
			});
		}else
			swal("Todos os pontos já foram oficializados ou não há ponto(s) disponíve(ies)l para " + $("#codZonaMunic option:selected").text() );
		    //swal("Pontos Tranmissão", "Todos os pontos já foram oficializados ou não ponto disponível", "info");
		  		
	});		
	  	   
}


 function verificaDados(){
    if ($("#idus").val()==0){
    	  $("#idus")[0].classList.add('is-invalid');
    	return false;
    }else {
   		$("#idus")[0].classList.remove('is-invalid');
	   return true;
    } 
 }
</script>

<jsp:include page="/mainfooter.inc.jsp" />








