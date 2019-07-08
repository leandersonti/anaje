package br.jus.tream.action;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import br.jus.tream.DAO.EntityManagerProvider;
import br.jus.tream.DAO.UsuarioDAO;
import br.jus.tream.DAO.UsuarioDAOImpl;
import br.jus.tream.dominio.BeanLogin;
import br.jus.tream.dominio.Usuario;

public class LoginAD {

	private static LoginAD loginAD;

	public static LoginAD getInstance() throws Exception {
		if (loginAD == null) {
			loginAD = new LoginAD();
		}
		return loginAD;
	}

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public boolean loginActiveDirectory(String usuario, String senha) throws Exception {
		LdapContext ctx = null;
		String loginAD = String.format("%s@tre-am.gov.br", usuario);
		String enderecoAD = "10.22.1.180";
		String portaAD = "389"; // Geralmente  a 389
		try {
			if (!senha.equals("")) {
				Hashtable environment = new Hashtable();
				environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				environment.put(Context.PROVIDER_URL, String.format("ldap://%s:%s", enderecoAD, portaAD));
				environment.put(Context.SECURITY_AUTHENTICATION, "simple");
				environment.put(Context.SECURITY_PRINCIPAL, loginAD);
				environment.put(Context.SECURITY_CREDENTIALS, senha);
				ctx = new InitialLdapContext(environment, null);
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAmbienteProducao() {
		boolean ret = false; 
		try {
			ret = EntityManagerProvider.getInstance().isAmbienteProducao();
		} catch (Exception e) {
			ret = false; // ocorreu um erro			
		} 
	  return ret;
	}
	
	public BeanLogin getSGRH(String tituloEleitor) throws Exception {
		UsuarioDAO db = UsuarioDAOImpl.getInstance();
		BeanLogin login = new BeanLogin();
		try {
			Usuario usuario = new Usuario();
			usuario = db.getBean(tituloEleitor);
			if (usuario.getNome().length()>0) {
				login.setNome(usuario.getNome());
				login.setFirstName(usuario.getNome());
				login.setTitulo(usuario.getTituloEleitor());
				login.setIsAmbienteProducao(this.isAmbienteProducao());
			}
		} finally {
		 //
		}
		return login;
	}

	public BeanLogin getLogin(String titulo, String senha) throws SQLException, Exception {
		BeanLogin l = new BeanLogin();
		try {
			l = getSGRH(titulo);
			l.setLogou(loginActiveDirectory(l.getTitulo(), senha));
			return l;
		} catch (Exception e) {
			return l;
		}
	}

	public static void main(String[] args) throws SQLException, Exception {
		BeanLogin s = new BeanLogin();
		LoginAD l = LoginAD.getInstance();
		s = l.getLogin("037337472259", "Fcd08be893");
		System.out.println("login " + s.getLogou());
		if (s.getLogou())
			System.out.println("logou " + s.getNome() + " / " + s.getSigla());
		else
			System.out.println("Nao Logou");
		
		
		
		
	}
}
