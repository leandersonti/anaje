<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />


<div class="container">
	

<form class="form">
<s:if test='eleicao.id != null'><input type="hidden" id="id" name="eleicao.id" value="${eleicao.id}"></s:if>
  <div class="form-row">
    <div class="col-md-6 mb-3">
      <label for="validationCustom01">Data Eleição</label>
      <input type="text" class="form-control" id="validationCustom01" placeholder="Nome" value="Mark" required>
      <div class="valid-feedback">
        Tudo certo!
      </div>
    </div>
    <div class="col-md-6 mb-3">
      <label for="validationCustom02">Turno</label>
      <input type="text" class="form-control" id="validationCustom02" placeholder="Sobrenome" value="Otto" required>
      <div class="valid-feedback">
        Tudo certo!
      </div>
    </div>
    
    
    
  </div>
  <div class="form-row">
    <div class="col-md-6 mb-3">
      <label for="validationCustom03">Cidade</label>
      <input type="text" class="form-control" id="validationCustom03" placeholder="Cidade" required>
      <div class="invalid-feedback">
        Por favor, informe uma cidade válida.
      </div>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">Estado</label>
      <input type="text" class="form-control" id="validationCustom04" placeholder="Estado" required>
      <div class="invalid-feedback">
        Por favor, informe um estado válido.
      </div>
    </div>
    <div class="col-md-3 mb-3">
      <label for="validationCustom05">CEP</label>
      <input type="text" class="form-control" id="validationCustom05" placeholder="CEP" required>
      <div class="invalid-feedback">
        Por favor, informe um CEP válido.
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="form-check">
      <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
      <label class="form-check-label" for="invalidCheck">
        Concordo com os termos e condições
      </label>
      <div class="invalid-feedback">
        Você deve concordar, antes de continuar.
      </div>
    </div>
  </div>
  <button class="btn btn-primary" type="submit">Enviar</button>
</form>



	<form class="well form-horizontal" action="incluir" method="post"  id="contact_form">
	
    
		<!-- Text input-->
		
		<div class="form-group row">
		    
		    <div class="col-auto">
		      <label for="dataEleicao" class="col-sm-2 col-form-label">Data Eleicao</label>
		      <input id="dataEleicao" name="eleicao.dataEleicao" placeholder="___/___/____" class="form-control"  type="text">
		    </div>
		    
		    <div class="col-auto">
		      <label for="dataEleicao" class="col-sm-2 col-form-label">Turno</label>
		      <input type="number" name="eleicao.turno" placeholder="Informe o turno" class="form-control">
		    </div>
    	</div>
    	
		  <label class="col-md-4 control-label">Data Eleição</label>  
		  <div class="col-md-4 inputGroupContainer">
		  <div class="input-group">
		  <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
		  <input id="dataEleicao" name="eleicao.dataEleicao" placeholder="___/___/____" class="form-control"  type="text">
		    </div>
		  </div>
		</div>
		
		<!-- Text input-->
		
		<div class="form-group">
		  <label class="col-md-4 control-label" >Turno</label> 
		    <div class="col-md-4 inputGroupContainer">
		    <div class="input-group">
		  <span class="input-group-addon"><i class="glyphicon glyphicon-sort"></i></span>
		  <input type="number" name="eleicao.turno" placeholder="Informe o turno" class="form-control">
		    </div>
		  </div>
		</div>
		
		<!-- Text input-->
		       <div class="form-group">
		  <label class="col-md-4 control-label">Descrição</label>  
		    <div class="col-md-4 inputGroupContainer">
		    <div class="input-group">
		        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
		  <input name="eleicao.descricao" placeholder="municipal ou geral" class="form-control"  type="text">
		    </div>
		  </div>
		</div>
		
		<!-- Text input-->
		     
		<div class="form-group">
		  <label class="col-md-4 control-label">TRE</label>  
		    <div class="col-md-4 inputGroupContainer">
		    <div class="input-group">
		        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
		  <input name="eleicao.titTRE" placeholder="sigla TRE" class="form-control" type="text">
		    </div>
		  </div>
		</div>
		
		<!-- radio checks -->
			 <div class="form-group">
			                        <label class="col-md-4 control-label">Ativar Eleição?</label>
			                        <div class="col-md-4">
			                            <div class="radio">
			                                <label>
			                                    <input type="radio" name="eleicao.ativo" value="1" /> Sim
			                                </label>
			                            </div>
			                            <div class="radio">
			                                <label>
			                                    <input type="radio" name="eleicao.ativo" value="0" /> Não
			                                </label>
			                            </div>
			                        </div>
			                    </div>
			
			<!-- Button -->
			<div class="form-group">
			  <label class="col-md-4 control-label"></label>
			  <div class="col-md-4">
			    <button type="submit" class="btn btn-success" >Gravar <span class="glyphicon glyphicon-send"></span></button>
			  </div>
			</div>
			
			
	</form>
	
		

				
	
</div>

<jsp:include page="/javascripts.jsp" />

<jsp:include page="/mainfooter.inc.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	
 $("#btnSave").click(function() {
 var URL = ""; 
	 if ( $('#cd').length ) { URL = "atualizar"; }
	 else{ URL = "adicionar";  }	 
	 swal({
         title: "Confirma ?",
         text: "Confirma " + URL + "?",
         icon: "info",
         buttons: true,
         dangerMode: true,
         })
         .then((resp) => {
				if (resp) {
					var frm = $("#form1").serialize();					
					$.getJSON({
							url: URL,
							data: frm
				    }).done(function( data ) {
				    	if(data.ret==1)
							 swal(URL, data.mensagem, "success");
				    	else 
				    		swal(URL, data.mensagem, "error");
					}).fail(function() {
								swal("Update", "An error occurred", "error");
					});

			    } 
			});
	 
  });
   
});


</script>	








