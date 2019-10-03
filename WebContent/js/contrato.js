$(document).ready(function() {
  // DATATABLE COMPONENT
	if($("#tbContratos").length){
	  $('#tbContratos').dataTable( {
	        "order": [[ 0, "des" ],[ 1, "des" ]]
	   });
  }
	
	 // CLIQUE DO BOTAO SAVE
	 $("#btnSave").click(function() {
		var URL = ""; 
		if ( $('#id').length ) { URL = "atualizar"; }
		else{ URL = "adicionar";  }	
		if (verificaDados()){
			 swal({
		         title: "Confirma ?",
		         text: "Confirma " + URL + "?",
		         icon: 'warning',
		         buttons: [true, "Sim Incluir!"]
		         }).then((result) => {
					if (result) {
						var frm = $("#form1").serialize();
						// console.log(frm);
						$.getJSON({
							url: URL,
							data: frm
					    }).done(function( data ) {
					    	//if(data.ret==1)
					    	swal(URL, data.mensagem, data.type);
					    	//else 
					    	//	swal(URL, data.mensagem, "error");
						}).fail(function() {
							swal("Adicionar", "Ocorreu um erro ao incluir", "error");
						});
				      } 
			   }); // -- FIM SWAL --
		   }else{
			   swal("Dados", "Verifique os campos obrigatórios ", "error");
		   }
	 	}); // -- FIM btnSave --
	 
	 // BOTÃO EXCLUIR
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
						  url: "remover?contrato.id="+id
					   }).done(function( data ) {
					    	  if (data.ret==1){
					    		  $('#tr'+id).fadeOut(); 
					    		  swal("Remover", data.mensagem, "success");
					    	  }
					    	  else
					    		  swal("Remover", "Ocorreu um erro ao remover", "error");
						}).fail(function() {
							swal("Remover", "Ocorreu um erro ao remover", "error");
						});
				   }
				})
		  });
	 
});

 function verificaDados(){
    if ($("#form1")[0].checkValidity()===false){
    	$("#form1")[0].classList.add('was-validated');
    	return false;
    }else 
	   return true;
 }