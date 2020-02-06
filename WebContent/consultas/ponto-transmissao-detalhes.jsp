<div id="printThis">
	<div class="modal fade" id="modalPontoTrans" tabindex="-1" role="dialog" aria-hidden="true">
	  
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">    
	      <div class="modal-header">
	        <h5 class="modal-title">Ponto de Transmissão</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	             <div id="result"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" id="btnPrint">Imprimir</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	  
	</div>
</div>  
<script type="text/javascript" language="javascript" class="init">
$(document).ready(function() {
	$("#btnPrint").click(function(event) {
			printElement(document.getElementById("printThis"));		
    });

$( "[id*='detalhePontoTrans']" ).click(function(event) {
		var data = $(event.delegateTarget).data();	
		var id = data.recordId;
		var path = "${pageContext.request.contextPath}";	
		var URL = path+'/pontotrans/getBeanFull?id.id=' + id;
		   $.ajax({
	           type: "POST",
	           url: URL,
	           success: function (result) {     
	           		$('#result').html(result);
               }
	       });
	   $('#modalPontoTrans').modal('show');
	});
});

// função para tabelas criadas dinamicamente
// como usar:
// <a href="#" onclick="showDetalhePontoTransmissao(this)" id="btnfoto122" class="btn btn-sm btn-danger" role="button" data-record-id="122">1040 Escola Catuaba</a>
showDetalhePontoTransmissao = function(handler) {
    var id = handler.getAttribute("data-record-id");
    var path = "${pageContext.request.contextPath}";	
	var URL = path+'/pontotrans/getBeanFull?id.id=' + id;
    $.ajax({
        type: "POST",
        url: URL,
        success: function (result) {     
        	$('#result').html(result);
        }
    });
    $('#modalPontoTrans').modal('show');
  };
</script>