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
import br.jus.tream.DAO.DistribuicaoEquipamentoDAOImpl;
import br.jus.tream.DAO.DistribuicaoSecaoDAOImpl;
import br.jus.tream.DAO.DistribuicaoTecnicoDAOImpl;
import br.jus.tream.DAO.EleicaoDAOImpl;
import br.jus.tream.DAO.PontoTransmissaoDAO;
import br.jus.tream.DAO.PontoTransmissaoDAOImpl;
import br.jus.tream.dominio.BeanPontoTransmissao;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.CADLocalvotacao;
import br.jus.tream.dominio.CADZonaEleitoral;
import br.jus.tream.dominio.PontoTransmissao;
import br.jus.tream.dominio.SRHServidores;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

@SuppressWarnings("serial")
@Namespace("/pontotrans")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionPontoTransmissao extends ActionSupport {
	private List<PontoTransmissao> lstPontoTransmissao;
	private List<CADLocalvotacao> lstLocalVotacao;
	private List<CADZonaEleitoral> lstZonaEleitoral;
	private List<SRHServidores> lstServidores;
	private List<String> lstPontos;
	private CADZonaEleitoral cadZonaEleitoral;
	private PontoTransmissao pt;
	private BeanPontoTransmissao beanPontoTransmissao;
	private PontoTransmissaoPK id = new PontoTransmissaoPK();
	private BeanResult result;
	private String codZonaMunic;
	private Integer zona;
	private Integer codmunic;
	private final static PontoTransmissaoDAO dao = PontoTransmissaoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "getBeanFull", results = {
			@Result(name = "success", location = "/consultas/ponto-transmissao-bean-full.jsp"),
			@Result(name = "error", location = "/result.jsp") })
	public String getBeanFull() {
		try {
			BeanPontoTransmissao pontoT = new BeanPontoTransmissao();
			pontoT.setPontoTransmissao(dao.getBean(id.getId()));
			beanPontoTransmissao = pontoT;
			beanPontoTransmissao.setSecoesDistribuidas(
					DistribuicaoSecaoDAOImpl.getInstance().listarByClassLocalVotacao(id.getId()));
			beanPontoTransmissao
					.setEquipamentosDistribuidos(DistribuicaoEquipamentoDAOImpl.getInstance().listar(id.getId()));
			beanPontoTransmissao.setTecnicosDistribuidos(DistribuicaoTecnicoDAOImpl.getInstance().listar(id.getId()));
		} catch (Exception e) {
			addActionError(getText("getbean.error") + ". Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "getBeanFullJson", results = {
			@Result(name = "success", type = "json", params = { "root", "beanPontoTransmissao" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }
	// , interceptorRefs = @InterceptorRef("authStack")
	)
	public String getBeanFullJson() {
		try {
			BeanPontoTransmissao pontoT = new BeanPontoTransmissao();
			pontoT.setPontoTransmissao(dao.getBean(id.getId()));
			beanPontoTransmissao = pontoT;
			beanPontoTransmissao.setSecoesDistribuidas(
					DistribuicaoSecaoDAOImpl.getInstance().listarByClassLocalVotacao(id.getId()));
			beanPontoTransmissao
					.setEquipamentosDistribuidos(DistribuicaoEquipamentoDAOImpl.getInstance().listar(id.getId()));
		} catch (Exception e) {
			addActionError(getText("getbean.error") + ". Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/ponto-transmissao.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			if (permissao.getAdmin()) {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
				if (codZonaMunic != null) {
					CadZonaEleitoralPK pk = new CadZonaEleitoralPK(codZonaMunic);
					this.lstPontoTransmissao = dao.listar(pk);
				} else {
					this.lstPontoTransmissao = dao.listar();
				}
			} else {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX(permissao.getZona());
				if (codZonaMunic == null || codZonaMunic.equals("-1")) {

				} else {
					CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
					this.lstPontoTransmissao = dao.listar(pkze);
				}
			}

		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarSemDistribuicaoSecao", results = {
			@Result(name = "success", location = "/consultas/pontoSemDistribuicaoSecao.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarSemDistribuicaoSecao() {
		try {
			System.out.println("===" + codZonaMunic);
			if (permissao.getAdmin()) {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
				if (codZonaMunic == null || codZonaMunic.equals("-1")) {

				} else {
					CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
					System.out.println("===" + codZonaMunic);
					this.lstPontoTransmissao = dao.listarSemDistribuicaoSecao(pkze);
				}
			} else {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX(permissao.getZona());
				if (codZonaMunic == null || codZonaMunic.equals("-1")) {

				} else {
					CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
					System.out.println("===" + codZonaMunic);
					this.lstPontoTransmissao = dao.listarSemDistribuicaoSecao(pkze);
				}
			}

		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarSemDistribuicaoTecnico", results = {
			@Result(name = "success", location = "/consultas/pontoSemDistribuicaoTecnico.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarSemDistribuicaoTecnico() {
		try {

			if (permissao.getAdmin()) {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
				if (codZonaMunic == null || codZonaMunic.equals("-1")) {

				} else {
					CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
					this.lstPontoTransmissao = dao.listarSemDistribuicaoTecnico(pkze);
				}
			} else {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX(permissao.getZona());
				if (codZonaMunic == null || codZonaMunic.equals("-1")) {

				} else {
					CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
					this.lstPontoTransmissao = dao.listarSemDistribuicaoTecnico(pkze);
				}
			}

		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = {
			@Result(name = "success", type = "json", params = { "root", "lstPontoTransmissao" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarJson() {
		try {
			// PEGANDO CODZONAMUNIC
			CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
			this.lstPontoTransmissao = dao.listar(pkze);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarSemOficializar", results = {
			@Result(name = "success", type = "json", params = { "root", "lstPontoTransmissao" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarSemOficializar() {
		try {
			// PEGANDO CODZONAMUNIC
			CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
			this.lstPontoTransmissao = dao.listarSemOficializar(pkze);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmPontoTransmissao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCad() {
		try {
			if (permissao.getAdmin()) {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
			} else {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX(permissao.getZona());
			}
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "frmOficializar", results = { @Result(name = "success", location = "/forms/frmOficializar.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmOficializar() {
		try {

			if (permissao.getAdmin()) {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
			} else {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX(permissao.getZona());
			}

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmPontoTransmissao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.pt = dao.getBean(this.id);
			if (permissao.getAdmin()) {
				return "success";
			} else {
				if (permissao.getZona() == this.pt.getZona()) {
					return "success";
				} else {
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
			PontoTransmissaoPK pk = new PontoTransmissaoPK();
			CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
			pk.setEleicao(EleicaoDAOImpl.getInstance().getBeanAtiva());
			this.pt.setId(pk);
			this.pt.setCodmunic(pkze.getCodmunic());
			this.pt.setZona(pkze.getZona());
			beanResult.setRet(dao.adicionar(this.pt));
			/*
			 * for (String item : this.lstPontos) {
			 * beanResult.setRet(dao.adicionar(this.pt)); }
			 */
			if (beanResult.getRet() == 1) {
				beanResult.setMsg(getText("inserir.sucesso"), "error");
			} else {
				beanResult.setMsg(getText("inserir.error"), "error");
			}
			beanResult.setMensagem(getText("inserir.sucesso"));

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
			beanResult.setRet(dao.atualizar(this.pt));
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
				beanResult.setRet(dao.remover(this.pt));
				if (beanResult.getRet() == 1)
					beanResult.setMensagem(getText("remover.sucesso"));
				else
					beanResult.setMensagem(getText("restricao.integridade.violada"));
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

	public PontoTransmissao getPt() {
		return pt;
	}

	public void setPt(PontoTransmissao pt) {
		this.pt = pt;
	}

	public PontoTransmissaoPK getId() {
		return id;
	}

	public void setId(PontoTransmissaoPK id) {
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

	public BeanPontoTransmissao getBeanPontoTransmissao() {
		return beanPontoTransmissao;
	}

	public void setBeanPontoTransmissao(BeanPontoTransmissao beanPontoTransmissao) {
		this.beanPontoTransmissao = beanPontoTransmissao;
	}

	public List<PontoTransmissao> getLstPontoTransmissao() {
		return lstPontoTransmissao;
	}

	public void setLstPontoTransmissao(List<PontoTransmissao> lstPontoTransmissao) {
		this.lstPontoTransmissao = lstPontoTransmissao;
	}

	public List<SRHServidores> getLstServidores() {
		return lstServidores;
	}

	public void setLstServidores(List<SRHServidores> lstServidores) {
		this.lstServidores = lstServidores;
	}

	public List<String> getLstPontos() {
		return lstPontos;
	}

	public void setLstPontos(List<String> lstPontos) {
		this.lstPontos = lstPontos;
	}

}
