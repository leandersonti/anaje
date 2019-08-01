<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/mainhead.inc.jsp" />

<div class="container">
  	<div class="container-fluid">
  	<div class="card">
  	
	<h5 class="card-header">Importar Equipamentos</h5>
	
	
	<div class="card-body">
		
	<s:form id="frmImportar" action="frmImportar"  method="POST" enctype="multipart/form-data">
	
		<div class="row">
			<s:select id="idEquipamento"
					  label="Tipo do Equipamento" 
					  class="form-control" name="equipamento.tipo.id"
									headerKey="-1" headerValue="--Selecione--" list="lstEquipamentoTipo"
									listKey="id" listValue="descricao" required="true" 
									disabled="editar"/>
		</div>
		<div class="row">
			<s:file name="fileUpload" id="fileUpload" 
				accept=".txt" label="Lista de Equipamentos" size="100"/>
				
		</div>
		<s:submit value="Enviar arquivo" name="btnEnviar" />
	</s:form>	
	<br>	
		<fieldset>
		<table id="table1" class="table table-hover">
			<thead>
				<tr>
					<th width="10%">Tipo</th>					
					<th width="10%">S�rie</th>
					<th width="10%">Tombo</th>
					
					<th width="10%">Fone</th>
					<th width="10%">Param</th>
					<th width="10%">Chaves</th>
					<th width="10%">-</th>
				</tr>
			</thead>
			<tbody>
			  <s:iterator value="lstEquipamento">
			  	<s:if test="inserted">
					<tr id="tr${id}" class="table-success">
				</s:if>
				<s:else>
					<tr id="tr${id}" class="table-danger">
				</s:else>
						<td><s:property value="tipo.descricao"/></td>						
						<td><s:property value="serie"/></td>
						<td><s:property value="tomb"/></td>
						
						<td><s:property value="fone"/></td>
						
						<td><s:property value="param"/></td>
						
						<td><s:property value="chave"/></td>	
											
						<td>
						<s:if test="inserted">
							<img  src="${pageContext.request.contextPath}/images/check_ok.png" style="width: 20px;height: 20px;">
						</s:if>
						<s:else>
							<img  src="${pageContext.request.contextPath}/images/check_error.png" style="width: 20px;height: 20px;">
						</s:else><!-- a href="frmEditar?id=${id}" class="btn btn-sm btn-info" role="button" title="Editar">
						      Editar
						</a>
						    
						    <a href="#" id="excluir${id}" class="btn btn-sm btn-danger" role="button" 
						             data-record-id="${id}" data-record-dataeleicao="${dataEleicao}" data-record-turno="${turno}" data-record-descricao="${descricao}">
						                  Excluir
						             </a-->
						 </td>
					</tr>
				</s:iterator>
			 </tbody>
		 </table>
	
		</fieldset>
		
	
	<s:if test="hasActionErrors()">
		<div class="alert alert-danger" role="alert" id="error_message">
			<s:actionerror/>
		</div>	
	</s:if>
		
	<s:if test="hasActionMessages()">
		<!-- Success message -->
	    <div class="alert alert-success" role="alert" id="success_message">
			<s:actionmessage/>
		</div>
	</s:if>
	
		</div>
	</div>
	</div>  
</div> 


