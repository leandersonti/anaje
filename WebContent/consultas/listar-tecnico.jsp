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
				 <a href="#" id="modal${id}" role="button" data-record-id="${id}" data-record-nome="${nome}" data-record-data="<s:property value="%{getText('format.date',{dataNasc})}"/>"   data-record-datacad="<s:property value="%{getText('format.date',{dataCad})}"/>" >
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
  <div class="modal-dialog modal-lg mw-100 w-50">
    <div class="modal-content">
    
			    <div class="card">
			 		 <div class="card-header"> <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
			  <h5 id="nometecnico"></h5>
			  		
			  </div>
			  <div class="card-body">
			   	    <table id="tabcomp" class="table table-striped" width=100% >
						   <thead>						   	
						   		
								
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
	$("#corpotab").empty();
	$.getJSON('../tecnico/getBeanJson?id='+id,function(jsonResponse) {
		       		 $('#nometecnico').empty();
		             $('#nometecnico').append(jsonResponse.nome);
					 tr += '<tr><td><b> Endereço: </b> &nbsp; '+ jsonResponse.endereco +'</td>'					
					 tr += '<td> <b>N° Casa: </b>  &nbsp; '+ jsonResponse.numCasa +'</td></tr>'					 
					
					 tr += '<tr><td><b> Bairro: </b> &nbsp; '+ jsonResponse.bairro +'</td>'
					 tr += '<td> <b>Cep: </b>   &nbsp; '+ jsonResponse.cep +'</td></tr>'
					 
					 tr += '<tr><td> <b>Cidade: </b> &nbsp; '+ jsonResponse.cidade +'</td>'
					 tr += '<td> <b>Uf: </b> &nbsp; '+ jsonResponse.uf +'</td></tr>'
				
					 tr += '<tr><td> <b>Zona: </b>  &nbsp; '+ jsonResponse.zona +'</td>'
					 tr += '<td> <b>Seção: </b> &nbsp; '+ jsonResponse.secao +'</td></tr>'
					
					 tr += '<tr><td> <b>Rg: </b>  &nbsp; '+ jsonResponse.rg +'</td>'
					 tr += '<td> <b>Orgão Expedidor: </b> &nbsp; '+ jsonResponse.orgaoRg +'</td></tr>'
					 
					 tr += '<tr><td> <b>Cpf: </b>  &nbsp; '+ jsonResponse.cpf +'</td>'
					 tr += '<td> <b>Dt de Nasc : </b> &nbsp; '+  (jsonResponse.dataNasc==null?'-':dataNasc) +'</td></tr>'
					
					 tr += '<tr><td> <b>Sexo: </b>  &nbsp; '+ jsonResponse.sexo +'</td>'
					 tr += '<td> <b> E-mail: </b> &nbsp; '+  jsonResponse.email +'</td></tr>'
					
					 tr += '<tr><td> <b>Dt de Cadastro: </b>  &nbsp; '+  (jsonResponse.dataCad==null?'-':dataCad) +'</td></tr>'		

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