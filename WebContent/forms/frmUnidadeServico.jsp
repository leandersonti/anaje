<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">
			<b>Nova Unidade Serviço</b>
		</div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1"
				class="needs-validation_" novalidate>
				<s:if test='uservico.id != null'>
					<input type="hidden" id="id" name="uservico.id" value="${uservico.id}">
					<input type="hidden" id="id" name="uservico.cadZonaEleitoral.id" value="${uservico.cadZonaEleitoral.id}">
				</s:if>
				<div class="form-row">
					<div class="col-md-6 mb-6">
						<label for="zona">Zona:</label>
						<s:select label="Zona" headerKey="-1"
							headerValue="Selecione a zona" tooltip="Informe a Zona"
							list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
							listValue="zona +' - '+ municipio"
							name="cadZonaEleitoral.municipio"  id="codZonaMunic" theme="simple"
							cssClass="form-control" />

					</div>

					<div class="col-md-6 mb-6">
						<label for="local">Local :</label>		
						<select class="form-control" id="selectlocal"></select>
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
						        <option selected>Selecione sexo</option>
						        <option value="M">Masculino</option>
						        <option value="F">Feminino</option>
						        <option value="O">Outros</option>
     					 </select>						
					</div>

					<div class="col-md-3 mb-3">
						<label for="telefone">Telefone:</label> <input type="text"
							class="form-control" id="codmunic'" name="uservico.telefone"
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
			   Swal.fire("Dados", "Verifique os campos obrigatórios ", "error");
		   }
	 	}); // -- FIM btnSave --
	 
	});

	$('#codZonaMunic').change(function(event) {
		CarregaLocalVotacao();
	});

	function CarregaLocalVotacao() {

		var codZonaMunic = $("#codZonaMunic option:selected").val().split(';');
		var select = $('#selectlocal');
		select.find('option').remove();
		//console.log(codZonaMunic);
		//console.log("zona : "+codZonaMunic[0] +" codMunic: "+codZonaMunic[1]);
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
						// console.log("key " + key + " value " + value.descricao)
					});
				}); 
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








