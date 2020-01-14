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
import br.jus.tream.DAO.UsuarioDAO;
import br.jus.tream.DAO.UsuarioDAOImpl;
import br.jus.tream.dominio.BeanResult;
import br.jus.tream.dominio.CADZonaEleitoral;
import br.jus.tream.dominio.Usuario;

@SuppressWarnings("serial")
@Namespace("/permissao")
@ResultPath(value = "/")
@ParentPackage(value = "default")
public class ActionPermissao extends ActionSupport{
	private List<Usuario> lstUsuarios;
	private List<CADZonaEleitoral> lstZonaEleitoral;
	private Usuario usuario;	
	private BeanResult result;	
	private final static UsuarioDAO dao = UsuarioDAOImpl.getInstance();
	private final static Permissao permissao = Permissao.getInstance();
	
	@Action(value = "listar", results = { @Result(name = "success", location = "/consultas/permissoes.jsp"),
			@Result(name = "error", location = "/result.jsp")}, interceptorRefs = @InterceptorRef("authStack")
	)
	public String listar() {
		try {
			if (permissao.getAdmin()) {
				this.lstUsuarios = dao.listar();				
			}else {
				this.lstUsuarios = dao.listarPorZona(permissao.getZona());
			}

		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}

	@Action(value = "listarJson", results = { @Result(name = "success", type = "json", params = { "root", "lstUsuarios" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp")})
	public String listarJson() {
		try {
			this.lstUsuarios = dao.listarCbx();
		} catch (Exception e) {
			addActionError(getText("listar.error"));
			return "error";
		}
		return "success";
	}
	
	@Action(value = "frmCad", results = { @Result(name = "success", location = "/forms/frmPermissao.jsp"),
			@Result(name = "error", location = "/pages/error.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
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
	
		
	@Action(value = "adicionar", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAdicionar() {
		BeanResult beanResult = new BeanResult();
		try {
			if (permissao.getAdmin()) {
				beanResult.setRet(dao.adicionar(usuario));
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
	
	
	@Action(value = "ativarUser", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAtivarUser() {
		BeanResult beanResult = new BeanResult();
		try {
			if (permissao.getAdmin()) {
				
				this.usuario = dao.getBeanAtivo(this.usuario.getTituloEleitor());
				
				if(this.usuario.getAtivo() == 1) {
				  this.usuario.setAtivo(0);				  
				}else {
					this.usuario.setAtivo(1);
				}
						
				beanResult.setRet(dao.atualizar(this.usuario));
				
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
	
	@Action(value = "ativarAdm", results = { @Result(name = "success", type = "json", params = { "root", "result" }),
			@Result(name = "error", location = "/pages/resultAjax.jsp") }, interceptorRefs = @InterceptorRef("authStack"))
	public String doAtivarAdm() {
		BeanResult beanResult = new BeanResult();
		try {
			if (permissao.getAdmin()) {
				
				this.usuario = dao.getBeanAtivo(this.usuario.getTituloEleitor());
				
				if(this.usuario.getAdm() == 1) {
				  this.usuario.setAdm(0);				  
				}else {
				 this.usuario.setAdm(1);	 
				}
				
				beanResult.setRet(dao.atualizar(this.usuario));
				
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
				beanResult.setRet(dao.remover(this.usuario));
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

	public List<Usuario> getLstUsuarios() {
		return lstUsuarios;
	}

	public void setLstUsuarios(List<Usuario> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BeanResult getResult() {
		return result;
	}

	public void setResult(BeanResult result) {
		this.result = result;
	}

	public static Permissao getPermissao() {
		return permissao;
	}

	public List<CADZonaEleitoral> getLstZonaEleitoral() {
		return lstZonaEleitoral;
	}

	public void setLstZonaEleitoral(List<CADZonaEleitoral> lstZonaEleitoral) {
		this.lstZonaEleitoral = lstZonaEleitoral;
	}
	
}
