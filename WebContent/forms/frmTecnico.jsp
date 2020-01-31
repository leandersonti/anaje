<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header"><b>Técnico</b> <s:if test='tecnico.id != null'> - Editar</s:if></div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<s:if test='tecnico.id != null'>
					<input type="hidden" id="id" name="tecnico.id" value="${tecnico.id}">
				</s:if>
				<s:else>
					<div class="form-row">
						<div class="col-md-6 mb-3">
							<label for="Tecnico"><b>Contrato</b></label>
							<s:select label="Zona" headerKey="-1"
									headerValue="Selecione Contrato" tooltip="Informe contrato"
									list="lstContrato" listKey="id"
									listValue="sigla +' - '+ descricao"
									name="contrato.id"  id="codZonaMunic" theme="simple"
									cssClass="form-control" />  
						</div>					
						<div class="col-md-3 mb-3"></div>
						<div class="col-md-3 mb-3"></div>
					</div>
				</s:else>
				
				
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="Tecnico">Nome:</label> 
						<input type="text" class="form-control" name="tecnico.nome" id="nome" value="${tecnico.nome}" placeholder="Informe o Nome" >						
					</div>
					
					<div class="col-md-3 mb-3">
						<label for="dtNasc">Dt de Nasc. :</label> 
						<input type="text" class="form-control" id="dtNasc" name="DtNasc" value="" placeholder="dd/mm/aaaa"/>						
					</div>
					
					<div class="col-md-3 mb-3">
							<label for="inputState">Sexo:</label>
						<select id="sexo" class="form-control" name="tecnico.sexo">
						        <option value="">Selecione...</option>
						        <option value="M"<s:if test='tecnico.sexo == "M"'> Selected</s:if>>Masculino</option>
						        <option value="F"<s:if test='tecnico.sexo == "F"'> Selected</s:if>>Feminino</option>
						        <option value="O"<s:if test='tecnico.sexo == "O"'> Selected</s:if>>Outros</option>
     					 </select>												
					</div>
				</div>				
				
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="endereco">Endereço - Rua :</label> 
						<input type="text" class="form-control" id="rua" name="tecnico.endereco" placeholder="Rua" value="${tecnico.endereco}">
					</div>	
					
						
					<div class="col-md-6 mb-3">
						<label for="bairro">Bairro:</label> 
						<input type="text" class="form-control" id="bairro" name="tecnico.bairro"	placeholder="Informe o bairro" value="${tecnico.bairro}">
					</div>			
				</div>				
				
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="ncada">N° Casa:</label> 
						<input type="text" class="form-control" id="ncasa" name="tecnico.numCasa"  value="${tecnico.numCasa}" maxlength="10" placeholder="Num Casa" >
					</div>
				
					<div class="col-md-3 mb-3">
						<label for="cep">Cep:</label> 
						<input type="text" class="form-control" id="cep" name="tecnico.cep" placeholder="Cep" value="${tecnico.cep}">
					</div>	
					<div class="col-md-3 mb-3">
						<label for="cidade">Cidade:</label> 
						<input type="text" class="form-control" id="cidade" name="tecnico.cidade" placeholder="cidade" value="${tecnico.cidade}">
					</div>
					<div class="col-md-3 mb-3">
						<label for="uf">Uf:</label> 
						<input type="text" class="form-control" id="uf" name="tecnico.uf" placeholder="Ex:AM" maxlength="2" value="${tecnico.uf}">
					</div>					
				</div>
				
				
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="rg">Título:</label> 
						<input type="text" class="form-control" id="titulo" name="tecnico.tituloEleitor" placeholder="titulo" value="${tecnico.tituloEleitor}">
					</div>
					
					<div class="col-md-3 mb-3">
						<label for="cpf">Zona:</label> 
						<input type="text" class="form-control" id="zona" name="tecnico.zona"	placeholder="Informe a zona" value="${tecnico.zona}">
					</div>
					<div class="col-md-3 mb-3">
						<label for="secao">Seção:</label> 
						<input type="text" class="form-control" id="secao" name="tecnico.secao" placeholder="Seção" value="${tecnico.secao}">
					</div>					
				</div>
				
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="rg">Rg:</label> 
						<input type="text" class="form-control" id="rg" name="tecnico.rg" placeholder="Rg" value="${tecnico.rg}">
					</div>
					<div class="col-md-3 mb-3">
						<label for="orgaoRg">Orgão Expedidor:</label> 
						<input type="text" class="form-control" id="orgaoRg" name="tecnico.orgaoRg" placeholder="Orgão Expedidor" value="${tecnico.orgaoRg}">
					</div>
					<div class="col-md-3 mb-3">
						<label for="cpf">Cpf:</label> 
						<input type="text" class="form-control" id="cpf" name="tecnico.cpf"	placeholder="Informe o cpf" value="${tecnico.cpf}">
					</div>
				</div>
				
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="tel">Telefone:</label> 
						<input type="text" class="form-control" id="telefone" name="tecnico.telefone" placeholder="Telefone" value="${tecnico.telefone}">
					</div>
					<div class="col-md-6 mb-3">
						<label for="celular">Celular:</label> 
						<input type="text" class="form-control" id="celular" name="tecnico.celular" placeholder="Celular" value="${tecnico.celular}">
					</div>				
				</div>
				
				<div class="form-row">
					<label for="email">E-mail:</label> 
					<input type="text" class="form-control" id="descricao" name="tecnico.email"	placeholder="E-mail" value="${tecnico.email}">
					
				</div>
				<br>		
				<br>
				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>
			</form>
		</div>
	</div>
</div>

<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">
$(document).ready(function() {	
	$('#dtNasc').mask('99/99/9999');	
	
	 $("#btnSave").click(function() {
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
					    	console.log(data);
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
	 	}); // -- FIM btnSave --
	 
});


//var idtec =  $('#id').val();
//$.getJSON('../tecnico/getBeanJson?id='+idtec,function(jsonResponse) {		
//	 document.getElementById("sexo").value = jsonResponse.sexo;
//});


 function verificaDados(){
    if ($("#form1")[0].checkValidity()===false){
    	$("#form1")[0].classList.add('was-validated');
    	return false;
    }else 
	   return true;
 }
</script>
	
<jsp:include page="/mainfooter.inc.jsp" />








