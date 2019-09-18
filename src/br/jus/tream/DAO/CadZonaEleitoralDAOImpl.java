package br.jus.tream.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.CADZonaEleitoral;
import br.jus.tream.dominio.SRHServidores;

public class CadZonaEleitoralDAOImpl implements CadZonaEleitoralDAO {
	private DAO<CADZonaEleitoral> dao = new DAO<CADZonaEleitoral>(CADZonaEleitoral.class);
	
    static CadZonaEleitoralDAO db;
	
	public static CadZonaEleitoralDAO getInstance(){
		if (db == null){
			db = new CadZonaEleitoralDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<CADZonaEleitoral> listar() throws Exception {
		List<CADZonaEleitoral> lista = new ArrayList<CADZonaEleitoral>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<CADZonaEleitoral> query = em.createQuery("SELECT z FROM CADZonaEleitoral z ORDER BY z.id.zona", 
		    		 CADZonaEleitoral.class);
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
	public List<CADZonaEleitoral> listarCbx() throws Exception{
		List<CADZonaEleitoral> lista = new ArrayList<CADZonaEleitoral>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<CADZonaEleitoral> query = em.createQuery("SELECT NEW CADZonaEleitoral(u.id.zona,u.id.codmunic) FROM CADZonaEleitoral u", 
		    		 CADZonaEleitoral.class);
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
	public List<CADZonaEleitoral> listarCbx(Integer zona) throws Exception	{
		List<CADZonaEleitoral> lista = new ArrayList<CADZonaEleitoral>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<CADZonaEleitoral> query = em.createQuery("SELECT NEW CADZonaEleitoral(z.id.zona,z.id.codmunic) FROM CADZonaEleitoral z WHERE z.id.zona=?1", 
		    		 CADZonaEleitoral.class);
		      query.setParameter(1, zona);
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
		CadZonaEleitoralDAO dao =  CadZonaEleitoralDAOImpl.getInstance();
		/*
		u.setTituloEleitor("037337472259");
		u.setAtivo(1);
		u.setNome("VINICIUS CAVALCANTE");
		int ret = dao.inserir(u);
		System.out.println("Retorno = " + ret);
		*/
		
		//u = dao.getBean("2301619");
		
		  for(CADZonaEleitoral s:dao.listarCbx()) { 
			  System.out.println("zona ==" + s.getZona());
		  
		  }	 
		
		
	

		
	}
}
