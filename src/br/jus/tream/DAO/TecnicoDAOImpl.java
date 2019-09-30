package br.jus.tream.DAO;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Tecnico;

public class TecnicoDAOImpl implements TecnicoDAO {
	private DAO<Tecnico> dao = new DAO<Tecnico>(Tecnico.class);
	
    static TecnicoDAO db;
	
	public static TecnicoDAO getInstance(){
		if (db == null){
			db = new TecnicoDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<Tecnico> listar() throws Exception {
		List<Tecnico> lista = null;
		  try {
			  lista = dao.listarTodos();
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		return lista;	
	}
	
	@Override
	public Tecnico getBean(Integer id) throws Exception{
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		Tecnico tecnico = new Tecnico();
		try {
			TypedQuery<Tecnico> query = em.createQuery("SELECT t FROM Tecnico t WHERE t.id=?1", 
					Tecnico.class);
			tecnico = query.setParameter(1, id).getSingleResult(); 
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		}finally {
				em.close();
		  }
		return tecnico;
	}
	
	public Tecnico getBean(String tituloEleitor) throws Exception{
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		Tecnico tecnico = new Tecnico();
		try {
			TypedQuery<Tecnico> query = em.createQuery("SELECT t FROM Tecnico t WHERE t.tituloEleitor=?1", 
					Tecnico.class);
			tecnico = query.setParameter(1, tituloEleitor).getSingleResult(); 
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		}finally {
				em.close();
		  }
		return tecnico;
	}
	
	@Override
	public List<Tecnico> listarCbx() throws Exception{
		List<Tecnico> lista = new ArrayList<Tecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<Tecnico> query = em.createQuery("SELECT NEW tecnico(t.id, t.nome) FROM tecnico t", 
		    		 Tecnico.class);
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
	public List<Tecnico> listarCbx(Integer contrato) throws Exception{
		List<Tecnico> lista = new ArrayList<Tecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<Tecnico> query = em.createQuery("SELECT NEW Tecnico(t.id,t.nome) FROM Tecnico t "
		     		+ "WHERE t.id in(SELECT d.id.tecnico.id FROM DistribuicaoTecnicoContrato d WHERE d.ativo = 1 AND d.id.contrato.id = ?1 AND id.dataEleicao.ativo = 1) ", 
		    		 Tecnico.class);
		     	query.setParameter(1, contrato);
			  lista = query.getResultList();
		  }
		  catch (Exception e) {
			     em.close();
				 e.printStackTrace();
		  }	finally {
				em.close();
		  }
		return lista;	
	}
	
	@Override
	public List<Tecnico> listarCbxResponsavel() throws Exception{
		List<Tecnico> lista = new ArrayList<Tecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<Tecnico> query = em.createQuery("SELECT NEW Tecnico(t.id,t.nome) FROM Tecnico t "
		     		+ "WHERE t.id in(SELECT d.id.tecnico.id FROM DistribuicaoTecnicoContrato d WHERE d.ativo = 1 AND d.id.contrato.sigla = 'MULT' AND id.dataEleicao.ativo = 1) ", 
		    		 Tecnico.class);		     	
			  lista = query.getResultList();
		  }
		  catch (Exception e) {
			     em.close();
				 e.printStackTrace();
		  }	finally {
				em.close();
		  }
		return lista;	
	}
	
	@Override
	public int adicionar (Tecnico tecnico) throws Exception{
		int ret = 0;
		try {
			tecnico.setDataCad(new Date(System.currentTimeMillis()) );
			dao.adicionar(tecnico);
			ret =1;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int atualizar (Tecnico tecnico) throws Exception{
		int ret = 0;
		try {
			dao.atualizar(tecnico);
			ret =1;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int remover (Tecnico tecnico) throws Exception{
		int ret = 0;
		try {
			dao.remover(tecnico);
			ret =1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	

	    
	public static void main(String[] args) throws Exception{
		TecnicoDAO dao = TecnicoDAOImpl.getInstance();
		//Tecnico t = new Tecnico();
		//t = dao.getBean("034068942566");
		//System.out.println("Nome " + t.getId());
		
		/*
		 * t.setTitulo_eleitor("034068942259"); t.setZona(59); t.setSecao(139);
		 * t.setNome("Estevan Gomez"); t.setTelefone("36366363");
		 * t.setCelular("929292952"); t.setEndereco("Rua Pardal"); t.setNum_casa("59");
		 * t.setBairro("Jesus Me Deu"); t.setCep("6900000"); t.setCidade("Manaus");
		 * t.setUf("AM"); t.setSexo("M"); t.setEmail("email@gmail.com");
		 * t.setRg("58595458"); t.setOrgaoRg("SSP"); t.setCpf("0123687668");
		 * t.setDataCad(new Date()); int ret = dao.inserir(t);
		 * System.out.println("Retorno = " + ret);
		 */
	
		
		  for(Tecnico t:dao.listarCbx()) { 
			  System.out.println(t.getNome());		  
		  }
		 
		
		System.out.println("Done!!");

		
	}
}
