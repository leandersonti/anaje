<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container-fluid">
	<div class="card">
		<div class="card-header">
			<b>Equipamentos Distribuídos :</b>
			<s:select label="Zona" headerKey="-1" headerValue="Selecione a zona"
				tooltip="Informe a Zona" list="lstZonaEleitoral"
				listKey="id.zona+';'+id.codmunic"
				listValue="fzona +' - '+ municipio" name="codZonaMunic"
				id="codZonaMunic" theme="simple" cssClass="col-md-2 mb-2" />
			- <select id="us" name="us.id.id">
				<option value="0">Informe Ponto Transmissão</option>
			</select> <a href="#" id="btnconsultar" class="btn btn btn-primary btn-sm" role="button">Listar Todos</a>

		</div>
		<div class="card-body">

			<div class="container-fluid">
			<table class="table table-hover" id="tb">
			  <thead>
			    <tr>
			       <th scope="col">Ponto de Transmissão</th>
			       <th scope="col">Tipo</th>
   			       <th scope="col">Serie</th>
			       <th scope="col">Menu</th>
			    </tr>
			  </thead>
			  
			  <tbody>
			   
			  </tbody>
	        </table>
	    </div>


		</div>
	</div>
</div>



<jsp:include page="/javascripts.jsp" />

<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
	$('#table1').dataTable({
		"order" : [ [ 0, "des" ], [ 1, "des" ] ]
	});

	$('#codZonaMunic').change(function(event) {
		CarregaPontoTransmissao();
	});
	//aaaa
	$('#btnconsultar').click(function(event) {
		 var codZonaMunic = $("#codZonaMunic").val();
	
		$.getJSON('listar?codZonaMunic='+codZonaMunic,
		function(jsonResponse) {
		//	$("#tb tr").remove(); //
			  $.each(jsonResponse, function(key, value) {             
		           	 console.log(jsonResponse);
		           	 $('#tb > tbody:last-child').append('<tr><td>'+value.id.unidadeServico.descricao+'</td><td>'
		           			+value.id.unidadeServico.tipo.descricao+'</td><td>'
		           			+value.id.equipamento.serie+'</td><td>');
		      	 });
		     });
	});
	//
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
	
	
	

});
</script>

<jsp:include page="/mainfooter.inc.jsp" />