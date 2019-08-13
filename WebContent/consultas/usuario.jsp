<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container-full"> 
 <div class="container-fluid">   
 <div class="card">
  <div class="card-header"><strong><i>Usuários</i></strong></div>
  <div class="card-body">
   
    <table id="table1" class="table table-hover">
	<thead>
		<tr>
			<th width="18%">Nome</th>
			<th width="8%">Titulo</th>
			<th width="5%">Zona</th>
			<th width="5%">Ativo</th>			
			<th width="10%">Admin</th>		
			<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
		    </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstUsuario">
		<tr id="tr${tituloEleitor}">
			<td><s:property value="nome"/></td>
			<td><s:property value="tituloEleitor"/></td>
			<td><s:property value="zona"/></td>
			<td>
			<s:if test="ativo ==1">
				<img  src="${pageContext.request.contextPath}/images/check_ok.png" style="width: 20px;height: 20px;">
			</s:if>			
			<s:else>
			<img  src="${pageContext.request.contextPath}/images/check_error.png" style="width: 20px;height: 20px;">
			
			</s:else>			
			<td>
				<s:if test="admin ==1">
					<img  src="${pageContext.request.contextPath}/images/check_ok.png" style="width: 20px;height: 20px;">
			   </s:if>			
			<s:else>
				<img  src="${pageContext.request.contextPath}/images/check_error.png" style="width: 20px;height: 20px;">			
			</s:else>			
			
			
			</td>

			<td> 
			 		    
				    <a href="frmEditar?usuario.tituloEleitor=${tituloEleitor}" id="idedit" class="btn btn-sm btn-warning" role="button">
							Editar
				    </a>
					
					<a href="#" id="excluir${tituloEleitor}" class="btn btn-sm btn-danger" role="button" data-record-id="${tituloEleitor}"  data-record-nome="${nome}">
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
		var nome = data.recordNome;
		Swal.fire({
			  title: 'Excluir?',
			  text: "Deseja excluir esse Usuário? (" + nome + ")",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonText: 'Sim excluir!'
			}).then((result) => {
			  if (result.value) {
			    
			       $.getJSON({
					  url: "remover?usuario.tituloEleitor="+id
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