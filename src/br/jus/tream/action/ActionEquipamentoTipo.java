package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.EquipamentoTipoDAO;
import br.jus.tream.DAO.EquipamentoTipoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.EquipamentoTipo;

@SuppressWarnings("serial")
@Namespace("/EquipamentoTipo")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionEquipamentoTipo extends ActionSupport {
	private List<EquipamentoTipo> lstEquipamentoTipo;
	private EquipamentoTipo equipamentoTipo;
	private BeanResult result;
	private final static EquipamentoTipoDAO dao = EquipamentoTipoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/equipamentoTipo.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			this.lstEquipamentoTipo = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = {
			@Result(name = "success", type = "json", params = { "root", "lstEquipamentoTipo" }),
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarJson() {
		try {
			this.lstEquipamentoTipo = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmEquipamentoTipo.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadContrato() {
		try {
			this.lstEquipamentoTipo = EquipamentoTipoDAOImpl.getInstance().listar();

		} catch (Exception e) {
			addActionError(getText("EquipamentoTipo.error.listar"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmEquipamentoTipo.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.lstEquipamentoTipo = EquipamentoTipoDAOImpl.getInstance().listar();
			this.equipamentoTipo = dao.getBean(this.equipamentoTipo.getId());
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
				beanResult.setRet(dao.adicionar(equipamentoTipo));
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
			// result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
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
				beanResult.setRet(dao.atualizar(this.equipamentoTipo));
				if (beanResult.getRet() == 1) {
					beanResult.setMensagem(getText("alterar.sucesso"));
				} else {
					beanResult.setMensagem(getText("alterar.error"));
				}
			} else {
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
				beanResult.setRet(dao.remover(this.equipamentoTipo));
				beanResult.setMensagem(getText("remover.sucesso"));
			} else {
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

	public List<EquipamentoTipo> getLstEquipamentoTipo() {
		return lstEquipamentoTipo;
	}

	public void setLstEquipamentoTipo(List<EquipamentoTipo> lstEquipamentoTipo) {
		this.lstEquipamentoTipo = lstEquipamentoTipo;
	}

	public EquipamentoTipo getEquipamentoTipo() {
		return equipamentoTipo;
	}

	public void setEquipamentoTipo(EquipamentoTipo equipamentoTipo) {
		this.equipamentoTipo = equipamentoTipo;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public static EquipamentoTipoDAO getDao() {
		return dao;
	}

	public static Permissao getPermissao() {
		return permissao;
	}
	
	

}
