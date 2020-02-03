<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="pontotrans">active</s:set>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">
	<div class="card">
		<div class="card-header text-white bg-dark">Mudança de cargo</div>
		<div class="card-body">

			<form action="adicionar" method="post" name="form1" id="form1">

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="inputSolicitante">Contrato Atual</label>
						<select class="form-control" name="contrato.id" id="contrato" required>
					  			<option value="0">Informe o Contrato</option>
						</select>
					</div>
				</div> 
				
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="inputSolicitante">*Tecnico </label>
						<select class="form-control" name="tc.id.tecnico.id" id="tecnico" required>
					  			<option value="0">Informe o Técnico</option>
						</select>
						<div class="invalid-feedback">Informe o técnico</div>
					</div>
												
					<div class="col-md-6 mb-3">
					     <label for="inputSolicitante">*Novo contrato</label>		
						 <select class="form-control" name="tc.id.contrato.id" id="novocontrato" required>
					  			<option value="0">Informe o Cargo</option>
						</select>					
						<div class="invalid-feedback">Informe o cargo</div>	
					</div>
				</div>  
				<button class="btn btn-primary" id="btnMudar" type="button">Mudar</button>
			</form>
			
		</div>
	</div> 

</div>

<jsp:include page="/javascripts.jsp" />
<script type="text/javascript">
var URLSIS = "${pageContext.request.contextPath}";
$(document).ready(function() {	
	carregarContratos();
	$('#contrato').change(function(event) {	
		$('#tecnico').removeClass("is-invalid");
		$('#novocontrato').removeClass("is-invalid");
		carregarTecnicos();	     
		carregarNovoContrato();
	});	
	
	
  $("#btnMudar").click(function() {
 	if (verificaDados()){
 		 swal({
 	         title: "Confirma ?",
 	         text: "Confirma mudança de contrato?",
 	         icon: "warning",
 	         buttons: [true, "Sim"]
 		 }).then((result) => {
 			 if (result) {
 				var frm = $("#form1").serialize();
 				console.log(frm);
 				$.getJSON({
 					url: 'mudarContrato',
 					data: frm
 			    }).done(function( data ) {					    	
 			    	//if(data.ret==1){
 			    		//carregarPontoTransmissao();
 						swal("Mudança Contrato", data.mensagem, "success");
 						carregarTecnicos();	     
 						carregarNovoContrato();
 					//}else 
 						//swal("Mudança Contrato", data.mensagem, "error");
 				}).fail(function() {
 							swal("Mudança Contrato", "Ocorreu um erro ao incluir", "error");
 				});
 		     } 
 		   }); // -- FIM SWAL --
 	}else{
 		   swal("Dados", "Verifique os campos obrigatórios ", "error");
 	   }
   }); // -- FIM btnSave -- */
	 
});

 function verificaDados(){
	 var ret = true;
	 if ($("#tecnico").val()==0){
	  	  $("#tecnico")[0].classList.add('is-invalid');
	  	   ret = false;
	  }
	 if ($("#novocontrato").val()==0){
   	    $("#novocontrato")[0].classList.add('is-invalid');
   	    ret = false;
     }	
    return ret;
 }
 
 
//CARREGAR TODOS OS TECNICOS
 function carregarTecnicos(){
  var contrato = $('#contrato').val();
  var cbxpt = $('#tecnico');
      cbxpt.find('option').remove();
     $.getJSON('listarJsonByContrato?contrato.id=' + contrato,function(jsonResponse) {
   	   $('<option>').val(0).text("Informe o Técnico").appendTo(cbxpt);
           $.each(jsonResponse, function(key, value) {             
           	 $('<option>').val(value.id).text( (value.nome)).appendTo(cbxpt);
    	   });
      });
  }
//CARREGAR TODOS OS TECNICOS
 function carregarContratos(){
  var cbxpt = $('#contrato');
      cbxpt.find('option').remove();
     $.getJSON('../contrato/listarJson',function(jsonResponse) {
   	   $('<option>').val(0).text("Informe o Contrato").appendTo(cbxpt);
           $.each(jsonResponse, function(key, value) {             
           	 $('<option>').val(value.id).text( (value.descricao)).appendTo(cbxpt);
    	   });
      });
  }
  
//CARREGAR CARGOS
 function carregarNovoContrato(){
  var cbx = $('#novocontrato');
  	  cbx.find('option').remove();
      $.getJSON('../contrato/listarJson',function(jsonResponse) {
	   $('<option>').val(0).text("Informe o Contrato").appendTo(cbx);
        $.each(jsonResponse, function(key, value) {             
        	if ($('#contrato').val()!=value.id){
        		$('<option>').val(value.id).text( (value.descricao)).appendTo(cbx);
        	}
 	   });
   });
  }
 
  
  
</script>

<jsp:include page="/mainfooter.inc.jsp" />