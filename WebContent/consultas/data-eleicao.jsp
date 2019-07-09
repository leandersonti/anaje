<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page = "/mainhead.inc.jsp" />


<div class="container">
     
<div class="card">
  <div class="card-header">Data Eleição</div>
  <div class="card-body">
  
    <table id="table1" class="table">
	<thead>
		<tr>
			<th width="8%">Data</th>
			<th width="28%">Descricao</th>
			<th width="10%">turno</th> 
			<th width="18%">Ativo</th>
			<th width="15%"><a href="frmCad" class="btn btn-sm btn-primary" role="button">Novo</a>
		    </th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="lstEleicao">
		<tr id="tr${id}">
		    <td><s:property value="%{getText('format.date',{dataEleicao})}"/></td>
			<td><s:property value="descricao"/></td>
			<td><s:property value="turno"/></td>
			<td><s:property value="ativo"/></td>
			<td>  		    
				    <a href="frmEditar?eleicao.id=${id}" id="idedit" class="btn btn-sm btn-warning" role="button">
							Editar
				    </a>
					
					<a href="#" id="excluir${id}" class="btn btn-sm btn-danger" role="button" data-record-id="${id}" data-record-data="<s:property value="%{getText('format.date',{dataEleicao})}"/>"
					  data-record-descricao="${descricao}">
					  		Remover
				    </a>
			</td>
		</tr>
		</s:iterator>
	 </tbody>	
	</table>
	
    
	  </div>
	</div>

</div>

   

<jsp:include page = "/javascripts.jsp" />

<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
	    $('#table1').dataTable( {
	        "order": [[ 0, "des" ],[ 1, "des" ]]
	    } );

});
	
				
	$( "[id*='excluir']" ).click(function(event) {
	    var data = $(event.delegateTarget).data();
		var id = data.recordId; 
		var ano = data.recordAno; 
		var nrdoc = data.recordNrdoc; 
		var especie = data.recordEspecie; 
		var descricao = data.recordDescricao;
		
		Swal.fire({
			  title: 'Excluir?',
			  text: "Deseja excluir esse registro?",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonText: 'Sim excluir!'
			}).then((result) => {
			  if (result.value) {
			    console.log("excluído!!!")
				  
				  
			  }
			})
			
			
			
		/*
		swal({
	        title: "Confirma remover?",
	        text: "Confirma remover este registro (" + descricao + ")?",
	        icon: "info",
	        buttons: true,
	        dangerMode: true
	        })
	        .then((resp) => {
				if (resp) {
					
					$.getJSON({
							url: "remover?docpk.anodoc="+ano+"&docpk.nrdoc="+nrdoc+"&docpk.especie.cdespecie="+especie
				    }).done(function( data ) {
				    	  if (data.ret==1){
				    		  $('#tr'+ano+''+nrdoc+''+especie).fadeOut(); 
				    		  swal("Remover", data.mensagem, "success");
				    	  }
				    	  else
				    		  swal("Remover", "An error occurred", "error");
					}).fail(function() {
								swal("Remover", "An error occurred", "error");
					});
			    } 
			});
		
		*/
	  });
		
$( "[id*='setcontext']" ).click(function(event) {
    var data = $(event.delegateTarget).data();
	var id = data.recordId;  
	var dtelei = data.recordDataeleicao;
	var turno = data.recordTurno;
	var descricao = data.recordDescricao;
		BootstrapDialog.confirm({
			   title: 'Atenção',
			   message: "Setar eleição <b>" + dtelei  +"</b> turno:  " + turno + " - " + descricao + " como padrão?",
			   type: BootstrapDialog.TYPE_DANGER, 
			   closable: true, 
			   btnCancelLabel: 'Cancelar', 
			   btnOKLabel: "Setar", 
			   btnOKClass: 'btn-danger', 
			   callback: function(result) {
			        if(result) {
			        	$.getJSON('setcontexto?eleicao.id=' + id,
			        	     function(jsonResponse) {
			        		       var tipomsg = 'success';
			        		       var msg = jsonResponse.mensagem; 
			        		       if(jsonResponse.id==1){
			        		    	   location.reload();
			        		       }else{
			        		    	   tipomsg = 'danger';
			        		       }
			        		       $.notify(msg, {
	                         		   animate: {
	                         		       enter: 'animated bounceInDown',
	                         		       exit: 'animated bounceOutUp'
	                         		   },
	                         		   type: tipomsg });
	                         	  setTimeout(function(){$.notifyClose();},2500);
	        	          });
			         }
			    }
			});
		});		


</script>

<jsp:include page = "/mainfooter.inc.jsp" />