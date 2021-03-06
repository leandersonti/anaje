package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.SRHServidoresDAO;
import br.jus.tream.DAO.SRHServidoresDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.SRHServidores;

@SuppressWarnings("serial")
@Namespace("/servidor")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionSRHServidores extends ActionSupport {
	private List<SRHServidores> lstServidores;
	private SRHServidores servidor;
	private BeanResult result;
	private final static SRHServidoresDAO dao = SRHServidoresDAOImpl.getInstance();

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/servicor.jsp"),
			@Result(name = "error", location = "/result.jsp") })
	public String listar() {
		try {
			this.lstServidores = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	  @Action(value = "listarParaPermissaoJson", results = { @Result(name = "success", type = "json", params = { "root", "lstServidores" }),	  
	  @Result(name = "error", location = "/pages/resultAjax.jsp")})	  
	  public String listarJson() { 		  
		  try {
			    this.lstServidores = dao.ListParaUser();
		  	 } catch (Exception e) {
		  	  addActionError(getText("listar.error"));
		  	  return "error";
		  	} 
		 return "success";
	 }


	public List<SRHServidores> getLstServidores() {
		return lstServidores;
	}

	public void setLstServidores(List<SRHServidores> lstServidores) {
		this.lstServidores = lstServidores;
	}

	public SRHServidores getServidor() {
		return servidor;
	}

	public void setServidor(SRHServidores servidor) {
		this.servidor = servidor;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}


}
