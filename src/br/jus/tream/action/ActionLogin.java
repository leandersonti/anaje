package br.jus.tream.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.EleicaoDAOImpl;
import br.jus.tream.dominio.BeanLogin;

@SuppressWarnings("serial")
@Namespace("/login")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionLogin extends ActionSupport implements SessionAware{  
  private BeanLogin beanlogin;
  private String username,userpass; 
  private String endpoint = "/main.jsp";
  
  SessionMap<String,BeanLogin> sessionmap;
  
  
  @Action(value = "frmLogin", results = { @Result(name = "success", location = "/frmLogin.jsp"),
			@Result(name = "error", location = "/pages/error.jsp")})
  public String frmLogin() {
	   try {
		   HttpSession session = ServletActionContext.getRequest().getSession(true);
		   endpoint = (String)session.getAttribute("endpoint");		   
		   
		   if(endpoint.length() == 0) {
			   endpoint = "/main.jsp";
		   }
		} catch (Exception e) {
			this.setEndpoint("/main.jsp");			
		}
		   	   
	  return "success";
  }
  
  @Action(value = "main", results = { @Result(name = "success", location = "/main.jsp"),
			@Result(name = "error", location = "/pages/error.jsp")})
  public String main() {	 
	  return "success";
}
	
  @Action(value = "process", results = {@Result(name = "success", location = "%{endpoint}", type = "redirect"),
	        @Result(name = "error", location = "/frmLogin.jsp")})
	public String getLogin(){  
		try{					
			
			this.beanlogin = LoginAD.getInstance().getLogin(this.getUsername(), this.getUserpass());
			this.beanlogin.setIdEleicao(EleicaoDAOImpl.getInstance().getBeanAtiva().getId());
		    if(this.beanlogin.getLogou()){ 		    	
		    	sessionmap.put("login",this.beanlogin);  
		        return "success";  
		    }  
		    else{ 
		    	  addActionError(getText("error.login"));
				 return "error";
		    }  
		}catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("error.login")+ "==" + e.getMessage());			 
			return "error";
		}
	}  
  
  
  @Action(value = "logout", results = {@Result(name = "success", location = "/frmLogin.jsp"),
	        @Result(name = "error", location = "/pages/error.jsp")})
	public String logout(){  
	    sessionmap.invalidate(); 	    
	    return "success";  
	}  
  
    public BeanLogin getBeanlogin() {
		return beanlogin;
	}

	public void setBeanlogin(BeanLogin beanlogin) {
		this.beanlogin = beanlogin;
	}

	public SessionMap<String, BeanLogin> getSessionmap() {
		return sessionmap;
	}

	public void setSessionmap(SessionMap<String, BeanLogin> sessionmap) {
		this.sessionmap = sessionmap;
	}

	  public String getUsername() {  
	    return username;  
	  }  
  
		public void setUsername(String username) {  
		    this.username = username;  
		}  
		  
		public String getUserpass() {  
		    return userpass;  
		}  
		  
		public void setUserpass(String userpass) {  
		    this.userpass = userpass;  
		}  
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void setSession(Map map) {  
		    sessionmap=(SessionMap)map;  
		    sessionmap.put("login",this.beanlogin);  
		}

		public String getEndpoint() {
			return endpoint;
		}

		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}  
		
  
}  