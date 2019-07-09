<!-- MENU TOP -->
<%@page import="br.jus.tream.dominio.BeanLogin"%>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <a class="navbar-brand" href="#">AnaJÉ</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/eleicao/listar">Eleicao <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#">Disabled</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
			   <%
					BeanLogin s = (BeanLogin)session.getAttribute("login");
				try{
				   if (!s.getNome().equals("")){
			    %>
			   <li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					 <i class="glyphicon glyphicon-user"></i>
							<%=s.getTitulo() + " - " + s.getFirstName()%> 
					    <b class="caret"></b></a>
					<ul class="dropdown-menu">
					  <li><a id="logout" href="${pageContext.request.contextPath}/login/logout"> Sair</a></li>
					</ul>
			   </li>
			   <%
				   }
			     }catch (Exception e){
							        // out.print("Sessão expirou! ");
									// response.sendRedirect(pg);		
				%>
			     <li>
                       <a href="#">Sessão não iniciada</a>
                 </li>
			     <%} %>
				 
				</ul>
				
        <!--  
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form> -->
      </div>
    </nav>
    