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
			<td><s:property value="adm"/></td>			
			<td>
			<s:if test="ativo == 1">
				<input type="checkbox" id="ativar" data-record-id="${tituloEleitor}" class="form-check-input" id="exampleCheck1" checked>
			</s:if>
			<s:else>
				<input type="checkbox" id="ativar" data-record-id="${tituloEleitor}" class="form-check-input" id="exampleCheck1">
			</s:else>			
    		 <label class="form-check-label" for="exampleCheck1">Ativo</label>	  
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
		
		 // CLIQUE DO BOTAO SAVE
		 $("#btnSave").click(function() {
			var URL = ""; 
			if ( $('#id').length ) { URL = "atualizar"; }
			else{ URL = "adicionar";  }	
			if (verificaDados()){
				 swal({
			         title: "Confirma ?",
			         text: "Confirma " + URL + "?",
			         icon: 'warning',
			         buttons: [true, "Sim Incluir!"]
			         }).then((result) => {
						if (result) {
							var frm = $("#form1").serialize();
							// console.log(frm);
							$.getJSON({
								url: URL,
								data: frm
						    }).done(function( data ) {
						    	//if(data.ret==1)
						    	swal(URL, data.mensagem, data.type);
						    	//else 
						    	//	swal(URL, data.mensagem, "error");
							}).fail(function() {
								swal("Adicionar", "Ocorreu um erro ao incluir", "error");
							});
					      } 
				   }); // -- FIM SWAL --
			   }else{
				   swal("Dados", "Verifique os campos obrigatórios ", "error");
			   }
		 	}); // -- FIM btnSave --
		 
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
		 	
		 	
		 	
		 // BOTÃO EXCLUIR
		 $( "[id*='ativar']" ).click(function(event) {
			    var data = $(event.delegateTarget).data();
				var id = data.recordId; 
				var ativo = $("#ativar").val();
				if(ativo == "on"){
					ativo = 1;
				}else{
					ativo = 0;
				}
				swal({
					  title: 'Ativar?',
					  text: "Deseja ativar esse registro? (" + ativo + ")",
					  icon: 'warning',
					  buttons: [true, "Sim ativar!"]
					}).then((result) => {
					  if (result) { 
					       $.getJSON({
							  url: "atualizar?usuario.tituloEleitor="+id+"&ativo="+ativo
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