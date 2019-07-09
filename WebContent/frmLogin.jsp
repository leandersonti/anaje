<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container">
  
	
	 <form class="form-signin" action="${pageContext.request.contextPath}/login/process" method="post">
        <h2 class="form-signin-heading"><img src="${pageContext.request.contextPath}/images/brasao-peq2.png" /> �rea restrita</h2>
        <label for="inputEmail" class="sr-only">T�tulo</label>
       
        <input type="text" name="username" id="username" class="form-control" placeholder="T�tulo Eleitor" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name ="userpass" id="userpass" class="form-control" placeholder="Password" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
        <br>
         <s:actionerror />    
      </form>
  	
  </div> 

<jsp:include page = "/javascripts.jsp" />

<jsp:include page = "/mainfooter.inc.jsp" />