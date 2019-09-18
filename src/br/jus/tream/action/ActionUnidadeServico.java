package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.CadEloDAOImpl;
import br.jus.tream.DAO.DataEleicaoDAOImpl;
import br.jus.tream.DAO.UnidadeServicoDAO;
import br.jus.tream.DAO.UnidadeServicoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.CADLocalvotacao;
import br.jus.tream.dominio.CADZonaEleitoral;
import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.UnidadeServico;
import br.jus.tream.dominio.pk.UnidadeServicoPK;

@SuppressWarnings("serial")
@Namespace("/uservico")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionUnidadeServico extends ActionSupport {
	private List<UnidadeServico> lstUnidadeServico;
	private List<CADLocalvotacao> lstLocalVotacao;
	private List<CADZonaEleitoral> lstZonaEleitoral;
	private CADZonaEleitoral cadZonaEleitoral;
	private UnidadeServico uservico;
	private UnidadeServicoPK id = new UnidadeServicoPK();
	private BeanResult result;
	private String codZonaMunic;
	private Integer zona;
	private Integer codmunic;
	private final static UnidadeServicoDAO dao = UnidadeServicoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/unidade-servico.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			this.lstUnidadeServico = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = { @Result(name = "success", type = "json", params = { "root", "lstUnidadeServico" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarJson() {
		try {
			// PEGANDO CODZONAMUNIC
			String[] zonamunic = this.codZonaMunic.split(";");
			this.lstUnidadeServico = dao.listar(Integer.valueOf(zonamunic[0]), Integer.valueOf(zonamunic[1]));
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
				
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmUnidadeServico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadUnidadeServico() {
		try {
			if (permissao.getAdmin()) {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
			} else {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
			}
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
			if (permissao.getAdmin()) {
				return "success";
			} else {
				if (permissao.getZona()==this.uservico.getZona()) {
					return "success";
				}else {
					addActionError(getText("permissao.negada"));
					return "error";
				}
			}
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			UnidadeServicoPK pk = new UnidadeServicoPK();
			DataEleicao dtEleicao = new DataEleicao();
			// PEGANDO ELEIÇÃO ATIVA
			dtEleicao = DataEleicaoDAOImpl.getInstance().getBeanAtiva();
			pk.setDataEleicao(dtEleicao);
			this.uservico.setId(pk);			
			// PEGANDO CODZONAMUNIC
			String[] zonamunic = this.codZonaMunic.split(";");
			this.uservico.setCodmunic(Integer.valueOf(zonamunic[1]));
			this.uservico.setZona(Integer.valueOf(zonamunic[0]));
			beanResult.setRet(dao.adicionar(this.uservico));
			if (beanResult.getRet() == 1)
				beanResult.setMensagem(getText("inserir.sucesso"));
			else
				beanResult.setMensagem(getText("inserir.error"));
			
		} catch (Exception e) {
			    // System.out.println("Erro " + e.getMessage());
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
			beanResult.setRet(dao.atualizar(this.uservico));			
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
			 beanResult.setMensagem(getText("remover.error") + " Error: " + e.getMessage());
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

	public UnidadeServicoPK getId() {
		return id;
	}

	public void setId(UnidadeServicoPK id) {
		this.id = id;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public CADZonaEleitoral getCadZonaEleitoral() {
		return cadZonaEleitoral;
	}

	public void setCadZonaEleitoral(CADZonaEleitoral cadZonaEleitoral) {
		this.cadZonaEleitoral = cadZonaEleitoral;
	}

	public String getCodZonaMunic() {
		return codZonaMunic;
	}

	public void setCodZonaMunic(String codZonaMunic) {
		this.codZonaMunic = codZonaMunic;
	}

	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona = zona;
	}

	public Integer getCodmunic() {
		return codmunic;
	}

	public void setCodmunic(Integer codmunic) {
		this.codmunic = codmunic;
	}

}
