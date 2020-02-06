<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page = "/mainhead.inc.jsp" />


<div class="container">
     
	
	
	<div class="alert alert-danger">
		  <strong>Ops! <s:property value="getText('error.label')" /> </strong>
		      <s:actionerror />
		</div>
	
	
    		
    
 </div>


<jsp:include page = "/javascripts.jsp" />

<jsp:include page = "/mainfooter.inc.jsp" />