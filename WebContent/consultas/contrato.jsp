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
		<th width="20%">Descrição</th>
			<th width="3%">Sigla</th>
			<th width="5%">Empresa</th> 		
			<th width="8%">Data Início</th>
			<th width="8%">Data Fim</th> 
			<th width="36%">Cargo</th>
			<th width="16%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
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
							<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
				    </a>
					
					<a href="#" id="excluir${id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id}" 
					     data-record-data="<s:property value="%{getText('format.date',{dataEleicao})}"/>"
					     data-record-descricao="${descricao}">
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
<script src="${pageContext.request.contextPath}/js/contrato.js" charset="utf-8"></script>

<jsp:include page = "/mainfooter.inc.jsp" />