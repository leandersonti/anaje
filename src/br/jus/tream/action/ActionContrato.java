package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.CargoDAOImpl;
import br.jus.tream.DAO.ContratoDAO;
import br.jus.tream.DAO.ContratoDAOImpl;
import br.jus.tream.DAO.EleicaoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Cargo;
import br.jus.tream.dominio.Contrato;
import br.jus.tream.dominio.Eleicao;

@SuppressWarnings("serial")
@Namespace("/contrato")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionContrato extends ActionSupport {
	private List<Contrato> lstContrato;
	private List<Cargo> lstCargo;
	private List<Eleicao> lstDataEleicao;
	private Cargo cargo;
	private Contrato contrato;
	private BeanResult result;
	private final static ContratoDAO dao = ContratoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/contrato.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			this.lstContrato = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = {
			@Result(name = "success", type = "json", params = { "root", "lstContrato" }),
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarJson() {
		try {
			this.lstContrato = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmContrato.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadContrato() {
		try {
			this.lstCargo = CargoDAOImpl.getInstance().listar();
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmContrato.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.lstCargo = CargoDAOImpl.getInstance().listar();
			this.contrato = dao.getBean(this.contrato.getId());
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			if (permissao.getAdmin()) {
				contrato.setEleicao(EleicaoDAOImpl.getInstance().getBeanAtiva());
				beanResult.setRet(dao.adicionar(contrato));
				if (beanResult.getRet() == 1) {
					beanResult.setMensagem(getText("inserir.sucesso"));
					beanResult.setType("success");
				}
				else {
					beanResult.setMensagem(getText("inserir.error"));
					beanResult.setType("error");
				}
			} else {
		    	beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
				beanResult.setType("error");
		    }	
		} catch (Exception e) {
			addActionError(getText("inserir.error") + " Error: " + e.getMessage());
			// result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}

	@Action(value = "atualizar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")}, interceptorRefs = @InterceptorRef("authStack"))
	public String doAtualizar() {
		BeanResult beanResult = new BeanResult();
		beanResult.setRet(0);
		try {			
		   if (permissao.getAdmin()) {
				beanResult.setRet(dao.atualizar(this.contrato));
				if (beanResult.getRet() == 1) {
					beanResult.setMensagem(getText("alterar.sucesso"));
					beanResult.setType("success");
				} else {
					beanResult.setMensagem(getText("alterar.error"));
					beanResult.setType("error");
				}
			}else {
				beanResult.setMensagem(getText("permissao.negada"));
				beanResult.setType("error");
			}
		} catch (Exception e) {
			beanResult.setMensagem("Error: " + e.getMessage());
			beanResult.setType("error");
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
				beanResult.setRet(dao.remover(this.contrato));
				beanResult.setMensagem(getText("remover.sucesso"));
				beanResult.setType("success");
			} else {
		    	beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
				beanResult.setType("error");
			}	
		
		} catch (Exception e) {
			addActionError(getText("remover.error") + " Error: " + e.getMessage());
			// r.setMensagem(getText("remover.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}

	public List<Contrato> getLstContrato() {
		return lstContrato;
	}

	public void setLstContrato(List<Contrato> lstContrato) {
		this.lstContrato = lstContrato;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public List<Cargo> getLstCargo() {
		return lstCargo;
	}

	public void setLstCargo(List<Cargo> lstCargo) {
		this.lstCargo = lstCargo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Eleicao> getLstDataEleicao() {
		return lstDataEleicao;
	}

	public void setLstDataEleicao(List<Eleicao> lstDataEleicao) {
		this.lstDataEleicao = lstDataEleicao;
	}

}
