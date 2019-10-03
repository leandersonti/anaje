package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.EleicaoDAOImpl;
import br.jus.tream.DAO.PpoDAO;
import br.jus.tream.DAO.PpoDAOImpl;
import br.jus.tream.DAO.TecnicoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Eleicao;
import br.jus.tream.dominio.Ppo;
import br.jus.tream.dominio.Tecnico;

@SuppressWarnings("serial")
@Namespace("/ppo")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionPpo extends ActionSupport {
	private List<Ppo> lstPpo;
	private Ppo ppo;
	private BeanResult result;
	private String tituloEleitor;
	private Integer idTecnicoResponsavel;
	private Integer id;
	private final static PpoDAO dao = PpoDAOImpl.getInstance();
	
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

	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			Tecnico tecnico = new Tecnico();
			tecnico = TecnicoDAOImpl.getInstance().getBean(tituloEleitor);
			this.ppo.setTecnico(tecnico);
			
			Eleicao dataeleicao = new Eleicao();
			dataeleicao = EleicaoDAOImpl.getInstance().getBeanAtiva();
			this.ppo.setDataEleicao(dataeleicao);
	
				beanResult.setRet(dao.adicionar(ppo));
				if (beanResult.getRet() == 1)
					beanResult.setMensagem(getText("inserir.sucesso"));
				else
					beanResult.setMensagem(getText("inserir.violado"));
				
		} catch (Exception e) {
			  addActionError(getText("alterar.error") + " Error: " + e.getMessage());
			 result.setMensagem(getText("inserir.error") + " Error: " + e.getMessage());
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

}
