package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.PontoTransmissao;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public class PontoTransmissaoDAOImpl implements PontoTransmissaoDAO {

	private DAO<PontoTransmissao> dao = new DAO<PontoTransmissao>(PontoTransmissao.class);

	static PontoTransmissaoDAO db;

	public static PontoTransmissaoDAO getInstance() {
		if (db == null) {
			db = new PontoTransmissaoDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<PontoTransmissao> listar() throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM PontoTransmissao u "
		     						+ "WHERE u.id.eleicao.ativo=1 ORDER BY u.zona, u.codLocal", 
		    		 PontoTransmissao.class);
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
	public List<PontoTransmissao> listarSemDistribuicaoSecao() throws Exception{
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	 
		   String sql = "SELECT pt FROM PontoTransmissao pt " + 
		   		"  WHERE pt.id.eleicao.ativo=1" +  
		   		"  AND (pt.id.id, pt.id.eleicao.id) NOT IN ( " + 
		   		"     SELECT distinct ds.id.pontoTransmissao.id.id, ds.id.pontoTransmissao.id.eleicao.id " + 
		   		"        FROM DistribuicaoSecao ds " + 
		   		"        WHERE ds.id.pontoTransmissao.id.eleicao.ativo=1)";
		   TypedQuery<PontoTransmissao> query = em.createQuery(sql, PontoTransmissao.class);
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
	public List<PontoTransmissao> listarSemDistribuicaoSecao(CadZonaEleitoralPK pkze) throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	 
		   String sql = "SELECT pt FROM PontoTransmissao pt " + 
		   		"  WHERE pt.id.eleicao.ativo=1" +  
		   		"  AND pt.zona=?1" + 
				"  AND pt.codmunic=?2" +
		   		"  AND (pt.id.id, pt.id.eleicao.id) NOT IN ( " + 
		   		"     SELECT distinct ds.id.pontoTransmissao.id.id, ds.id.pontoTransmissao.id.eleicao.id " + 
		   		"        FROM DistribuicaoSecao ds " + 
		   		"        WHERE ds.id.pontoTransmissao.id.eleicao.ativo=1)";
		   TypedQuery<PontoTransmissao> query = em.createQuery(sql, PontoTransmissao.class);
		   	  query.setParameter(1, pkze.getZona());
		      lista = query.setParameter(2, pkze.getCodmunic()).getResultList();
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
	public List<PontoTransmissao> listarSemDistribuicaoTecnico() throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	 
		   String sql = "SELECT pt FROM PontoTransmissao pt " + 
		   		"  WHERE pt.id.eleicao.ativo=1 " +
		   		"  AND (pt.id.id, pt.id.eleicao.id) NOT IN ( " + 
		   		"     SELECT distinct ds.id.pontoTransmissao.id.id, ds.id.pontoTransmissao.id.eleicao.id " + 
		   		"        FROM DistribuicaoTecnico ds " + 
		   		"        WHERE ds.id.pontoTransmissao.id.eleicao.ativo=1)";
		     TypedQuery<PontoTransmissao> query = em.createQuery(sql, PontoTransmissao.class);
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
	public List<PontoTransmissao> listarSemDistribuicaoTecnico(CadZonaEleitoralPK pkze) throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	 
		   String sql = "SELECT pt FROM PontoTransmissao pt " + 
		   		"  WHERE pt.id.eleicao.ativo=1 " +
				"  AND pt.zona=?1" + 
				"  AND pt.codmunic=?2" +
		   		"  AND (pt.id.id, pt.id.eleicao.id) NOT IN ( " + 
		   		"     SELECT distinct ds.id.pontoTransmissao.id.id, ds.id.pontoTransmissao.id.eleicao.id " + 
		   		"        FROM DistribuicaoTecnico ds " + 
		   		"        WHERE ds.id.pontoTransmissao.id.eleicao.ativo=1)";
		   TypedQuery<PontoTransmissao> query = em.createQuery(sql, PontoTransmissao.class);
		      query.setParameter(1, pkze.getZona());
		      lista = query.setParameter(2, pkze.getCodmunic()).getResultList();
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
	public List<PontoTransmissao> listarSemOficializar(CadZonaEleitoralPK pkze) throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM PontoTransmissao u "
		     						+ "WHERE u.id.eleicao.ativo=1 "
		     						+ "AND u.zona=?1 "		     						
		     						+ "AND u.codmunic=?2 "
		     						+ "AND u.oficial=0 ",		     						
		    		 PontoTransmissao.class);
		      query.setParameter(1, pkze.getZona());
		      lista = query.setParameter(2, pkze.getCodmunic()).getResultList();
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
	public List<PontoTransmissao> listar(CadZonaEleitoralPK pkze) throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM PontoTransmissao u "
		     						+ "WHERE u.id.eleicao.ativo=1 AND u.zona=?1 AND u.codmunic=?2 ORDER BY u.codLocal", 
		    		 PontoTransmissao.class);
		      query.setParameter(1, pkze.getZona());
		      lista = query.setParameter(2, pkze.getCodmunic()).getResultList();
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
	public PontoTransmissao getBean(PontoTransmissaoPK pk) throws Exception {
		PontoTransmissao uservico = new PontoTransmissao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM PontoTransmissao u "
		     												  + "WHERE u.id.eleicao.id=?1 AND u.id.id=?2", 
		    		 PontoTransmissao.class);
		     query.setParameter(1, pk.getEleicao().getId());
		     uservico =  query.setParameter(2, pk.getId()).getSingleResult();
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
	public PontoTransmissao getBean(Integer id) throws Exception{
		PontoTransmissao uservico = new PontoTransmissao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM PontoTransmissao u"
		     												  + " WHERE u.id.eleicao.ativo=1 AND u.id.id=?1", 
		    		 PontoTransmissao.class);
		     uservico =  query.setParameter(1, id).getSingleResult();
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
	public int oficializar(PontoTransmissaoPK pk) throws Exception{
		int ret = 0;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		  try {	  
			    em.getTransaction().begin();
			      Query query = em.createQuery("UPDATE PontoTransmissao p SET p.oficial=1, p.status=1 "
		     								+ "WHERE p.id.eleicao.id=?1 AND p.id.id=?2");
		           query.setParameter(1, pk.getIdEleicao());
			       query.setParameter(2, pk.getId()).executeUpdate();
			     em.getTransaction().commit();
			    ret  = 1;
			  }
			  catch (Exception e) {
				    em.close();
				 e.printStackTrace();
			  }	finally {
				em.close();
		  }
	  return ret; 
	}
	
	public int oficializar(CadZonaEleitoralPK pkze, Integer idEleicao) throws Exception{
		int ret = 0;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		  try {	  
			    em.getTransaction().begin();
			      Query query = em.createQuery("UPDATE PontoTransmissao p SET p.oficial=1, p.status=1 "
		     								+ "WHERE p.oficial=0 AND p.id.eleicao.id=?3 AND p.zona=?1 AND p.codmunic=?2");
		           query.setParameter(1, pkze.getZona());
		           query.setParameter(3, idEleicao);
		           query.setParameter(2, pkze.getCodmunic()).executeUpdate();
			     em.getTransaction().commit();
			    ret  = 1;
			  }
			  catch (Exception e) {
				    em.close();
				 e.printStackTrace();
			  }	finally {
				em.close();
		  }
	  return ret; 
	}

	@Override
	public int adicionar(PontoTransmissao pontoTransmissao) throws Exception {
		int ret = 0;
		try {			
			PontoTransmissaoPK pk = pontoTransmissao.getId();
			pk.setId(dao.gerarId());
			pontoTransmissao.setId(pk);
			ret = dao.adicionar(pontoTransmissao);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(PontoTransmissao pontoTransmissao) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(pontoTransmissao);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(PontoTransmissao pontoTransmissao) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(pontoTransmissao);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}
	

	public static void main(String[] args) throws Exception{
		PontoTransmissaoDAO dao = PontoTransmissaoDAOImpl.getInstance();
		
		for (PontoTransmissao p : dao.listarSemDistribuicaoTecnico()) {
			System.out.println("Ponto " + p.getZona() + " " + p.getCodLocal() + " " + p.getDescricao());
		}
		
		/*
		Eleicao eleicao = new Eleicao();
		eleicao = EleicaoDAOImpl.getInstance().getBeanAtiva();
		PontoTransmissaoPK pk = new PontoTransmissaoPK(832020, eleicao);
		
		System.out.println("Eleicao id= " + pk.getEleicao().getId());
		PontoTransmissao pt = new PontoTransmissao();
		pt.setId(pk);
		
		int ret = dao.oficializar(pk);
		System.out.println("Ret = " + ret);
		*/

		
		//int ret = dao.oficializar(new CadZonaEleitoralPK("60;2895"), 12019);
		//System.out.println("Ret = " + ret);
		
		/*
		PontoTransmissaoPK pk = new PontoTransmissaoPK();
		Eleicao eleicao = new Eleicao();
		eleicao = EleicaoDAOImpl.getInstance().getBeanAtiva();
		pk.setEleicao(eleicao);
		System.out.println("Eleicao id= " + pk.getEleicao().getId());
		PontoTransmissao pt = new PontoTransmissao();
		pt.setId(pk);
		pt.setZona(60);
		pt.setCodmunic(2895);
		pt.setCodLocal(0);
		pt.setDescricao("CADASTRO MANUAL 03");
		pt.setEndereco("ESTRADA YELLOW FIELD, COMUNIDADE DO BLACK");
		pt.setOficial(0);
		pt.setStatus(0);
		int ret = dao.adicionar(pt);
		System.out.println("Ret " + ret);
		*/
		
		/*
		PontoTransmissao pt = new PontoTransmissao();
		pt = dao.getBean(132019);
		System.out.println("Descricao " + pt.getDescricao());
		int ret = dao.remover(pt);
		System.out.println("ret == " + ret);
		*/
		/*
		PontoTransmissao pt = new PontoTransmissao();
		pt = dao.getBean(182019);
		System.out.println("Descricao " + pt.getDescricao());
		*/
		
		
		/*
		for (PontoTransmissao p : dao.listarSemDistribuicaoSecao(new CadZonaEleitoralPK("60;2895"))) {
			System.out.println("Ponto " + p.getZona() + " " + p.getCodLocal() + " " + p.getDescricao());
		}
		 */
		
		System.out.println("Done!!");
	}

	

}
