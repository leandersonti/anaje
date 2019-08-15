<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">

		<div class="card-header">
			<b>Distribuição de Técnico</b>
		</div>

		<div class="card-body">

			<form action="" method="post" name="form1" id="form1"
				class="needs-validation_" novalidate>

				<input type="hidden" id="tipo" name="action.id" value="1">
				<s:if test='action.id != null'>
					<input type="hidden" id="id" name="" value="">
				</s:if>

				<div class="form-group ">
					<label for="inputTipo">Zona:</label> <select
						class="form-control form-control" id="" name=" " required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<br>

				<div class="form-group ">
					<label for="inputTipo">Ponto Transmissão :</label> <select
						class="form-control form-control" id="" name=" " required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<br>

				<div class="form-group ">
					<label for="inputTipo">Contrato:</label> <select
						class="form-control form-control" id="" name=" " required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<br>

				<div class="form-group ">
					<label for="inputTipo">Técnico:</label> <select
						class="form-control form-control" id="" name=" " required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<br>

				<div class="form-group ">
					<label for="inputTipo">Técnico Responsável :</label> <select
						class="form-control form-control" id="" name=" " required>
						<option value="0">Selecione</option>
					</select>
				</div>
				<br>

				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>

			</form>
		</div>
	</div>
</div>

<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">
	
</script>

<jsp:include page="/mainfooter.inc.jsp" />








