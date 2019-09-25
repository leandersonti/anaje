<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container-full"> 
 <div class="container-fluid">   
 <div class="card">
  <div class="card-header">  
      <form action="" class="form-inline" name="form1" id="form1">	
  		Ponto de Transmissão  Zona    
	          <s:select label="Zona" headerKey="-1"
								headerValue="Selecione a zona" tooltip="Informe a Zona"
								list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
								listValue="fzona +' - '+ municipio"
								name="codZonaMunic"  id="codZonaMunic" theme="simple"  cssClass="form-control"/>
         </form>
                     
 </div>
  <div class="card-body">
   
    <table id="table1" class="table table-hover">
		<thead>
			<tr>
				<th width="5%">Zona</th>
				<th width="5%">Local</th>
				<th width="5%">Município</th>	
				<th width="12%">Descrição</th>			
				<th width="12%">Endereço</th>
				<th width="5%">Status</th>
				<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a></th>
			</tr>
		</thead>
	<tbody>
	<s:iterator value="lstUnidadeServico">
		<tr id="tr${id.id}">
			<td><s:property value="zona"/></td>
			<td><s:property value="local"/></td>
			<td><s:property value="codmunic"/></td>
			<td><s:property value="descricao"/></td>
			<td><s:property value="endereco"/></td>
			<td><s:property value="status"/></td>
		
			<td> 	    
				    <a href="frmEditar?id.id=${id.id}&id.dataEleicao.id=${id.dataEleicao.id}" id="idedit" class="btn btn-sm btn-warning" role="button">
							Editar
				    </a>
					
					<a href="#" id="excluir${id.id}${id.dataEleicao.id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id.id}"  
					     data-record-ideleicao="${id.dataEleicao.id}" data-record-descricao="${descricao}" 
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
	$( "[id*='excluir']" ).click(function(event) {
	    var data = $(event.delegateTarget).data();
		var id = data.recordId; 
		var idDtElei = data.recordIdeleicao;
		var descricao = data.recordDescricao;
		Swal.fire({
			  title: 'Excluir?',
			  text: "Deseja excluir esse registro? (" + descricao + ")",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonText: 'Sim excluir!'
			}).then((result) => {
			  if (result.value) {
			      var vurl = "remover?uservico.id.id="+id+'&uservico.id.dataEleicao.id='+idDtElei; 
			      $.getJSON({
					  url: vurl
				  }).done(function( data ) {
				    	  if (data.ret==1){
				    		  $('#tr'+id).fadeOut(); 
				    		     Swal.fire("Remover", data.mensagem, "success");
				    	  }
				    	  else
				    		  Swal.fire("Remover", data.mensagem, "error");
					}).fail(function() {
						Swal.fire("Remover", "Ocorreu um erro ao remover", "error");
					});
			   }
			})
	  });
	
	$("#codZonaMunic").change(function(event) {
		console.log("textestsdfsdf");
		$("#form1").submit();
	});
	

});
</script>

<jsp:include page = "/mainfooter.inc.jsp" />