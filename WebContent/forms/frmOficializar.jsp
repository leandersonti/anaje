<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">
	<div class="card">
		<div class="card-header"> <b><i>Oficializar: </i> </b></div>
		<div class="card-body">

			<form action="adicionar" method="post" name="form1" id="form1" >
			
				<!-- MULTISELECT DE CONVOCADOS -->
				<div class="panel-body">
					<div class="row">								  								
		 	 				<select name="lstTitulos" id="multiselect2"
						 		class="listbox" multiple="multiple" size="8">										
							</select>
					</div>
				</div>
					
				<br> 
				
				<div class="form-row">					
					<div class="col-xs-6">
							 <label	for="inputSolicitante">Zona: </label>
							<s:select label="Zona" headerKey="-1"
								headerValue="Selecione a zona" tooltip="Informe a Zona"
								list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
								listValue="fzona +' - '+ municipio"
								name="ds.codZonaMunic"  id="codZonaMunic" theme="simple"  cssClass="form-control"/>
						</div>								
				</div>						 
	
				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>
			</form>
		</div>
	</div>

</div>

<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">
$(document).ready(function() {	
	
	$('#codZonaMunic').change(function(event) {	
		loadServidores();	     
	 });	
	
 	 $("#btnSave").click(function() {
 		 var user =  $("#multiselect2").val(); 	 		
 		 if(user!=null){
 			var URL = ""; 
 			if ( $('#id').length ) { URL = "atualizar"; }
 			else{ URL = "adicionar";  }	
 			if (verificaDados()){
 				 Swal.fire({
 			         title: "Confirma ?",
 			         text: "Confirma " + URL + "?",
 			         type: 'warning',
 			         showCancelButton: true,
 					  confirmButtonText: 'Incluir'
 			         }).then((result) => {
 						if (result.value) {
 							var frm = $("#form1").serialize();						
 							$.getJSON({
 								url: URL,
 								data: frm
 						    }).done(function( data ) {					    	
 						    	if(data.ret==1)
 						    		Swal.fire(URL, data.mensagem, "success");
 						    	else 
 						    		Swal.fire(URL, data.mensagem, "error");
 							}).fail(function() {
 									Swal.fire("Adicionar", "Ocorreu um erro ao incluir", "error");
 							});
 					      } 
 				   }); // -- FIM SWAL --
 			   }else{
 				   Swal.fire("Dados", "Verifique os campos obrigatórios ", "error");
 			   }
 			 
 		 }else{
 			 alert("Escolha ao menos um Usuário !");
 		 }

	 	}); // -- FIM btnSave -- */
	 
});

function loadServidores() {
	codZonaMunic = $("#codZonaMunic").val();
	$.getJSON('listarJson?codZonaMunic='+codZonaMunic, function(jsonResponse) {
		var select2 = $('#multiselect2');			
		select2.find('option').remove();
		
		$.each(jsonResponse, function(key, value) {		
			$('<option>').val(value.id.id).text(value.descricao).appendTo(select2);
		});  
		  $('.listbox').bootstrapDualListbox({
	 			moveOnSelect: false, 
	 			moveOnDoubleClick: true,
	 			preserveSelectionOnMove: 'all',
	 			nonSelectedListLabel: 'Não-selecionados',
	 			selectedListLabel: 'Selecionados',	 			
	 		});  
		  $('.listbox').bootstrapDualListbox('refresh');
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








