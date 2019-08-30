<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />



<div class="container-fluid">
	<div class="card">
		<div class="card-header">Protocolo Padrão Obrigatório -
			Técnicos: 247 |<a href="consultaPormultiplicador.jsp"> Consulta por multiplicador </a></div>
		<div class="card-body">

			<table id="table1" class="table">
				<thead>
					<tr>
						<th width="10%">Zona</th>
						<th width="10%">Município</th>
						<th width="3%">Total TEC</th>
						<th width="3%">Total CC</th>
						<th width="3%">Total CP</th>
						<th width="3%">Total OS</th>
						<th width="3%">Total EN</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="">
						<tr id="tr${id}">
							<td><s:property value="" /></td>
							<td><s:property value="" /></td>
							<td><s:property value="" /></td>
							<td><s:property value="" /></td>
							<td><s:property value="" /></td>
							<td><s:property value="" /></td>
							<td><s:property value="" /></td>


						</tr>
					</s:iterator>
				</tbody>
			</table>


		</div>
	</div>
</div>



<jsp:include page="/javascripts.jsp" />

<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
		$('#table1').dataTable({
			"order" : [ [ 0, "des" ], [ 1, "des" ] ]
		});

	});
</script>

<jsp:include page="/mainfooter.inc.jsp" />