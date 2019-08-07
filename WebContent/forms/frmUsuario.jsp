<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">
	<div class="card">
		<div class="card-header"> <b><i>Cadastrar/Atualizar Usu�rio : </i> </b></div>
		<div class="card-body">

			<form action="adicionar" method="post" name="form1" id="form1" >
				<s:if test='usuario.tituloEleitor != null'>
					<input type="hidden" id="id" name="usuario.tituloEleitor" value="${usuario.tituloEleitor}">
				</s:if>
				
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
							<s:select id="zona" class="form-control" theme="simple"
								name="usuario.zona" headerKey="-1"
								headerValue="--Selecione--" list="lstZonas"
								listKey="id.zona" listValue="%{zona + ' - ' + municipio}" required="true" />
						</div>								
				</div>		
				 <br>
					<div class="form-group">
					<div class="custom-control custom-switch">						
			                  <input type="checkbox" class="custom-control-input"	id="ativo" name="usuario.ativo" value="1"<s:if test="usuario.ativo ==1"> checked</s:if>>  
						      <label class="custom-control-label" for="ativo">Ativo</label>			         				         						
					</div>	
					<div class="custom-control custom-switch">
							<input type="checkbox" class="custom-control-input"	id="admin" name="usuario.admin" value="1" <s:if test="usuario.admin ==1"> checked</s:if>> 
							<label class="custom-control-label" for="admin">Admin</label>
				
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
	loadServidores();	
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
 				   Swal.fire("Dados", "Verifique os campos obrigat�rios ", "error");
 			   }
 			 
 		 }else{
 			 alert("Escolha ao menos um Usu�rio !");
 		 }

	 	}); // -- FIM btnSave -- */
	 
});

function loadServidores() {
	
	$.getJSON('../servidor/listarJson', function(jsonResponse) {
		var select2 = $('#multiselect2');			
		//select2.find('option').remove(); 
		
		$.each(jsonResponse, function(key, value) {		
			$('<option>').val(value.tituloEleitor + ';' + value.nome).text(value.siglaUnid +" - " +value.nome).appendTo(select2);
		});  
		  $('.listbox').bootstrapDualListbox({
	 			moveOnSelect: false, 
	 			moveOnDoubleClick: true,
	 			preserveSelectionOnMove: 'all',
	 			nonSelectedListLabel: 'N�o-selecionados',
	 			selectedListLabel: 'Selecionados',
	 			

	 		});  
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








