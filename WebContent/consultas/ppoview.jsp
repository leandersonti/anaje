<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="monitoramento">active</s:set>
<jsp:include page = "/mainhead.inc.jsp" />

<div class="container-full"> 
 <div class="container-fluid">   
 <div class="card">
  <div class="card-header">  
      <form action="" class="form-inline" name="frmConsultaPonto" id="frmConsultaPonto">	
  		<strong>Protocolo Padrão Obrigatório &nbsp</strong>   
  		
  				<select class="form-control form-control" id="ZonaMunic" name="codZonaMunic">
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
   
    <table id="tabPontoTransmissao" class="table table-sm table-hover">
		<thead>
			<tr>
				<th width="5%">Zona</th>
				<th width="5%">CodMunic</th>
				<th width="25%">Tecnico</th>	
				<th width="25%">Ponto Transmissão</th>			
				<th width="5%">CC</th>
				<th width="5%">CP</th>
				<th width="5%">OS</th>
				<th width="5%">EN</th>
			</tr>
		</thead>
	<tbody id="tbody">
	<s:iterator value="lstVWPpo">
		<tr id="tr${id.idTecnico}">
			<td><s:property value="zona"/></td>
			<td><s:property value="codmunic"/></td>
			<td><s:property value="nome"/></td>
			<td>
				<s:if test='oficial == 0'>
				      <i class="fa fa-circle-o" aria-hidden="true" title="Fase Simulado"></i>
				 </s:if>
				 <s:else>
				 	<i class="fa fa-circle" style="color:green" aria-hidden="true" title="Fase Oficial"></i>
				 </s:else>
				 <a id="detalhePontoTrans${idus}" href="#" data-record-id="${idus}"><s:property value="pontoTransmissao"/></a></td>
			<td>
				<s:if test='chegouCartorio == null'>
				      <i class="fa fa-circle-o" aria-hidden="true"></i>
				 </s:if>
				 <s:else>
				      <i class="fa fa-check-circle" style="color:green" aria-hidden="true" title="${chegouCartorio}"></i>
				 </s:else>
			</td>
			<td>
				 <s:if test='chegouPonto == null'>
				      <i class="fa fa-circle-o" aria-hidden="true"></i>
				 </s:if>
				 <s:else>
				      <i class="fa fa-check-circle" style="color:green" aria-hidden="true" title="${chegouPonto}"></i>
				 </s:else>
			</td>
			<td>
				<s:if test='oficializouSistema == null'>
				      <i class="fa fa-circle-o" aria-hidden="true"></i>
				 </s:if>
				 <s:else>
				      <i class="fa fa-check-circle" style="color:green" aria-hidden="true" title="${oficializouSistema}"></i>
				 </s:else>
			</td>			 	 
			<td>
				 <s:if test='encerrou == null'>
				      <i class="fa fa-circle-o" aria-hidden="true"></i>
				 </s:if>
				 <s:else>
				      <i class="fa fa-check-circle" style="color:green" aria-hidden="true" title="${encerrou}"></i>
				 </s:else>
			</td>
		</tr>
		</s:iterator>
	 </tbody>	
	</table>	
	<br>
	<i class="fa fa-circle-o" aria-hidden="true" title="Fase Simulado"></i> Fase Simulado | <i class="fa fa-circle" style="color:green" aria-hidden="true" title="Fase Oficial"></i> Fase Oficial
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
	
function CarregaTecnicoResp(){	
  var select = $('#idTecnicoResponsavel');
  var idTecnicoResponsavel = '${idTecnicoResponsavel}';
  select.find('option').remove();
 	$.getJSON('../tecnico/listarJsonResponsavel',function(jsonResponse) {
    	  $('<option>').val(9999).text("Todos").appendTo(select);
               $.each(jsonResponse, function(key, value) {
               $('<option>').val(value.id).text(value.nome).appendTo(select);
        });
       $('#idTecnicoResponsavel  option[value="'+idTecnicoResponsavel+'"]').prop("selected", true);          
	});
}	

CarregaTecnicoResp();
</script>


<script src="${pageContext.request.contextPath}/js/pontotransmissao.js" charset="utf-8"></script>

<jsp:include page = "/consultas/ponto-transmissao-detalhes.jsp" />

<jsp:include page = "/mainfooter.inc.jsp" />