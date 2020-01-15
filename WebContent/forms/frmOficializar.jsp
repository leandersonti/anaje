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
					     <label for="inputSolicitante">Ponto Transmissão: </label>		
						 <select class="form-control" name="id.id" id="idus" required>
					  			<option>--</option>
						</select>						
					</div>
				</div>  
				<br />
				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>
			</form>
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
		console.log(jsonResponse);
		$('<option>').val(999999).text("Oficializar todos").appendTo(cbxIUS);
		$.each(jsonResponse, function(key, value) {		
			$('<option>').val(value.id.id).text(value.descricao).appendTo(cbxIUS);
		});  
		/*
		  $('.listbox').bootstrapDualListbox({
	 			moveOnSelect: false, 
	 			moveOnDoubleClick: true,
	 			preserveSelectionOnMove: 'all',
	 			nonSelectedListLabel: 'Não-selecionados',
	 			selectedListLabel: 'Selecionados',	 			
	 		});  
		  $('.listbox').bootstrapDualListbox('refresh');
		  */
	});		
	  	   
}


 function verificaDados(){
    if ($("#form1")[0].checkValidity()===false){
    	$("#form1")[0].classList.add('was-validated');
    	return false;
    }else 
	   return true;
 }
</script>

<jsp:include page="/mainfooter.inc.jsp" />








