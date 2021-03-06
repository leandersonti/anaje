<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="pontotrans">active</s:set>
<jsp:include page = "/mainhead.inc.jsp" />

<div class="container-full"> 
 <div class="container-fluid">   
 <div class="card">
  <div class="card-header">  
      <form action="" class="form-inline" name="frmConsultaPonto" id="frmConsultaPonto">	
  		Pontos sem Tecnicos&nbsp  
  		
  		<select class="form-control form-control" id="ZonaMunic" name="codZonaMunic">
									<option value="0">Selecione</option>
				</select>&nbsp
				
	            <button class="btn btn-sm btn-primary" id="btnConsultar" type="submit">Consultar</button>
         </form>
                     
 </div>
  <div class="card-body">
   
    <table id="tabPontoTransmissao" class="table table-sm table-hover">
		<thead>
			<tr>
				<th width="5%">Zona</th>
				<th width="5%">Local</th>
				<th width="5%">Município</th>	
				<th width="12%">Descrição</th>			
				<th width="12%">Endereço</th>
				<th width="5%">Status</th>
				<th width="5%">Modo</th>
				<th width="15%"></th>
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


<jsp:include page = "/javascripts.jsp" />
<script>
 var codZonaMunic = '${codZonaMunic}';
 var select = $('#ZonaMunic');
 select.find('option').remove();
	$.getJSON('../elo/listarJsonZonaEleitoralCBX', 
		function(jsonResponse) {
			$('<option>').val(-1).text("Informe a Zona").appendTo(select);
			$('<option>').val('9999;9999').text("Listar todos").appendTo(select);
			$.each(jsonResponse, function(key, value) {				
				$('<option>').val(value.id.zona + ";" + value.id.codmunic).text(
					value.fzona + " - " + value.municipio)
						.appendTo(select);
			});
			$('#ZonaMunic  option[value="'+codZonaMunic+'"]').prop("selected", true);
		}); 
</script>
<script src="${pageContext.request.contextPath}/js/pontotransmissao.js" charset="utf-8"></script>

<jsp:include page = "/consultas/ponto-transmissao-detalhes.jsp" />

<jsp:include page = "/mainfooter.inc.jsp" />