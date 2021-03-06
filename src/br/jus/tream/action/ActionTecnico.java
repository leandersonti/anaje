package br.jus.tream.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.ContratoDAOImpl;
import br.jus.tream.DAO.EleicaoDAOImpl;
import br.jus.tream.DAO.TecnicoContratoDAOImpl;
import br.jus.tream.DAO.TecnicoDAO;
import br.jus.tream.DAO.TecnicoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Contrato;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.TecnicoContrato;
import br.jus.tream.dominio.pk.TecnicoContratoPK;

@SuppressWarnings("serial")
@Namespace("/tecnico")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionTecnico extends ActionSupport{
	private List<Tecnico> lstTecnico;
	private List<Contrato> lstContrato;
	private List<TecnicoContrato> lstDistribuicaoTecnicoContrato;
	private Contrato contrato;
	private TecnicoContrato tc;
	private String DtNasc;
	private Tecnico tecnico;
	private Integer id = 0;	
	private BeanResult result;	
	private final static TecnicoDAO dao = TecnicoDAOImpl.getInstance();	
	private final static Permissao permissao = Permissao.getInstance();
	
	
	@Action(value = "frmSetupTrocarCargo", results = { @Result(name = "success", location = "/forms/frmMudarCargoTecnico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp")}, interceptorRefs = @InterceptorRef("authStack"))
	public String frmSetupTrocarCargo() {
		if (permissao.getAdmin()) {
		    return "success";
		}else {
			addActionError(getText("permissao.negada"));
			return "error";
		}
	}
	
	@Action(value = "mudarContrato", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doMudarContrato() {
		BeanResult b = new BeanResult();
		try {
			int idContrato = tc.getId().getContrato().getId();
			if (permissao.getAdmin()) {
				TecnicoContrato tecnicoContrato = TecnicoContratoDAOImpl.getInstance().getBean(tc.getTecnico().getId());
				Contrato c = new Contrato();
				c.setId(idContrato);
				tecnicoContrato.getId().setContrato(c);
				b.setRet(TecnicoContratoDAOImpl.getInstance().mudarCargo(tecnicoContrato));
				b.setMensagem("Mudan�a de contrato realizado com sucesso!");
				b.setType("success");
			}else {
				addActionError(getText("permissao.negada"));
				return "error";
			}			
		} catch (Exception e) {
			b.setRet(0);
			b.setType("error");
			b.setMensagem(getText("alterar.error"));
			this.result = b;
			return "error";
		}
		  this.result = b;
		return "success";
	}
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/tecnico.jsp"),
			@Result(name = "error", location = "/result.jsp") },interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			this.lstTecnico = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listarJson", results = { @Result(name = "success", type = "json", params = { "root", "lstTecnico" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")})
	public String listarJson() {
		try {
			this.lstTecnico = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listarCbxJson", results = { @Result(name = "success", type = "json", params = { "root", "lstTecnico" }),
			@Result(name = "error", location = "/login.jsp")})
	public String listarCbxJson() {
		try {
			this.lstTecnico = dao.listarCbx();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "listarJsonByContrato", results = { @Result(name = "success", type = "json", params = { "root", "lstTecnico" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")})
	public String listarJsonByContrato() {
		try {
			this.lstTecnico = dao.listarCbx(contrato.getId());
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		} 
		return "success";
	}
	
	@Action(value = "listarJsonResponsavel", results = { @Result(name = "success", type = "json", params = { "root", "lstTecnico" }),
			@Result(name = "error", location = "/login.jsp")})
	public String listarJsonResponsavel() {
		try {
			this.lstTecnico = dao.listarCbxResponsavel();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "getBeanJson", results = { @Result(name = "success", type = "json", params = { "root", "tecnico" }),
			@Result(name = "error", location = "/login.jsp")})
	public String getBeanJson() {
		try {
			this.tecnico = TecnicoDAOImpl.getInstance().getBean(this.id);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmTecnico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadTecnico() {
		try {
			if (permissao.getAdmin()) {
				this.lstContrato = ContratoDAOImpl.getInstance().listar();
			    return "success";
			}else {
				addActionError(getText("permissao.negada"));
				return "error";
			}
		} catch (Exception e) {
			addActionError(getText("frmsetup.error"));
			return "error";
		}
	}
	
	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmTecnico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.tecnico = dao.getBean(this.tecnico.getId());
			lstDistribuicaoTecnicoContrato = TecnicoContratoDAOImpl.getInstance().listar(this.tecnico.getId());
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() throws ParseException {
		BeanResult beanResult = new BeanResult();		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (permissao.getAdmin()) {
				if(DtNasc!=null) {
					Date datanasc = sdf.parse(DtNasc);
					tecnico.setDataNasc(datanasc);			
				}		
				tecnico.setDataCad(new Date());			
				beanResult.setRet(dao.adicionar(tecnico));
				if (beanResult.getRet() == 1) {
					beanResult.setMensagem(getText("inserir.sucesso"));
					//ADICIONA CONTRATO
					TecnicoContratoPK tecContratopk = new TecnicoContratoPK();
					tecContratopk.setEleicao(EleicaoDAOImpl.getInstance().getBeanAtiva());
					tecContratopk.setContrato(this.contrato);
					tecContratopk.setTecnico(this.tecnico);
					TecnicoContrato dtc = new TecnicoContrato();
					dtc.setId(tecContratopk);
					TecnicoContratoDAOImpl.getInstance().adicionar(dtc);
					//
				}	
				else
					beanResult.setMensagem(getText("permissao.negada"));
			}else {
				
			}	
		} catch (Exception e) {
			    addActionError(getText("inserir.error") + " Error: " + e.getMessage());
			  //result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
		return "success";
	}
	
	@Action(value = "atualizar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAtualizar() throws ParseException {
		BeanResult beanResult = new BeanResult();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date datanasc = sdf.parse(DtNasc);
		beanResult.setRet(0);
		try {
			if (permissao.getAdmin()) {
				tecnico.setDataNasc(datanasc);		
				beanResult.setRet(dao.atualizar(this.tecnico));
				if (beanResult.getRet()==1) {
					beanResult.setMensagem(getText("alterar.sucesso"));
				}else {
					beanResult.setMensagem(getText("alterar.error")); 
				}
				
			}else {
				 beanResult.setMensagem(getText("permissao.negada"));
				this.result = beanResult;
			}
		}catch (Exception e) {
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
			beanResult.setRet(dao.remover(this.tecnico));
			beanResult.setMensagem(getText("remover.sucesso"));
		} catch (Exception e) {
			addActionError(getText("remover.error") + " Error: " + e.getMessage());
			// r.setMensagem(getText("remover.error") + " Error: " + e.getMessage());
			return "error";
		}
		this.result = beanResult;
	  return "success";
	}

	public List<Tecnico> getLstTecnico() {
		return lstTecnico;
	}

	public void setLstTecnico(List<Tecnico> lstTecnico) {
		this.lstTecnico = lstTecnico;
	}

	public List<Contrato> getLstContrato() {
		return lstContrato;
	}

	public void setLstContrato(List<Contrato> lstContrato) {
		this.lstContrato = lstContrato;
	}

	public List<TecnicoContrato> getLstDistribuicaoTecnicoContrato() {
		return lstDistribuicaoTecnicoContrato;
	}

	public void setLstDistribuicaoTecnicoContrato(List<TecnicoContrato> lstDistribuicaoTecnicoContrato) {
		this.lstDistribuicaoTecnicoContrato = lstDistribuicaoTecnicoContrato;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public String getDtNasc() {
		return DtNasc;
	}

	public void setDtNasc(String dtNasc) {
		DtNasc = dtNasc;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public TecnicoContrato getTc() {
		return tc;
	}

	public void setTc(TecnicoContrato tecnicoContrato) {
		this.tc = tecnicoContrato;
	}

	

}
