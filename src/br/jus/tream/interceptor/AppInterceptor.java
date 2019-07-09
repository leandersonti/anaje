package br.jus.tream.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import br.jus.tream.dominio.BeanLogin;
import br.jus.tream.dominio.Usuario;

@SuppressWarnings("serial")
public class AppInterceptor implements Interceptor{

	public void destroy() {}

	//called during interceptor initialization
	public void init() {}
	
	//put interceptor code here
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "success";

		/* VERIFICANDO A SESSÃO */ 
		try{
		   HttpSession session = ServletActionContext.getRequest().getSession(true);
    	   BeanLogin b = (BeanLogin)session.getAttribute("login");
	    	if (b.getNome()==null) {
	    		 System.out.println("Sessão expirada");
	    		 result = "error";
	    	} 
	    	else {
	    	     System.out.println("Sessão Ativa - titulo : " + b.getTitulo());
	    		result = invocation.invoke();
	    	}
		}catch (Exception e) {
			result = "loginNecessario";
			System.out.println("Sessão expirada xxxxxxxx " + e.getMessage());
		}
		// -----------------------   DEPOIS DA ACTION
		// System.out.println("CustomInterceptor, after invocation.invoke()... Result........>> " + result);
		return result;
	}

}