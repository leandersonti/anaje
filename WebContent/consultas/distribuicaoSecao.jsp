<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container-fluid">
	<div class="card">
		<div class="card-header">
		 <form action="" class="form-inline" name="frmConsultaEquipamento" id="frmConsultaEquipamento">	
			<b>Seções Distribuídos</b>  
			<s:select label="Zona" headerKey="-1" headerValue="Selecione a zona"
				tooltip="Informe a Zona" list="lstZonaEleitoral"
				listKey="id.zona+';'+id.codmunic"
				listValue="fzona +' - '+ municipio" name="codZonaMunic"
				id="codZonaMunic" theme="simple" cssClass="form-control" />
			- <select class="form-control form-control" id="pontoTransmissao" name="pontoTransmissao.id.id" >
				<option value="0">Informe Ponto Transmissão</option>
			</select> <a href="#" id="btnconsultar" class="btn btn btn-primary btn-sm" role="button">Consultar</a>
		 </form>
		</div>
		<div class="card-body">

			<div class="container-fluid">
			<table class="table table-sm table-hover" id="tb">
			  <thead>
			    <tr>
			       <th scope="col">Ponto de Transmissão</th>
			       <th scope="col">Zona</th>
   			       <th scope="col">Seção</th>
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
	$('#codZonaMunic').change(function(event) {
		var codZonaMunic = $("#codZonaMunic").val();
		var cbxpt = $('#pontoTransmissao');	
		cbxpt.find('option').remove();
	 	 if(codZonaMunic != -1){	    		 
			     $.getJSON('../pontotrans/listarJson?codZonaMunic='+codZonaMunic,function(jsonResponse) {
			   	        $('<option>').val(99999).text("Todos").appendTo(cbxpt);
			             $.each(jsonResponse, function(key, value) {             
			            	 $('<option>').val(value.id.id).text(value.codLocal + " " + value.descricao).appendTo(cbxpt);
			      		 });
			     });
		     }else{
		    	 $('<option>').val(-1).text("Informe o Ponto de Transmissao").appendTo(cbxpt);
	     }
	});
	
	$('#btnconsultar').click(function(event) {
		var codZonaMunic = $("#codZonaMunic").val();
		var param = ($("#pontoTransmissao").val() == "99999" ? "codZonaMunic=" + codZonaMunic : "pontoTransmissao.id.id=" + $("#pontoTransmissao").val()); 
		var url = "listarByPontoTransmissaoJson?"+param;
		console.log(url);
		if(codZonaMunic != -1){
			$("#tb > tbody:last").children().remove();
			$.getJSON(url, function(jsonResponse) {
				   $.each(jsonResponse, function(key, value) {     
			           	 $('#tb > tbody:last-child').append('<tr id="tr'+value.codObjetoLocal+'"><td>'+value.id.pontoTransmissao.descricao+'</td><td>'
			           			+ value.zona+'</td><td>'
			           			+ value.secao+'</td><td>');
			      	 });
			     });
		}else
		{
		   swal("Atenção", "Informe a Zona Eleitoral", "error");
		}
	});
	
});	
</script>

<jsp:include page="/mainfooter.inc.jsp" />