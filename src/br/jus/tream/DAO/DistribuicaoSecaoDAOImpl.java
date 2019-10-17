package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.CADLocalvotacao;
import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.DistribuicaoSecao;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

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
	public List<DistribuicaoSecao> listar(Integer idPontoTransmissao) throws Exception {
		List<DistribuicaoSecao> lista = new ArrayList<DistribuicaoSecao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoSecao> query = em
					.createQuery("SELECT ds FROM DistribuicaoSecao ds WHERE "
								+ "ds.id.pontoTransmissao.id.id=?1 AND ds.id.pontoTransmissao.id.eleicao.ativo=1 ORDER BY ds.secao",
							DistribuicaoSecao.class);
			query.setParameter(1, idPontoTransmissao);
			lista = query.getResultList();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}
	
	public List<CADLocalvotacao> listarByClassLocalVotacao(Integer idPontoTransmissao) throws Exception{
		List<CADLocalvotacao> lista = new ArrayList<CADLocalvotacao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADLocalvotacao> query = em
					.createQuery("SELECT a FROM CADLocalvotacao a WHERE a.id IN ("
								+ "SELECT DISTINCT ds.codObjetoLocal FROM DistribuicaoSecao ds "
								      + "WHERE ds.id.pontoTransmissao.id.id=?1 AND ds.id.pontoTransmissao.id.eleicao.ativo=1"
								+ ") ORDER BY a.numLocal",
								CADLocalvotacao.class);
			query.setParameter(1, idPontoTransmissao);
			lista = query.getResultList();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}
	
	public List<CADLocalvotacao> listarByClassLocalVotacao(CadZonaEleitoralPK pkze) throws Exception{
		List<CADLocalvotacao> lista = new ArrayList<CADLocalvotacao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADLocalvotacao> query = em
					.createQuery("SELECT a FROM CADLocalvotacao a WHERE a.id IN ("
								+ "SELECT DISTINCT ds.codObjetoLocal FROM DistribuicaoSecao ds "
								      + "WHERE ds.id.pontoTransmissao.zona=?1 AND ds.id.pontoTransmissao.codmunic=?2 AND ds.id.pontoTransmissao.id.eleicao.ativo=1"
								+ ") ORDER BY a.numLocal",
								CADLocalvotacao.class);
			query.setParameter(1, pkze.getZona());
			query.setParameter(2, pkze.getCodmunic());
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
	public List<DistribuicaoSecao> listar(CadZonaEleitoralPK pkze) throws Exception {
		List<DistribuicaoSecao> lista = new ArrayList<DistribuicaoSecao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoSecao> query = em
					.createQuery("SELECT ds FROM DistribuicaoSecao ds WHERE "
								+ "ds.zona=?1 AND ds.codmunic=?2 AND ds.id.pontoTransmissao.id.eleicao.ativo=1 ORDER BY ds.secao",
							DistribuicaoSecao.class);
			query.setParameter(1, pkze.getZona());
			query.setParameter(2, pkze.getCodmunic());
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
	public List<CADSecao> listarParaDistribuir(CadZonaEleitoralPK pkze, Integer numlocal) throws Exception {
		List<CADSecao> lista = new ArrayList<CADSecao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<CADSecao> query = em
					.createQuery("SELECT s FROM CADSecao s WHERE s.zona=?1 AND s.codmunic=?2 and s.numLocal=?3 "
							+ "AND s.secaoPrincipal IS NULL AND s.secao NOT IN (SELECT ds.secao FROM DistribuicaoSecao ds "
													+ "WHERE ds.zona=?4 AND ds.codmunic=?5 AND ds.id.pontoTransmissao.id.eleicao.ativo=1)"
							+ "ORDER BY s.secao",
								CADSecao.class);
			query.setParameter(1, pkze.getZona());
			query.setParameter(2, pkze.getCodmunic());
			query.setParameter(3, numlocal);
			query.setParameter(4, pkze.getZona());
			query.setParameter(5, pkze.getCodmunic());
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
								+ "ds.id.codOjeto=?1 AND ds.id.pontoTransmissao.id.eleicao.ativo=1",
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
			// SE HOUVER UM VET DE SEÇÕES INSERÇÃO EM LOTE
			// o valor de cada elementro é: cod_objeto_secao;num_secao 
			if (ds.getVetsecoes()!=null) {
				String[] vetsecao = ds.getVetsecoes();
				for (String vet : vetsecao) {
					String[] secao = vet.split(";");
					ds.getId().setCodOjeto(secao[0]);
					ds.setSecao( Integer.valueOf(secao[1]));
					// System.out.println(">> estarei inserindo na US " + ds.getId().getUnidadeServico().getId().getId() + " e objeto secao  " + secao[0] + "/" + secao[1]);
					ret = dao.adicionar(ds);
				}
			}else	
				//System.out.println(">> estarei secao individualmente " + ds.getSecao() + " e cod_objeto_secao = " + ds.getId().getCodOjeto());
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
		/*
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
		
		String secoes = "253065;10,253067;34,310422;42";
		String[] vet = secoes.split(",");
		ds.setVetsecoes(vet);

		int ret = dao.adicionar(ds);
		System.out.println("ret == " + ret);
		*/
		//ds = dao.getBean("253066");
		//     System.out.println("ZE " + ds.getZona());
		
		CadZonaEleitoralPK pkze = new CadZonaEleitoralPK("60;2895");
		
		for(CADLocalvotacao d : dao.listarByClassLocalVotacao(pkze)) {
			System.out.println("Zona " + d.getZona() + " " + d.getNumLocal() );
			//for (DistribuicaoSecao ds : d.getSecoesDistribuidas()) {
				//System.out.println("------ " + ds.getSecao());
			//}
		}
		
		
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
