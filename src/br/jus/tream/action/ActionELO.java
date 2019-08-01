package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.CadEloDAO;
import br.jus.tream.DAO.CadEloDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.CADLocalvotacao;
import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.CADZonaEleitoral;

@SuppressWarnings("serial")
@Namespace("/elo")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionELO extends ActionSupport {
	private List<CADLocalvotacao> lstLocalVotacao;
	private List<CADZonaEleitoral> lstZonaEleitoral;
	private List<CADSecao> lstSecao;
	private CADLocalvotacao localVotacao;
	private CADZonaEleitoral zonaEleitoral;
	private CADSecao secao;
	private Integer zona;
	private Integer codmunic;
	private Integer numLocal;
	private BeanResult result;
	private final static CadEloDAO dao = CadEloDAOImpl.getInstance();
	// private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "listarZonasEleitorais", results = { @Result(name = "success", location = "/consultas/elo/zonas-eleitorais.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarZonaEleitoral() {
		try {
			this.lstZonaEleitoral = dao.listarZonaEleitoral();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJsonZonaEleitoral", results = {
			@Result(name = "success", type = "json", params = { "root", "lstZonaEleitoral" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String listarJsonZonaEleitoral() {
		try {
			this.lstZonaEleitoral = dao.listarZonaEleitoral();
		} catch (Exception e) {
			addActionError(getText("listar.error") + " table: ZonaEleitoral");
			return "error";
		}
		return "success";
	}

	@Action(value = "getBeanJsonLocalVotacao", results = {
			@Result(name = "success", type = "json", params = { "root", "localVotacao" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String getBeanJsonLocalVotacao() {
		try {
			this.localVotacao = dao.getBeanLocalVotacao(this.zona, this.codmunic, this.numLocal);
		} catch (Exception e) {
			addActionError(getText("listar.error") + " table: LocalVotacao");
			return "error";
		}
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

	public List<CADSecao> getLstSecao() {
		return lstSecao;
	}

	public void setLstSecao(List<CADSecao> lstSecao) {
		this.lstSecao = lstSecao;
	}

	public CADLocalvotacao getLocalVotacao() {
		return localVotacao;
	}

	public void setLocalVotacao(CADLocalvotacao localVotacao) {
		this.localVotacao = localVotacao;
	}

	public CADZonaEleitoral getZonaEleitoral() {
		return zonaEleitoral;
	}

	public void setZonaEleitoral(CADZonaEleitoral zonaEleitoral) {
		this.zonaEleitoral = zonaEleitoral;
	}

	public CADSecao getSecao() {
		return secao;
	}

	public void setSecao(CADSecao secao) {
		this.secao = secao;
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

	public Integer getNumLocal() {
		return numLocal;
	}

	public void setNumLocal(Integer numLocal) {
		this.numLocal = numLocal;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

}
