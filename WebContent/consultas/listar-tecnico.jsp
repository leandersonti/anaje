<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container-full"> 
 <div class="container-fluid">   
 <div class="card">
  <div class="card-header"><strong><i>Técnicos</i></strong></div>
  <div class="card-body">
   
    <table id="table1" class="table table-hover">
	<thead>
		<tr>
			<th width="18%">Nome</th>
			<th width="8%">Titulo</th>			
			
			<th width="10%">Telefone</th>
			<th width="10%">Celular</th>			
			
			<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
		    </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstTecnico">
		<tr id="tr${id}">
			<td>
				 <a href="#" id="modal${id}" role="button" data-record-id="${id}"  data-record-nome="${nome}" 
					    data-record-data="<s:property value="%{getText('format.date',{dataNasc})}"/>"   data-record-datacad="<s:property value="%{getText('format.date',{dataCad})}"/>" >
					  	<s:property value="nome"/>	
				    </a>
			</td>	
			<td><s:property value="tituloEleitor"/></td>	
			<td><s:property value="telefone"/></td>
			<td><s:property value="celular"/></td>			
			<td> 
			 		    
				    <a href="frmEditar?tecnico.id=${id}" id="idedit" class="btn btn-sm btn-warning" role="button">
							Editar
				    </a>
					
					<a href="#" id="excluir${id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id}"  data-record-nome="${nome}" 
					     data-record-data="<s:property value="%{getText('format.date',{dataCad})}"/>">
					  		Remover
				    </a>
				   
			</td>
		</tr>
		</s:iterator>
	 </tbody>	
	</table>	
  </div>
</div>
</div>
</div>  

<!-- Large modal -->


<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="modalTecnico">
  <div class="modal-dialog modal-lg mw-100 w-75">
    <div class="modal-content">
    
			    <div class="card">
			  <div class="card-header">
			  Detalhes do Técnico
			  </div>
			  <div class="card-body">
			   	    <table id="tabcomp" class="table table-striped" width=100% >
						   <thead>
								<tr>
									<th >Endereço</th>
									<th  width="8%" align="center">N° Casa</th>
									<th align="center">Bairro</th>
									<th align="center">Cidade</th>
									<th align="center">Uf</th>									
									<th align="center">Zona</th>
									<th align="center">Seção</th>
									<th align="center">RG</th>
									<th align="center">Orgão Expedidor</th>
									<th align="center">Cpf</th>
									<th align="center">Dt de Nasc.</th>
									<th align="left">Sexo</th>
									<th align="right">E-mail</th>
									<th align="center">Dt de Cadastro</th>
								</tr>
							</thead>
						  <tbody id="corpotab">
						  </tbody>
					</table>
			   
			   
			  </div>
			</div>
  
    </div>
  </div>
</div>

<jsp:include page = "/javascripts.jsp" />

<script type="text/javascript" language="javascript" class="init">
$(document).ready(function() {
    $('#table1').dataTable( {
        "order": [[ 0, "des" ],[ 1, "des" ]]
   });   


});

$( "[id*='modal']" ).click(function(event) {
	
    var data = $(event.delegateTarget).data();
	var id = data.recordId; 
	var nome = data.recordNome;
	var dataNasc = data.recordData;
	var dataCad = data.recordDatacad;
	var tr = ''; 
	console.log("Cadastro == " +dataCad);
	$("#corpotab").empty();
	$.getJSON('../tecnico/getBeanJson?id='+id,function(jsonResponse) {		
			
					 tr += '<tr><td>' + jsonResponse.endereco + '</td>'
					 tr += '<td align="center">' +  jsonResponse.numCasa+ '</td>'
					 tr += '<td align="center">' +  jsonResponse.bairro + '</td>'
					 tr += '<td align="center">' +  jsonResponse.cidade + '</td>'
					 tr += '<td align="center">' +  jsonResponse.uf + '</td>'
					 tr += '<td align="center">' +  jsonResponse.zona + '</td>'
					 tr += '<td align="center">' +  jsonResponse.secao + '</td>'
					 tr += '<td align="center">' +  jsonResponse.rg + '</td>'
					 tr += '<td align="center">' +  jsonResponse.orgaoRg + '</td>'
					 tr += '<td align="center">' +  jsonResponse.cpf + '</td>'
					 tr += '<td align="center">' +  (jsonResponse.dataNasc==null?'-':dataNasc) + '</td>'
					 tr += '<td align="center">' +  jsonResponse.sexo + '</td>'
					 tr += '<td align="center">' +  jsonResponse.email + '</td>'
					 tr += '<td align="center">' +  (jsonResponse.dataCad==null?'-':dataCad) + '</td></tr>'
					
		
		 $('#tabcomp > tbody:last-child').append(tr);
	});
	$('#modalTecnico').modal('show');
	
  
  });
  
				
	$( "[id*='excluir']" ).click(function(event) {
	    var data = $(event.delegateTarget).data();
		var id = data.recordId; 
		var nome = data.recordNome;
		Swal.fire({
			  title: 'Excluir?',
			  text: "Deseja excluir esse Técnico? (" + nome + ")",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonText: 'Sim excluir!'
			}).then((result) => {
			  if (result.value) {
			    
			       $.getJSON({
					  url: "remover?tecnico.id="+id
				   }).done(function( data ) {
				    	  if (data.ret==1){
				    		  $('#tr'+id).fadeOut(); 
				    		     Swal.fire("Remover", data.mensagem, "success");
				    	  }
				    	  else
				    		  Swal.fire("Remover", "Ocorreu um erro ao remover", "error");
					}).fail(function() {
						Swal.fire("Remover", "Ocorreu um erro ao remover", "error");
					});
			   }
			})
	  });
		
	
</script>

<jsp:include page = "/mainfooter.inc.jsp" />