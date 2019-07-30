<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />



<div class="container-fluid">   
<div class="card">
  <div class="card-header">Contrato</div>
  <div class="card-body">
  
    <table id="table1" class="table">
	<thead>
		<tr>
		<th width="10%">Descrição</th>
			<th width="3%">Sigla</th>
			<th width="5%">Empresa</th> 		
			<th width="8%">Data Início</th>
			<th width="8%">Data Fim</th> 
			<th width="6%">Cargo</th>
			<th width="6%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
		    </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstContrato">
		<tr id="tr${id}">
			<td><s:property value="descricao"/></td>
			<td><s:property value="sigla"/></td>
			<td><s:property value="empresa"/></td>	
			<td><s:property value="%{getText('format.date',{dataInicio})}"/></td>
			<td><s:property value="%{getText('format.date',{dataFim})}"/></td>
			<td><s:property value="cargo.descricao"/></td>
		
			<td>  		    
				    <a href="frmEditar?contrato.id=${id}" id="idedit" class="btn btn-sm btn-warning" role="button">
							Editar
				    </a>
					
					<a href="#" id="excluir${id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id}" 
					     data-record-data="<s:property value="%{getText('format.date',{dataEleicao})}"/>"
					     data-record-descricao="${descricao}">
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
					  url: "remover?contrato.id="+id
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