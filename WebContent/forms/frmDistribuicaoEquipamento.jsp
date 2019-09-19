<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">

		<div class="card-header">
			<b>Distribuição de Equipamento</b>
		</div>

		<div class="card-body">

			<form action="" method="post" name="form1" id="form1"
				class="needs-validation_" novalidate>

				<input type="hidden" id="tipo" name="action.id" value="1">
				<s:if test='action.id != null'>
					<input type="hidden" id="id" name="" value="">
				</s:if>

				<div class="form-group ">
					<label for="inputTipo">Zona:</label>
					<s:select label="Zona" headerKey="-1"
						headerValue="Selecione a zona" tooltip="Informe a Zona"
						list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
						listValue="fzona +' - '+ municipio" name="ds.codZonaMunic"
						id="codZonaMunic" theme="simple" cssClass="form-control" />
				</div>
				<br>

				<div class="form-group ">
					<label for="inputTipo">Ponto Transmissão :</label> <select
						class="form-control form-control" id="us" name="us.id.id" required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<br>

				<div class="form-group ">
					<label for="inputTipo">Tipo:</label>
					<s:select label="Equipamento" headerKey="-1"
						headerValue="Selecione tipo equipamento"
						tooltip="Informe Equipamento" list="lstEquipamentoTipo"
						listKey="id" listValue="descricao" name="descricao" id="id"
						theme="simple" cssClass="form-control" />
				</div>
				<br>

				<div class="form-group ">
					<label for="inputTipo">Equipamento:</label> <select
						class="form-control form-control" id="idequipamento" name="equipamento.id" required>
						<option value="0">Selecione</option>
					</select>

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
	   $('#codZonaMunic').change(function(event) {	
		     $('#ajaxResponse').text('');
			  CarregaLocalVotacao();	  		  
			  CarregaPontoTransmissao();				     
		 });
		    
		 $('#numlocal').change(function(event) {	
			     $('#ajaxResponse').text('');
			  		  CarregaSecoes();	  		  
		 });
		 
		 $('#id').change(function(event) {	
		     $('#ajaxResponse').text('');
			  CarregaEquipamento();			     
		 });

		 
		 $("#btnSave").click(function() {
				var URL = ""; 
				if ( $('#id').length ) { URL = "atualizar"; }
				else{ URL = "adicionar";  }	
				if (verificaDados()){
					 Swal.fire({
				         title: "Distribuicao Secao?",
				         text: "Confirma essa distribuicao?",
				         type: 'warning',
				         showCancelButton: true,
						  confirmButtonText: 'Sim'
				         }).then((result) => {
							if (result.value) {
								var frm = $("#form1").serialize();
								$.getJSON({
									url: URL,
									data: frm
							    }).done(function( data ) {
							    	if(data.ret==1){
							    		CarregaSecoes();
							    		Swal.fire(URL, data.mensagem, "success");
							         }	
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
	    }else {
	    	var cbxCollection = document.getElementsByName('secoesCbx');
	    	if (cbxCollection.length==0 || $('#us option:selected').val()==-1 )
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

	function CarregaLocalVotacao(){
		var codZonaMunic = $("#codZonaMunic").val();
	    var select = $('#numlocal');	      
	        select.find('option').remove();
	    	if(codZonaMunic != -1){
			      $.getJSON('../elo/listarJsonLocalVotacao?codZonaMunic='+codZonaMunic,function(jsonResponse) {
			    	  $('<option>').val(-1).text("Informe o local").appendTo(select);
			                $.each(jsonResponse, function(key, value) {
			                $('<option>').val(value.numLocal).text(value.numLocal + " " + value.nomeLocal).appendTo(select);
			                // console.log("key " + key + " value " + value.descricao)
			        });
			      });
	   		 }else{
	   			 $('<option>').val(-1).text("Informe o local votacao").appendTo(select);   			 
	   		 }
	}

	function CarregaSecoes(){
		var codZonaMunic = $("#codZonaMunic").val();
		var codLocal = $("#numlocal").val();
		if(codZonaMunic != -1 || codZonaMunic != 0){	  				
		 $('#ajaxResponse').text('');
		 $('#ajaxResponse').append("<img src='../images/waiting.gif'> Carregando seções...</img>");
	     $.getJSON('listarParaDistribuirJson?codZonaMunic='+codZonaMunic+'&numlocal='+codLocal,function(jsonResponse) {
	  	  $('#ajaxResponse').text('');
	  	     $.each(jsonResponse, function(key, value) {
	  	       $('#ajaxResponse').append('<input type="checkbox" checked id="secoesCbx" name="secoesCbx" value="'+ value.id +';' + value.secao +'"/> ' + value.secao + " ");
	  	    }); 
		  });
		}
	}
	
	function CarregaEquipamento(){
		 var tipo = $("#id").val();
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








