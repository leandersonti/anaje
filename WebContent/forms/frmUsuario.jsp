<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header"> <b><i>Cadastrar/Atualizar Usuário : </i> </b></div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<s:if test='usuario.tituloEleitor != null'>
					<input type="hidden" id="id" name="usuario.tituloEleitor" value="${usuario.tituloEleitor}">
				</s:if>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="Usuario">Titulo:</label> 
						<input type="text" class="form-control" name="usuario.tituloEleitor" id="titulo" value="${usuario.tituloEleitor}" placeholder="Informe o Titulo" >						
					</div>
					
					<div class="col-md-6 mb-3">
						<label for="nome">Nome :</label> 
						<input type="text" class="form-control" id="nome" name="usuario.nome" value="${usuario.nome}" placeholder=""/>						
					</div>				
				</div>	
				
				<div class="form-row">					
					<div class="col-md-3 mb-3">
						<label for="nome">Zona :</label> 
						<input type="text" class="form-control" id="zona" name="usuario.zona" value="${usuario.zona}" placeholder=""/>						
					</div>									
				</div>		
				
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

 function verificaDados(){
    if ($("#form1")[0].checkValidity()===false){
    	$("#form1")[0].classList.add('was-validated');
    	return false;
    }else 
	   return true;
 }
</script>
	
<jsp:include page="/mainfooter.inc.jsp" />








