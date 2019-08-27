package br.jus.tream.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

import br.jus.tream.DAO.PpoDAO;
import br.jus.tream.DAO.PpoDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.Ppo;

@SuppressWarnings("serial")
@Namespace("/ppo")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionPpo extends ActionSupport {
	private List<Ppo> lstPpo;
	private List<Ppo> lstTitulo;
	private List<Ppo> lstIdTecnico;
	private Ppo ppo;
	private BeanResult result;
	private String tituloEleitor;
	private Integer idTecnicoResponsavel;
	private Integer id;
	private final static PpoDAO dao = PpoDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();

	@Action(value = "listarJson", results = { @Result(name = "success", type = "json", params = { "root", "lstPpo" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String listarJson() {
		try {
			this.lstPpo = dao.listar();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJsonTitulo", results = {
			@Result(name = "success", type = "json", params = { "root", "lstTitulo" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String listarJsonTitulo() {
		try {
			System.out.println("==" + tituloEleitor);
			this.lstTitulo = dao.listar(tituloEleitor);
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJsonIdTecnico", results = {
			@Result(name = "success", type = "json", params = { "root", "lstIdTecnico" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") })
	public String listarJsonIdTecnico() {
		try {
			this.lstIdTecnico = dao.listar(idTecnicoResponsavel);
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
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.inserir(ppo));
				if (beanResult.getRet() == 1)
					beanResult.setMensagem(getText("inserir.sucesso"));
				else
					beanResult.setMensagem(getText("inserir.error"));
			} else {
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
				beanResult.setRet(dao.alterar(this.ppo));
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

	public List<Ppo> getLstTitulo() {
		return lstTitulo;
	}

	public void setLstTitulo(List<Ppo> lstTitulo) {
		this.lstTitulo = lstTitulo;
	}

	public List<Ppo> getLstIdTecnico() {
		return lstIdTecnico;
	}

	public void setLstIdTecnico(List<Ppo> lstIdTecnico) {
		this.lstIdTecnico = lstIdTecnico;
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
