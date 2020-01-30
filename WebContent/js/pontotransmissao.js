$(document).ready(function() {
	if($("#tabPontoTransmissao").length){
		   $('#tabPontoTransmissao').dataTable( {
		        "order": [[ 0, "asc" ],[ 1, "asc" ]]
		   });
		   // $("#tabPontoTransmissao").DataTable();
     }
	
	$("#btnConsultar").click(function() {
		$("#frmConsultaPonto").submit();
	});
	
	$("#btnSave").click(function() {
		var URL = ""; 
		if ( $('#id').length ) { URL = "atualizar"; }
		else{ URL = "adicionar";  }
		if (verificaDados()){
			 swal({
		         title: "Confirma?",
		         text: "Confirma " + URL + "?",
		         icon: "warning",
		         buttons: [true, "Salvar"]
		       }).then((result) => {
					if (result) {
						var frm = $("#form1").serialize();
						// console.log(frm);
						$.getJSON({
							url: URL,
							data: frm
					    }).done(function( data ) {
					    	if(data.ret==1){
					    		if (! $('#id').length ) { 
					    			limparDados();
					    			CarregaLocalVotacao();
					    		}
					    		swal(URL, data.mensagem, "success");
					        }else {
					    		swal (URL, data.mensagem, "error" )
					    	}
						}).fail(function() {
								swal("Adicionar", "Ocorreu um erro ao incluir", "error");
						});
				     } 
			   });
		   }else{
			   swal("Dados", "Verifique os campos obrigatórios ", "error");
		   }
	 	}); // -- FIM Click btnSave --
	
	// CLICK DO BOTÃO EXCLUIR PONTO DE TRANSMISSAO
	$( "[id*='excluir']" ).click(function(event) {
	    var data = $(event.delegateTarget).data();
		var id = data.recordId; 
		var idDtElei = data.recordIdeleicao;
		var descricao = data.recordDescricao;
		swal({
			  title:'Excluir?',
			  text: "Deseja excluir esse registro? (" + descricao + ")",
			  icon: 'warning',
			  buttons: [true, "Sim excluir!"]
			}).then((result) => {
			  if (result) {
			      var vurl = "remover?pt.id.id="+id+'&pt.id.eleicao.id='+idDtElei; 
			      $.getJSON({
					  url: vurl
				  }).done(function( data ) {
				    	  if (data.ret==1){
				    		  $('#tr'+id).fadeOut(); 
				    		     swal("Remover", data.mensagem, "success");
				    	  }
				    	  else
				    		  swal("Remover", data.mensagem, "error");
					}).fail(function() {
						swal("Remover", "Ocorreu um erro ao remover", "error");
					});
			   }
			})
	  });
	
	$('#codZonaMunic').change(function(event) {
		CarregaLocalVotacao();
	});
	
	$('#codLocal').change(function(event) {
		getLocalVotacao();
	});
	
});


function CarregaLocalVotacao() {
	var codZonaMunic = $("#codZonaMunic option:selected").val();
	var select = $('#codLocal');
	select.find('option').remove();
 	$.getJSON('../elo/listarJsonLocalVotacaoParaCadastrar?codZonaMunic=' + codZonaMunic, 
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

function getLocalVotacao() {
	var codZonaMunic = $("#codZonaMunic option:selected").val().split(';');
	var numLocal = $("#codLocal option:selected").val();
 	$.getJSON('../elo/getBeanJsonLocalVotacao?zona=' + codZonaMunic[0] 
			      +'&codmunic=' + codZonaMunic[1] + '&numLocal=' + numLocal, 
		function(dados) {
			$("#descricao").val(dados.nomeLocal);
			$("#endereco").val(dados.endereco);
			$("#latitude").val(dados.latitude);
			$("#longitude").val(dados.longitude);
			$("#codObjetoLocal").val(dados.id);	
	 });	  
}

function limparDados() {
	$("#descricao").val("");
 	$("#endereco").val("");
	$("#latitude").val("");
	$("#longitude").val("");
	$("#codObjetoLocal").val("");
	$("#sala").val("");
	$("#contato").val("");
	$("#telefone").val("");
	$("#cargoContato").val("");
}

function verificaDados() {
	if ($("#form1")[0].checkValidity() === false) {
		$("#form1")[0].classList.add('was-validated');
		return false;
	} 
	if ($("#codZonaMunic option:selected").val()==-1){
		return false;
	}
	if ($("#codLocal option:selected").val()==-1){
		return false;
	}
	return true;
}
/*
$("#btnPontoSemSecoes").click(function() {
	$.getJSON('listarTodosSemDistribuicaoSecaoJson', 
		function(jsonResponse) {
		    $('#tbody').empty();
			$.each(jsonResponse, function(key, value) {
				var linha = "<tr>";
				      linha += "<td>"+ value.zona +"</td><td>"+ value.codLocal+"</td><td>"+ 
				      value.codmunic +'</td><td><a id="detalhePontoTrans${id.id}" href="#" data-record-id="${id.id}">'+ value.descricao+"</a></td>"
				      linha += "<td>"+ value.endereco+"</td><td>"+value.status+"</td><td>"+value.oficial+"</td><td>-</td>"	
				    linha += "</tr>"
				$('#tbody').append(linha);				    	
		    });
	 });  
});
*/