<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">
			<b>Distribuição de Equipamento</b>
		</div>

		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<input type="hidden" id="id" name="de.tecnico.id" value="1">
				<div class="form-group ">
					<label for="inputTipo">Zona:</label>
					<s:select label="Zona" headerKey="-1"
						headerValue="Selecione a zona" tooltip="Informe a Zona"
						list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
						listValue="fzona +' - '+ municipio" name="codZonaMunic"
						id="codZonaMunic" theme="simple" cssClass="form-control" />
				</div>
			
				<div class="form-group ">
					<label for="inputTipo">Ponto Transmissão :</label> <select
						class="form-control form-control" id="us" name="us.id.id" required>
						<option value="0">Selecione</option>
					</select>
				</div>
	
				<div class="form-group ">
					<label for="inputTipo">Tipo:</label>
					<s:select label="Equipamento" headerKey="-1"
						headerValue="Selecione tipo equipamento"
						tooltip="Informe Equipamento" list="lstEquipamentoTipo"
						listKey="id" listValue="descricao" name="equipamento.tipo.id" id="tipoEquipamento"
						theme="simple" cssClass="form-control" />
				</div>
	
				<div class="form-group ">
					<label for="inputTipo">Equipamento:</label> <select
						class="form-control form-control" id="idequipamento" name="equipamento.id" required>
						<option value="0">Selecione</option>
					</select>

				</div>

				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>

			</form>
		</div>
	</div>
</div>

<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">
$(document).ready(function() {
   $('#codZonaMunic').change(function(event) {	
		  CarregaPontoTransmissao();				     
	 });
		    
	 $('#tipoEquipamento').change(function(event) {	
			  CarregaEquipamento();			     
	 });

	 $("#btnSave").click(function() {
		if (verificaDados()){
			 Swal.fire({
		         title: "Distribuicao Equipamento?",
		         text: "Confirma essa distribuicao?",
		         type: 'warning',
		         showCancelButton: true,
			     confirmButtonText: 'Sim'
				   }).then((result) => {
						if (result.value) {
							var frm = $("#form1").serialize();
							$.getJSON({
									url: 'adicionar',
									data: frm
							 }).done(function( data ) {
							   	if(data.ret==1){
							   		CarregaEquipamento();
							   		Swal.fire('Distribuicao', data.mensagem, "success");
							     }	
							   	else 
							   		Swal.fire('Distribuicao', data.mensagem, "error");
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
	    }else {
	    	//var cbxCollection = document.getElementsByName('secoesCbx'); us
	    	if ($('#us option:selected').val()==-1 || $('#idequipamento option:selected').val()==-1 )
	    		return false;
	    	else
	    		return true;
	    }
	 }
	 
	function CarregaPontoTransmissao(){
		 var codZonaMunic = $("#codZonaMunic").val();
	     var cbxpt = $('#us');	
	         cbxpt.find('option').remove();
	    	 if(codZonaMunic != -1){	    		 
			     $.getJSON('../uservico/listarJson?codZonaMunic='+codZonaMunic,function(jsonResponse) {
			   	  $('<option>').val(-1).text("Informe o ponto de transmissao").appendTo(cbxpt);
			             $.each(jsonResponse, function(key, value) {             
			            	 $('<option>').val(value.id.id).text(value.local + " " + value.descricao).appendTo(cbxpt);
			      		 });
			     });
	     }else{
	    	 $('<option>').val(-1).text("Informe o Ponto de Transmissao").appendTo(cbxpt);
	     }
	}

	function CarregaEquipamento(){
		 var tipo = $("#tipoEquipamento").val();
	     var cbxpt = $('#idequipamento');	
	         cbxpt.find('option').remove();
	    	 if(tipo != -1){	    		 
			     $.getJSON('../equipamento/listarParaDistribuirJson?tipo='+tipo,function(jsonResponse) {
			   	  $('<option>').val(-1).text("Informe o ponto de transmissao").appendTo(cbxpt);
			             $.each(jsonResponse, function(key, value) {             
			            	 $('<option>').val(value.id).text(" " + value.serie).appendTo(cbxpt);
			      		 });
			     });
	     }else{
	    	 $('<option>').val(-1).text("Informe o Equipamento").appendTo(cbxpt);
	     }
	}
</script>

<jsp:include page="/mainfooter.inc.jsp" />