package br.jus.tream.action;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.CadEloDAOImpl;
import br.jus.tream.DAO.DistribuicaoTecnicoDAO;
import br.jus.tream.DAO.DistribuicaoTecnicoDAOImpl;
import br.jus.tream.DAO.PontoTransmissaoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.CADZonaEleitoral;
import br.jus.tream.dominio.Contrato;
import br.jus.tream.dominio.DistribuicaoSecao;
import br.jus.tream.dominio.DistribuicaoTecnico;
import br.jus.tream.dominio.PontoTransmissao;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
@SuppressWarnings("serial")
@Namespace("/distribtecnico")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionDistribuicaoTecnico extends ActionSupport{
	private List<CADSecao> lstCadSecao;
	private List<DistribuicaoSecao> lstDistribuicaoSecao;
	private List<CADZonaEleitoral> lstZonaEleitoral;
	private List<Tecnico> lstTecnico;
	private List<DistribuicaoTecnico> lstDistribuicaoTecnico;
	private BeanResult result;
	private PontoTransmissao pt;
	private DistribuicaoSecao ds;
	private Tecnico t;
	private Contrato contrato;
	private DistribuicaoTecnico dst;
	private String codZonaMunic;
	private final static DistribuicaoTecnicoDAO dao = DistribuicaoTecnicoDAOImpl.getInstance();
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
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/distribuicaoTecnico.jsp"),
			@Result(name = "error", location = "/result.jsp")}, interceptorRefs = @InterceptorRef("authStack")
	)
	public String listar() {
		try {
				
			if (permissao.getAdmin()) {				
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();						
			} else {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX(permissao.getZona());							
			}
			
			if(codZonaMunic != null) {				
				CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
				this.lstDistribuicaoTecnico = dao.listar(pkze, contrato.getId());
			}
			
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success"; 
	}
	
	@Action(value = "listarParaDistribuirJson", results = { @Result(name = "success", type = "json", params = { "root", "lstTecnico" }),
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarParaDistribuirJson() {
		try {
			if (permissao.getAdmin()) {				
				this.lstTecnico = dao.listarParaDistribuir(contrato.getId());
			} else {
				this.lstTecnico = dao.listarParaDistribuir(permissao.getZona(), contrato.getId());
			}
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listarDistribuidosJson", results = { @Result(name = "success", type = "json", params = { "root", "lstDistribuicaoTecnico" }),
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarDistribuidosJson() {
		try {			
			
				this.lstDistribuicaoTecnico = dao.listar(dst.getId().getPontoTransmissao().getId().getId());
				
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
			if (permissao.getAdmin() || permissao.getZona() == pkze.getZona()) {												
				dst.setDataCad(new Date());							
				pt = PontoTransmissaoDAOImpl.getInstance().getBean(pt.getId().getId());
				dst.getId().setPontoTransmissao(pt);
			
				beanResult.setRet(dao.adicionar(dst));
				beanResult.setType("success");
				beanResult.setMensagem(getText("inserir.sucesso"));
			}else{
				beanResult.setRet(0);
				beanResult.setType("error");
				beanResult.setMensagem(getText("permissao.negada"));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
				beanResult.setRet(dao.remover(dst));				
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

	public DistribuicaoTecnico getDst() {
		return dst;
	}

	public void setDst(DistribuicaoTecnico dst) {
		this.dst = dst;
	}

	public Tecnico getT() {
		return t;
	}

	public void setT(Tecnico t) {
		this.t = t;
	}

	public PontoTransmissao getPt() {
		return pt;
	}

	public void setPt(PontoTransmissao pt) {
		this.pt = pt;
	}

	public List<Tecnico> getLstTecnico() {
		return lstTecnico;
	}

	public void setLstTecnico(List<Tecnico> lstTecnico) {
		this.lstTecnico = lstTecnico;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<DistribuicaoTecnico> getLstDistribuicaoTecnico() {
		return lstDistribuicaoTecnico;
	}

	public void setLstDistribuicaoTecnico(List<DistribuicaoTecnico> lstDistribuicaoTecnico) {
		this.lstDistribuicaoTecnico = lstDistribuicaoTecnico;
	}
	
	
}