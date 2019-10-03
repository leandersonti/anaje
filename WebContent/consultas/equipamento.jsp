<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />

<div class="container-full"> 
 <div class="container-fluid">   
 <div class="card">
  <div class="card-header"><strong><i>Equipamentos</i></strong></div>
  <div class="card-body">
  
	<table id="table1" class="table table-sm table-hover">
			<thead>
				<tr>
					<th width="10%">Tipo</th>					
					<th width="10%">Série</th>
					<th width="10%">Tombo</th>
					
					<th width="10%">Fone</th>
					<th width="10%">Param</th>
					<th width="10%">Chaves</th>
					<th width="10%"> <a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a> &nbsp;<a href="${pageContext.request.contextPath}/equipamento/frmImportar" class="btn btn-sm btn-info" role="button">Importar</a> </th>
				</tr>
			</thead>
			<tbody>
			  <s:iterator value="lstEquipamento">			  
					<tr id="tr${id}" class="table table-hover">
			
			
						<td><s:property value="tipo.descricao"/></td>						
						<td><s:property value="serie"/></td>
						<td><s:property value="tomb"/></td>
						
						<td><s:property value="fone"/></td>
						
						<td><s:property value="param"/></td>
						
						<td><s:property value="chave"/></td>	
											
						<td>
							    
				    <a href="frmEditar?equipamento.id=${id}" id="idedit" class="btn btn-sm btn-warning" role="button">
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
				    </a>
					
					<a href="#" id="excluir${id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id}" data-record-descricao="${tipo.descricao}">
					  		<i class="fa fa-trash-o" aria-hidden="true"></i>
				    </a>
						 </td>
				
				</s:iterator>
			 </tbody>
		 </table>
	
    
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
				
	$( "[id*='excluir']" ).click(function(event) {
	    var data = $(event.delegateTarget).data();
		var id = data.recordId; 
		var descricao = data.recordDescricao;
		Swal.fire({
			  title: 'Excluir?',
			  text: "Deseja excluir esse registro? (" + descricao + ")",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonText: 'Sim excluir!'
			}).then((result) => {
			  if (result.value) {
			    
			       $.getJSON({
					  url: "remover?equipamento.id="+id
				   }).done(function( data ) {
				    	  if (data.ret==1){
				    		  $('#tr'+id).fadeOut(); 
				    		     Swal.fire("Remover", data.mensagem, "success");
				    	  }
				    	  else
				    		  Swal.fire("Remover",  data.mensagem, "error");
					}).fail(function() {
						Swal.fire("Remover", "Ocorreu um erro ao remover", "error");
					});
			   }
			})
	  });
		
</script>

<jsp:include page = "/mainfooter.inc.jsp" />