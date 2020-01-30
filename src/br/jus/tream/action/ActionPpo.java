package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.DistribuicaoTecnicoDAOImpl;
import br.jus.tream.DAO.EleicaoDAOImpl;
import br.jus.tream.DAO.PpoDAO;
import br.jus.tream.DAO.PpoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.DistribuicaoTecnico;
import br.jus.tream.dominio.Ppo;
import br.jus.tream.dominio.VWPpo;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

@SuppressWarnings("serial")
@Namespace("/ppo")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionPpo extends ActionSupport {
	private List<Ppo> lstPpo;
	private List<VWPpo> lstVWPpo;
	private Ppo ppo;
	private String codZonaMunic;
	private PontoTransmissaoPK pkPonto = new PontoTransmissaoPK();
	private BeanResult result;
	private String tituloEleitor;
	private Integer idTecnicoResponsavel;
	private Integer id;
	private final static PpoDAO dao = PpoDAOImpl.getInstance();
	
	@Action(value = "frmSetupReinicializa", results = { @Result(name = "success", location = "/forms/frmReinicializarPPO.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCad() {
		return "success";
	}
	
	@Action(value = "listarView", results = { @Result(name = "success", location = "/consultas/ppoview.jsp"),
			@Result(name = "error", location = "/result.jsp")}, interceptorRefs = @InterceptorRef("authStack")
	)
	public String listarView() {
		try {
			CadZonaEleitoralPK pkzona = new CadZonaEleitoralPK(codZonaMunic);
			this.lstVWPpo = dao.listarView(pkzona, idTecnicoResponsavel);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listarJsonByTitulo", results = {
			@Result(name = "success", type = "json", params = { "root", "lstPpo" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String listarJsonByTitulo() {
		try {
			//System.out.println("==" + tituloEleitor);
			this.lstPpo = dao.listar(tituloEleitor);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}


	@Action(value = "getBeanJson", results = { @Result(name = "success", type = "json", params = { "root", "ppo" }),
			@Result(name = "error", location = "/login.jsp") })
	public String getBeanJson() {
		try {
			this.ppo = PpoDAOImpl.getInstance().getBean(this.id);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "reiniciarJson", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String reiniciar() {
		BeanResult beanResult = new BeanResult();
		beanResult.setType("success");
		try {
			// (CadZonaEleitoralPK pkzona, PontoTransmissaoPK ponto) 
			CadZonaEleitoralPK pkzona = new CadZonaEleitoralPK(this.codZonaMunic);
			beanResult.setRet(PpoDAOImpl.getInstance().reinicializar(pkzona, pkPonto));
			beanResult.setMensagem(getText("ppo.reinicializar.sucesso"));
			beanResult.setType("success");
		} catch (Exception e) {
			 beanResult.setType("warning");
			 beanResult.setMensagem(getText("ppo.error.reinicializar") + " Error: " + e.getMessage());
			//return "error";
		}
		this.result = beanResult;
		return "success";
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
				// VERIFICA SE O TÉCNICO ESTÁ DISTRIBUIDO
				DistribuicaoTecnico distribuicaoTecnico = DistribuicaoTecnicoDAOImpl.getInstance().getBean(tituloEleitor);
				if (distribuicaoTecnico.getId().getTecnico()!=null) {
					this.ppo.setTecnico(distribuicaoTecnico.getId().getTecnico());
					this.ppo.setDataEleicao(EleicaoDAOImpl.getInstance().getBeanAtiva());
						beanResult.setRet(dao.adicionar(ppo));
						if (beanResult.getRet() == 1) {
							beanResult.setMensagem(getText("inserir.sucesso"));
							beanResult.setType("success");
						}
						else {
							beanResult.setMensagem(getText("inserir.violado"));
							beanResult.setType("warning");
						}
				}else {
					beanResult.setType("warning");
					beanResult.setMensagem(getText("ppo.error.distrib.tec"));
				}	
			} catch (Exception e) {
				  addActionError(getText("alterar.error") + " Error: " + e.getMessage());
				return "error";
			}
		  this.result = beanResult;
		return "success";
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public List<Ppo> getLstPpo() {
		return lstPpo;
	}

	public void setLstPpo(List<Ppo> lstPpo) {
		this.lstPpo = lstPpo;
	}

	public Ppo getPpo() {
		return ppo;
	}

	public void setPpo(Ppo ppo) {
		this.ppo = ppo;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public Integer getIdTecnicoResponsavel() {
		return idTecnicoResponsavel;
	}

	public void setIdTecnicoResponsavel(Integer idTecnicoResponsavel) {
		this.idTecnicoResponsavel = idTecnicoResponsavel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodZonaMunic() {
		return codZonaMunic;
	}

	public void setCodZonaMunic(String codZonaMunic) {
		this.codZonaMunic = codZonaMunic;
	}

	public PontoTransmissaoPK getPkPonto() {
		return pkPonto;
	}

	public void setPkPonto(PontoTransmissaoPK pkPonto) {
		this.pkPonto = pkPonto;
	}

	public List<VWPpo> getLstVWPpo() {
		return lstVWPpo;
	}

	public void setLstVWPpo(List<VWPpo> lstVWPpo) {
		this.lstVWPpo = lstVWPpo;
	}

}
