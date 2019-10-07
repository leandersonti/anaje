<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container-fluid">
	<div class="card">
		<div class="card-header">

			<form action="" method="post" name="form1" id="form1"
				class="needs-validation_ form-inline" novalidate>
														
						<s:select label="Zona" headerKey="-1"
							headerValue="Selecione a zona" tooltip="Informe a Zona"
							list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
							listValue="fzona +' - '+ municipio" name="codZonaMunic"
							id="codZonaMunic" theme="simple" cssClass="form-control" />
						<div class="invalid-feedback">Por favor, informe a zona
							eleitoral.</div>
					
					<select class="form-control form-control" id="contrato" name="contrato.id" required>
							<option value="-1">Selecione</option>
						</select>
						<div class="invalid-feedback">Por favor, informe o ponto de
							transmissão.</div>
				
					<button class="btn btn-primary" type="submit">Consultar</button>			
			</form>
		</div>
		<div class="card-body">
			<div class="container-fluid">
				<table class="table table-sm table-hover" id="tb">
					<thead>
						<tr>
							<th scope="col">Nome</th>
	 						<th scope="col">Celular</th>
							<th scope="col">NumLocal</th>
							<th scope="col">Ponto Transmissao</th>							
						</tr>
					</thead>

					<tbody>
				 <s:iterator value="lstDistribuicaoTecnico">				
					<tr>
						<td><s:property value="id.tecnico.nome" /></td>
						<td><s:property value="id.tecnico.celular" /></td>						
						<td><s:property value="id.pontoTransmissao.codLocal" /></td>
						<td><s:property value="id.pontoTransmissao.descricao" /></td>						
						<!--  <td><s:property value="dttotalizacao" /></td>-->																										
					</tr>									
				</s:iterator>	
					</tbody>
				</table>
			</div>


		</div>
	</div>
</div>



<jsp:include page="/javascripts.jsp" />

<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
		$('#table1').dataTable({
			"order" : [ [ 0, "des" ], [ 1, "des" ] ]
		});

		CarregaContrato();
		 
	});

	function CarregaContrato() {
		var select = $('#contrato');
		select.find('option').remove();
		$.getJSON('../contrato/listarJson', function(jsonResponse) {
			$('<option>').val(-1).text("Informe o contrato").appendTo(select);
			$.each(jsonResponse, function(key, value) {
				if(value.id == "${contrato.id}"){									
					$('<option selected>').val(value.id).text(value.descricao).appendTo(select);
				}else{
					$('<option>').val(value.id).text(value.descricao).appendTo(select);	
				}
				
				// console.log("key " + key + " value " + value.descricao)
			});
		});
	}
</script>

<jsp:include page="/mainfooter.inc.jsp" />