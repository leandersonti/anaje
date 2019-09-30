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
import br.jus.tream.DAO.DistribuicaoSecaoDAO;
import br.jus.tream.DAO.DistribuicaoSecaoDAOImpl;
import br.jus.tream.DAO.PontoTransmissaoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.CADZonaEleitoral;
import br.jus.tream.dominio.DistribuicaoSecao;
import br.jus.tream.dominio.PontoTransmissao;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.DistribuicaoSecaoPK;

@SuppressWarnings("serial")
@Namespace("/distribtecnico")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionDistribuicaoTecnico extends ActionSupport{
	private List<CADSecao> lstCadSecao;
	private List<DistribuicaoSecao> lstDistribuicaoSecao;
	private List<CADZonaEleitoral> lstZonaEleitoral;
	private BeanResult result;
	private PontoTransmissao us;
	private DistribuicaoSecao ds;
	private String codZonaMunic;
	private final static DistribuicaoSecaoDAO dao = DistribuicaoSecaoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();
	private Integer zona, codmunic,numlocal;
	private String[] secoesCbx;
	
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmDistribuicaoTecnicos.jsp"),
			@Result(name = "error", location = "/pages/error.jsp")}, interceptorRefs = @InterceptorRef("authStack"))
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
	
	@Action(value = "listarParaDistribuirJson", results = { @Result(name = "success", type = "json", params = { "root", "lstCadSecao" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String listarSecaoParaDistribuiJson() {
		try {
			// PEGANDO CODZONAMUNIC
			CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
			this.lstCadSecao = dao.listarParaDistribuir(pkze, numlocal);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/data-eleicao.jsp"),
			@Result(name = "error", location = "/result.jsp")}, interceptorRefs = @InterceptorRef("authStack")
	)
	public String listar() {
		try {
			if (permissao.getAdmin()) {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
			} else {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoral(permissao.getZona());
			}
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
			if (permissao.getAdmin() || permissao.getZona()==zona) {
				this.us = PontoTransmissaoDAOImpl.getInstance().getBean(this.us.getId().getId());
				ds.getId().setPontoTransmissaoo(us);
				ds.setZona(pkze.getZona());
				ds.setCodmunic(pkze.getCodmunic());
				ds.setVetsecoes(this.secoesCbx);
				beanResult.setRet(dao.adicionar(ds));
				beanResult.setMensagem(getText("inserir.sucesso") + " (" + secoesCbx.length + " Secao(oes))");
			}else
			{
				beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
			}
		} catch (Exception e) {
			    addActionError(getText("inserir.error") + " Error: " + e.getMessage());
			    beanResult.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
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
				beanResult.setRet(1);
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
	
	public DistribuicaoSecao getDs() {
		return ds;
	}

	public void setDs(DistribuicaoSecao ds) {
		this.ds = ds;
	}

	public PontoTransmissao getUs() {
		return us;
	}

	public void setUs(PontoTransmissao us) {
		this.us = us;
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

	public Integer getNumlocal() {
		return numlocal;
	}

	public void setNumlocal(Integer numlocal) {
		this.numlocal = numlocal;
	}

	public BeanResult getResult() {
		return result;
	}
	public void setResult(BeanResult result) {
		this.result = result;
	}

	public List<CADSecao> getLstCadSecao() {
		return lstCadSecao;
	}

	public void setLstCadSecao(List<CADSecao> lstCadSecao) {
		this.lstCadSecao = lstCadSecao;
	}

	public List<DistribuicaoSecao> getLstDistribuicaoSecao() {
		return lstDistribuicaoSecao;
	}

	public void setLstDistribuicaoSecao(List<DistribuicaoSecao> lstDistribuicaoSecao) {
		this.lstDistribuicaoSecao = lstDistribuicaoSecao;
	}

	public List<CADZonaEleitoral> getLstZonaEleitoral() {
		return lstZonaEleitoral;
	}

	public void setLstZonaEleitoral(List<CADZonaEleitoral> lstZonaEleitoral) {
		this.lstZonaEleitoral = lstZonaEleitoral;
	}

	public String getCodZonaMunic() {
		return codZonaMunic;
	}

	public void setCodZonaMunic(String codZonaMunic) {
		this.codZonaMunic = codZonaMunic;
	}

	public String[] getSecoesCbx() {
		return secoesCbx;
	}

	public void setSecoesCbx(String[] secoesCbx) {
		this.secoesCbx = secoesCbx;
	}
	
}
