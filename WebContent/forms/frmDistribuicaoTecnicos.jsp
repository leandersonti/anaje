<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">

		<div class="card-header">
			<b>Distribuição de Técnico</b>
		</div>

		<div class="card-body">

			<form action="" method="post" name="form1" id="form1"
				class="needs-validation_" novalidate>
				
				<div class="form-group ">
					<label for="inputTipo">Zona:</label> <s:select label="Zona" headerKey="-1"
								headerValue="Selecione a zona" tooltip="Informe a Zona"
								list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
								listValue="fzona +' - '+ municipio"
								name="ds.codZonaMunic"  id="codZonaMunic" theme="simple"  cssClass="form-control"/>
					<div class="invalid-feedback">Por favor, informe a zona eleitoral.</div>
				</div>				 

				<div class="form-group ">
					<label for="inputTipo">Ponto Transmissão :</label> <select
						class="form-control form-control" id="us" name="us.id.id" required>
						<option value="0">Selecione</option>
					</select>
					<div class="invalid-feedback">Por favor, informe o ponto de transmissão.</div>
				</div>
				<div class="form-group ">
					<label for="inputTipo">Contrato:</label> <select
						class="form-control form-control" id="contrato" name="contrato.id" required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<div class="form-group ">
					<label for="inputTipo">Técnico:</label> <select
						class="form-control form-control" id="tecnico" name=" " required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<div class="form-group ">
					<label for="inputTipo">Técnico Responsável :</label> <select
						class="form-control form-control" id="tecnicoresp" name=" " required>
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
	
	CarregaContrato();
	
	
	$('#codZonaMunic').change(function(event) {
		$('#ajaxResponse').text('');	  	 
		  CarregaPontoTransmissao();		 
	});
	
	$('#contrato').change(function(event) {
		$('#ajaxResponse').text('');	  	 
			CarregaTecnico();
			CarregaTecnicoResp();	 
	});
	

});

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
	
	
function CarregaContrato(){	
    var select = $('#contrato');	      
        select.find('option').remove();    	
		      $.getJSON('../contrato/listarJson',function(jsonResponse) {
		    	  $('<option>').val(-1).text("Informe o contrato").appendTo(select);
		                $.each(jsonResponse, function(key, value) {
		                $('<option>').val(value.id).text(value.descricao).appendTo(select);
		                // console.log("key " + key + " value " + value.descricao)
		        });
		      });   		 
}

function CarregaTecnico(){
	var contrato = $("#contrato").val();
    var select = $('#tecnico');	      
        select.find('option').remove();
    	if(contrato != -1){
		      $.getJSON('../tecnico/listarJsonByContrato?contrato.id='+contrato,function(jsonResponse) {
		    	  $('<option>').val(-1).text("Informe o tecnico").appendTo(select);
		                $.each(jsonResponse, function(key, value) {
		                $('<option>').val(value.id).text(value.nome).appendTo(select);
		                // console.log("key " + key + " value " + value.descricao)
		        });
		      });
   		 }else{
   			 $('<option>').val(-1).text("Informe o tecnico").appendTo(select);   			 
   		 }
}

function CarregaTecnicoResp(){	
	var contrato = $("#contrato").val();
    var select = $('#tecnicoresp');	      
        select.find('option').remove();
    	if(contrato != -1){
		      $.getJSON('../tecnico/listarJsonResponsavel',function(jsonResponse) {
		    	  $('<option>').val(-1).text("Informe o responsavel").appendTo(select);
		                $.each(jsonResponse, function(key, value) {
		                $('<option>').val(value.id).text(value.nome).appendTo(select);
		                // console.log("key " + key + " value " + value.descricao)
		        });
		      });
   		 }else{
   			 $('<option>').val(-1).text("Informe o responsavel").appendTo(select);   			 
   		 }
}
</script>

<jsp:include page="/mainfooter.inc.jsp" />








