<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="tecnico">active</s:set>
<jsp:include page = "/mainhead.inc.jsp" />

<div class="container-full"> 
 <div class="container-fluid">   
 
 <div class="card">
  <div class="card-header"><strong>Técnicos</strong></div>
  <div class="card-body">
   
    <table id="table1" class="table table-sm table-hover">
	<thead>
		<tr>
			<th width="18%">Nome</th>
			<th width="8%">Titulo</th>			
			<th width="10%">Telefone</th>
			<th width="10%">Celular</th>			
			<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button" title="Cadastrar novo tecnico"><i class="fa fa-file-o" aria-hidden="true"></i> Cadastrar</a></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstTecnico">
		<tr id="tr${id}">
			<td>
				 <a href="#" id="detalheTecnico${id}" role="button" data-record-id="${id}" data-record-nome="${nome}" 
				                   data-record-datacad="<s:property value="%{getText('format.date',{dataCad})}"/>" >
				  	<s:property value="nome"/>	
				 </a>
			</td>	
			<td><s:property value="tituloEleitor"/></td>	
			<td><s:property value="telefone"/></td>
			<td><s:property value="celular"/></td>			
			<td> 
				    <a href="frmEditar?tecnico.id=${id}" id="idedit" class="btn btn-sm btn-warning" role="button">
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
				    </a>					
					<a href="#" id="excluir${id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id}"  
					     data-record-nome="${nome}" 
					     data-record-data="<s:property value="%{getText('format.date',{dataCad})}"/>">
					  		<i class="fa fa-trash-o" aria-hidden="true"></i>
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


<jsp:include page = "/javascripts.jsp" />

<jsp:include page = "/consultas/tecnico-modaldialog.jsp" />

<script type="text/javascript" language="javascript" class="init">
$(document).ready(function() {
    $('#table1').dataTable( {
        "order": [[ 0, "asc" ]]
   });   
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