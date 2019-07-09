package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.DataEleicaoDAO;
import br.jus.tream.DAO.DataEleicaoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.DataEleicao;

@SuppressWarnings("serial")
@Namespace("/eleicao")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionDataEleicao extends ActionSupport{
	private List<DataEleicao> lstEleicao;
	private DataEleicao eleicao;
	private BeanResult result;
	private final static DataEleicaoDAO dao = DataEleicaoDAOImpl.getInstance();
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/data-eleicao.jsp"),
			@Result(name = "error", location = "/result.jsp")}, interceptorRefs = @InterceptorRef("authStack")
	)
	public String getListar() {
		try {
			this.lstEleicao = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	
	@Action(value = "listarJson", results = { @Result(name = "success", type = "json", params = { "root", "lstEleicao" }),
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String getListarJson() {
		try {
			this.lstEleicao = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmDataEleicao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadEleicao() {	
		return "success";
	}
	
	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmDataEleicao.jsp"),
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
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doSetContexto() {
		BeanResult r = new BeanResult();
		try {
			int result = dao.ativar(this.eleicao.getId());
			r.setMensagem(getText("eleicao.setcontexto"));
			r.setId(result);
		} catch (Exception e) {
			r.setMensagem(getText("eleicao.setcontexto.erro"));
			return "error";
		}
		this.result = r;
		return "success";
	}
	
	
	@Action(value = "inserir", results = { @Result(name = "success", location = "/forms/frmCadEleicao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doInserir() {
		try {
			int ret = 0;
			ret = dao.inserir(eleicao);
			if (ret == 1)
			    addActionMessage(getText("inserir.sucesso"));
			else
				addActionError(getText("inserir.error"));
		} catch (Exception e) {
			addActionError(getText("inserir.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}
	
	
	
	@Action(value = "alterar", results = { @Result(name = "success", location = "/forms/frmEditEleicao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAlterar() {
		try {			
			int ret = 0;
			ret = dao.alterar(this.eleicao);
			if (ret==1) {
				addActionMessage(getText("alterar.sucesso"));
			}else {
				addActionError(getText("alterar.error")); 
			}
		} catch (Exception e) {
			addActionError(getText("alterar.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}
	
	@Action(value = "excluir", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doExcluir() {
		BeanResult r = new BeanResult();
		try {
			int ret = 0;
			ret = dao.remover(this.eleicao);
			r.setMensagem(getText("remover.sucesso"));
			r.setId(ret);
		} catch (Exception e) {
			r.setMensagem(getText("remover.error") + " Error: " + e.getMessage());
			this.result = r;
			return "error";
		}
		this.result = r;
		return "success";
	}
	
	public List<DataEleicao> getLstEleicao() {
		return lstEleicao;
	}
	public void setLstEleicao(List<DataEleicao> lstEleicao) {
		this.lstEleicao = lstEleicao;
	}
	public DataEleicao getEleicao() {
		return eleicao;
	}
	public void setEleicao(DataEleicao eleicao) {
		this.eleicao = eleicao;
	}
	public BeanResult getResult() {
		return result;
	}
	public void setResult(BeanResult result) {
		this.result = result;
	}
}
