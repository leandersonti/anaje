<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header">Cadastrar Contrato:</div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<s:if test='contrato.id != null'>
					<input type="hidden" id="id" name="contrato.id" value="${contrato.id}">
					<input type="hidden" id="id2" name="contrato.eleicao.id" value="${contrato.eleicao.id}">
				</s:if>
				
				<div class="form-row">
					<label for="descricao">*Descrição :</label> 
					<input type="text" class="form-control" id="descricao" name="contrato.descricao" placeholder=" " value="${contrato.descricao}" required>
					<div class="invalid-feedback">Por favor, informe uma descricao.</div>
				</div>
				
				<div class="form-row">
					
					<div class="col-md-6 mb-3">
						<label for="sigla">Sigla :</label> 
						<input type="text" class="form-control" id="sigla" name="contrato.sigla" placeholder="" value="${contrato.sigla}" required>
						<div class="invalid-feedback">Por favor, informe uma descricao.</div>
					</div>
					
					<div  class="col-md-6 mb-3">
						<label for="email">*Empresa :</label> 
						<input type="text" class="form-control" id="empresa" name="contrato.empresa"	placeholder=" " value="${contrato.empresa}" required>
						<div class="invalid-feedback">Por favor, informe a empresa.</div>
				    </div>
					
				</div>
				
			
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="dataInicio">*Data Início:</label> 
						<input type="date" class="form-control" name="contrato.dataInicio" id="dataInicio" value="<s:property value="%{getText('format.dtUSA',{contrato.dataInicio})}"/>" required>
						<div class="invalid-feedback">Por favor, informe a data de início.</div>
					</div>
					
					<div class="col-md-6 mb-3">
						<label for="dataFim">*Data Fim:</label> 
						<input type="date" class="form-control" name="contrato.dataFim" id="dataFim" value="<s:property value="%{getText('format.dtUSA',{contrato.dataFim})}"/>" required>
						<div class="invalid-feedback">Por favor, informe a data de fim.</div>
					</div>
				</div>
							
				<div class="form-row">
			      <label for="cargo">Cargo :</label>
			     	 <s:select label="Cargo" headerKey="-1" headerValue="Selecione o cargo" tooltip="Informe um Cargo "
						list="lstCargo" 
						listKey="id"
						listValue="descricao"
						name="contrato.cargo.id" theme="simple" cssClass="form-control"/>
			    </div>
				
				<br>
				<button class="btn btn-primary" id="btnSave" type="button">Enviar</button>
			</form>
		</div>
	</div>

</div>

<jsp:include page="/javascripts.jsp" />
<script src="${pageContext.request.contextPath}/js/contrato.js" charset="utf-8"></script>
	
<jsp:include page="/mainfooter.inc.jsp" />