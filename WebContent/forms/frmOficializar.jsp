<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="pontotrans">active</s:set>
<jsp:include page="/mainhead.inc.jsp" />
<div class="container">
	<div class="card">
		<div class="card-header text-white bg-dark">
			<b>Oficializar Ponto de Transmissão</b>
		</div>
		<div class="card-body">

			<form action="adicionar" method="post" name="form1" id="form1">

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="inputSolicitante">Zona: </label>
						<s:select label="Zona" headerKey="-1"
							headerValue="Selecione a zona" tooltip="Informe a Zona"
							list="lstZonaEleitoral" listKey="id.zona+';'+id.codmunic"
							listValue="fzona +' - '+ municipio" name="codZonaMunic"
							id="codZonaMunic" theme="simple" cssClass="form-control" />
					</div>
												
					<div class="col-md-6 mb-3">
					     <label for="inputSolicitante">*Ponto Transmissão: </label>		
						 <select class="form-control" name="id.id" id="idus" required>
					  			<option value="0">Informe o Ponto</option>
						</select>					
						<div class="invalid-feedback">Informe o ponto de transmissão</div>	
					</div>
				</div>  
				
				<button class="btn btn-primary" id="btnSave" type="button">Oficializar</button>
			</form>
			
		</div>
	</div> 

</div>

<jsp:include page="/javascripts.jsp" />
<script type="text/javascript">
var URLSIS = "${pageContext.request.contextPath}";
$(document).ready(function() {	
	
	$('#codZonaMunic').change(function(event) {	
		//$('#codZonaMunic').removeClass("is-invalid");
		carregarPontoTransmissaoNaoOficializados();	     
	});	
	
	
  $("#btnSave").click(function() {
 	 var idus =  $("#idus").val(); 	 		
 	 var URL = "oficializar"; 	
 	if (verificaDados()){
 		 swal({
 	         title: "Confirma ?",
 	         text: "Confirma " + URL + "?",
 	         icon: "warning",
 	         buttons: [true, "Sim"]
 		 }).then((result) => {
 			 if (result) {
 				var frm = $("#form1").serialize();	
 				$.getJSON({
 					url: URL,
 					data: frm
 			    }).done(function( data ) {					    	
 			    	if(data.ret==1){
 			    		carregarPontoTransmissao();
 						swal(URL, data.mensagem, "success");
 					}else 
 						swal(URL, data.mensagem, "error");
 				}).fail(function() {
 							swal("Adicionar", "Ocorreu um erro ao incluir", "error");
 				});
 		     } 
 		   }); // -- FIM SWAL --
 	}else{
 		   swal("Dados", "Verifique os campos obrigatórios ", "error");
 	   }
   }); // -- FIM btnSave -- */
	 
});

 function verificaDados(){
    if ($("#idus").val()==0){
    	  $("#idus")[0].classList.add('is-invalid');
    	return false;
    }else {
   		$("#idus")[0].classList.remove('is-invalid');
	   return true;
    } 
 }
</script>
<script src="${pageContext.request.contextPath}/js/commonutils.js" charset="utf-8"></script>
<jsp:include page="/mainfooter.inc.jsp" />