<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">Cadastrar Ponto de Transmissão</div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<input type="hidden" id="tipo" name="uservico.tipo.id" value="1">
				<s:if test='uservico.id != null'>
					<input type="hidden" id="id" name="uservico.id.id" value="${uservico.id.id}">	
					<input type="hidden" id="oficial" name="uservico.oficial" value="${uservico.oficial}">				
					<input type="hidden" id="zona" name="uservico.zona" value="${uservico.zona}">
					<input type="hidden" id="local" name="uservico.local" value="${uservico.local}">
					<input type="hidden" id="codObjeto" name="uservico.codObjeto" value="${uservico.codObjeto}">
					<input type="hidden" id="codmunic" name="uservico.codmunic" value="${uservico.codmunic}">
					<input type="hidden" id="status" name="uservico.status" value="${uservico.status}">
					<input type="hidden" id="dataeleicao" name="uservico.id.dataEleicao.id" value="${uservico.id.dataEleicao.id}">
				</s:if>
				<s:else>
				    <input type="hidden" id="oficial" name="uservico.oficial" value="0">
				    <input type="hidden" id="oficial" name="uservico.status" value="1">
				    <input type="hidden" id="codObjeto" name="uservico.codObjeto" value="">
				</s:else>
				
				<div class="form-row">
					<div class="col-md-6 mb-6">
						<label for="zona">Zona:</label>
						 <s:if test='uservico.id != null'>
							${uservico.zona}
						 </s:if>
						 <s:else>
						    <s:select label="Zona" headerKey="-1"
								headerValue="Selecione a zona" tooltip="Informe a Zona"
								list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
								listValue="zona +' - '+ municipio"
								name="codZonaMunic"  id="codZonaMunic" theme="simple"
								cssClass="form-control" />  
						 </s:else>	

					</div>

					<div class="col-md-6 mb-6">
						<label for="local">Local :</label>	
						
						 <s:if test='uservico.id != null'>
						     ${uservico.local}
						     <s:if test='uservico.oficial == 1'>
							      <span class="badge badge-pill badge-success">Oficial</span>
						 	 </s:if>
						 	 <s:if test='uservico.oficial == 0'>
							      <span class="badge badge-pill badge-danger">Homologação</span>
						 	 </s:if>
						 </s:if>
						 <s:else>
						      <select class="form-control" id="selectlocal" name="uservico.local"></select>
						 </s:else>	
						
					</div>
				
				</div><br>

				<div class="form-row">
					<label for="descricao">Descrição :</label> <input type="text"
						class="form-control" id="descricao" name="uservico.descricao"
						value="${uservico.descricao}" placeholder=" ">
				</div>
				<br>

				<div class="form-row">
					<label for="endereco">Endereço :</label> <input type="text"
						class="form-control" id="endereco" name="uservico.endereco"
						value="${uservico.endereco}" placeholder="">
				</div>
				<br>

				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="inputState">Sexo:</label>
						<select id="inputState" class="form-control" name="uservico.sexo">
						        <option value='N'>Selecione sexo</option>
						        <option value="M"<s:if test='uservico.sexo=="M"'> selected</s:if>>Masculino</option>
						        <option value="F"<s:if test='uservico.sexo=="F"'> selected</s:if>>Feminino</option>
						        <option value="O"<s:if test='uservico.sexo=="O"'> selected</s:if>>Outros</option>
     					 </select>						
					</div>

					<div class="col-md-3 mb-3">
						<label for="telefone">Telefone:</label> <input type="text"
							class="form-control" id="telefone" name="uservico.telefone"
							value="${uservico.telefone}" placeholder=" ">
					</div>

					<div class="col-md-3 mb-3">
						<label for="contato">Contato:</label> <input type="text"
							class="form-control" id="contato" name="uservico.contato"
							value="${uservico.contato}" placeholder=" ">
					</div>

					<div class="col-md-3 mb-3">
						<label for="cargoContato">Cargo Contato:</label> <input
							type="text" class="form-control" id="cargoContato"
							name="uservico.cargoContato" value="${uservico.cargoContato}"
							placeholder=" ">
					</div>

				</div>
				<br>

				<div class="form-row">

					<div class="col-md-6 mb-6">
						<label for="sala">Sala:</label> <input type="text"
							class="form-control" id="sala" name="uservico.sala"
							value="${uservico.sala}" placeholder=" ">
					</div>

					<div class="col-md-3 mb-3">
						<label for="latitude">Coordenadas Latitude:</label> <input
							type="text" class="form-control" id="latitude"
							name="uservico.latitude" value="${uservico.latitude}"
							placeholder=" ">
					</div>

					<div class="col-md-3 mb-3">
						<label for="longitude">Coordenadas Longitude:</label> <input
							type="text" class="form-control" id="longitude"
							name="uservico.longitude" value="${uservico.longitude}"
							placeholder=" ">
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
		         title: "Confirma?",
		         text: "Confirma " + URL + "?",
		         type: 'warning',
		         showCancelButton: true,
				  confirmButtonText: 'Salvar'
		         }).then((result) => {
					if (result.value) {
						var frm = $("#form1").serialize();
						// console.log(frm);
						$.getJSON({
							url: URL,
							data: frm
					    }).done(function( data ) {
					    	if(data.ret==1){
					    		if (! $('#id').length ) { 
					    			limparDados();
					    			CarregaLocalVotacao();
					    		}
					    		Swal.fire(URL, data.mensagem, "success");
					        }	
					    	else {
					    		Swal.fire(URL, data.mensagem, "error");
					    	}
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

	$('#codZonaMunic').change(function(event) {
		CarregaLocalVotacao();
	});
	
	$('#selectlocal').change(function(event) {
		getLocalVotacao();
	});

	function CarregaLocalVotacao() {
		var codZonaMunic = $("#codZonaMunic option:selected").val().split(';');
		var select = $('#selectlocal');
		select.find('option').remove();
	 	$.getJSON(
				'../elo/listarJsonLocalVotacao?zona=' + codZonaMunic[0] +'&codmunic=' + codZonaMunic[1], 
				function(jsonResponse) {
					$('<option>').val(-1).text("Informe o local").appendTo(
							select);
					$('<option>').val(0).text("Cadastro Manual").appendTo(
							select);
					$.each(jsonResponse, function(key, value) {
						$('<option>').val(value.numLocal).text(
								value.numLocal + " " + value.nomeLocal)
								.appendTo(select);						
					});
				}); 
	}
	
	function getLocalVotacao() {
		var codZonaMunic = $("#codZonaMunic option:selected").val().split(';');
		var numLocal = $("#selectlocal option:selected").val();
		//console.log(codZonaMunic);
	 	$.getJSON('../elo/getBeanJsonLocalVotacao?zona=' + codZonaMunic[0] 
				      +'&codmunic=' + codZonaMunic[1] + '&numLocal=' + numLocal, 
			function(dados) {
				$("#descricao").val(dados.nomeLocal);
				$("#endereco").val(dados.endereco);
				$("#latitude").val(dados.latitude);
				$("#longitude").val(dados.longitude);
				$("#codObjeto").val(dados.id);	
		 });	  
	}
	
	function limparDados() {
		$("#descricao").val("");
		 $("#endereco").val("");
		 $("#latitude").val("");
		$("#longitude").val("");
		$("#codObjeto").val("");
		$("#sala").val("");
		$("#contato").val("");
		$("#telefone").val("");
		$("#cargoContato").val("");
	}
	
	function verificaDados() {
		if ($("#form1")[0].checkValidity() === false) {
			$("#form1")[0].classList.add('was-validated');
			return false;
		} else
			return true;
	}
</script>

<jsp:include page="/mainfooter.inc.jsp" />








