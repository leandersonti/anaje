<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:push value="pontoTransmissao">
         <label> Fase</label> 
			   <s:if test="%{oficial==1}">
			       <span class="label label-success" title="${unidadeServico.id.id}">Ofi - ${unidadeServico.id.id}</span>
			    </s:if>
			    <s:else>
			        <span class="label label-warning" title="${unidadeServico.id.id}">SM - ${unidadeServico.id.id}</span>
			    </s:else>
	 <br />
	 <label>ZE: ${unidadeServico.zona} Local:</label> ${unidadeServico.local} - ${unidadeServico.descricao}<br>
	 ${unidadeServico.endereco}
	 	<table class="table table-hover table-sm">
						<thead class="thead-default">
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
						<thead class="thead-default">
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
						
</s:push>				
					
