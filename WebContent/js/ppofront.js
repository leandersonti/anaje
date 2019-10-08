$(document).ready(function() {
	var loading = '<img src="images/waiting.gif" /> processando...';
	
	$( "[id*='checkincluir']" ).click(function(event) {
		
	    var data = $(event.delegateTarget).data();
		var id = data.recordId;  
		var descricao = data.recordDescricao;
		var titulo =  $('#tituloEleitor').val();
		   $('#result').html("");	
		    $("#result").hide();
			$('#stepprogress').html("");
			$("#stepprogress").hide();
				if (titulo == ""){
					$('#result').attr("class","alert alert-danger");
					$('#result').html("Informe por favor o título de eleitor");
					swal("Ops!", "Informe por favor o título de eleitor", "warning");
				} else	{
					
					swal({
						  title: 'Confirme',
						  text: "Deseja registrar o procedimento " + descricao +  "?",
						  icon: 'warning',
						  buttons: [true, "Registrar"]
						}).then((result) => {
						  if (result) {
							   $('#result').html(loading);
							   $("#result").show();
							   var vUrl = 'ppo/adicionar?tituloEleitor=' + titulo +"&ppo.ppoTipo.id="+id;
						       $.getJSON({
								  url: vUrl
							   }).done(function( data ) {
								     $('#result').attr("class","alert alert-"  + data.type);
									 $('#result').html(descricao + " - " + data.mensagem);
									 $("#result").show();
						    		 swal(descricao, data.mensagem, data.type);
								}).fail(function() {
									swal("Ops!", "Ocorreu um erro ao realizar esse procedimento", "error");
									 $('#result').attr("class","alert alert-danger");
					       		     $('#result').html("Tecnico nao encontrado!");
					       		   	 $("#result").show();
								});
						   }
					  })
				 
			  }
		   });
		   
   $("#consreg").click(function(event) {
		$("#consreg").attr("disabled", true);
		$("#stepprogress").hide();
	    var data = $(event.delegateTarget).data();
		var id = data.recordId;  
		var descricao = data.recordDescricao;
		var titulo =  $('#tituloEleitor').val();
				$("#result").empty();	
			    $('#result').html(loading);
				$("#result").show();
			if (titulo == ""){
					$('#result').attr("class","alert alert-danger");
					$('#result').html("Informe por favor o título de eleitor");
					$("#consreg").attr("disabled", false);
				} else {
				$("#constab").html("carregando...")	
				$.getJSON('ppo/listarJsonByTitulo?tituloEleitor=' + titulo,
		       	     function(jsonResponse) {       		       
		       		     var tr;
		       		     var stepprogress = '<ul class="progressbar">';
		       		     
		       			 $("#constab").html("<thead><th> Data </th><th> Desc </th><th> CodAuth </th></thead>");
			       		    	for (var i = 0; i < jsonResponse.length; i++) {
			       		    		var dtf = new Date(jsonResponse[i].dataCad);
				       	            tr = $('<tr/>');
				       	            stepprogress +='<li class="active">'+jsonResponse[i].ppoTipo.sigla+'</li>';
				       	            tr.append("<td>" + dtf.toLocaleString('pt-BR') + "</td>");
				       	            tr.append("<td>" + jsonResponse[i].ppoTipo.descricao + "</td>");	
				       	         	tr.append("<td>" + jsonResponse[i].codigo + "</td>");	
				       	            $("#constab").append(tr);	 	       	            
				       	        }
			       		    	for (var j = 1; j <= (4-i); j++) {
			       		    		stepprogress +='<li></li>';	
			       		    	} 	
		       			if(jsonResponse.length){      
		       				 $("#result").hide();
		       				 $("#constab").show();
		       				 stepprogress +='</ul>';
		       				$("#stepprogress").html(stepprogress);
		       				$("#stepprogress").show();
		       		       }else{
			       		    	$("#constab").hide();
			       		    	$("#stepprogress").hide();
			       		    	$('#result').attr("class","alert alert-info");
			       				$('#result').html("Não existe nenhum registro.");
			       				$("#result").show();  
			       				swal("Consulta", "Não existe nenhum registro", "warning");
		       		       }
		   		   }).done(function() {
		   			  $('#consreg').attr("disabled", false);
		   		   })
			 }
	   });
});