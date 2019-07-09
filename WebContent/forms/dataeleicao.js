/* DATA ELEICAO  */
$(document).ready(function() {
	 // CLICK DO BOTÃO SAVE 
	 $("#btnSave").click(function() {
		var URL = ""; 
		if ( $('#id').length ) { URL = "atualizar"; }
		else{ URL = "adicionar";  }	 
		 swal({
	         title: "Confirma ?",
	         text: "Confirma " + URL + "?",
	         icon: "info",
	         buttons: true,
	         dangerMode: true,
	         })
	         .then((resp) => {
				if (resp) {
					var frm = $("#form1").serialize();					
					$.getJSON({
						url: URL,
						data: frm
				    }).done(function( data ) {
				    	if(data.ret==1)
							 swal(URL, data.mensagem, "success");
				    	else 
				    		swal(URL, data.mensagem, "error");
					}).fail(function() {
								swal("Update", "An error occurred", "error");
					});
			      } 
		   }); // -- FIM SWAL --
	 	}); 
});
