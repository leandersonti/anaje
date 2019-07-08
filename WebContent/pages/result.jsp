<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>



<div class="container">
  <div class="container-fluid">   
  
		 
           <!-- MENSAGENS DE ERRO DO SISTEMA  -->
				<s:if test="hasActionErrors()">
				
				<div class="card bg-danger text-center">
				  <div class="card-header">Ocorreu um erro no Sistema </div>
					  <div class="card-body">
						    <h5 class="card-title">Veja as informações abaixo detalhadas</h5>
						    <p class="card-text">
						       <i class="glyphicon glyphicon-remove-sign"></i>
						       <s:iterator value="actionErrors">
								<s:property escapeHtml="false" />
									<br>
								</s:iterator>
						    </p>
					  </div>
				  </div>
				</s:if>
			<!-- FIM error MENSAGENS -->
			
			<!-- MENSAGENS  DO SISTEMA  -->
				<s:if test="hasActionMessages()">
				
					<div class="card bg-warning text-center">
					  <div class="card-header">Mensagem do Sistema </div>
						  <div class="card-body">
							    <h5 class="card-title">Veja as informações abaixo detalhadas pelo sistema</h5>
							    <p class="card-text">
							    	<i class="glyphicon glyphicon-ok"></i>
									<s:iterator value="actionMessages">
										<s:property escapeHtml="false" />
										<br>
									</s:iterator>
							    </p>
						  </div>
					  </div>
					  
				</s:if>
			<!-- FIM MENSAGENS -->
	
	  </div>
   </div>
	
    










	
	
		

 
