<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="distribuicao">active</s:set>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container">
	<div class="card">
		<div class="card-header"><b>Distribuição de Equipamento</b></div>
		<div class="card-body">
			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
			      <input type="hidden" id="id" name="de.tecnico.id" value="1">
				  <div class="form-row">
					  <div class="form-group col-md-6">
					      <label for="inputEmail4">Zona</label>
					      <s:select label="Zona" headerKey="-1"
							headerValue="Selecione a zona" tooltip="Informe a Zona"
							 list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
							  listValue="fzona +' - '+ municipio" name="codZonaMunic"
							    id="codZonaMunic" theme="simple" cssClass="form-control" />
					  </div>
					  <div class="form-group col-md-6">
					       <label for="inputPassword4">Ponto Transmissão</label>
					        <select class="form-control form-control" id="us" name="pontoTransmissao.id.id" required>
								<option value="0">Selecione</option>
							</select>
					  </div>
				   </div>
				
				<div class="form-row">
					  <div class="form-group col-md-6">
					      <label for="inputEmail4">Tipo</label>
					      <s:select label="Equipamento" headerKey="-1"
							headerValue="Selecione tipo equipamento"
								tooltip="Informe Equipamento" list="lstEquipamentoTipo"
									listKey="id" listValue="descricao" name="equipamento.tipo.id" id="tipoEquipamento"
										theme="simple" cssClass="form-control" />
					  </div>
					  <div class="form-group col-md-6">
					       <label for="inputPassword4">Equipamentos</label>
					        <select	class="form-control form-control" id="idequipamento" name="equipamento.id" required>
								<option value="0">Selecione</option>
							</select>
					  </div>
				   </div>
				   
				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>

			</form>
		</div>
	</div>
	<table class="table table-sm table-striped" id="tbequipdist" style="display:none;">
				  <thead>
					<tr>
					  <th scope="col">Tipo</th>
					  <th scope="col">Serie</th>
					  <th scope="col">Tombamento</th>
					</tr>
				  </thead>
				  <tbody> </tbody>
			 </table>
</div>

<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$( "#idequipamento").select2({
	    theme: "bootstrap4"
	});
	
	 $('#codZonaMunic').change(function(event) {	
		  CarregaPontoTransmissao();				     
	 });
		    
	 $('#tipoEquipamento').change(function(event) {	
		  CarregaEquipamento();			     
	 });
	 
	 $('#us').change(function(event) {	
		  CarregaEquipamentosDistribuidos();			     
     });

	 $("#btnSave").click(function() {
		if (verificaDados()){
			 swal({
		         title: "Distribuicao Equipamento?",
		         text: "Confirma essa distribuicao?",
		         icon: 'warning',
		         buttons: [true, "Salvar"]
			  }).then((result) => {
						if (result) {
							var frm = $("#form1").serialize();
							$.getJSON({
									url: 'adicionar',
									data: frm
							 }).done(function( data ) {
							   	if(data.ret==1){
							   		CarregaEquipamento();
							   		CarregaEquipamentosDistribuidos()
							   		swal('Distribuicao', data.mensagem, "success");
							     }	
							   	else 
							   		swal('Distribuicao', data.mensagem, "error");
							}).fail(function() {
									swal("Adicionar", "Ocorreu um erro ao incluir", "error");
							});
						  } 
					   }); // -- FIM SWAL --
		   }else{
			   swal("Dados", "Verifique os campos obrigatórios ", "error");
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
			     $.getJSON('../pontotrans/listarJson?codZonaMunic='+codZonaMunic,function(jsonResponse) {
			   	  $('<option>').val(-1).text("Informe o ponto de transmissao").appendTo(cbxpt);
			             $.each(jsonResponse, function(key, value) {             
			            	 $('<option>').val(value.id.id).text(("0000" + value.codLocal).slice(-4) + " " + value.descricao).appendTo(cbxpt);
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
	
	function CarregaEquipamentosDistribuidos(){
		var cdUS = $("#us").val();
		$("#tbequipdist").hide();
		 $.getJSON('listarByPontoTransmissaoJson?pontoTransmissao.id.id='+cdUS,function(jsonResponse) {
		    if (jsonResponse.length >=  1){ $("#tbequipdist").show(); }
			$("#tbequipdist > tbody:last").children().remove();
	        $.each(jsonResponse, function(key, value) {             
	           	 $("#tbequipdist > tbody:last-child").append('<tr><td>'+ value.id.equipamento.tipo.descricao 
	           			 +'</td><td>'+ value.id.equipamento.serie +'</td><td>'+ (value.id.equipamento.tomb ? value.id.equipamento.tomb : "-")  +'</td></tr>');
	      	 });
	    });
	}
</script>

<jsp:include page="/mainfooter.inc.jsp" />