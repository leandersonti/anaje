<!-- MENU TOP -->
<%@page import="br.jus.tream.dominio.BeanLogin"%>
       
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <!--  <a class="navbar-brand" href="#">AnaJÉ</a> -->
      <a class="navbar-brand" href="#">
         <img class="topmnu" src="${pageContext.request.contextPath}/images/brasao2.gif" alt="AnaJé" width="60" height="60"> 
        </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/">AnaJé<span class="sr-only">(current)</span></a>
          </li>
          
          <li class="nav-item">
             <a class="nav-link" href="${pageContext.request.contextPath}/uservico/listar">Ponto Trans</a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/tecnico/listar">Técnico</a>
          </li>
          
          <li class="nav-item dropdown">
	            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Distribuição</a>
	            <div class="dropdown-menu" aria-labelledby="dropdown01">
	              <a class="dropdown-item" href="#">Seções</a>
	              <a class="dropdown-item" href="#">Equipamentos</a>
	              <a class="dropdown-item" href="#">Técnicos</a>
	              <a class="dropdown-item" href="#">Permissões</a>
	            </div>
	          </li>
	          
	          <li class="nav-item dropdown">
	            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Monitoramento</a>
	            <div class="dropdown-menu" aria-labelledby="dropdown01">
	              <a class="dropdown-item" href="#">PPO</a>
	              <a class="dropdown-item" href="#">Encerramento</a>
	              <a class="dropdown-item" href="#">Equipamentos</a>
	            </div>
	          </li>   

	         <li class="nav-item dropdown">
	            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Tabelas</a>
	            <div class="dropdown-menu" aria-labelledby="dropdown01">
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/eleicao/listar">Eleição</a>
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/contrato/listar">Contratos</a>
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/EquipamentoTipo/listar">Tipo Equipamentos</a>
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/tecnico/frmImportar">Importar Equipamentos</a>
	            </div>
	          </li>        
              
        </ul>
        <ul class="nav navbar-nav navbar-right">
             
             <li class="nav-item dropdown">
                <%
			     BeanLogin s = (BeanLogin)session.getAttribute("login");
				  try{
				      if (!s.getNome().equals("")){
			    %>
	            <a class="nav-link dropdown-toggle" href="http://example.com" id="sessionmnu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                <%=s.getTitulo() + " - " + s.getFirstName()%>
	             </a>
	            <div class="dropdown-menu" aria-labelledby="sessionmnu">
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/login/logout">Sair</a>
	            </div>
	            
	            <%
				   }
			     }catch (Exception e){
			    	 %>
			    	 <a class="nav-link dropdown-toggle" href="http://example.com" id="sessionmnu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	               				Sessão não iniciada
			             </a>
			            <div class="dropdown-menu" aria-labelledby="sessionmnu">
			              <a class="dropdown-item" href="${pageContext.request.contextPath}/login/frmLogin">Login</a>
			            </div> 
			    <%	 
			     }
				%>
	          </li>
		
				 
			</ul>
				
        <!--  
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form> -->
      </div>
    </nav>
    