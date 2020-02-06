<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="monitoramento">active</s:set>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container-fluid">
	<div class="card">

		<div class="card-header">
			<form action="listar" class="form-inline" name="frmConsultaPontoEncerrado" id="frmConsultaPontoEncerrado">	
	  		<strong>Encerramento &nbsp</strong>   
	  				<select class="form-control form-control" id="codZonaMunic" name="codZonaMunic">
							<option value="0">Selecione</option>
					</select>&nbsp
					<select	class="form-control form-control" id="estadoRecebimento" name="estadoRecebimento" required>
							<option value="is not null"<s:if test='estadoRecebimento == "is not null"'> selected</s:if>>Encerradas</option>
							<option value="is null"<s:if test='estadoRecebimento == "is null"'> selected</s:if>>Não Encerradas</option>
					</select>&nbsp
					Téc Responsável&nbsp
					<select	class="form-control form-control" id="idTecnicoResponsavel" name="tecResponsavel.id" required>
							<option value="9999">Todos</option>
					</select>&nbsp
										
					<button class="btn btn-sm btn-primary" id="btnConsultar" type="button"><i class="fa fa-search" aria-hidden="true"></i> 
					      Consultar
					</button>
					<!-- 
					<button class="btn btn-sm btn-primary" id="btnNaoEncerradas" type="button"> 
					      Não Encerradas
					</button>  -->
	         </form>
		</div>
		<div class="card-body">

			<table id="tbencerramento" class="table table-sm table-hover">
				<thead>
					<tr>
						<th width="5%">Zona</th>
						<th width="5%">CodMunic</th>
						<th width="25%">Ponto de Transmissão</th>
						<th width="25%">Nome</th>
						<th width="12%">DataCad</th>
						<th width="5%">Status</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="lstEncerramento">
						<tr>
							<td><s:property value="zona" /></td>
							<td><s:property value="codmunic" /></td>
							<td><a id="detalhePontoTrans${id.idus}" href="#" data-record-id="${id.idus}">
							           <s:property value="pontoTransmissao" />
							    </a>       
							</td>
							<td><s:property value="nome" /></td>
							<td><s:property value="dataCad" /></td>
							<td>
								 <s:if test='status == 0 || status == null'>
								      <i class="fa fa-circle-o" aria-hidden="true"></i>
								 </s:if>
								 <s:else>
								      <i class="fa fa-check-circle" style="color:green" aria-hidden="true" title="Ciente"></i>
								 </s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>

		</div>
	</div>
</div>
<jsp:include page="/javascripts.jsp" />
<jsp:include page = "/consultas/ponto-transmissao-detalhes.jsp" />
<script src="${pageContext.request.contextPath}/js/commonutils.js" charset="utf-8"></script>
<script type="text/javascript" language="javascript" class="init">
 var URLSIS = "${pageContext.request.contextPath}";	
 var idTecResponsavel = '${tecResponsavel.id}';
 var codZonaMunic = '${codZonaMunic}';
	$(document).ready(function() {
		 carregaZonaEleitoralCBX(codZonaMunic);
		 carregarTecnicoResponsavel(idTecResponsavel);		
		
		$('#table1').dataTable({
			"order" : [ [ 0, "des" ], [ 1, "des" ] ]
		});
		
		$("#btnConsultar").click(function() {
			if ($("#codZonaMunic").val()==0){
				swal("Ops!", "Informe a Zona Eleitoral", "error");
			}else{
				$("#frmConsultaPontoEncerrado").submit();
			}
			//$("#frmConsultaPontoEncerrado").attr('action', 'listarNaoEncerradas');
			// carregarNaoEncerradas();
		});
		
	});
	
 	
/*	
 function carregarNaoEncerradas() {
		var pkze = $('#codZonaMunic').val();
		$('#tbencerramento tbody').empty();
		$.getJSON('listarNaoEncerradasJson?codZonaMunic='+pkze, function(jsonResponse) {			
			$.each(jsonResponse, function(key, value) {
				$("#tbencerramento tbody").append(
				     '<tr><td>' + value.zona + '</td>'+
				         '<td>' + value.codmunic + '</td>'+
				         '<td><a onclick="showDetalhePontoTransmissao(this)" href="#" data-record-id="'+ value.id.idus +'">'+ value.pontoTransmissao + '</a></td>'+
				         '<td>'+ value.nome + '</td>'+
				         '<td>-<td>'+
				         '<td>-</td></tr>');
			});
		});
	} */
</script>

<jsp:include page="/mainfooter.inc.jsp" />