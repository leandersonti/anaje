<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="distribuicao">active</s:set>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">Cadastrar Permissao Usuario:</div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>				
				<input type="hidden" value="" name="usuario.nome" id="nome" />
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="descricao">*Servidor :</label> 
					    <select class="form-control" name="usuario.tituloEleitor" id="tituloeleitor" required>
					  			<option>Small select</option>
						</select>
					    <div class="invalid-feedback">Por favor, informe um usuário.</div>
					</div>
					
					<div  class="col-md-6 mb-3">
						 <label	for="inputSolicitante">Zona: </label>
						 <s:select id="codZonaMunic" class="form-control" theme="simple"
								name="usuario.zona" headerKey="0"
								   headerValue="Usuário TRE-AM" list="lstZonaEleitoral"
								      listKey="id.zona" listValue="%{fzona + ' - ' + municipio}" required="true" />	
				    </div>					 
				</div>
				
					<div class="form-check form-check-inline">
						<input type="checkbox" name="usuario.adm" value="1" 
							   class="form-check-input" id="usuario.adm" <s:if test="usuario.adm == 1">checked</s:if>>
						
						<label for="sigla">Adm </label> 	
						<div class="invalid-feedback">Por favor, informe uma descricao.</div>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" name="usuario.ativo" value="1" 
							      class="form-check-input" id="usuario.ativo" <s:if test="usuario.ativo == 1">checked</s:if>>
						<label for="ativo">Ativo </label>
						<div class="invalid-feedback">Por favor, informe a empresa.</div>
					</div>

				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>
			</form>
		</div>
	</div>

</div>

<jsp:include page="/javascripts.jsp" />
<script>
$(document).ready(function() {	
	loadServidores();
	
	$( "#tituloeleitor").select2({
	    theme: "bootstrap4"
	});
	
	
 	 $("#btnSave").click(function() {
	    	var URL = "adicionar";  	
 			if (verificaDados()){
 				 swal({
 			         title: "Confirma ?",
 			         text: "Confirma " + URL + "?",
 			         icon: "warning",
 			         buttons: [true, "Salvar"]
 			       }).then((result) => {
 						if (result) {
 							var frm = $("#form1").serialize();	
 							console.log(frm);
 							$.getJSON({
 								url: URL,
 								data: frm
 						    }).done(function( data ) {					    	
 						    	if(data.ret==1)
 						    		swal(URL, data.mensagem, "success");
 						    	else 
 						    		swal(URL, data.mensagem, "error");
 							}).fail(function() {
 									swal("Adicionar", "Ocorreu um erro ao incluir", "error");
 							});
 					      } 
 				   }); // -- FIM SWAL --
 			   }else{
 				   swal("Servidor", "Informe pelo menos um servidor", "error");
 			   }
 		
 	}); // -- FIM btnSave -- */
 	
 	function loadServidores(){
 		var vUrl = "../servidor/listarParaPermissaoJson";
 		var select = $('#tituloeleitor');	      
 	    select.find('option').remove();
 		$.getJSON({
				url: vUrl
		    }).done(function( data ) {					    	
		    	$('<option>').val(0).text("Informe o servidor").appendTo(select);			    	  
			      $.each(data, function(key, value) {
			            $('<option>').val(value.tituloEleitor).text(value.nome).appendTo(select);
	      	      });
			}).fail(function() {
				swal("Servidores", "Ocorreu um erro ao ler dados dos servidores", "error");
			});
 	}
 		      
 	function verificaDados(){
 		//console.log($('#tituloeleitor').select2('data')[0]);
 		//console.log("Nome = " + $('#tituloeleitor').select2('data')[0].text);
 		// $("#tituloeleitor option:selected").text);
 		if ($('#tituloeleitor').select2('data')[0].id==0){
 			return false;
 		}else{
 			$('#nome').val($('#tituloeleitor').select2('data')[0].text);
 			//console.log($('#nome').val());
 			return true;
 		}
 			
 	} 
});

</script>
	
<jsp:include page="/mainfooter.inc.jsp" />