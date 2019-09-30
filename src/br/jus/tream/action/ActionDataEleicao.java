package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.EleicaoDAO;
import br.jus.tream.DAO.EleicaoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Eleicao;

@SuppressWarnings("serial")
@Namespace("/eleicao")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionDataEleicao extends ActionSupport{
	private List<Eleicao> lstEleicao;
	private Eleicao eleicao;
	private BeanResult result;
	private final static EleicaoDAO dao = EleicaoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/eleicao.jsp"),
			@Result(name = "error", location = "/result.jsp")}, interceptorRefs = @InterceptorRef("authStack")
	)
	public String listar() {
		try {
			this.lstEleicao = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listarJson", results = { @Result(name = "success", type = "json", params = { "root", "lstEleicao" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarJson() {
		try {
			this.lstEleicao = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmEleicao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadEleicao() {	
		return "success";
	}
	
	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmEleicao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.eleicao = dao.getBean(this.eleicao.getId());
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}
	
	@Action(value = "setcontexto", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doSetContexto() {
		BeanResult beanResult = new BeanResult();
		try {
			//HttpSession session = ServletActionContext.getRequest().getSession(true);
	    	//BeanLogin b = (BeanLogin)session.getAttribute("login");
		    if (permissao.getAdmin()) {
		    	beanResult.setRet(dao.ativar(this.eleicao.getId()));
				beanResult.setMensagem(getText("eleicao.setcontexto"));
		    } else {
		    	beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));				
		    }
		} catch (Exception e) {
			 addActionError(getText("eleicao.setcontexto.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}
	
	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.adicionar(eleicao));
				if (beanResult.getRet() == 1)
					beanResult.setMensagem(getText("inserir.sucesso"));
				else
					beanResult.setMensagem(getText("inserir.error"));
			}else {
				beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
			}
		} catch (Exception e) {
			    addActionError(getText("alterar.error") + " Error: " + e.getMessage());
			  //result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}
	
	
	@Action(value = "atualizar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAtualizar() {
		BeanResult beanResult = new BeanResult();
		try {
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.atualizar(this.eleicao));
				if (beanResult.getRet()==1) {
					beanResult.setMensagem(getText("alterar.sucesso"));
				}else {
					beanResult.setMensagem(getText("alterar.error")); 
				}
			}else {
				beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
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
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.remover(this.eleicao));
				beanResult.setMensagem(getText("remover.sucesso"));
			}else {
				beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
			}	
		} catch (Exception e) {
			addActionError(getText("remover.error") + " Error: " + e.getMessage());
			// r.setMensagem(getText("remover.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
	  return "success";
	}
	
	public List<Eleicao> getLstEleicao() {
		return lstEleicao;
	}
	public void setLstEleicao(List<Eleicao> lstEleicao) {
		this.lstEleicao = lstEleicao;
	}
	public Eleicao getEleicao() {
		return eleicao;
	}
	public void setEleicao(Eleicao eleicao) {
		this.eleicao = eleicao;
	}
	public BeanResult getResult() {
		return result;
	}
	public void setResult(BeanResult result) {
		this.result = result;
	}
}
