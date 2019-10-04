package br.jus.tream.action;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import br.jus.tream.dominio.BeanLogin;

@SuppressWarnings("serial")
public class Permissao implements Serializable{
	static Permissao db;
	//final static HttpSession session = ServletActionContext.getRequest().getSession(true);

	public static Permissao getInstance() {
		if (db == null) {
			db = new Permissao();
		}
		return db;
	}
	
	public final boolean getAdmin() {
		HttpSession session = ServletActionContext.getRequest().getSession(true);
    	BeanLogin b = (BeanLogin)session.getAttribute("login");
	    return (b.getAdmin()==1);
	}
	
	public final int getZona() {
		HttpSession session = ServletActionContext.getRequest().getSession(true);
    	BeanLogin b = (BeanLogin)session.getAttribute("login");
	    return b.getZona();
	}
	
	public final int getIdEleicao() {
		HttpSession session = ServletActionContext.getRequest().getSession(true);
    	BeanLogin b = (BeanLogin)session.getAttribute("login");
	    return b.getIdEleicao();
	}
	
}
