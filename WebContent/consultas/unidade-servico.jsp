<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container-full"> 
 <div class="container-fluid">   
 <div class="card">
  <div class="card-header"><strong>Ponto de Transmissão</strong></div>
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
			
			<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
		    </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstUnidadeServico">
		<tr id="tr${id}">
			<td><s:property value="zona"/></td>
			<td><s:property value="local"/></td>
			<td><s:property value="codmunic"/></td>
			<td><s:property value="descricao"/></td>
			<td><s:property value="endereco"/></td>
			<td><s:property value="status"/></td>
		
			<td> 	    
				    <a href="frmEditar?uservico.id=${id}" id="idedit" class="btn btn-sm btn-warning" role="button">
							Editar
				    </a>
					
					<a href="#" id="excluir${id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id}"  data-record-descricao="${descricao}" 
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
	
</script>

<jsp:include page = "/mainfooter.inc.jsp" />