$(document).ready(function() {
	if($("#tbeleicao").length){
		   $('#tbeleicao').dataTable( {
		        "order": [[ 0, "des" ],[ 1, "des" ]]
		   });
    }
	
	// CLICK SETAR CONTEXTO
	$( "[id*='setcontext']" ).click(function(event) {
	    var data = $(event.delegateTarget).data();
		var id = data.recordId;  
		var turno = data.recordTurno;
		var dt = data.recordData;
		swal({
			  title: 'Ativar Eleição?',
			  text: "Deseja tornar a eleição " + dt + " turno " + turno + " ativa?",
			  icon: 'warning',
			  buttons: [true, "Sim ativar!"]
			}).then((result) => {
			  if (result) {
			       $.getJSON({
					  url: "setcontexto?eleicao.id="+id
				   }).done(function( data ) {
				    	  if (data.ret==1){	    		  
				    		  $( "span:contains('Ativo')" ).attr('class', 'badge badge-pill badge-secondary');
				    		  $( "span:contains('Ativo')" ).text("Desativado");
				    		  $('#ele'+id).text("Ativo");
				    		  $('#ele'+id).attr('class', 'badge badge-pill badge-success');
				    	  }
				    	  else
				    		 swal("Ativar Eleição", data.mensagem, "error");
					}).fail(function() {
						swal("Ativar Eleição", "Ocorreu um erro ao realizar esse procedimento", "error");
					});
			   }
		  })
	   });	
	
	// CLICK DO BOTÃO EXCLUIR
	$( "[id*='excluir']" ).click(function(event) {
	    var data = $(event.delegateTarget).data();
		var id = data.recordId; 
		var descricao = data.recordDescricao;
		swal({
			  title: 'Excluir?',
			  text: "Deseja excluir esse registro? (" + descricao + ")",
			  icon: 'warning',
			  buttons: [true, "Sim excluir!"]
			}).then((result) => {
			  if (result) {
			       $.getJSON({
					  url: "remover?eleicao.id="+id
				   }).done(function( data ) {
				    	  if (data.ret==1){
				    		  $('#tr'+id).fadeOut(); 
				    		  swal("Remover", data.mensagem, data.type);
				    	  }
				    	  else
				    		  swal("Remover", "Ocorreu um erro ao remover", data.type);
					}).fail(function() {
						swal("Remover", "Ocorreu um erro ao remover", "error");
					});
			   }
			})
	  });
	
	// CLICK DO BOTÃO SAVE	
	$("#btnSave").click(function() {
		var URL = ""; 
		if ( $('#id').length ) { URL = "atualizar"; }
		else{ URL = "adicionar";  }	
		if (verificaDados()){
			 swal({
		         title: "Confirma ?",
		         text: "Confirma " + URL + "?",
		         icon: 'warning',
		         buttons: [true, "Salvar"]
		         }).then((result) => {
					if (result) {
						var frm = $("#frmEleicao").serialize();
						$.getJSON({
							url: URL,
							data: frm
					    }).done(function( data ) {
					    	if(data.ret==1)
					    		swal(URL, data.mensagem, "success");
					    	else 
					    		swal(URL, data.mensagem, "error");
						}).fail(function() {
								swal("Adicionar", "Ocorreu um erro ao incluir", "error");
						});
				      } 
			   }); // -- FIM SWAL --
		   }else{ swal("Dados", "Verifique os campos obrigatorios!!", "error");  }
	 	}); 
	
	function verificaDados(){
	    if ($("#frmEleicao")[0].checkValidity()===false){
	    	$("#frmEleicao")[0].classList.add('was-validated');
	    	return false;
	    }else 
		   return true;
	 }
	
});