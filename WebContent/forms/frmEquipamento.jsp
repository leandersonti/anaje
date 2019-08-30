<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">Cadastrar/Editar Equipamento:</div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<s:if test='equipamento.id != null'>
					<input type="hidden" id="id" name="equipamento.id" value="${equipamento.id}">
				</s:if>				

				<div class="form-row">
					<div class="col-md-6 mb-6">
						<label for="ncada">Tipo :</label> 								
							<s:select label="Tipo" headerKey="-1"
									headerValue="Selecione o Tipo"
									list="lstEquipamentoTipo" listKey="id"
									listValue="descricao"
									name="equipamento.tipo.id"  id="tipo.id" theme="simple"
									cssClass="form-control" />  
					</div>
					
					<div class="col-md-6 mb-6">
						<label for="ncada">Eleição :</label> 								
							<s:select label="Eleicao" headerKey="-1"
									headerValue="Selecione a Eleicao"
									list="lstDataEleicao" listKey="id"
									listValue="descricao"
									name="equipamento.dataEleicao.id"  id="dataEleicao.id" theme="simple"
									cssClass="form-control" />  
					</div>
					
				    </div>				    
				    <br>
				    <div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="cep">Serie: </label> 
						<input type="text" class="form-control" id="serie" name="equipamento.serie" placeholder="Serie" value="${equipamento.serie}">
					</div>	
					<div class="col-md-3 mb-3">
						<label for="cidade">Tomb: </label> 
						<input type="text" class="form-control" id="tomb" name="equipamento.tomb" placeholder="tomb" value="${equipamento.tomb}">
					</div>
					<div class="col-md-3 mb-3">
						<label for="uf">Param:</label> 
						<input type="text" class="form-control" id="param" name="equipamento.param" placeholder="Param"  value="${equipamento.param}">
					</div>	
					<div class="col-md-3 mb-3">
						<label for="uf">Chave:</label> 
						<input type="text" class="form-control" id="chave" name="equipamento.chave" placeholder="Chave"  value="${equipamento.chave}">
					</div>				
				</div>
				<br>
				 <div class="form-row">
					<div class="col-md-6 mb-3">	
					<label for="uf">Fone:</label> 
						<input type="text" class="form-control" id="fone" name="equipamento.fone" placeholder="Fone"  value="${equipamento.fone}">
					</div>
					</div>		
				<br>
				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>
			</form>
		</div>
	</div>

</div>

<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">
$(document).ready(function() {
 
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

 function verificaDados(){
    if ($("#form1")[0].checkValidity()===false){
    	$("#form1")[0].classList.add('was-validated');
    	return false;
    }else 
	   return true;
 }
</script>
	
<jsp:include page="/mainfooter.inc.jsp" />








