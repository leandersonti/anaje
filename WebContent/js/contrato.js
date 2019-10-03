$(document).ready(function() {
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
	 
});

 function verificaDados(){
    if ($("#form1")[0].checkValidity()===false){
    	$("#form1")[0].classList.add('was-validated');
    	return false;
    }else 
	   return true;
 }