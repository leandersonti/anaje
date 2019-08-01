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
			<th width="5%">Zona</th>
			<th width="5%">Seção</th>			
			<th width="18%">Dt de Nasc.</th>
			
			<th width="18%">Telefone</th>
			<th width="18%">Celular</th>
			<th width="18%">Endereço</th>
			<th width="5%">N° Casa</th>
			<th width="18%">Bairro</th>
			
			<th width="18%">Cep</th>
			<th width="18%">Cidade</th>
			<th width="5%">Uf</th>
			<th width="1%">Sexo</th>
			<th width="18%">Email</th>
			
			<th width="18%">Rg</th>
			<th width="18%">Orgão Exp.</th>		
			<th width="18%">Cpf</th>
			<th width="18%">Data de Cadastro</th>
			
			<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
		    </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstTecnico">
		<tr id="tr${id}">
			<td><s:property value="nome"/></td>
			<td><s:property value="tituloEleitor"/></td>
			<td><s:property value="zona"/></td>
			<td><s:property value="secao"/></td>
			<td><s:property value="%{getText('format.date',{dataNasc})}"/></td>
			
			<td><s:property value="telefone"/></td>
			<td><s:property value="celular"/></td>
			<td><s:property value="endereco"/></td>
			<td><s:property value="numCasa"/></td>
			<td><s:property value="bairro"/></td>
			
			<td><s:property value="cep"/></td>
			<td><s:property value="cidade"/></td>
			<td><s:property value="uf"/></td>
			<td><s:property value="sexo"/></td>
			<td><s:property value="email"/></td>
			
			<td><s:property value="rg"/></td>
			<td><s:property value="orgaoRg"/></td>
			<td><s:property value="cpf"/></td>		
		    <td><s:property value="%{getText('format.date',{dataCad})}"/></td>		
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