<%@ taglib prefix="s" uri="/struts-tags"%>
<%response.setContentType("application/json");%>
{<s:if test="hasActionMessages()">"ret":"1","mensagem":"${result.mensagem}"</s:if><s:if test="hasActionErrors()">"ret":"0","mensagem":"${result.mensagem}"</s:if>}