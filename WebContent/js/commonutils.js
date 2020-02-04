
// CARREGAR ZONA ELEIRORAL COMBOX
function carregaZonaEleitoralCBX(codZonaMunic){
	var select = $('#codZonaMunic');
	select.find('option').remove();
	$.getJSON(URLSIS + '/elo/listarJsonZonaEleitoralCBX', 
		function(jsonResponse) {
			$('<option>').val(0).text("Informe a Zona").appendTo(select);
			$('<option>').val('9999;9999').text(" Todas ").appendTo(select);
			$.each(jsonResponse, function(key, value) {				
				$('<option>').val(value.id.zona + ";" + value.id.codmunic).text(
					value.fzona + " - " + value.municipio)
					.appendTo(select);
			});
	}).done(function( data ) {
		$('#codZonaMunic  option[value="'+codZonaMunic+'"]').prop("selected", true);
	}); 	
}

//CARREGAR PONTO TRANSMISSAO
function carregarPontoTransmissao(){
	var codZonaMunic = $("#codZonaMunic").val();
    var cbxpt = $('#idus');
        cbxpt.find('option').remove();
   	 if(codZonaMunic != -1){	    		 
		     $.getJSON(URLSIS + '/pontotrans/listarJson?codZonaMunic='+codZonaMunic,function(jsonResponse) {
		   	  $('<option>').val(0).text("Informe o ponto de transmissao").appendTo(cbxpt);
		   	$('<option>').val(999999).text("Todos os pontos").appendTo(cbxpt);
		             $.each(jsonResponse, function(key, value) {             
		            	 $('<option>').val(value.id.id).text( ("0000" + value.codLocal).slice(-4) + " " + value.descricao).appendTo(cbxpt);
		      		 });
		     });
    }else{
   	 $('<option>').val(-1).text("Informe o Ponto de Transmissao").appendTo(cbxpt);
    }
}

//CARREGAR TECNICOS RESPONSÁVEIS
function carregarTecnicoResponsavel(idTecResponsavel){	
  var select = $('#idTecnicoResponsavel');
  select.find('option').remove();
	 	$.getJSON('../tecnico/listarJsonResponsavel',function(jsonResponse) {
	    	  $('<option>').val(9999).text("Todos").appendTo(select);
	               $.each(jsonResponse, function(key, value) {
	               $('<option>').val(value.id).text(value.nome).appendTo(select);
	        });
		}).done(function( data ) {
			$('#idTecnicoResponsavel  option[value="'+idTecResponsavel+'"]').prop("selected", true);
		});
}

//CARREGAR PONTOS NÃO OFICIALIZADOS
function carregarPontoTransmissaoNaoOficializados() {
	codZonaMunic = $("#codZonaMunic").val();
	$.getJSON(URLSIS + '/pontotrans/listarSemOficializar?codZonaMunic='+codZonaMunic, function(jsonResponse) {
		var cbxIUS = $('#idus');			
		cbxIUS.removeClass("is-invalid");
		cbxIUS.find('option').remove();
		$('<option>').val(0).text("Informe o ponto").appendTo(cbxIUS);
		if (jsonResponse.length>0){
			$('<option>').val(999999).text("Oficializar todos").appendTo(cbxIUS);
			$.each(jsonResponse, function(key, value) {		
				$('<option>').val(value.id.id).text(value.descricao).appendTo(cbxIUS);
			});
		} 		  		
	});		
	  	   
}

//CARREGAR LOCAIS DE VOTAÇÃO PARA CADASTRAR PONTOS TRANSMISSÃO
// não são listados os que já foram cadastrados
function carregaLocalVotacaoParaCadastrarPonto() {
	var codZonaMunic = $("#codZonaMunic option:selected").val();
	var select = $('#codLocal');
	select.find('option').remove();
 	$.getJSON(URLSIS + '/elo/listarJsonLocalVotacaoParaCadastrar?codZonaMunic=' + codZonaMunic, 
			function(jsonResponse) {
				$('<option>').val(-1).text("Informe o local").appendTo(
						select);
				$('<option>').val(0).text("Cadastro Manual").appendTo(
						select);
				$.each(jsonResponse, function(key, value) {
					$('<option>').val(value.numLocal).text(
							value.numLocal + " " + value.nomeLocal)
							.appendTo(select);						
				});
			}); 
}