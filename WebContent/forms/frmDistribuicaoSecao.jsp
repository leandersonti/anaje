<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">

		<div class="card-header">
			<b>Distribuição de Seção</b>
		</div>

		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				
				 <div class="form-row">
					  <div class="form-group col-md-6">
					      <label for="codZonaMunic">Zona</label>
					      <s:select label="Zona" headerKey="-1"
								headerValue="Selecione a zona" tooltip="Informe a Zona"
								list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
								listValue="fzona +' - '+ municipio"
								name="ds.codZonaMunic"  id="codZonaMunic" theme="simple"  cssClass="form-control"/>
						   <div class="invalid-feedback">Por favor, informe a zona eleitoral.</div>		
					  </div>
					  <div class="form-group col-md-6">
					       <label for="us">Ponto Transmissão</label>
					        <select class="form-control form-control" id="us" name="us.id.id" required>
									<option value="0">Selecione</option>
							</select>
							<div class="invalid-feedback">Por favor, informe o ponto de transmissão.</div>
					  </div>
				   </div>
				   

				<div class="form-group ">
					<label for="inputTipo">Local Votação:</label> 
					 <select class="form-control form-control" id="numlocal" name="codObjetoLocal" required>
						<option value="0">Selecione</option>
					</select>
					<div class="invalid-feedback">Por favor, informe o local de votação.</div>
				</div>
				<div id="ajaxResponse"></div>
				<br>
				<button class="btn btn-primary" id="btnSave" type="button">Salvar</button>
			</form>
		</div>
	</div>
	
	 <table class="table table-sm" id="tbedist">
			<tr>
			  <td with="10%">Seções Distribuídas: </td>
			  <td></td>
			</tr>
	 </table>
			 
</div>

<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">
$(document).ready(function() {
   $('#codZonaMunic').change(function(event) {	
	      $('#result').empty();
	      $('#ajaxResponse').text('');
		  CarregaLocalVotacao();	  		  
		  CarregaPontoTransmissao();				     
	 });
	    
	 $('#numlocal').change(function(event) {	
		     $('#ajaxResponse').text('');
		    CarregaSecoes();	  		  
	 });
	
	 $('#us').change(function(event) {	
		 CarregaSecoesDistribuidas();
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
						    		CarregaSecoesDistribuidas();
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
		     $.getJSON('../pontotrans/listarJson?codZonaMunic='+codZonaMunic,function(jsonResponse) {
		   	  $('<option>').val(-1).text("Informe o ponto de transmissao").appendTo(cbxpt);
		             $.each(jsonResponse, function(key, value) {             
		            	 $('<option>').val(value.id.id).text( ("0000" + value.codLocal).slice(-4) + " " + value.descricao).appendTo(cbxpt);
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
		                $('<option>').val(value.id+';'+value.numLocal).text(value.numLocal + " " + value.nomeLocal).appendTo(select);
		                // console.log("key " + key + " value " + value.descricao)
		        });
		      });
   		 }else{
   			 $('<option>').val(-1).text("Informe o local votacao").appendTo(select);   			 
   		 }
}

function CarregaSecoes(){
	var codZonaMunic = $("#codZonaMunic").val();
	var codLocal = $("#numlocal").val().split(";");
	if(codZonaMunic != -1 || codZonaMunic != 0){	  				
	 $('#ajaxResponse').text('');
	 $('#ajaxResponse').append("<img src='../images/waiting.gif'> Carregando seções...</img>");
     $.getJSON('listarParaDistribuirJson?codZonaMunic='+codZonaMunic+'&numlocal='+codLocal[1],function(jsonResponse) {
  	  $('#ajaxResponse').text('');
  	     $.each(jsonResponse, function(key, value) {
  	       $('#ajaxResponse').append('<input type="checkbox" checked id="secoesCbx" name="secoesCbx" value="'+ value.id +';' + value.secao +'"/> ' + value.secao + " ");
  	    }); 
	  });
	}
}

function CarregaSecoesDistribuidas(){
	var cdUS = $("#us").val();
	$("#tbedist").hide();
	
	 $.getJSON('listarByPontoTransmissaoJson?us.id.id='+cdUS,function(jsonResponse) {
	    if (jsonResponse.length >=  1){ $("#tbedist").show(); }
	    $("#tbedist > tr").remove();
	     var linha = "";
        $.each(jsonResponse, function(key, value) {
        	console.log("key ==" + key);	
        	linha +=  '<tr class="table-active"><th scope="row">' + value.numLocal + '</th><td>' + value.nomeLocal + '</td></tr>';
        	var sec = "";
        	$.each(value.secoesDistribuidas, function(i, jSecoes) {
        		sec += jSecoes.secao  + ",";
          	 });
        	linha += '<tr><td></td><td>' + sec.substring(0, sec.length - 1) + '</td></tr>';
        	//console.log(cols);
        	//newRow.append(cols);
      	 });
        console.log(linha);
	    $("#tbedist").html(linha);
    });
}
</script>

<jsp:include page="/mainfooter.inc.jsp" />








