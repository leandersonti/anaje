package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DistribuicaoTecnico;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

public class DistribuicaoTecnicoDAOImpl implements DistribuicaoTecnicoDAO {
	
	private DAO<DistribuicaoTecnico> dao = new DAO<DistribuicaoTecnico>(DistribuicaoTecnico.class);

	static DistribuicaoTecnicoDAO db;

	public static DistribuicaoTecnicoDAO getInstance() {
		if (db == null) {
			db = new DistribuicaoTecnicoDAOImpl();
		}
		return db;
	}


	@Override
	public List<DistribuicaoTecnico> listar(CadZonaEleitoralPK codzonaMunic, Integer contrato) throws Exception {
		List<DistribuicaoTecnico> lista = new ArrayList<DistribuicaoTecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			
			TypedQuery<DistribuicaoTecnico> query = em
					.createQuery("SELECT ds FROM DistribuicaoTecnico ds,TecnicoContrato dt WHERE "
								+ "dt.id.contrato.id=?3 AND ds.id.pontoTransmissao.zona=?1 AND ds.id.pontoTransmissao.codmunic=?2"
								+ " AND ds.id.pontoTransmissao.id.eleicao.ativo=1 AND ds.id.tecnico.id = dt.id.tecnico.id",
								DistribuicaoTecnico.class);
			query.setParameter(1, codzonaMunic.getZona());
			query.setParameter(2, codzonaMunic.getCodmunic());
			query.setParameter(3, contrato);
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
	public List<DistribuicaoTecnico> listar(CadZonaEleitoralPK codzonaMunic) throws Exception {
		List<DistribuicaoTecnico> lista = new ArrayList<DistribuicaoTecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {			  
			TypedQuery<DistribuicaoTecnico> query = em
					.createQuery("SELECT ds FROM DistribuicaoTecnico ds WHERE "
								+ " ds.id.pontoTransmissao.zona=?1 AND ds.id.pontoTransmissao.codmunic=?2"
								+ " AND ds.id.pontoTransmissao.id.eleicao.ativo=1",
								DistribuicaoTecnico.class);
			query.setParameter(1, codzonaMunic.getZona());
			query.setParameter(2, codzonaMunic.getCodmunic());			
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
	public List<Tecnico> listarParaDistribuir() throws Exception {
		List<Tecnico> lista = new ArrayList<Tecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Tecnico> query = em
					.createQuery("SELECT s FROM Tecnico s WHERE "
							+ "s.id NOT IN (SELECT ds.id.tecnico.id FROM DistribuicaoTecnico ds "
							+ "WHERE ds.id.pontoTransmissao.id.eleicao.ativo=1)",
							Tecnico.class);			
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
	public List<Tecnico> listarParaDistribuir(Integer contrato) throws Exception {
		List<Tecnico> lista = new ArrayList<Tecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Tecnico> query = em
					.createQuery("SELECT NEW Tecnico(t.id,t.nome) FROM Tecnico t " + 
							" WHERE t.id in(SELECT d.id.tecnico.id FROM TecnicoContrato d WHERE d.ativo = 1 " + 
							" AND d.id.contrato.id = ?1 AND id.eleicao.ativo = 1) " + 
							" AND t.id NOT IN (SELECT ds.id.tecnico.id FROM DistribuicaoTecnico ds " + 
							" WHERE ds.id.pontoTransmissao.id.eleicao.ativo=1)",
							Tecnico.class);	
			query.setParameter(1, contrato);
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
	public List<Tecnico> listarParaDistribuir(Integer zona, Integer contrato) throws Exception {
		List<Tecnico> lista = new ArrayList<Tecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Tecnico> query = em
					.createQuery("SELECT NEW Tecnico(t.id,t.nome) FROM Tecnico t " + 
							" WHERE t.zona=?1 AND t.id in(SELECT d.id.tecnico.id FROM TecnicoContrato d WHERE d.ativo = 1" + 
							" AND d.id.contrato.id = ?2 AND id.eleicao.ativo = 1) "+ 
							" AND t.id NOT IN (SELECT ds.id.tecnico.id FROM DistribuicaoTecnico ds " + 
							" WHERE ds.id.pontoTransmissao.id.eleicao.ativo=1)",
							Tecnico.class);				
			query.setParameter(1, zona);
			query.setParameter(2, contrato);
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
	public DistribuicaoTecnico getBean(String tituloEleitor) throws Exception {
		DistribuicaoTecnico ds = new DistribuicaoTecnico();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoTecnico> query = em.createQuery("SELECT ds FROM DistribuicaoTecnico ds WHERE "
								+ "ds.id.tecnico.tituloEleitor = ?1 AND ds.id.pontoTransmissao.id.eleicao.ativo=1",
								DistribuicaoTecnico.class);
			query.setParameter(1, tituloEleitor);
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
	public List<DistribuicaoTecnico> listar(Integer idPontoTransmissao) throws Exception {
		List<DistribuicaoTecnico> lista = new ArrayList<DistribuicaoTecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoTecnico> query = em.createQuery("SELECT ds FROM DistribuicaoTecnico ds WHERE "
								+ "ds.id.pontoTransmissao.id.id = ?1 AND ds.id.pontoTransmissao.id.eleicao.ativo=1",
								DistribuicaoTecnico.class);
			query.setParameter(1, idPontoTransmissao);
			lista = query.getResultList();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	} 

	@Override
	public int adicionar(DistribuicaoTecnico ds) throws Exception {
		int ret = 0;
		try {
			 ret = dao.adicionar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}


	@Override
	public int remover(DistribuicaoTecnico ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		 DistribuicaoTecnicoDAO dao = DistribuicaoTecnicoDAOImpl.getInstance();
		 //CadZonaEleitoralPK cad = new CadZonaEleitoralPK();		 
		 //cad.setZona(31);
		 //cad.setCodmunic(2046);
		 
		 System.out.println("entrei!!");
		
		 for(Tecnico t:dao.listarParaDistribuir(20)) { 
			  System.out.println("==="+t.getNome());		  
		  }
		 
		/*
		 * DistribuicaoTecnico dst = new DistribuicaoTecnico();
		 * 
		 * dst = dao.getBean("037443852224");
		 * System.out.println("==="+dst.getId().getTecnico().getNome());
		 */
		
		
		/*DistribuicaoTecnico dst = new DistribuicaoTecnico();
		dst.setDataCad(new Date());		
		Tecnico t = new Tecnico();
		t.setId(1282018);
		Tecnico t2 = new Tecnico();
		t2.setId(1302018);				
		dst.getId().setTecnico(t);
		dst.setTecnicoResponsavel(t2);
		
		PontoTransmissao us = new PontoTransmissao();		
		 us = PontoTransmissaoDAOImpl.getInstance().getBean(112019);
		dst.getId().setPontoTransmissao(us);
		
		DistribuicaoTecnicoPK dstpk = new DistribuicaoTecnicoPK();
		dstpk.setTecnico(t);
		dstpk.setPontoTransmissao(us);
		
		int ret = dao.adicionar(dst);				
		//int ret = dao.remover(dst);
		System.out.println("ret == " + ret);
		*/
		System.out.println("Done!!");
	}



}
