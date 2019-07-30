<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">Cadastrar Contrato:</div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<s:if test='contrato.id != null'>
					<input type="hidden" id="id" name="contrato.id" value="${contrato.id}">
				</s:if>
				<div class="form-row">
					
					<div class="col-md-6 mb-3">
						<label for="sigla">Sigla :</label> 
						<input type="text" class="form-control" id="sigla" name="contrato.sigla" placeholder="" value="${contrato.sigla}">
					</div>
					
					<div  class="col-md-6 mb-3">
						<label for="email">*Empresa :</label> 
						<input type="text" class="form-control" id="empresa" name="contrato.empresa"	placeholder=" " value="${contrato.empresa}" required>
						<div class="invalid-feedback">Por favor, informe a empresa.</div>
				    </div>
					
				</div>
				
				

				<div class="form-row">
					<label for="descricao">*Descri��o :</label> 
					<input type="text" class="form-control" id="descricao" name="contrato.descricao"	placeholder=" " value="${contrato.descricao}" required>
					<div class="invalid-feedback">Por favor, informe uma descricao.</div>
				</div>
				<br>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="dataInicio">*Data In�cio:</label> 
						<input type="date" class="form-control" name="contrato.dataInicio" id="dataInicio" value="<s:property value="%{getText('format.dtUSA',{contrato.dataInicio})}"/>" required>
						<div class="invalid-feedback">Por favor, informe a data de in�cio.</div>
					</div>
					
					<div class="col-md-6 mb-3">
						<label for="dataFim">*Data Fim:</label> 
						<input type="date" class="form-control" name="contrato.dataFim" id="dataFim" value="<s:property value="%{getText('format.dtUSA',{contrato.dataFim})}"/>" required>
						<div class="invalid-feedback">Por favor, informe a data de fim.</div>
					</div>
				</div>
							
				<div class="form-row">
			      <label for="cargo">Cargo :</label>
			     	 <s:select label="Cargo" headerKey="-1" headerValue="Selecione o cargo" tooltip="Informe um Cargo"
						list="lstCargo" 
						listKey="id"
						listValue="descricao"
						name="contrato.cargo.id" theme="simple" cssClass="form-control"/>
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
						console.log(frm);
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








