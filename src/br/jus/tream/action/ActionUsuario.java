package br.jus.tream.action;

import java.text.ParseException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.UsuarioDAO;
import br.jus.tream.DAO.UsuarioDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Usuario;

@SuppressWarnings("serial")
@Namespace("/usuario")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionUsuario extends ActionSupport{
	private List<Usuario> lstUsuario;	
	private Usuario usuario;
	private BeanResult result;
	private final static UsuarioDAO dao = UsuarioDAOImpl.getInstance();
	
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/listar-usuario.jsp"),
			@Result(name = "error", location = "/result.jsp") },interceptorRefs = @InterceptorRef("authStack") 
	)
	public String listar() {
		try {
			this.lstUsuario = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = { @Result(name = "success", type = "json", params = { "root", "lstUsuario" }),
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarJson() {
		try {
			this.lstUsuario = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmUsuario.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadTecnico() {	
		return "success";
	}
	
	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmUsuario.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.usuario = dao.getBean(this.usuario.getTituloEleitor());			
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	
	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() throws ParseException {
		BeanResult beanResult = new BeanResult();		
		try {			
				
			beanResult.setRet(dao.inserir(usuario));		
			if (beanResult.getRet() == 1)
				beanResult.setMensagem(getText("inserir.sucesso"));
			else
				beanResult.setMensagem(getText("inserir.error"));
		} catch (Exception e) {
			    addActionError(getText("inserir.error") + " Error: " + e.getMessage());
			  //result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}
	
	
	@Action(value = "atualizar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAtualizar() throws ParseException {
		BeanResult beanResult = new BeanResult();

		try {
		
			beanResult.setRet(dao.alterar(this.usuario));
			if (beanResult.getRet()==1) {
				beanResult.setMensagem(getText("alterar.sucesso"));
			}else {
				beanResult.setMensagem(getText("alterar.error")); 
			}
		} catch (Exception e) {
			addActionError(getText("alterar.error") + " Error: " + e.getMessage());
			return "error";
		}
		 this.result = beanResult;
		return "success";
	}
	
	@Action(value = "remover", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doRemover() {
		BeanResult beanResult = new BeanResult();
		try {
			beanResult.setRet(dao.remover(this.usuario));
			beanResult.setMensagem(getText("remover.sucesso"));
		} catch (Exception e) {
			addActionError(getText("remover.error") + " Error: " + e.getMessage());
			// r.setMensagem(getText("remover.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
	  return "success";
	}

	public List<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(List<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}


	

}
