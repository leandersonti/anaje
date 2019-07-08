<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="container-full">
  <div class="container-fluid">   


		<div class="alert alert-danger">
		  <strong>Ops!</strong> <s:property value="getText('error.label')" />
		       <s:actionerror />
		</div>


   </div>  
  </div>
