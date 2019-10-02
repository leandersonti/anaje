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
   
    <table id="table1" class="table table-sm table-hover">
		<thead>
			<tr>
				<th width="5%">Zona</th>
				<th width="5%">Local</th>
				<th width="5%">Município</th>	
				<th width="12%">Descrição</th>			
				<th width="12%">Endereço</th>
				<th width="5%">Status</th>
				<th width="5%">Modo</th>
				<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button" title="Cadastrar novo ponto">
				         			<i class="fa fa-file-o" aria-hidden="true"></i>
								</a>
				</th>
			</tr>
		</thead>
	<tbody>
	<s:iterator value="lstPontoTransmissao">
		<tr id="tr${id.id}">
			<td><s:property value="zona"/></td>
			<td><s:property value="codLocal"/></td>
			<td><s:property value="codmunic"/></td>
			<td><a id="detalhePontoTrans${id.id}" href="#" data-record-id="${id.id}"><s:property value="descricao"/></a></td>
			<td><s:property value="endereco"/></td>
			<td><s:if test='status == 1'>
				      <i class="fa fa-check-circle-o" aria-hidden="true" title="Ponto Confirmado"></i>
				 </s:if>
				 <s:elseif test='status == 0'>
				      <i class="fa fa-circle-o" aria-hidden="true" title="Ponto Não Confirmado"></i>
				 </s:elseif>
			</td>
			<td>
				 <s:if test='oficial == 1'>
				      <span class="badge badge-pill badge-success">Oficial</span>
				 </s:if>
				 <s:elseif test='oficial == 0'>
				      <span class="badge badge-pill badge-danger">Homologa</span>
				 </s:elseif>
			</td>			 	 
			<td> 	  
				    <a href="frmEditar?id.id=${id.id}&id.eleicao.id=${id.eleicao.id}" id="idedit" class="btn btn-sm btn-warning" role="button">
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
				    </a>
					
					<a href="#" id="excluir${id.id}${id.eleicao.id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id.id}"  
					     data-record-ideleicao="${id.eleicao.id}" data-record-descricao="${descricao}" 
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


<div class="modal fade" id="modalPontoTrans" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">    
      <div class="modal-header">
        <h5 class="modal-title">Ponto de Transmissão</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
             <div id="result"></div>
             
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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
		swal({
			  title:'Excluir?',
			  text: "Deseja excluir esse registro? (" + descricao + ")",
			  icon: 'warning',
			  buttons: [true, "Sim excluir!"]
			}).then((result) => {
			  if (result) {
			      var vurl = "remover?pt.id.id="+id+'&pt.id.eleicao.id='+idDtElei; 
			      $.getJSON({
					  url: vurl
				  }).done(function( data ) {
				    	  if (data.ret==1){
				    		  $('#tr'+id).fadeOut(); 
				    		     swal("Remover", data.mensagem, "success");
				    	  }
				    	  else
				    		  swal("Remover", data.mensagem, "error");
					}).fail(function() {
						swal("Remover", "Ocorreu um erro ao remover", "error");
					});
			   }
			})
	  });
	
	$("#codZonaMunic").change(function(event) {
		console.log("textestsdfsdf");
		$("#form1").submit();
	});
	
	$( "[id*='detalhePontoTrans']" ).click(function(event) {
		var data = $(event.delegateTarget).data();	
		var id = data.recordId;
		var path = "${pageContext.request.contextPath}";	
		var URL = path+'/pontotrans/getBeanFull?id.id=' + id	;
		   $.ajax({
	           type: "POST",
	           url: URL,
	           success: function (result) {     
	           		$('#result').html(result);
               }
	       });
		   $('#modalPontoTrans').modal('show');
		});

});
</script>

<jsp:include page = "/mainfooter.inc.jsp" />