<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container">
     
<div class="card">
  <div class="card-header">Data Eleição</div>
  <div class="card-body">
  
    <table id="table1" class="table">
	<thead>
		<tr>
			<th width="8%">Data</th>
			<th width="28%">Descricao</th>
			<th width="10%">turno</th> 
			<th width="18%">Ativo</th>
			<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
		    </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstEleicao">
		<tr id="tr${id}">
		    <td><s:property value="%{getText('format.date',{dataEleicao})}"/></td>
			<td><s:property value="descricao"/></td>
			<td><s:property value="turno"/></td>
			<td>
					<s:if test='ativo == 1'>
							<span id="ele${id}" class="badge badge-pill badge-success" active="1">Ativo</span>
					</s:if>
					<s:else>
					 <a href="#" id="setcontext${id}" data-record-data="<s:property value="%{getText('format.date',{dataEleicao})}"/>" 
					      data-record-turno="${turno}" data-record-id="${id}">
					      <span id="ele${id}" class="badge badge-pill badge-secondary">Desativado</span>
					 </a>  
					</s:else>
			</td>
			<td>  		    
				    <a href="frmEditar?eleicao.id=${id}" id="idedit" class="btn btn-sm btn-warning" role="button">
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
					  url: "remover?eleicao.id="+id
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
		
$( "[id*='setcontext']" ).click(function(event) {
    var data = $(event.delegateTarget).data();
	var id = data.recordId;  
	var turno = data.recordTurno;
	var dt = data.recordData;
	Swal.fire({
		  title: 'Ativar Eleição?',
		  text: "Deseja tornar a eleição " + dt + " turno " + turno + " ativa?",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Ativar'
		}).then((result) => {
		  if (result.value) {
		    
		       $.getJSON({
				  url: "setcontexto?eleicao.id="+id
			   }).done(function( data ) {
			    	  if (data.ret==1){	    		  
			    		  $( "span:contains('Ativo')" ).attr('class', 'badge badge-pill badge-secondary');
			    		  $( "span:contains('Ativo')" ).text("Desativado");
			    		  $('#ele'+id).text("Ativo");
			    		  $('#ele'+id).attr('class', 'badge badge-pill badge-success');
			    		  // Swal.fire("Ativar Eleição", data.mensagem, "success");
			    	  }
			    	  else
			    		  Swal.fire("Ativar Eleição", data.mensagem, "error");
				}).fail(function() {
					Swal.fire("Ativar Eleição", "Ocorreu um erro ao realizar esse procedimento", "error");
				});
		   }
		})
   });		
</script>

<jsp:include page = "/mainfooter.inc.jsp" />