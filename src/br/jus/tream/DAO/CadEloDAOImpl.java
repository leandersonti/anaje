package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.CADLocalvotacao;
import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.CADZonaEleitoral;

public class CadEloDAOImpl implements CadEloDAO {
	
	static CadEloDAO db;

	public static CadEloDAO getInstance() {
		if (db == null) {
			db = new CadEloDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<CADLocalvotacao> listarLocalVotacao(Integer zona, Integer codmunic) throws Exception {
		List<CADLocalvotacao> lst = new ArrayList<CADLocalvotacao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADLocalvotacao> query = em.createQuery("SELECT c FROM CADLocalvotacao c WHERE c.zona=?1 AND c.codmunic=?2 ORDER BY c.numLocal",
					CADLocalvotacao.class);
			query.setParameter(1, zona);
			lst = query.setParameter(2, codmunic).getResultList();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return lst;
	}
	
	@Override
	public List<CADLocalvotacao> listarLocalVotacaoParaCadastrar(Integer zona, Integer codmunic) throws Exception{
		List<CADLocalvotacao> lst = new ArrayList<CADLocalvotacao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADLocalvotacao> query = em.createQuery("SELECT c FROM CADLocalvotacao c WHERE c.zona=?1 AND c.codmunic=?2 "
					+ "AND (c.id NOT IN (SELECT p.codObjetoLocal FROM PontoTransmissao p WHERE p.id.eleicao.ativo=1 AND p.zona=?3 AND p.codmunic=?4)) ORDER BY c.numLocal",
					CADLocalvotacao.class);
			query.setParameter(1, zona);
			query.setParameter(2, codmunic);
			query.setParameter(3, zona);
			lst = query.setParameter(4, codmunic).getResultList();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return lst;
	}
	
	@Override
	public CADLocalvotacao getBeanLocalVotacao(String codObjeto) throws Exception{
		CADLocalvotacao localvotacao = new CADLocalvotacao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADLocalvotacao> query = em.createQuery("SELECT c FROM CADLocalvotacao c WHERE c.id=?1",
					CADLocalvotacao.class);
			localvotacao = query.setParameter(1, codObjeto).getSingleResult();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return localvotacao;
	}
	
	public CADLocalvotacao getBeanLocalVotacao(Integer zona, Integer codmunic, Integer numLocal) throws Exception{
		CADLocalvotacao localvotacao = new CADLocalvotacao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADLocalvotacao> query = em.createQuery("SELECT c FROM CADLocalvotacao c "
													+ "WHERE c.zona=?1 AND c.codmunic=?2 AND c.numLocal=?3",
					CADLocalvotacao.class);
			query.setParameter(1, zona);
			query.setParameter(2, codmunic);
			localvotacao = query.setParameter(3, numLocal).getSingleResult();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return localvotacao;
	}

	@Override
	public List<CADSecao> listarSecao(Integer zona, Integer codmunic) throws Exception {
		List<CADSecao> lst = new ArrayList<CADSecao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADSecao> query = em.createQuery("SELECT s FROM CADSecao s WHERE s.zona=?1 AND s.codmunic=?2 ORDER BY s.secao",
					CADSecao.class);
			query.setParameter(1, zona);
			lst = query.setParameter(2, codmunic).getResultList();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return lst;
	}
	

	@Override
	public List<CADSecao> listarSecao(Integer zona, Integer codmunic, Integer numLocal) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CADZonaEleitoral> listarZonaEleitoralCBX() throws Exception{
		List<CADZonaEleitoral> lst = new ArrayList<CADZonaEleitoral>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADZonaEleitoral> query = em.createQuery("SELECT NEW CADZonaEleitoral(id, municipio) FROM CADZonaEleitoral z "
					+ "ORDER BY z.municipio, z.id.zona",
					CADZonaEleitoral.class);
			lst = query.getResultList();
		} catch (Exception e) {
			em.close();
		} finally {
			em.close();
		}
		return lst;
	}
	
	@Override
	public List<CADZonaEleitoral> listarZonaEleitoralCBX(Integer zona) throws Exception{
		List<CADZonaEleitoral> lst = new ArrayList<CADZonaEleitoral>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADZonaEleitoral> query = em.createQuery("SELECT NEW CADZonaEleitoral(id, municipio) FROM CADZonaEleitoral z "
					+ "WHERE z.id.zona=?1 ORDER BY z.municipio, z.id.zona",
					CADZonaEleitoral.class);
			lst = query.setParameter(1, zona).getResultList();
		} catch (Exception e) {
			em.close();
		} finally {
			em.close();
		}
		return lst;
	}
	
	@Override
	public List<CADZonaEleitoral> listarZonaEleitoral() throws Exception {
		List<CADZonaEleitoral> lst = new ArrayList<CADZonaEleitoral>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADZonaEleitoral> query = em.createQuery("SELECT z FROM CADZonaEleitoral z ORDER BY z.id.zona, z.municipio",
					CADZonaEleitoral.class);
			lst = query.getResultList();
		} catch (Exception e) {
			em.close();
		} finally {
			em.close();
		}
		return lst;
	}

	@Override
	public List<CADZonaEleitoral> listarZonaEleitoral(Integer zona) throws Exception {
		List<CADZonaEleitoral> lst = new ArrayList<CADZonaEleitoral>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADZonaEleitoral> query = em.createQuery("SELECT z FROM CADZonaEleitoral z WHERE z.id.zona=?1",
					CADZonaEleitoral.class);
			lst = query.setParameter(1, zona).getResultList();
		} catch (Exception e) {
			em.close();
		} finally {
			em.close();
		}
		return lst;
	}

	@Override
	public CADZonaEleitoral getZonaEleitoral(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {
		CadEloDAO dao = CadEloDAOImpl.getInstance();
		
		
		for(CADZonaEleitoral z : dao.listarZonaEleitoralCBX(60)) {
			System.out.println(z.getCodmunic() + " Zona " + z.getFzona() + " " + z.getMunicipio());
		}
		
		/*
		for(CADLocalvotacao local : dao.listarLocalVotacaoParaCadastrar(31, 2550)) {
			System.out.println(local.getId() + " :: Zona " + local.getZona() + " " + local.getNumLocal() + " " + local.getNomeLocal());
		}
		*/
		
		/*
		for(CADZonaEleitoral z : dao.listarZonaEleitoralCBX(31)) {
			System.out.println(z.getZona() + " :: Zona " + z.getZona() + " " + z.getMunicipio() + " " + z.getCodmunic());
		}
		*/
		
		//CADLocalvotacao local = dao.getBeanLocalVotacao(31, 2550, 1910);
		//System.out.println(local.getId() + " :: Zona " + local.getZona() + " " + local.getNumLocal() + " " + local.getNomeLocal());
		
		System.out.println("Done!!");
	}

}
