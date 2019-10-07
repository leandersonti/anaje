$(document).ready(function() {
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
				} else	{
				    if (confirm("Confirma check Point: " + descricao + "?")){
				    	   var url = 'ppo/adicionar?tituloEleitor=' + titulo +"&ppo.ppoTipo.id="+id;
							$.getJSON(url,
				       	     function(jsonResponse) {
				       		       var ret = jsonResponse.ret;
				       		       var msg = jsonResponse.mensagem;
				       		       var vClass;
				       		       
				       		    switch (ret) {
				       		    case 1:
				       		    	vClass = "alert alert-success";
				       		        break; 
				       		    case 2:
				       		    	vClass = "alert alert-danger";
				       		        break; 
				       			case 5:
				       				vClass = "alert alert-warning";
				    		        break;
				       			case 9:
				       				vClass = "alert alert-info";
				    		        break;
				       		    default: 
				       		    	vClass = "alert alert-danger";
				       		 }       		              				        		
				       		     $('#result').attr("class",vClass);
				       		     $('#result').html(descricao + " - " + msg);
				       		   	 $("#result").show(); 
				   		     }).fail(function() {
				   		    	 $('#result').attr("class","alert alert-danger");
				       		     $('#result').html("Servidor não encontrado");
				       		   	 $("#result").show(); 
				   		     })
		          }
			  }
		   });
		   
		$("#consreg").click(function(event) {
			$("#consreg").attr("disabled", true);
			$("#stepprogress").hide();
		    var data = $(event.delegateTarget).data();
			var id = data.recordId;  
			var descricao = data.recordDescricao;
			var titulo =  $('#tituloEleitor').val();
			$('#result').html("");
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
		       		     
		       			 $("#constab").html("<thead><th> Data </th><th> Descrição </th><th> Código </th></thead>");
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
		       		       }
		   		   }).done(function() {
		   			  $('#consreg').attr("disabled", false);
		   		   })
			 }
	   });
});