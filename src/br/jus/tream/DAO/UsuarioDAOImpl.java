package br.jus.tream.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	private DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
	
    static UsuarioDAO db;
	
	public static UsuarioDAO getInstance(){
		if (db == null){
			db = new UsuarioDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<Usuario> listar() throws Exception {
		List<Usuario> lista = null;
		  try {
			  lista = dao.listarTodos();
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		return lista;	
	}
	
	@Override
	public Usuario getBean(String tituloEleitor) throws Exception{
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		Usuario usuario = new Usuario();
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.ativo=1 AND u.tituloEleitor=lpad(?1,12,0)", 
		    		   Usuario.class);
			usuario = query.setParameter(1, tituloEleitor).getSingleResult(); 
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		}finally {
				em.close();
		  }
		return usuario;
	}
	
	@Override
	public List<Usuario> listarCbx() throws Exception{
		List<Usuario> lista = new ArrayList<Usuario>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<Usuario> query = em.createQuery("SELECT NEW Usuario(u.tituloEleitor) FROM Usuario u", 
		    		   Usuario.class);
			  lista = query.getResultList();
		  }
		  catch (Exception e) {
			     em.close();
				// e.printStackTrace();
		  }	finally {
				em.close();
		  }
		return lista;	
	} 
	
	@Override
	public int inserir (Usuario dateEleicao) throws Exception{
		int ret = 0;
		try {
			dao.adicionar(dateEleicao);
			ret =1;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int alterar (Usuario dateEleicao) throws Exception{
		int ret = 0;
		try {
			dao.atualizar(dateEleicao);
			ret =1;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int remover (Usuario dateEleicao) throws Exception{
		int ret = 0;
		try {
			dao.remover(dateEleicao);
			ret =1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	

	    
	public static void main(String[] args) throws Exception{
		UsuarioDAO dao = UsuarioDAOImpl.getInstance();
		Usuario u = new Usuario();
		/*
		u.setTituloEleitor("037337472259");
		u.setAtivo(1);
		u.setNome("VINICIUS CAVALCANTE");
		int ret = dao.inserir(u);
		System.out.println("Retorno = " + ret);
		*/
		
		u = dao.getBean("15697172275");
		System.out.println(u.getNome());
		
		System.out.println("Done!!");

		
	}
}
