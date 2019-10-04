<%@page import="br.jus.tream.dominio.BeanLogin"%>
		<div class="bg_load"></div>
		  <div class="wrapper">
		    <div class="inner">
		        <span>L</span>
		        <span>o</span>
		        <span>a</span>
		        <span>d</span>
		        <span>i</span>
		        <span>n</span>
		        <span>g</span>
		    </div>
		</div>
 
 
   <footer class="footer">
      <div class="container">
         <span class="text-light">Controle de Ponto de Transmissão
      	<%
	     BeanLogin s = (BeanLogin)session.getAttribute("login");
		  try{
			  if (!s.getNome().equals("")){
				if (s.getIsAmbienteProducao()){ %>
	                   | <span class="badge badge-pill badge-success">Banco Produção</span>
	                <%}else{%>
	                   | <span class="badge badge-danger">Banco Homologa</span>
	               <%}
				   }
			}catch (Exception e){ %>
			       <span class="badge badge-pill badge-secondary"><i class="fa fa-lock" aria-hidden="true"></i> Sessão não iniciada</span>
		    <%	 
			     }
			%>				
         </span>
        
      </div>
    </footer>
    
    
   </body>
</html>