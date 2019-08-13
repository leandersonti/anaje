package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.IDEleicaoPK;
import br.jus.tream.dominio.UnidadeServico;
import br.jus.tream.dominio.UnidadeServicoTipo;

public class UnidadeServicoDAOImpl implements UnidadeServicoDAO {

	private DAO<UnidadeServico> dao = new DAO<UnidadeServico>(UnidadeServico.class);

	static UnidadeServicoDAO db;

	public static UnidadeServicoDAO getInstance() {
		if (db == null) {
			db = new UnidadeServicoDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<UnidadeServico> listar() throws Exception {
		List<UnidadeServico> lista = new ArrayList<UnidadeServico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<UnidadeServico> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     						+ "WHERE u.id.dataEleicao.ativo=1 ORDER BY u.zona, u.local", 
		    		 UnidadeServico.class);
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
	public List<UnidadeServico> listarSemDistribuicaoSecao() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UnidadeServico> listar(Integer zona, Integer codmunic) throws Exception {
		List<UnidadeServico> lista = new ArrayList<UnidadeServico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<UnidadeServico> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     						+ "WHERE u.zona=?1 AND u.codmunic=?2 ORDER BY u.local", 
		    		 UnidadeServico.class);
		      query.setParameter(1, zona);
		      lista = query.setParameter(2, codmunic).getResultList();
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
	public UnidadeServico getBean(IDEleicaoPK id) throws Exception {
		UnidadeServico uservico = new UnidadeServico();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<UnidadeServico> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     						+ "WHERE u.id.dataEleicao.id=?1 AND u.id.id=?2", 
		    		 UnidadeServico.class);
		     query.setParameter(1, id.getDataEleicao().getId());
		     uservico =  query.setParameter(2, id.getId()).getSingleResult();
		  }
		  catch (Exception e) {
			     em.close();
				 e.printStackTrace();
		  }	finally {
				em.close();
		  }
		return uservico;
	}

	@Override
	public int inserir(UnidadeServico us) throws Exception {
		int ret = 0;
		try {
			IDEleicaoPK pk = us.getId();
			pk.setId(dao.gerarId());
			us.setId(pk);
			ret = dao.adicionar(us);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int alterar(UnidadeServico us) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(us);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(UnidadeServico us) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(us);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}
	

	public static void main(String[] args) throws Exception{
		
		UnidadeServicoDAO dao = UnidadeServicoDAOImpl.getInstance();
<<<<<<< HEAD
		/*
		for(UnidadeServico u : dao.listar(31,2046)) {
			System.out.println(u.getDescricao());
		}
		*/
=======
		UnidadeServico us = new UnidadeServico();
		
		  
		  for(UnidadeServico u : dao.listar()) {
			  System.out.println(u.getDescricao());
			
		  }
		 
>>>>>>> branch 'master' of http://10.22.1.119/gitlab/tre-sti/anaje.git
		
		UnidadeServico u2 = new UnidadeServico();
		DataEleicao dt = new DataEleicao();
		dt.setId(1);
		IDEleicaoPK pk = new IDEleicaoPK();
		pk.setDataEleicao(dt);
<<<<<<< HEAD
		u2.setId(pk);
		
		UnidadeServicoTipo tipo = new UnidadeServicoTipo();
		tipo.setId(1);
		u2.setTipo(tipo);
		u2.setDescricao("XXX xxTESTE TESTE ZZZ WWWWW");
		u2.setCodmunic(2550);
		u2.setZona(40);
		u2.setLocal(1900);
		u2.setOficial(0);
		
		int ret = dao.inserir(u2);
		
		System.out.println("RET ==  " + ret);
		
		System.out.println("Done!!");
=======
		pk.setId(1);
		u2 = dao.getBean(pk);
		System.out.println("Ponto Trans " + u2.getZona() + "/" + u2.getLocal() + " " + u2.getDescricao());
		*/
		//System.out.println("Done!!");
>>>>>>> branch 'master' of http://10.22.1.119/gitlab/tre-sti/anaje.git

	}

}
