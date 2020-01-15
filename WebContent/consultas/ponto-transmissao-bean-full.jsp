<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:push value="beanPontoTransmissao">         
	<div class="form-row">			
	<div class="col-md-4 mb-4">
			   <s:if test="%{pontoTransmissao.oficial==1}">
			       <span class="badge badge-pill badge-success" title="${pontoTransmissao.id.id}">Fase Ofi - ${pontoTransmissao.id.id}</span>
			    </s:if>
			    <s:else>
			        <span class="badge badge-pill badge-danger" title="${pontoTransmissao.id.id}">Fase SM - ${pontoTransmissao.id.id}</span>
			    </s:else>	<br>
			  ZE: ${pontoTransmissao.zona}	  		 				    			    			
		</div>
		<div class="col-md-8 mb-12">
		   <label>
		  	 Local: ${pontoTransmissao.codLocal} - ${pontoTransmissao.descricao}<br>		  
			 Endereço: ${pontoTransmissao.endereco}</label>
		</div>
		</div>		
	 	<table class="table table-hover table-sm">
						<thead class="thead-dark">
						      <tr>
							      <th>Num Local</th>						      
							      <th>Nome Local</th>								      						     
							   </tr>
						</thead>	
			 	<tbody>						
				<s:iterator value="secoesDistribuidas">				
					<tr>
						<td><s:property value="numLocal" /></td>
						<td><s:property value="nomeLocal" />						
							<br/>								
							<s:iterator value="secoesDistribuidas">																
								<s:if test="%{dttotalizacao == null}" >		
									[ ]									 
								</s:if>	
								<s:else>						
									[x]									
								</s:else>
									<s:property value="secao" />							
							</s:iterator>	
							</td>		<!--  <td><s:property value="dttotalizacao" /></td>-->																										
					</tr>									
				</s:iterator>											
				</tbody>
			</table>

			<table class="table table-hover table-sm">
						<thead class="thead-dark">
						      <tr>
							      <th>Equipamento</th>						      
							      <th>Serie</th>								      						     
							      <th>Tombamento</th>
							      <th></th>
							   </tr>
						</thead>	
				<tbody>						
				<s:iterator value="equipamentosDistribuidos">				
					<tr>
						<td><s:property value="id.equipamento.tipo.descricao" /></td>
						<td><s:property value="id.equipamento.serie" /></td>																										
						<td><s:property value="id.equipamento.tomb" /></td>
						<td></td>
					</tr>									
				</s:iterator>											
				</tbody>
			</table>
			
			<table class="table table-hover table-sm">
						<thead class="thead-dark">
						      <tr>
							      <th>Tecnico</th>						      
							      <th>Celular</th>								      						     
							      <th>Titulo</th>
							      <th></th>
							   </tr>
						</thead>	
				<tbody>						
				<s:iterator value="tecnicosDistribuidos">				
					<tr>
						<td><s:property value="id.tecnico.nome" /></td>
						<td><s:property value="id.tecnico.celular" /></td>																										
						<td><s:property value="id.tecnico.tituloEleitor" /></td>
						<td></td>
					</tr>									
				</s:iterator>											
				</tbody>
			</table>					
						
</s:push>				
					
