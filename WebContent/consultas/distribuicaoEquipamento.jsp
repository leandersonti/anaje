<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container-fluid">
	<div class="card">
		<div class="card-header">
		 <form action="" class="form-inline" name="frmConsultaEquipamento" id="frmConsultaEquipamento">	
			Equipamentos Distribuídos
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
		if(codZonaMunic != -1){
			$("#tb > tbody:last").children().remove();
			$.getJSON(url, function(jsonResponse) {
				   $.each(jsonResponse, function(key, value) {     					   
			           	 $('#tb > tbody:last-child').append('<tr ><td>'+value.id.pontoTransmissao.descricao+'</td><td>'
			           			+value.id.equipamento.tipo.descricao+'</td><td>'
			           			+value.id.equipamento.serie+'</td>'+
			           			'<td><button class="btn btn-sm btn-danger" onclick="removeRow(this)" data-idequip="'+value.id.equipamento.id+'" data-idponto="'+value.id.pontoTransmissao.id.id+'" type="button"><i class="fa fa-trash-o" aria-hidden="true"></i></button></td></tr>');
			      	 });
			     });
		}else
		{
		   swal("Atenção", "Informe a Zona Eleitoral", "error");
		}
	});
	
	  removeRow = function(handler) {
		    //var data = $(event.delegateTarget).data();		    
			var idEquip = $(handler).attr('data-idequip'); 
			var idPonto = $(handler).attr('data-idponto');			
			var tr = $(handler).closest('tr');			
			swal({
				  title:'Excluir?',
				  text: "Deseja excluir esse registro?",
				  icon: 'warning',
				  buttons: [true, "Sim excluir!"]
				}).then((result) => {
				  if (result) {
				      var vurl = "remover?de.id.equipamento.id="+idEquip+'&de.id.pontoTransmissao.id.id='+idPonto; 
				      $.getJSON({
						  url: vurl
					  }).done(function( data ) {
					    	  if (data.ret==1){
					    		  tr.fadeOut(400, function(){ 
					    		      tr.remove(); 
					    		    }); 
					    		  swal("Remover", data.mensagem, "success");
					    	  }
					    	  else{
					    		  swal("Remover", data.mensagem, "error");
					    	  }				    		  
						}).fail(function() {
							swal("Remover", "Ocorreu um erro ao remover", "error");
						});
				   }
				})		    		
		  };
	
});	
</script>

<jsp:include page="/mainfooter.inc.jsp" />