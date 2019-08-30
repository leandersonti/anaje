package br.jus.tream.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.EquipamentoTipo;

@SuppressWarnings("serial")
@Namespace("/equipamento")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionEquipamento extends ActionSupport {
	private List<Equipamento> lstEquipamento;
	private List<EquipamentoTipo> lstEquipamentoTipo;
	private List<DataEleicao> lstDataEleicao;
	private Equipamento equipamento;
	private BeanResult result;
	private File fileUpload;
	private final EquipamentoDAO dao = EquipamentoDAOImpl.getInstance();
	private final EquipamentoDAO daoEquip = EquipamentoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/equipamento.jsp"),
			@Result(name = "error", location = "/result.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listar() {
		try {
			this.lstEquipamento = dao.listar();
			
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = {
			@Result(name = "success", type = "json", params = { "root", "lstEquipamento" }),
			@Result(name = "error", location = "/login.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String listarJson() {
		try {
			this.lstEquipamento = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmEquipamento.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String frmCadContrato() {
		try {
			this.lstEquipamento = EquipamentoDAOImpl.getInstance().listar();
			this.lstEquipamentoTipo = EquipamentoTipoDAOImpl.getInstance().listar();
			this.lstDataEleicao = DataEleicaoDAOImpl.getInstance().listar();
		} catch (Exception e) {
			addActionError(getText("EquipamentoTipo.error.listar"));
			return "error";
		}
		return "success";
	}

	@Action(value = "frmEditar", results = { @Result(name = "success", location = "/forms/frmEquipamento.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doFrmEditar() {
		try {
			this.lstEquipamentoTipo = EquipamentoTipoDAOImpl.getInstance().listar();
			this.lstEquipamento = EquipamentoDAOImpl.getInstance().listar();
			this.lstDataEleicao = DataEleicaoDAOImpl.getInstance().listar();
			equipamento = dao.getBean(this.equipamento.getId());
		} catch (Exception e) {
			addActionError(getText("frmsetup.error") + " Error: " + e.getMessage());
			return "error";
		}
		return "success";
	}
	
	@Action(value = "frmImportar", 
			results = { 
					@Result(name = "success", location = "/forms/frmImportEquip.jsp", params = {"root", "lstEquipamentoTipo"}),
					@Result(name = "importFailed", location = "/forms/frmImportEquip.jsp", params = {"root", "lstEquipamentoTipo"}),
					@Result(name = "error", location = "/pages /error.jsp")}, 
			interceptorRefs = {
					@InterceptorRef(
				            params = { "allowedTypes", "text/plain",
				            		   "maximumSize", "2097152" }, 
				            value = "fileUpload"),
					@InterceptorRef("authStack")})
	public String importarTecnico() {
		BufferedReader reader = null;
		BeanResult beanResult = new BeanResult();
		try {
			
				this.lstEquipamento = daoEquip.listar();
				this.lstEquipamentoTipo = EquipamentoTipoDAOImpl.getInstance().listar();
				if (permissao.getAdmin()) {
					if (getFileUpload() != null && this.getEquipamento() != null) {
						// List<String> list = new ArrayList<String>();
					    reader = new BufferedReader(new FileReader(getFileUpload()));
					    String text = null;
					    this.setLstEquipamento(new ArrayList<Equipamento>());
						    while ((text = reader.readLine()) != null) {
						    	final String row[] = text.split(";");
						    	final Equipamento equipamento = new Equipamento();			    	
								DataEleicao dt = new DataEleicao();			    	
								dt = DataEleicaoDAOImpl.getInstance().getBeanAtiva();					
								equipamento.setDataEleicao(dt);
								equipamento.setTipo(this.equipamento.getTipo());
						    	equipamento.setSerie(row[0]);
						    	equipamento.setTomb(row[1]);
						    	equipamento.setParam(row[2]);
						    	equipamento.setFone(row[3]);	
						    	equipamento.setChave(row[4]);			    	
						    	int ret = daoEquip.adicionar(equipamento);
						    	equipamento.setInserted(false);
								    	if (ret != 0) {
								    		equipamento.setInserted(true);
								    	}
						    	getLstEquipamento().add(equipamento);
						    }
						    
						    beanResult.setMensagem(getText("inserir.sucesso"));
					}				
			}else {
		    	beanResult.setRet(0);
				beanResult.setMensagem(getText("permissao.negada"));
				addActionError(getText("permissao.negada"));
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
		this.result = beanResult;
		return "success";
	}

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.adicionar(equipamento));
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
			// result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
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
				beanResult.setRet(dao.atualizar(this.equipamento));
				if (beanResult.getRet() == 1) {
					beanResult.setMensagem(getText("alterar.sucesso"));
				} else {
					beanResult.setMensagem(getText("alterar.error"));
				}
			} else {
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
				beanResult.setRet(dao.remover(this.equipamento));
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

	public List<Equipamento> getLstEquipamento() {
		return lstEquipamento;
	}

	public void setLstEquipamento(List<Equipamento> lstEquipamento) {
		this.lstEquipamento = lstEquipamento;
	}

	public List<EquipamentoTipo> getLstEquipamentoTipo() {
		return lstEquipamentoTipo;
	}

	public void setLstEquipamentoTipo(List<EquipamentoTipo> lstEquipamentoTipo) {
		this.lstEquipamentoTipo = lstEquipamentoTipo;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<DataEleicao> getLstDataEleicao() {
		return lstDataEleicao;
	}

	public void setLstDataEleicao(List<DataEleicao> lstDataEleicao) {
		this.lstDataEleicao = lstDataEleicao;
	}




}
