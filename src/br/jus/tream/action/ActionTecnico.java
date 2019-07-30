package br.jus.tream.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.DataEleicaoDAOImpl;
import br.jus.tream.DAO.EquipamentoDAO;
import br.jus.tream.DAO.EquipamentoDAOImpl;
import br.jus.tream.DAO.EquipamentoTipoDAOImpl;
import br.jus.tream.DAO.TecnicoDAO;
import br.jus.tream.DAO.TecnicoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.EquipamentoTipo;
import br.jus.tream.dominio.Tecnico;

@SuppressWarnings("serial")
@Namespace("/tecnico")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionTecnico extends ActionSupport{
	private List<Tecnico> lstTecnico;
	private List<Equipamento> lstEquipamento;
	private String DtNasc;
	private Tecnico tecnico;
	private Equipamento equipamento;
	private BeanResult result;
	private File fileUpload;
	private final static TecnicoDAO dao = TecnicoDAOImpl.getInstance();
	private final EquipamentoDAO daoEquip = EquipamentoDAOImpl.getInstance();
	
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
	
	@Action(value = "frmImportar", 
			results = { 
					@Result(name = "success", location = "/forms/frmImportEquip.jsp", params = {"root", "lstEquipamento"}),
					@Result(name = "importFailed", location = "/forms/frmImportEquip.jsp", params = {"root", "lstEquipamento"}),
					@Result(name = "error", location = "/pages /error.jsp")}, 
			interceptorRefs = {
					@InterceptorRef(
				            params = { "allowedTypes", "text/plain",
				            		   "maximumSize", "2097152" }, 
				            value = "fileUpload"),
					@InterceptorRef("authStack")})
	public String importarEleitor() {
		BufferedReader reader = null;
		
		try {
			this.lstEquipamento = daoEquip.listar();
			if (getFileUpload() != null && this.getEquipamento() != null) {
			
				List<String> list = new ArrayList<String>();
			    reader = new BufferedReader(new FileReader(getFileUpload()));
			    String text = null;
			    this.setLstEquipamento(new ArrayList<Equipamento>());
			    
			    while ((text = reader.readLine()) != null) {
			    	
			    	final String row[] = text.split(";");
			    	final Equipamento equipamento = new Equipamento();
			    	EquipamentoTipo et = new EquipamentoTipo();
					DataEleicao dt = new DataEleicao();
			    	
					et = EquipamentoTipoDAOImpl.getInstance().getBean(this.equipamento.getTipo().getId());
					dt = DataEleicaoDAOImpl.getInstance().getBean(this.equipamento.getDataEleicao().getId());
			    	
					
					Integer str =Integer.parseInt(row[0]);
					equipamento.setId(str); 
					equipamento.setTipo(et);
					equipamento.setDataEleicao(dt);
			    	equipamento.setSerie(row[1]);
			    	equipamento.setTomb(row[2]);
			    	equipamento.setParam(row[3]);
			    	equipamento.setFone(row[4]);
			    
			    	
			    	
			    	int ret = daoEquip.inserir(equipamento);
			    	
			    	equipamento.setInserted(false);
			    	if (ret != 0) {
			    		equipamento.setInserted(true);
			    	}
			    	
			    	getLstEquipamento().add(equipamento);
			    }
			}		
			
			if (getLstEquipamento() != null && getLstEquipamento().size() > 0) {
				return "importFailed";
			}			
			
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
	        if (reader != null) {
	            try {
					reader.close();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
	        }
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
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadTecnico() {	
		return "success";
	}
	
	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmTecnico.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
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
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
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
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
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

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
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
	
	

}
