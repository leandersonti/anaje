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
import br.jus.tream.DAO.DataEleicaoDAOImpl;
import br.jus.tream.DAO.DistribuicaoTecContratoDAOImpl;
import br.jus.tream.DAO.TecnicoDAO;
import br.jus.tream.DAO.TecnicoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Contrato;
import br.jus.tream.dominio.DistribuicaoTecnicoContrato;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.pk.DistribuicaoTecContratoPK;

@SuppressWarnings("serial")
@Namespace("/tecnico")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionTecnico extends ActionSupport{
	private List<Tecnico> lstTecnico;
	private List<Contrato> lstContrato;
	private List<DistribuicaoTecnicoContrato> lstDistribuicaoTecnicoContrato;
	private Contrato contrato;
	private String DtNasc;
	private Tecnico tecnico;
	private Integer id;	
	private BeanResult result;	
	private final static TecnicoDAO dao = TecnicoDAOImpl.getInstance();	
	private final static Permissao permissao = Permissao.getInstance();
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/listar-tecnico.jsp"),
			@Result(name = "error", location = "/result.jsp") },interceptorRefs = @InterceptorRef("authStack") 
	)
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
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarJson() {
		try {
			this.lstTecnico = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "getBeanJson", results = { @Result(name = "success", type = "json", params = { "root", "tecnico" }),
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
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
			this.lstContrato = ContratoDAOImpl.getInstance().listar();
		} catch (Exception e) {
			addActionError(getText("frmsetup.error"));
			return "error";
		}
		return "success";
	}
	
	
	
	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmTecnico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.tecnico = dao.getBean(this.tecnico.getId());
			lstDistribuicaoTecnicoContrato = DistribuicaoTecContratoDAOImpl.getInstance().listar(this.tecnico.getId());
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
			if(DtNasc!=null) {
				Date datanasc = sdf.parse(DtNasc);
				tecnico.setDataNasc(datanasc);			
			}		
			tecnico.setDataCad(new Date());			
			beanResult.setRet(dao.adicionar(tecnico));
			if (beanResult.getRet() == 1) {
				beanResult.setMensagem(getText("inserir.sucesso"));
				//ADICIONA CONTRATO
				DistribuicaoTecContratoPK tecContratopk = new DistribuicaoTecContratoPK();
				tecContratopk.setDataEleicao(DataEleicaoDAOImpl.getInstance().getBeanAtiva());
				tecContratopk.setContrato(this.contrato);
				tecContratopk.setTecnico(this.tecnico);
				DistribuicaoTecnicoContrato dtc = new DistribuicaoTecnicoContrato();
				dtc.setId(tecContratopk);
				DistribuicaoTecContratoDAOImpl.getInstance().adicionar(dtc);
				//
			}	
			else
				beanResult.setMensagem(getText("inserir.error"));
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
		try {
			tecnico.setDataNasc(datanasc);		
			beanResult.setRet(dao.atualizar(this.tecnico));
			if (beanResult.getRet()==1) {
				beanResult.setMensagem(getText("alterar.sucesso"));
			}else {
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

	public List<DistribuicaoTecnicoContrato> getLstDistribuicaoTecnicoContrato() {
		return lstDistribuicaoTecnicoContrato;
	}

	public void setLstDistribuicaoTecnicoContrato(List<DistribuicaoTecnicoContrato> lstDistribuicaoTecnicoContrato) {
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

	

}
