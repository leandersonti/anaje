package br.jus.tream.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.SRHServidores;

public class SRHServidoresDAOImpl implements SRHServidoresDAO {
	private DAO<SRHServidores> dao = new DAO<SRHServidores>(SRHServidores.class);
	
    static SRHServidoresDAO db;
	
	public static SRHServidoresDAO getInstance(){
		if (db == null){
			db = new SRHServidoresDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<SRHServidores> listar() throws Exception {
		List<SRHServidores> lista = null;
		  try {
			  lista = dao.listarTodos();
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		return lista;	
	}
	
	public List<SRHServidores> ListParaUser() throws Exception{		
		List<SRHServidores> lista = new ArrayList<SRHServidores>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<SRHServidores> query = em.createQuery("SELECT s FROM SRHServidores s WHERE s.tituloEleitor NOT IN (SELECT u.tituloEleitor FROM Usuario u) ORDER BY s.nome", 
					SRHServidores.class);
			lista = query.getResultList();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		}finally {
				em.close();
		  }
		return lista;
	}
	
	@Override
	public SRHServidores getBean(String matricula) throws Exception{
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		SRHServidores servidor = new SRHServidores();
		try {
			TypedQuery<SRHServidores> query = em.createQuery("SELECT u FROM SRHServidores u WHERE u.matricula=?1", 
					SRHServidores.class);
			servidor = query.setParameter(1, matricula).getSingleResult(); 
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		}finally {
				em.close();
		  }
		return servidor;
	}
	
	@Override
	public SRHServidores getBeanTitulo(String titulo) throws Exception{
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		SRHServidores servidor = new SRHServidores();
		try {
			TypedQuery<SRHServidores> query = em.createQuery("SELECT u FROM SRHServidores u WHERE u.tituloEleitor=?1", 
					SRHServidores.class);
			servidor = query.setParameter(1, titulo).getSingleResult(); 
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		}finally {
				em.close();
		  }
		return servidor;
	}
	
	@Override
	public List<SRHServidores> ListUnidade(String siglaUnid) throws Exception{		
		List<SRHServidores> lista = new ArrayList<SRHServidores>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<SRHServidores> query = em.createQuery("SELECT u FROM SRHServidores u WHERE u.siglaUnid=?1", 
					SRHServidores.class);
			lista = query.setParameter(1, siglaUnid).getResultList();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		}finally {
				em.close();
		  }
		return lista;
	}

	
	@Override
	public List<SRHServidores> listarCbx() throws Exception{
		List<SRHServidores> lista = new ArrayList<SRHServidores>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<SRHServidores> query = em.createQuery("SELECT NEW SRH_Servidores(u.matricula) FROM SRH_Servidores u", 
		    		 SRHServidores.class);
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

	    
	public static void main(String[] args) throws Exception{
		SRHServidoresDAO dao = SRHServidoresDAOImpl.getInstance();
		// SRHServidores u = new SRHServidores();
		/*
		u.setTituloEleitor("037337472259");
		u.setAtivo(1);
		u.setNome("VINICIUS CAVALCANTE");
		int ret = dao.inserir(u);
		System.out.println("Retorno = " + ret);
		*/
		
		//u = dao.getBean("2301619");
		
		  for(SRHServidores s:dao.ListParaUser()) { 
			  System.out.println("Nome == "+ s.getNome() + "Matricula == " + s.getMatricula() + " Titulo === " +
		  s.getTituloEleitor() + "SiglaUnidade ==== "+ s.getSiglaUnid());
		  
		  }	 
		
		
	

		
	}
}
