package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DistribuicaoTecnicoUS;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.UnidadeServico;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.DistribuicaoTecnicoUSPK;
import br.jus.tream.dominio.pk.UnidadeServicoPK;

public class DistribuicaoTecnicoUsDAOImpl implements DistribuicaoTecnicoUsDAO {
	
	private DAO<DistribuicaoTecnicoUS> dao = new DAO<DistribuicaoTecnicoUS>(DistribuicaoTecnicoUS.class);

	static DistribuicaoTecnicoUsDAO db;

	public static DistribuicaoTecnicoUsDAO getInstance() {
		if (db == null) {
			db = new DistribuicaoTecnicoUsDAOImpl();
		}
		return db;
	}


	@Override
	public List<DistribuicaoTecnicoUS> listar(CadZonaEleitoralPK codzonaMunic, Integer contrato) throws Exception {
		List<DistribuicaoTecnicoUS> lista = new ArrayList<DistribuicaoTecnicoUS>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			
			TypedQuery<DistribuicaoTecnicoUS> query = em
					.createQuery("SELECT ds FROM DistribuicaoTecnicoUS ds, DistribuicaoTecnicoContrato dt WHERE "
								+ "dt.id.contrato.id=?3 AND ds.id.unidadeServico.zona=?1 AND ds.id.unidadeServico.codmunic=?2"
								+ " AND ds.id.unidadeServico.id.dataEleicao.ativo=1 AND ds.id.tecnico.id = dt.id.tecnico.id",
								DistribuicaoTecnicoUS.class);
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
	public List<DistribuicaoTecnicoUS> listar(CadZonaEleitoralPK codzonaMunic) throws Exception {
		List<DistribuicaoTecnicoUS> lista = new ArrayList<DistribuicaoTecnicoUS>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {			  
			TypedQuery<DistribuicaoTecnicoUS> query = em
					.createQuery("SELECT ds FROM DistribuicaoTecnicoUS ds WHERE "
								+ " ds.id.unidadeServico.zona=?1 AND ds.id.unidadeServico.codmunic=?2"
								+ " AND ds.id.unidadeServico.id.dataEleicao.ativo=1",
								DistribuicaoTecnicoUS.class);
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
							+ "s.id NOT IN (SELECT ds.id.tecnico.id FROM DistribuicaoTecnicoUS ds "
							+ "WHERE ds.id.unidadeServico.id.dataEleicao.ativo=1)",
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
	public List<Tecnico> listarParaDistribuir(Integer zona) throws Exception {
		List<Tecnico> lista = new ArrayList<Tecnico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Tecnico> query = em
					.createQuery("SELECT s FROM Tecnico s WHERE s.zona=?1 "
							+ " AND s.id NOT IN (SELECT ds.id.tecnico.id FROM DistribuicaoTecnicoUS ds "
							+ " WHERE ds.id.unidadeServico.id.dataEleicao.ativo=1)",
							Tecnico.class);	
			query.setParameter(1, zona);
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
	public DistribuicaoTecnicoUS getBean(String tituloEleitor) throws Exception {
		DistribuicaoTecnicoUS ds = new DistribuicaoTecnicoUS();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoTecnicoUS> query = em.createQuery("SELECT ds FROM DistribuicaoTecnicoUS ds WHERE "
								+ "ds.id.tecnico.tituloEleitor = ?1 AND ds.id.unidadeServico.id.dataEleicao.ativo=1",
								DistribuicaoTecnicoUS.class);
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
	public int adicionar(DistribuicaoTecnicoUS ds) throws Exception {
		int ret = 0;
		try {
			 ret = dao.adicionar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}


	@Override
	public int remover(DistribuicaoTecnicoUS ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	public static void main(String[] args) throws Exception {
		DistribuicaoTecnicoUsDAO dao = DistribuicaoTecnicoUsDAOImpl.getInstance();
		 //CadZonaEleitoralPK cad = new CadZonaEleitoralPK();		 
		 //cad.setZona(31);
		 //cad.setCodmunic(2046);
		 
		 System.out.println("entrei!!");
		 /*for(Tecnico t:dao.listarParaDistribuir(45)) { 
			  System.out.println("==="+t.getNome());		  
		  }*/
		
		
		DistribuicaoTecnicoUS dst = new DistribuicaoTecnicoUS();
		dst.setDataCad(new Date());		
		Tecnico t = new Tecnico();
		t.setId(322);
		Tecnico t2 = new Tecnico();
		t2.setId(326);				
		dst.getId().setTecnico(t);
		dst.setTecnicoResponsavel(t2);
		
		UnidadeServico us = new UnidadeServico();		
		 us = UnidadeServicoDAOImpl.getInstance().getBean(2832019);
		dst.getId().setUnidadeServico(us);
		
		DistribuicaoTecnicoUSPK dstpk = new DistribuicaoTecnicoUSPK();
		dstpk.setTecnico(t);
		dstpk.setUnidadeServico(us);
		
		//int ret = dao.adicionar(dst);				
		//int ret = dao.remover(dst);
		//System.out.println("ret == " + ret);
		
		System.out.println("Done!!");
	}

}
