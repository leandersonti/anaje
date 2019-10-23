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
import br.jus.tream.DAO.DistribuicaoEquipamentoDAO;
import br.jus.tream.DAO.DistribuicaoEquipamentoDAOImpl;
import br.jus.tream.DAO.EquipamentoDAOImpl;
import br.jus.tream.DAO.EquipamentoTipoDAOImpl;
import br.jus.tream.DAO.PontoTransmissaoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.CADZonaEleitoral;
import br.jus.tream.dominio.DistribuicaoEquipamento;
import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.EquipamentoTipo;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.PontoTransmissao;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.DistribuicaoEquipamentoPK;

@SuppressWarnings("serial")
@Namespace("/distribequipamento")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionDistribuicaoEquipamento extends ActionSupport {
	private List<CADZonaEleitoral> lstZonaEleitoral;
	private List<EquipamentoTipo> lstEquipamentoTipo;
	private List<Equipamento> lstEquipamento;
	private List<DistribuicaoEquipamento> lstDistribuicaoEquipamento;
	private String codZonaMunic;
	private BeanResult result;
	private PontoTransmissao pontoTransmissao;
	private DistribuicaoEquipamento de;
	private Equipamento equipamento;
	private final static DistribuicaoEquipamentoDAO dao = DistribuicaoEquipamentoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmDistribuicaoEquipamento.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCad() {

		try {
			this.lstEquipamentoTipo = EquipamentoTipoDAOImpl.getInstance().listar();
			this.lstEquipamento = EquipamentoDAOImpl.getInstance().listar();
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

	@Action(value = "setuplistar", results = { @Result(name = "success", location = "/consultas/distribuicaoEquipamento.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String setupListar() {
		try {
			if (permissao.getAdmin()) {
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX();
			} else {				
				this.lstZonaEleitoral = CadEloDAOImpl.getInstance().listarZonaEleitoralCBX(permissao.getZona());
			}
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listar", results = { 
			@Result(name = "success", type = "json", params = { "root", "lstDistribuicaoEquipamento" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")}
	    //, interceptorRefs = @InterceptorRef("authStack")
	)
	public String listar() {
		try {
			//if (permissao.getAdmin()) {
			//	this.lstDistribuicaoEquipamento = dao.listar();
			//} else {
				
				CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
				System.out.println("ZonaMunic=" + pkze.getZona() + " / " + pkze.getCodmunic());
				this.lstDistribuicaoEquipamento = dao.listar(pkze);
			//}
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listarByPontoTransmissaoJson", results = { 
			@Result(name = "success", type = "json", params = { "root", "lstDistribuicaoEquipamento" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")}
	    //, interceptorRefs = @InterceptorRef("authStack")
	)
	public String listarByPontoTransmissao() {
		try {
			if (codZonaMunic != null) {
				CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
				this.lstDistribuicaoEquipamento = dao.listar(pkze);
			}	
			else	
			   this.lstDistribuicaoEquipamento = dao.listar(pontoTransmissao.getId().getId());
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "adicionar", results = { 
			@Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "input", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")}, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		beanResult.setRet(0);
		try {
			this.pontoTransmissao = PontoTransmissaoDAOImpl.getInstance().getBean(this.pontoTransmissao.getId().getId());
				if (permissao.getAdmin() || permissao.getZona() == this.pontoTransmissao.getZona()) {					
					DistribuicaoEquipamentoPK pk = new DistribuicaoEquipamentoPK();
					pk.setPontoTransmissao(pontoTransmissao);
					pk.setEquipamento(equipamento);
					de.setId(pk);
					Tecnico tec = new Tecnico(1,"SISTEMA");
					de.setTecnico(tec);
					beanResult.setRet(dao.adicionar(de));
					if (beanResult.getRet() == 1)
						beanResult.setMensagem(getText("inserir.sucesso"));
					else
						beanResult.setMensagem(getText("inserir.error"));
				} else {
					beanResult.setRet(0);
					beanResult.setMensagem(getText("permissao.negada"));
				}
			} catch (Exception e) {
				addActionError(getText("inserir.error") + " Error: " + e.getMessage());
				beanResult.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
				this.result = beanResult;
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
				this.equipamento = EquipamentoDAOImpl.getInstance().getBean(de.getId().getEquipamento().getId());				
				this.pontoTransmissao = PontoTransmissaoDAOImpl.getInstance().getBean(de.getId().getPontoTransmissao().getId().getId());
				
				de.getId().setPontoTransmissao(pontoTransmissao);
				de.getId().setEquipamento(equipamento);	
				
				beanResult.setRet(dao.remover(de));						
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

	public PontoTransmissao getPontoTransmissao() {
		return pontoTransmissao;
	}

	public void setPontoTransmissao(PontoTransmissao us) {
		this.pontoTransmissao = us;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
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

	public List<EquipamentoTipo> getLstEquipamentoTipo() {
		return lstEquipamentoTipo;
	}

	public void setLstEquipamentoTipo(List<EquipamentoTipo> lstEquipamentoTipo) {
		this.lstEquipamentoTipo = lstEquipamentoTipo;
	}

	public List<Equipamento> getLstEquipamento() {
		return lstEquipamento;
	}

	public void setLstEquipamento(List<Equipamento> lstEquipamento) {
		this.lstEquipamento = lstEquipamento;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public DistribuicaoEquipamento getDe() {
		return de;
	}

	public void setDe(DistribuicaoEquipamento de) {
		this.de = de;
	}

	public List<DistribuicaoEquipamento> getLstDistribuicaoEquipamento() {
		return lstDistribuicaoEquipamento;
	}

	public void setLstDistribuicaoEquipamento(List<DistribuicaoEquipamento> lstDistribuicaoEquipamento) {
		this.lstDistribuicaoEquipamento = lstDistribuicaoEquipamento;
	}
}
