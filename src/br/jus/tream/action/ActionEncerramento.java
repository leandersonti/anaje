package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.EncerramentoDAO;
import br.jus.tream.DAO.EncerramentoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Encerramento;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.VWEncerramento;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

@SuppressWarnings("serial")
@Namespace("/encerramento")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionEncerramento extends ActionSupport{
	private List<VWEncerramento> lstEncerramento;
	private Encerramento encerramento;
	private Tecnico tecResponsavel = new Tecnico(0,"");
	private String codZonaMunic = "0;0";
	private String estadoRecebimento = "is not null";
	//private Integer idTecnicoResponsavel;
	private BeanResult result;
	private final static EncerramentoDAO dao = EncerramentoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();
	
	
	@Action(value = "frmSetupReinicializa", results = { @Result(name = "success", location = "/forms/frmReinicializarEncerramento.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCad() {
		return "success";
	}
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/encerramento.jsp"),
			@Result(name = "error", location = "/result.jsp")}, interceptorRefs = @InterceptorRef("authStack")
	)
	public String listar() {
		try {
			CadZonaEleitoralPK pkze = new CadZonaEleitoralPK(codZonaMunic);
			if (pkze.getZona()==9999 && tecResponsavel.getId()==9999) {
				if (permissao.getAdmin()) {
					this.lstEncerramento = dao.listar(estadoRecebimento);	
				}else {
					this.lstEncerramento = dao.listar(permissao.getZona(), estadoRecebimento);
				}					
			}else {
				if (pkze.getZona()!=9999 && tecResponsavel.getId()==9999) {
				       this.lstEncerramento = dao.listar(pkze, estadoRecebimento);
			     }else {
			    	 if (pkze.getZona()==9999 && tecResponsavel.getId()!=9999) {
			    		 this.lstEncerramento = dao.listar(tecResponsavel, estadoRecebimento);
			    	 }
			     }
			}
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
		
	
	@Action(value = "listarJson", results = { @Result(name = "success", type = "json", params = { "root", "lstEncerramento" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")})
	public String listarJson() {
		try {
			CadZonaEleitoralPK	pkze = new CadZonaEleitoralPK(codZonaMunic);
			this.lstEncerramento = dao.listar(pkze, estadoRecebimento);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	
	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmDataEleicao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			//this.encerramento = dao.getBean();
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}
	
	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.adicionar(encerramento));
				if (beanResult.getRet() == 1)
					beanResult.setMensagem(getText("inserir.sucesso"));
				else
					beanResult.setMensagem(getText("inserir.error"));
			}else {
				beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
			}
		} catch (Exception e) {
			    addActionError(getText("alterar.error") + " Error: " + e.getMessage());
			  //result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
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
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.atualizar(this.encerramento));
				if (beanResult.getRet()==1) {
					beanResult.setMensagem(getText("alterar.sucesso"));
				}else {
					beanResult.setMensagem(getText("alterar.error")); 
				}
			}else {
				beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
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
				beanResult.setRet(dao.remover(this.encerramento));
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

	public List<VWEncerramento> getLstEncerramento() {
		return lstEncerramento;
	}

	public void setLstEncerramento(List<VWEncerramento> lstEncerramento) {
		this.lstEncerramento = lstEncerramento;
	}

	public Encerramento getEncerramento() {
		return encerramento;
	}

	public void setEncerramento(Encerramento encerramento) {
		this.encerramento = encerramento;
	}

	public String getCodZonaMunic() {
		return codZonaMunic;
	}

	public void setCodZonaMunic(String codZonaMunic) {
		this.codZonaMunic = codZonaMunic;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public Tecnico getTecResponsavel() {
		return tecResponsavel;
	}

	public void setTecResponsavel(Tecnico tecResponsavel) {
		this.tecResponsavel = tecResponsavel;
	}

	public String getEstadoRecebimento() {
		return estadoRecebimento;
	}

	public void setEstadoRecebimento(String estadoRecebimento) {
		this.estadoRecebimento = estadoRecebimento;
	}

}
