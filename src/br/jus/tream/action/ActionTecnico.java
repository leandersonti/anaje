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

import br.jus.tream.DAO.TecnicoDAO;
import br.jus.tream.DAO.TecnicoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Tecnico;

@SuppressWarnings("serial")
@Namespace("/tecnico")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionTecnico extends ActionSupport{
	private List<Tecnico> lstTecnico;
	private String DtNasc;
	private Tecnico tecnico;
	private BeanResult result;
	private final static TecnicoDAO dao = TecnicoDAOImpl.getInstance();
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/listar-tecnico.jsp"),
			@Result(name = "error", location = "/result.jsp") }/* , interceptorRefs = @InterceptorRef("authStack") */
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
	
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmTecnico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }/*, interceptorRefs = @InterceptorRef("authStack")*/)
	public String frmCadTecnico() {	
		return "success";
	}
	
	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmTecnico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }/*, interceptorRefs = @InterceptorRef("authStack")*/)
	public String doFrmEditar() {
		try {
			this.tecnico = dao.getBean(this.tecnico.getId());			
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}

	
	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }/*, interceptorRefs = @InterceptorRef("authStack")*/)
	public String doAdicionar() throws ParseException {
		BeanResult beanResult = new BeanResult();		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date datanasc = sdf.parse(DtNasc);
		try {			
			tecnico.setData_nasc(datanasc);			
			tecnico.setDataCad(new Date());			
			beanResult.setRet(dao.inserir(tecnico));
			if (beanResult.getRet() == 1)
				beanResult.setMensagem(getText("inserir.sucesso"));
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
			@Result(name = "error", location = "/pages/resultAjax.jsp") }/*, interceptorRefs = @InterceptorRef("authStack")*/)
	public String doAtualizar() throws ParseException {
		BeanResult beanResult = new BeanResult();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date datanasc = sdf.parse(DtNasc);
		try {
			tecnico.setData_nasc(datanasc);		
			beanResult.setRet(dao.alterar(this.tecnico));
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
			@Result(name = "error", location = "/pages/resultAjax.jsp") }/*, interceptorRefs = @InterceptorRef("authStack")*/)
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

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public String getDtNasc() {
		return DtNasc;
	}

	public void setDtNasc(String dtNasc) {
		DtNasc = dtNasc;
	}
	
	

}
