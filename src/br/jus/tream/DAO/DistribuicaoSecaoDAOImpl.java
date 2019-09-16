package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.DistribuicaoSecao;
import br.jus.tream.dominio.UnidadeServico;
import br.jus.tream.dominio.pk.DistribuicaoSecaoPK;

public class DistribuicaoSecaoDAOImpl implements DistribuicaoSecaoDAO {
	
	private DAO<DistribuicaoSecao> dao = new DAO<DistribuicaoSecao>(DistribuicaoSecao.class);

	static DistribuicaoSecaoDAO db;

	public static DistribuicaoSecaoDAO getInstance() {
		if (db == null) {
			db = new DistribuicaoSecaoDAOImpl();
		}
		return db;
	}


	@Override
	public List<DistribuicaoSecao> listar(Integer idUnidadeServico) throws Exception {
		List<DistribuicaoSecao> lista = new ArrayList<DistribuicaoSecao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoSecao> query = em
					.createQuery("SELECT ds FROM DistribuicaoSecao ds WHERE "
								+ "ds.id.unidadeServico.id.id=?1 AND ds.id.unidadeServico.id.dataEleicao.ativo=1 ORDER BY ds.secao",
							DistribuicaoSecao.class);
			query.setParameter(1, idUnidadeServico);
			lista = query.getResultList();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}
	
	@Override
	public List<DistribuicaoSecao> listar(Integer zona, Integer codmunic) throws Exception {
		List<DistribuicaoSecao> lista = new ArrayList<DistribuicaoSecao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoSecao> query = em
					.createQuery("SELECT ds FROM DistribuicaoSecao ds WHERE "
								+ "ds.zona=?1 AND ds.codmunic=?2 AND ds.id.unidadeServico.id.dataEleicao.ativo=1 ORDER BY ds.secao",
							DistribuicaoSecao.class);
			query.setParameter(1, zona);
			query.setParameter(2, codmunic);
			lista = query.getResultList();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}

	@Override
	public List<CADSecao> listarParaDistribuir(Integer zona, Integer codmunic, Integer numlocal) throws Exception {
		List<CADSecao> lista = new ArrayList<CADSecao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADSecao> query = em
					.createQuery("SELECT s FROM CADSecao s WHERE s.zona=?1 AND s.codmunic=?2 and s.numLocal=?3 "
							+ "AND s.secao NOT IN (SELECT ds.secao FROM DistribuicaoSecao ds "
													+ "WHERE ds.zona=?4 AND ds.codmunic=?5 AND ds.id.unidadeServico.id.dataEleicao.ativo=1)"
							+ "ORDER BY s.secao",
								CADSecao.class);
			query.setParameter(1, zona);
			query.setParameter(2, codmunic);
			query.setParameter(3, numlocal);
			query.setParameter(4, zona);
			query.setParameter(5, codmunic);
			lista = query.getResultList();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}

	@Override
	public DistribuicaoSecao getBean(String codobjeto) throws Exception {
		DistribuicaoSecao ds = new DistribuicaoSecao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoSecao> query = em.createQuery("SELECT ds FROM DistribuicaoSecao ds WHERE "
								+ "ds.id.codOjeto=?1 AND ds.id.unidadeServico.id.dataEleicao.ativo=1",
				DistribuicaoSecao.class);
			query.setParameter(1, codobjeto);
			ds = query.getSingleResult();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return ds;
	}

	@Override
	public int adicionar(DistribuicaoSecao ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(DistribuicaoSecao ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(DistribuicaoSecao ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		DistribuicaoSecaoDAO dao = DistribuicaoSecaoDAOImpl.getInstance();
		
		DistribuicaoSecaoPK dspk = new DistribuicaoSecaoPK();
		DistribuicaoSecao ds = new DistribuicaoSecao();
		UnidadeServico us = new UnidadeServico();
		us = UnidadeServicoDAOImpl.getInstance().getBean(12019);
		
		dspk.setUnidadeServico(us);
		dspk.setCodOjeto("262297");
		ds.setId(dspk);
		ds.setZona(14);
		ds.setSecao(36);
		ds.setCodmunic(2151);

		int ret = dao.adicionar(ds);
		System.out.println("ret == " + ret);
		
		//ds = dao.getBean("253066");
		//     System.out.println("ZE " + ds.getZona());
		
		/*
		for(DistribuicaoSecao d : dao.listar(12019)) {
			System.out.println("Zona " + d.getZona() + " " + d.getSecao() + " " + d.getCodmunic());
		}
		*/
		
		/*
		for(CADSecao cad : dao.listarParaDistribuir(14, 2151, 1040)) {
			System.out.println("Zona " + cad.getZona() + " " + cad.getSecao() );
		}
		*/
		/*
		for(DistribuicaoSecao d : dao.listar(14, 2151)) {
			System.out.println("Zona " + d.getZona() + " " + d.getSecao() + " " + d.getCodmunic());
		}
		*/
		
		
		System.out.println("Done!!");
	}

}
