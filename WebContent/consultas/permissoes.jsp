<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />



<div class="container-fluid">   
<div class="card">
  <div class="card-header">Contrato</div>
  <div class="card-body">
  
    <table id="tbContratos" name="tbContratos" class="table table-sm table-hover">
	<thead>
		<tr>
			<th width="10%">Titulo de Eleitor</th>
			<th width="20%">Nome</th>
			<th width="5%">Zona</th> 		
			<th width="8%">ADM</th>
			<th width="8%">Ativo</th> 			
			<th width="16%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
		    </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstUsuarios">
		<tr id="tr${tituloEleitor}">
			<td><s:property value="tituloEleitor"/></td>
			<td><s:property value="nome"/></td>
			<td><s:property value="zona"/></td>	
			<td>	
				<a href="#" id="adm${tituloEleitor}" <s:if test="adm == 1">class="btn btn-sm btn-success"</s:if><s:else>class="btn btn-sm btn-danger"</s:else> role="button" data-record-id="${tituloEleitor}" 
					     data-record-data="<s:property value="tituloEleitor"/>"
					     data-record-descricao="${nome}"
					    data-record-adm="${adm}">
					  		<i <s:if test="adm == 1">class="fa fa-thumbs-o-up"</s:if><s:else>class="fa fa-thumbs-o-down"</s:else> aria-hidden="true"></i>
				    </a>																
			</td>			
			<td>
					<a href="#" id="ativar${tituloEleitor}" <s:if test="ativo == 1">class="btn btn-sm btn-success"</s:if><s:else>class="btn btn-sm btn-danger"</s:else> role="button" data-record-id="${tituloEleitor}" 
					     data-record-data="<s:property value="tituloEleitor"/>"
					     data-record-descricao="${nome}"
					     data-record-ativo="${ativo}">
					  		<i <s:if test="ativo == 1">class="fa fa-thumbs-o-up"</s:if><s:else>class="fa fa-thumbs-o-down"</s:else> aria-hidden="true"></i>
				    </a>						 
    		</td>
		   
			<td>  		 			 	
					<a href="#" id="excluir${tituloEleitor}" class="btn btn-sm btn-danger" role="button" data-record-id="${tituloEleitor}" 
					     data-record-data="<s:property value="tituloEleitor"/>"
					     data-record-descricao="${nome}">
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


<jsp:include page = "/javascripts.jsp" />
<script>

$(document).ready(function() {
	  // DATATABLE COMPONENT
		if($("#tbContratos").length){
		  $('#tbContratos').dataTable( {
		        "order": [[ 0, "des" ],[ 1, "des" ]]
		   });
	  }
		
		 // BOTÃO EXCLUIR
		 $( "[id*='excluir']" ).click(function(event) {
			    var data = $(event.delegateTarget).data();
				var id = data.recordId; 
				var descricao = data.recordDescricao;
				swal({
					  title: 'Excluir?',
					  text: "Deseja excluir esse registro? (" + descricao + ")",
					  icon: 'warning',
					  buttons: [true, "Sim excluir!"]
					}).then((result) => {
					  if (result) {
					       $.getJSON({
							  url: "remover?usuario.tituloEleitor="+id
						   }).done(function( data ) {
						    	  if (data.ret==1){
						    		  $('#tr'+id).fadeOut(); 
						    		  swal("Remover", data.mensagem, "success");
						    	  }
						    	  else
						    		  swal("Remover", "Ocorreu um erro ao remover", "error");
							}).fail(function() {
								swal("Remover", "Ocorreu um erro ao remover", "error");
							});
					   }
					})
			  });
		 	
		 	
		 	
		 // BOTÃO Ativar
		 $( "[id*='ativar']" ).click(function(event) {
			    var data = $(event.delegateTarget).data();
				var id = data.recordId; 
				var descricao = data.recordDescricao;
				var ativo = data.recordAtivo;				
				if(ativo == 1){
				  var title = 'Desativar';
				}else{
				  var title = 'Ativar';
				}
					
				swal({
					  title: title,
					  text: "Deseja ativar esse registro? (" + ativo + ")",
					  icon: 'warning',
					  buttons: [true, "Sim ativar!"]
					}).then((result) => {
					  if (result) { 
					       $.getJSON({
							  url: "ativarUser?usuario.tituloEleitor="+id
						   }).done(function( data ) {
						    	  if (data.ret==1){						    		
						    		  swal("Ativado", data.mensagem, "success");
						    	  }
						    	  else
						    		  swal("Ativado", "Ocorreu um erro ao remover", "error");
							}).fail(function() {
								swal("Remover", "Ocorreu um erro ao remover", "error");
							});
					   }
					})
			  });
		 
		 $( "[id*='adm']" ).click(function(event) {
			    var data = $(event.delegateTarget).data();
				var id = data.recordId; 	
				var descricao = data.recordDescricao;
				var adm = data.recordAdm;
				if(adm == 1){
					  var title = 'Tirar Adm';
					}else{
					  var title = 'Tornar Adm';
					}
				swal({
					  title: title,
					  text: "Deseja ativar esse registro? (" + adm + ")",
					  icon: 'warning',
					  buttons: [true, "Sim!"]
					}).then((result) => {
					  if (result) { 
					       $.getJSON({
							  url: "ativarAdm?usuario.tituloEleitor="+id
						   }).done(function( data ) {
						    	  if (data.ret==1){						    		
						    		  swal("Admin", data.mensagem, "success");
						    	  }
						    	  else
						    		  swal("Ativado", "Ocorreu um erro ao remover", "error");
							}).fail(function() {
								swal("Remover", "Ocorreu um erro ao remover", "error");
							});
					   }
					})
			  });
		 
	});

	 function verificaDados(){
	    if ($("#form1")[0].checkValidity()===false){
	    	$("#form1")[0].classList.add('was-validated');
	    	return false;
	    }else 
		   return true;
	 }

</script>

<jsp:include page = "/mainfooter.inc.jsp" />