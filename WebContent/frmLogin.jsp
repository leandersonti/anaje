<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container">

  <main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
            
                <div class="card">
                    <div class="card-header"><img src="${pageContext.request.contextPath}/images/brasao-peq2.png" /> Para entrar informe os dados abaixo</div>
                    <div class="card-body">
                        <form  action="${pageContext.request.contextPath}/login/process" method="post">
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Título Eleitor</label>
                                <div class="col-md-6">
                                    <input type="text" id="username" class="form-control" name="username" required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="userpass" class="form-control" name="userpass" placeholder="senha" required>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Entrar
                                </button> <!-- 
	                                <a href="#" class="btn btn-link">
	                                    Esqueceu a senha?
	                                </a>  -->
                            </div>
                         </form>
                    </div>
                </div> <!-- FIM DO CARD -->
                
              </div>
           </div>
        </div>
      </main>
  </div>


<jsp:include page = "/javascripts.jsp" />

<jsp:include page = "/mainfooter.inc.jsp" />