<jsp:include page="/javascripts.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	   setTimeout(function() {
  		 $( "#error_message" ).fadeOut( "slow"); 
  	   }, 1000);
	$('#table1').dataTable( {
        "order": [[ 2, "asc" ]],
        language: {
            processing:     "Processando...",
            search:         "Pesquisar",
            lengthMenu:     "Mostrar _MENU_ registros",
            info:           "Mostrando _START_ &agrave; _END_ de um total de _TOTAL_ elementos",
            infoEmpty:      "Mostrando item 0 &agrave 0 de 0 itens",
            infoFiltered:   "(filtrado _MAX_ itens no total)",
            infoPostFix:    "",
            loadingRecords: "Carregando",
            zeroRecords:    "Nenhum item para exibir",
            emptyTable:     "Nenhum dado dispon&iacute;vel",
            paginate: {
                first:      "Primeiro",
                previous:   "Anterior",
                next:       "Pr&oacute;ximo",
                last:       "&Uacute;ltimo"
            },
            aria: {
                sortAscending:  ": ativar para classificar a coluna em ordem crescente",
                sortDescending: ": ativar para classificar a coluna em ordem decrescente"
            }
        }
	});
	$('#frmImportar').submit(function() {
		$(".wrapper").show();
	})
	$('#arquivoPath').change(function(e){
		var file = e.target.files[0];	 	 
	 	if (file != undefined && file.type == 'text/plain') {
	 		var reader = new FileReader();
		    reader.readAsText(file);
		    reader.onload = (function(value) {
		    	return function(e) {
					var table = $("#table1");
		    		var content = e.target.result;
		    		var rows = content.split('\n');
		    		
		    		for (var i = 0; i < rows.length; i++) {
		    			var row = rows[i];
		    			var col = row.split(';');
		    			if (col[0] != undefined 
		    					&& col[1]!= undefined
		    					&& col[2]!= undefined
		    					&& col[3]!= undefined
		    					&& col[4]!= undefined) {
			    			table.append(
					    			$('<tr id=row_' + i +'>')
					    				.append($('<td>'+col[0]+'</td>'))
					    				.append($('<td>'+col[1]+'</td>'))
					    				.append($('<td>'+col[2]+'</td>'))
					    				.append($('<td>'+col[3]+'</td>'))
					    				.append($('<td>'+col[4]+'</td>'))
					    				.append($('<td id="row_button">'+generateDeleteButton(i)+'</td>')));
		    			}
		    		}
		    	};
			})(file); 
	 	 } else {
	 		 console.log('file type not allowed');
	 		 console.log('clear');
	 	 }
	 });
	 
	$(document).on("click", "[id*='excluir']", function(event) {
		var row = $(this).parent().parent();
		var titulo = row[0].cells[1].innerHTML;
		var nome = row[0].cells[2].innerHTML;
		
		var data = $(event.delegateTarget).data();
		var id = event.target.id.replace('excluir', '');
	  	
		swal.fire({
			title: 'Deseja Remover Eleitor ?',
			text: titulo + ' - ' + nome,
			type: 'question',
			confirmButtonText: 'Confirmar',
			cancelButtonText: 'Cancelar',
			cancelButtonColor: '#d33',
			showCancelButton: true,
		}).then(function (resp) {
			if (resp.value){
				row.remove();
			} 
		});		 
	 })
	 
	 function generateDeleteButton(index) {
		 return '<button type="button" id="excluir'+index+'" class="btn btn-sm btn-danger" title="Excluir" role="button" data-record-id="'+index+'" >Excluir</button>';
	 }
	 $( "#btnImportar" ).click(function() {
		 var lstEleitor = [];
	
		console.log('importar tabela');
		var table = $("#table1");
		var rows = table[0].children[1].rows;
		for (var i = 0; i < rows.length; i++) {
			var cells = rows[i].cells;
			console.log('--------------');
			
			var eleitor = {
				'id': {
					'idEleicao':{
						'id' : cells[0].innerHTML,
					},
					'tituloEleitor' : cells[1].innerHTML,
				},
				'nome': cells[2].innerHTML,
				'token': cells[3].innerHTML,
				'email': cells[4].innerHTML
			}
			
			lstEleitor.push(eleitor);
		}
		console.log(lstEleitor);
		
		$.ajax({
		    type: "POST",
		    url: "/votaki/eleitor/adicionarLista",
		    dataType: "json",
		    data: [lstEleitor],
		    success: function(msg){
		    	console.log('sucess');
		        console.log(msg)
		    },
		    error: function(e) {
		    	console.log('error');
		    	console.log(e);
		    }
		});
	 });
 	 
	 $("#btnCancelar").click(function() {
		 window.location.href = '/votaki/eleitor/listar';
	 });
	$("#nome").keyup(function() {
		this.value = this.value.toUpperCase() 
	});
	$('#nome').unbind('keydown').bind('keydown',function(e) {
		validateLength($(this));
	}); 
	$('#tituloEleitor').unbind('keydown').bind('keydown',function(e) {
		validateLength($(this));
	});
	$('#token').unbind('keydown').bind('keydown',function(e) {
		validateLengthon($(this));
	});
	$('#email').unbind('keydown').bind('keydown',function(e) {
		validateLength($(this));
	});
	function validateLength(input) {
		var val = input.val();
		var maxCount = input.attr('maxlength');
		var valLength = val.length;
		if(valLength > maxCount){
	        input.val(input.val().substring(0, maxCount));
	    }
	}	
	/**
	 * Envia os dados do formulário para a ActionEleicao.adicionar
	 */
	function doSave() {
		 
		 
		 
	  	$.ajax({
	           type: "POST",
	           url: "/votaki/eleitor/adicionarLista",
	           dataType: "json",
	           data: {
	           		'idEleicao': getIdEleicao(),
	            	'tituloEleitor': getTituloEleitor(),
	           		'eleitor.nome': getNome(),
					'eleitor.token': getToken(),
					'eleitor.email': getEmail()
	           }, success: function (data) {
	        	   showMessageDialog(data, true);
	           }, error: function (e) {
	        	   showMessageDialog(e);
	           }
	       });	
	}
	function doEditar() {
	  	$.ajax({
	           type: "POST",
	           url: "/votaki/eleitor/atualizar",
	           dataType: "json",
	           data: {
	           		'idEleicao': getIdEleicao(),
	            	'tituloEleitor': getTituloEleitor(),
	           		'eleitor.nome': getNome(),
					'eleitor.token': getToken(),
					'eleitor.email': getEmail()
	           }, success: function (data) {
	        	   showMessageDialog(data, false);
	           }, error: function (e) {
	        	   showMessageDialog(e);
	           }
	       });	
	}
	function doVerify() {
		
		return (getIdEleicao() != null || getIdEleicao().length() != 0 || getIdEleicao().val() != 0);
		/*
		
		if ( $("#data").val()==0) {
	    	return false;
	    } 
		if ( $("#descricao").val()==0) {
	    	return false;
	   	} 			
		return true;
		*/
	}	
	/**
		Exibe dialog de sucesso ou erro
	 */
	function showMessageDialog(data, clear) {
		if (data.ret == 200) {
 		   Swal.fire({
         	   type: 'success',
         	   title: 'Sucesso',
         	   text: 'Dados salvos com sucesso!!!'
         	 });
 		   if (clear)
 		   	limparCampos();
 	   } else if (data.ret == 409) {
 		   Swal.fire({
         	   type: 'error',
         	   title: 'Erro',
         	   text: 'Título já está cadastrado para está eleicão!'
         	 })
 	   }
	}
});
</script>
	
<jsp:include page="/mainfooter.inc.jsp" />








