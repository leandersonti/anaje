<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">Cadastrar Permissao Usuario:</div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>				
				
				<div class="form-row">
					<label for="descricao">*Titulo de Eleitor :</label> 
					<input type="text" class="form-control" id="descricao" name="usuario.tituloEleitor" placeholder=" " value="${usuario.tituloEleitor}" required>
					<div class="invalid-feedback">Por favor, informe uma descricao.</div>
				</div>
				
				<div class="form-row">
					
					<div class="col-md-6 mb-3">
						<label for="sigla">Nome :</label> 
						<input type="text" class="form-control" id="sigla" name="usuario.nome" placeholder="" value="${usuario.nome}" required>
						<div class="invalid-feedback">Por favor, informe uma descricao.</div>
					</div>
					
					<div  class="col-md-6 mb-3">
						 <label	for="inputSolicitante">Zona: </label>
							<s:select id="zona" class="form-control" theme="simple"
								name="usuario.zona" headerKey="-1"
								headerValue="--Selecione--" list="lstZonas"
								listKey="id.zona" listValue="%{zona + ' - ' + municipio}" required="true" />
				    </div>
					 
				</div>
				 
				<div class="form-row">					
					<div class="col-md-6 mb-3">
						
						<s:if test="usuario.adm == 1">
							<input type="checkbox" name="usuario.adm" class="form-check-input" id="exampleCheck1" checked>
						</s:if>
						<s:else>
							<input type="checkbox" name="usuario.adm" class="form-check-input" id="exampleCheck1">
						</s:else>
						<label for="sigla">*Adm :</label> 	
						<div class="invalid-feedback">Por favor, informe uma descricao.</div>
					</div>
					
					<div class="col-md-6 mb-3">						
						 <s:if test="usuario.ativo == 1">
							<input type="checkbox" name="usuario.ativo" class="form-check-input" id="exampleCheck1" checked>
						</s:if>
						<s:else>
							<input type="checkbox" name="usuario.ativo" class="form-check-input" id="exampleCheck1">
						</s:else>	
						<label for="ativo">*Ativo :</label>
						<div class="invalid-feedback">Por favor, informe a empresa.</div>
				    </div>
				</div>
										
				<br>
				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>
			</form>
		</div>
	</div>

</div>

<jsp:include page="/javascripts.jsp" />
<script>
$(document).ready(function() {	
	loadServidores();	
 	 $("#btnSave").click(function() {
 		 var user =  $("#multiselect2").val(); 	 		
 		 if(user!=null){ 			  		
 			var URL = "adicionar";  	
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

</script>
	
<jsp:include page="/mainfooter.inc.jsp" />