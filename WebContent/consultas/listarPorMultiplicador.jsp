<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container-fluid">
	<div class="card">
		<div class="card-header">
			Encerramento - Por Multiplicador <select class="col-md-2 mb-2" id="">
				<option>Sistema</option>
				<option>...</option>
				<option>...</option>
			</select>

		</div>
		<div class="card-body">

			<table id="table1" class="table">
				<thead>
					<tr>
						<th width="10%">ID</th>
						<th width="5%">Data</th>
						<th width="5%">Zona</th>
						<th width="5%">Ponto Transmissão</th>
						<th width="3%">Status</th>
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