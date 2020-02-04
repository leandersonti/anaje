<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />



<div class="container-fluid">
	<div class="card">

		<div class="card-header">
		
			<form action="" class="form-inline" name="frmConsultaPonto" id="frmConsultaPonto">	
	  		<strong>Encerramento dos Pontos de Trasminssão &nbsp</strong>   
	  		
	  				<select class="form-control form-control" id="codZonaMunic" name="codZonaMunic">
							<option value="0">Selecione</option>
					</select>&nbsp
					Téc Responsável&nbsp
					<select	class="form-control form-control" id="idTecnicoResponsavel" name="idTecnicoResponsavel" required>
							<option value="9999">Todos</option>
					</select>&nbsp
										
					<button class="btn btn-sm btn-primary" id="btnConsultar" type="submit"><i class="fa fa-search" aria-hidden="true"></i> 
					      Consultar
					</button>
	
	         </form>
         
		</div>
		<div class="card-body">

			<table id="table1" class="table">
				<thead>
					<tr>
						<th width="5%">Zona</th>
						<th width="5%">CodMunic</th>
						<th width="25%">Ponto de Transmissão</th>
						<th width="25%">Nome</th>
						<th width="13%">Code</th>
						<th width="23%">DataCad</th>
						<th width="23%">Status</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="lstEncerramento">
						<tr>
							<td><s:property value="zona" /></td>
							<td><s:property value="codmunic" /></td>
							<td><s:property value="pontoTransmissao" /></td>
							<td><s:property value="nome" /></td>
							<td><s:property value="codigo" /></td>
							<td><s:property value="dataCad" /></td>
							<td><s:property value="status" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>


		</div>
	</div>
</div>

<jsp:include page="/javascripts.jsp" />
<script src="${pageContext.request.contextPath}/js/commonutils.js" charset="utf-8"></script>
<script type="text/javascript" language="javascript" class="init">
 var URLSIS = "${pageContext.request.contextPath}";	
 var idTecResponsavel = '${idTecnicoResponsavel}';
 var codZonaMunic = '${codZonaMunic}';
	$(document).ready(function() {
		 carregaZonaEleitoralCBX(codZonaMunic);
		 carregarTecnicoResponsavel(idTecResponsavel);		
		/*
		$('#table1').dataTable({
			"order" : [ [ 0, "des" ], [ 1, "des" ] ]
		});
		*/
	});
</script>

<jsp:include page="/mainfooter.inc.jsp" />