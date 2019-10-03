<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container">
     
<div class="card">
  <div class="card-header">Data Eleição</div>
  <div class="card-body">
  
    <table id="tbeleicao" class="table table-sm table-hover">
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
<script src="${pageContext.request.contextPath}/js/eleicao.js" charset="utf-8"></script>

<jsp:include page = "/mainfooter.inc.jsp" />