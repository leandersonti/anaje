package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
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
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

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
	private CadZonaEleitoralPK pkze;
	private Integer zona;
	private Integer codmunic;
	private Integer numLocal;
	private String codZonaMunic;
	private BeanResult result;
	private final static CadEloDAO dao = CadEloDAOImpl.getInstance();
	// private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "listarZonasEleitorais", results = { @Result(name = "success", location = "/consultas/elo/zonas-eleitorais.jsp"),
			@Result(name = "error", location = "/result.jsp")})
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
			@Result(name = "error", location = "/pages/resultAjax.jsp")})
	public String listarJsonZonaEleitoral() {
		try {
			Permissao permissao = Permissao.getInstance();
			if (permissao.getAdmin())
				this.lstZonaEleitoral = dao.listarZonaEleitoral();
			else
				this.lstZonaEleitoral = dao.listarZonaEleitoral(permissao.getZona());
		} catch (Exception e) {
			addActionError(getText("listar.error") + " table: ZonaEleitoral");
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listarJsonZonaEleitoralCBX", results = {
			@Result(name = "success", type = "json", params = { "root", "lstZonaEleitoral" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")})
	public String listarJsonZonaEleitoralCBX() {
		try {
			Permissao permissao = Permissao.getInstance();
			if (permissao.getAdmin())
				this.lstZonaEleitoral = dao.listarZonaEleitoralCBX();
			else
				this.lstZonaEleitoral = dao.listarZonaEleitoralCBX(permissao.getZona());
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
	
	@Action(value = "listarJsonLocalVotacaoParaCadastrar", results = {
			@Result(name = "success", type = "json", params = { "root", "lstLocalVotacao" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String listarJsonLocalVotacaoParaCadastrar() {
		try {
			// PEGANDO CODZONAMUNIC
			String[] zonamunic = this.codZonaMunic.split(";");
			this.lstLocalVotacao = dao.listarLocalVotacaoParaCadastrar(Integer.valueOf(zonamunic[0]), 
																	   Integer.valueOf(zonamunic[1]));
		} catch (Exception e) {
			addActionError(getText("listar.error") + " table: LocalVotacao");
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJsonLocalVotacao", results = {
			@Result(name = "success", type = "json", params = { "root", "lstLocalVotacao" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String listarJsonLocalVotacao() {
		try {
			// PEGANDO CODZONAMUNIC
			String[] zonamunic = this.codZonaMunic.split(";");
			this.lstLocalVotacao = dao.listarLocalVotacao(Integer.valueOf(zonamunic[0]), 
																	   Integer.valueOf(zonamunic[1]));
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

	public String getCodZonaMunic() {
		return codZonaMunic;
	}

	public void setCodZonaMunic(String codZonaMunic) {
		this.codZonaMunic = codZonaMunic;
	}

	public CadZonaEleitoralPK getPkze() {
		return pkze;
	}

	public void setPkze(CadZonaEleitoralPK pkze) {
		this.pkze = pkze;
	}

}
