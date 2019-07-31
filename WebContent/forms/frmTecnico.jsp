<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header"> <b><i>Cadastrar/Atualizar T�cnico : </i> </b></div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<s:if test='tecnico.id != null'>
					<input type="hidden" id="id" name="tecnico.id" value="${tecnico.id}">
				</s:if>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="Tecnico">Nome:</label> 
						<input type="text" class="form-control" name="tecnico.nome" id="nome" value="${tecnico.nome}" placeholder="Informe o Nome" >						
					</div>
					
					<div class="col-md-3 mb-3">
						<label for="dtNasc">Dt de Nasc. :</label> 
						<input type="text" class="form-control" id="dtNasc" name="DtNasc" value="<s:text name="format.date"><s:param value="tecnico.data_nasc"/></s:text>" placeholder="dd/mm/aaaa"/>						
					</div>
					
					<div class="col-md-3 mb-3">
						<label for="sexo">Sexo :</label> 
						<input type="text" class="form-control" id="sexo" name="tecnico.sexo" placeholder="Ex:M" maxlength="1" value="${tecnico.sexo}" >						
					</div>
				</div>				
				
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="endereco">Endere�o - Rua :</label> 
						<input type="text" class="form-control" id="rua" name="tecnico.endereco" placeholder="Rua" value="${tecnico.endereco}">
					</div>	
						
					<div class="col-md-6 mb-3">
						<label for="bairro">Bairro:</label> 
						<input type="text" class="form-control" id="bairro" name="tecnico.bairro"	placeholder="Informe a zona" value="${tecnico.bairro}">
					</div>			
				</div>
				
				
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="ncada">N� Casa:</label> 
						<input type="text" class="form-control" id="ncasa" name="tecnico.num_casa"  value="${tecnico.num_casa}" maxlength="10" placeholder="Num Casa" >
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
						<label for="uf">Uf (Estado):</label> 
						<input type="text" class="form-control" id="uf" name="tecnico.uf" placeholder="Ex:AM" maxlength="2" value="${tecnico.uf}">
					</div>					
				</div>
				
				
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="rg">T�tulo:</label> 
						<input type="text" class="form-control" id="titulo" name="tecnico.titulo_eleitor" placeholder="titulo" value="${tecnico.titulo_eleitor}">
					</div>
					
					<div class="col-md-3 mb-3">
						<label for="cpf">Zona:</label> 
						<input type="text" class="form-control" id="zona" name="tecnico.zona"	placeholder="Informe a zona" value="${tecnico.zona}">
					</div>
					<div class="col-md-3 mb-3">
						<label for="secao">Se��o:</label> 
						<input type="text" class="form-control" id="secao" name="tecnico.secao" placeholder="Se��o" value="${tecnico.secao}">
					</div>					
				</div>
				
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="rg">Rg:</label> 
						<input type="text" class="form-control" id="rg" name="tecnico.rg" placeholder="Rg" value="${tecnico.rg}">
					</div>
					<div class="col-md-3 mb-3">
						<label for="orgaoRg">Org�o Expedidor:</label> 
						<input type="text" class="form-control" id="orgaoRg" name="tecnico.orgaoRg" placeholder="Org�o Expedidor" value="${tecnico.orgaoRg}">
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
			   Swal.fire("Dados", "Verifique os campos obrigat�rios ", "error");
		   }
	 	}); // -- FIM btnSave --
	 
});

 function verificaDados(){
    if ($("#form1")[0].checkValidity()===false){
    	$("#form1")[0].classList.add('was-validated');
    	return false;
    }else 
	   return true;
 }
</script>
	
<jsp:include page="/mainfooter.inc.jsp" />







