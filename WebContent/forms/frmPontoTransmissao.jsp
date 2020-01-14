<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="pontotrans">active</s:set>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">

	<div class="card">
		<div class="card-header"><b>Ponto de Transmissão</b> <s:if test='pt.id != null'> - Editar</s:if><s:else> - Novo</s:else></div>
		<div class="card-body">

			<form action="" method="post" name="form1" id="form1" class="needs-validation_" novalidate>
				<s:if test='pt.id != null'>
					<input type="hidden" id="id" name="pt.id.id" value="${pt.id.id}">	
					<input type="hidden" id="oficial" name="pt.oficial" value="${pt.oficial}">				
					<input type="hidden" id="zona" name="pt.zona" value="${pt.zona}">
					<input type="hidden" id="local" name="pt.codLocal" value="${pt.codLocal}">
					<input type="hidden" id="codObjetoLocal" name="pt.codObjetoLocal" value="${pt.codObjetoLocal}">
					<input type="hidden" id="codmunic" name="pt.codmunic" value="${pt.codmunic}">
					<input type="hidden" id="status" name="pt.status" value="${pt.status}">
					<input type="hidden" id="dataeleicao" name="pt.id.eleicao.id" value="${pt.id.eleicao.id}">
				</s:if>
				<s:else>
				    <input type="hidden" id="oficial" name="pt.oficial" value="0">
				    <input type="hidden" id="status" name="pt.status" value="1">
				    <input type="hidden" id="codObjetoLocal" name="pt.codObjetoLocal" value="">
				</s:else>
				<div class="form-row">
					<div class="col-md-6 mb-6">
						<label for="zona">*Zona:</label>
						 <s:if test='pt.id != null'>
							${pt.zona}
						 </s:if>
						 <s:else>
						    <s:select label="Zona" headerKey="-1"
								headerValue="Selecione a zona" tooltip="Informe a Zona"
								list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
								listValue="fzona +' - '+ municipio"
								name="codZonaMunic"  id="codZonaMunic" theme="simple"
								cssClass="form-control" />  
						 </s:else>	

					</div>

					<div class="col-md-6 mb-6">
						<label for="local">*Local :</label>	
						 <s:if test='pt.id != null'>
						     ${pt.codLocal}
						     <s:if test='pt.oficial == 1'>
							      <span class="badge badge-pill badge-success">Oficial</span>
						 	 </s:if>
						 	 <s:if test='pt.oficial == 0'>
							      <span class="badge badge-pill badge-danger">Homologação</span>
						 	 </s:if>
						 </s:if>
						 <s:else>
						      <select class="form-control" id="codLocal" name="pt.codLocal"></select>
						 </s:else>	
					</div>
				</div>

				<div class="form-row">
					<label for="descricao">* Descrição :</label> <input type="text"
						class="form-control" id="descricao" name="pt.descricao"
						value="${pt.descricao}" placeholder="Nome do ponto ou da Escola">
				</div>

				<div class="form-row">
					<label for="endereco">Endereço:</label> <input type="text"
						class="form-control" id="endereco" name="pt.endereco"
						value="${pt.endereco}" placeholder="Endereço">
				</div>
				<br>

				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="inputState">Sexo:</label>
						<select id="inputState" class="form-control" name="pt.sexo">
						        <option value='N'>Selecione sexo</option>
						        <option value="M"<s:if test='pt.sexo=="M"'> selected</s:if>>Masculino</option>
						        <option value="F"<s:if test='pt.sexo=="F"'> selected</s:if>>Feminino</option>
						        <option value="O"<s:if test='pt.sexo=="O"'> selected</s:if>>Outros</option>
						        <option value="O"<s:if test='pt.sexo=="N"'> selected</s:if>>Não Informado</option>
     					 </select>						
					</div>

					<div class="col-md-3 mb-3">
						<label for="telefone">Telefone:</label> <input type="text"
							class="form-control" id="telefone" name="pt.fone"
							value="${pt.fone}" placeholder=" ">
					</div>

					<div class="col-md-3 mb-3">
						<label for="contato">Contato:</label> <input type="text"
							class="form-control" id="contato" name="pt.contato"
							value="${pt.contato}" placeholder=" ">
					</div>

					<div class="col-md-3 mb-3">
						<label for="cargoContato">Cargo Contato:</label> <input
							type="text" class="form-control" id="cargoContato"
							name="pt.cargoContato" value="${pt.cargoContato}"
							placeholder=" ">
					</div>
				</div>

				<div class="form-row">

					<div class="col-md-6 mb-6">
						<label for="sala">Sala:</label> <input type="text"
							class="form-control" id="sala" name="pt.sala"
							value="${pt.sala}" placeholder=" ">
					</div>

					<div class="col-md-3 mb-3">
						<label for="latitude">Coordenadas Latitude:</label> <input
							type="text" class="form-control" id="latitude"
							name="pt.latitude" value="${pt.latitude}"
							placeholder=" ">
					</div>

					<div class="col-md-3 mb-3">
						<label for="longitude">Coordenadas Longitude:</label> <input
							type="text" class="form-control" id="longitude"
							name="pt.longitude" value="${pt.longitude}"
							placeholder=" ">
					</div>

				</div>
				  <s:if test='pt.oficial == 1'>
					    <div class="alert alert-warning alert-dismissible fade show" role="alert">
						  <strong>Atenção!</strong> Não é possível alterar os dados! Ponto de transmissão em fase oficial.
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span>
						  </button>
						</div>
				  </s:if>
				  <s:else>
					      <button class="btn btn-primary" id="btnSave" type="button">Salvar</button>
				  </s:else>
			</form>
		</div>
	</div>

</div>

<jsp:include page="/javascripts.jsp" />
<script src="${pageContext.request.contextPath}/js/pontotransmissao.js" charset="utf-8"></script>

<jsp:include page="/mainfooter.inc.jsp" />