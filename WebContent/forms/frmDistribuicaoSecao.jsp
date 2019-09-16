<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">

		<div class="card-header">
			<b>Distribuição de Seção</b>
		</div>

		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				
				<div class="form-group ">
					<label for="inputTipo">Zona:</label> 
					 <s:select label="Zona" headerKey="-1"
								headerValue="Selecione a zona" tooltip="Informe a Zona"
								list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
								listValue="fzona +' - '+ municipio"
								name="codZonaMunic"  id="codZonaMunic" theme="simple"  cssClass="form-control" />  
				</div>

				<div class="form-group ">
					<label for="inputTipo">Ponto Transmissão :</label> 
					 <select class="form-control form-control" id="us" name="us" required>
						<option value="0">Selecione</option>
					</select>
				</div>

				<div class="form-group ">
					<label for="inputTipo">Local Votação:</label> 
					 <select class="form-control form-control" id="numlocal" name="numlocal" required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<div id="ajaxResponse"></div>
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
	
	});


function CarregaPontoTransmissao(){
	 var codZonaMunic = $("#codZonaMunic").val();
     var cbxpt = $('#us');	
         cbxpt.find('option').remove();
    	 if(codZonaMunic != -1){	    		 
		     $.getJSON('../uservico/listarJson?codZonaMunic='+codZonaMunic,function(jsonResponse) {
		   	  $('<option>').val(-1).text("Informe a unidade de servico").appendTo(cbxpt);
		             $.each(jsonResponse, function(key, value) {             
		            	 $('<option>').val(value.id).text(value.local + " " + value.descricao).appendTo(cbxpt);
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
  	       $('#ajaxResponse').append('<input type="checkbox" checked name="secoesChbx" value="'+ value.codObjetoSecao +'"/> ' + value.secao + " ");
  	    }); 
	  });
	}
}
</script>

<jsp:include page="/mainfooter.inc.jsp" />








