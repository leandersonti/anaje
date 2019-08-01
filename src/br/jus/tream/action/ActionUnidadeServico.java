package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.UnidadeServicoDAO;
import br.jus.tream.DAO.UnidadeServicoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.CADLocalvotacao;
import br.jus.tream.dominio.CADZonaEleitoral;
import br.jus.tream.dominio.IDEleicaoPK;
import br.jus.tream.dominio.UnidadeServico;

@SuppressWarnings("serial")
@Namespace("/uservico")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionUnidadeServico extends ActionSupport {
	private List<UnidadeServico> lstUnidadeServico;
	private List<CADLocalvotacao> lstLocalVotacao;
	private List<CADZonaEleitoral> lstZonaEleitoral;
	private UnidadeServico uservico;
	private IDEleicaoPK id = new IDEleicaoPK();
	private BeanResult result;
	private final static UnidadeServicoDAO dao = UnidadeServicoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/unidade-servico.jsp"),
			@Result(name = "error", location = "/result.jsp") })
	public String listar() {
		try {
			this.lstUnidadeServico = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	/*
	 * @Action(value = "listarJson", results = {
	 * 
	 * @Result(name = "success", type = "json", params = { "root", "lstContrato" }),
	 * 
	 * @Result(name = "error", location = "/login.jsp") }, interceptorRefs
	 * = @InterceptorRef("authStack")) public String listarJson() { try {
	 * this.lstContrato = dao.listar(); } catch (Exception e) {
	 * addActionError(getText("listar.error")); return "error"; } return "success";
	 * }
	 */
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmUnidadeServico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadUnidadeServico() {
		try {
			this.lstUnidadeServico = UnidadeServicoDAOImpl.getInstance().listar();

		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}

		return "success";
	}

	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmUnidadeServico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.uservico = dao.getBean(this.id);
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
			beanResult.setRet(dao.inserir(this.uservico));
			if (beanResult.getRet() == 1)
				beanResult.setMensagem(getText("inserir.sucesso"));
			else
				beanResult.setMensagem(getText("inserir.error"));
		} catch (Exception e) {
			addActionError(getText("inserir.error") + " Error: " + e.getMessage());
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
			beanResult.setRet(dao.alterar(this.uservico));
			if (beanResult.getRet() == 1) {
				beanResult.setMensagem(getText("alterar.sucesso"));
			} else {
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
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.remover(this.uservico));
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

	public List<UnidadeServico> getLstUnidadeServico() {
		return lstUnidadeServico;
	}

	public void setLstUnidadeServico(List<UnidadeServico> lstUnidadeServico) {
		this.lstUnidadeServico = lstUnidadeServico;
	}

	public List<CADLocalvotacao> getLstLocalVotacao() {
		return lstLocalVotacao;
	}

	public void setLstLocalVotacao(List<CADLocalvotacao> lstLocalVotacao) {
		this.lstLocalVotacao = lstLocalVotacao;
	}

	public List<CADZonaEleitoral> getLstZonaEleitoral() {
		return lstZonaEleitoral;
	}

	public void setLstZonaEleitoral(List<CADZonaEleitoral> lstZonaEleitoral) {
		this.lstZonaEleitoral = lstZonaEleitoral;
	}

	public UnidadeServico getUservico() {
		return uservico;
	}

	public void setUservico(UnidadeServico uservico) {
		this.uservico = uservico;
	}

	public IDEleicaoPK getId() {
		return id;
	}

	public void setId(IDEleicaoPK id) {
		this.id = id;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

}
