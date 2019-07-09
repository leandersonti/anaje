<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">Cadastrar Data Eleição</div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1">
				<s:if test='eleicao.id != null'>
					<input type="hidden" id="id" name="eleicao.id"
						value="${eleicao.id}">
				</s:if>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="dataEleicao">Data Eleição</label> <input type="date"
							class="form-control" name="eleicao.dataEleicao" id="dataEleicao"
							placeholder="" value="" required>
						<div class="valid-feedback">Tudo certo!</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="turno">Turno</label> <input type="text"
							class="form-control" id="turno" name="eleicao.turno"
							placeholder="Informe o turno" value="" required>
						<div class="valid-feedback">Tudo certo!</div>
					</div>


				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="tituloEleitor">Título de eleitor</label> <input
							type="text" class="form-control" id="tituloEleitor"
							name="eleicao.titTRE" placeholder="Informe o Título de eleitor">
						<div class="invalid-feedback">Por favor, informe uma cidade
							válida.</div>
					</div>

					<div class="col-md-6 mb-3">
						<label for="email">Email</label> <input type="text"
							class="form-control" id="email" name="eleicao.email"
							placeholder="Informe o email" >
						<div class="invalid-feedback">Por favor, informe um estado
							válido.</div>
					</div>
				</div>

				<div class="form-row">
					<label for="descricao">Descrição</label> <input type="text"
						class="form-control" id="descricao" name="eleicao.descricao"
						placeholder="Informe uma curta descrição">
					<div class="invalid-feedback">Por favor, informe uma cidade
						válida.</div>
				</div>

				<br>

				<div class="form-group">
					<div class="custom-control custom-switch">
						<input type="checkbox" class="custom-control-input"
							id="customSwitch1" name="eleicao.ativo" value="1"> <label
							class="custom-control-label" for="customSwitch1">Ativo</label>
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
			   Swal.fire("Dados", "Verifique os dados obrigatórios", "error");
		   }
	 	}); // -- FIM btnSave --
	 
});

 function verificaDados(){
	if ($('#dataEleicao').val().length == 0)
		return false;


	return true;
 }

</script>	
<jsp:include page="/mainfooter.inc.jsp" />








