<div class="modal fade" id="modalTecnicoDetalhe" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">    
      <div class="modal-header">
        <h5 class="modal-title" id="nomeTecnico"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
             <div id="result"></div>
             <table id="tabcomp" class="table table-striped">
				<thead>						   	
						   		
								
				</thead>
				<tbody id="corpotab">
				
				</tbody>
			 </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" language="javascript">
$(document).ready(function() {
	$( "[id*='detalheTecnico']" ).click(function(event) {
		var data = $(event.delegateTarget).data();	
		var id = data.recordId;
		var nome = data.recordNome;
		$('#nomeTecnico').html(nome);
		var path = "${pageContext.request.contextPath}";	
		var URL = path+'/tecnico/getBeanJson?id=' + id	;
		var tr = "";
		$("#corpotab").empty();
		   $.ajax({
	           type: "POST",
	           url: URL,
	           success: function (result) {     
	           		//$('#result').html(result);
	           		 tr += '<tr><td><b> Fone: </b> &nbsp; '+ result.telefone +'</td>'
					 tr += '<td> <b>Celular: </b>   &nbsp; '+ result.celular +'</td></tr>'
	        	     tr += '<tr><td><b> Endereço: </b> &nbsp; '+ result.endereco +'</td>'					
					 tr += '<td> <b>N° Casa: </b>  &nbsp; '+ result.numCasa +'</td></tr>'					 
					 tr += '<tr><td><b> Bairro: </b> &nbsp; '+ result.bairro +'</td>'
					 tr += '<td> <b>Cep: </b>   &nbsp; '+ result.cep +'</td></tr>'
					 tr += '<tr><td> <b>Cidade: </b> &nbsp; '+ result.cidade +'</td>'
					 tr += '<td> <b>Uf: </b> &nbsp; '+ result.uf +'</td></tr>'
					 tr += '<tr><td> <b>Zona: </b>  &nbsp; '+ result.zona +'</td>'
					 tr += '<td> <b>Seção: </b> &nbsp; '+ result.secao +'</td></tr>'
					 tr += '<tr><td> <b>Rg: </b>  &nbsp; '+ result.rg +'</td>'
					 tr += '<td> <b>Orgão Expedidor: </b> &nbsp; '+ result.orgaoRg +'</td></tr>'
					 tr += '<tr><td> <b>Cpf: </b>  &nbsp; '+ result.cpf +'</td>'
					 tr += '<td> <b>Dt de Nasc : </b> &nbsp; '+  (result.dataNasc==null?'-':result.dataNasc) +'</td></tr>'
					 tr += '<tr><td> <b>Sexo: </b>  &nbsp; '+ result.sexo +'</td>'
					 tr += '<td> <b> E-mail: </b> &nbsp; '+  result.email +'</td></tr>';
					// tr += '<tr><td><b>Dt de Cadastro: </b>  &nbsp; '+  (result.dataCad==null?'-':result.dataCad) +'</td></tr>'		
		 		 $('#tabcomp > tbody:last-child').append(tr);
               }
	       });
		   $('#modalTecnicoDetalhe').modal('show');
	});
});
</script>