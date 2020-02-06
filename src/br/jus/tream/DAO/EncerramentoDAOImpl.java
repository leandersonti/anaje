package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Encerramento;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.VWEncerramento;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public class EncerramentoDAOImpl implements EncerramentoDAO {

	private DAO<Encerramento> dao = new DAO<Encerramento>(Encerramento.class);

	static EncerramentoDAO db;

	public static EncerramentoDAO getInstance() {
		if (db == null) {
			db = new EncerramentoDAOImpl();
		}
		return db;
	}

	@Override
	public VWEncerramento getBean(PontoTransmissaoPK id) throws Exception {
		VWEncerramento encerramento = new VWEncerramento();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<VWEncerramento> query = em.createQuery("SELECT e FROM VWEncerramento e WHERE e.id.idus=?1 "
					+ "AND e.id.idEleicao=?2", VWEncerramento.class);
			query.setParameter(1, id.getId());
			query.setParameter(2, id.getIdEleicao());
			encerramento = query.getSingleResult();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
		} finally {
			em.close();
		}

		return encerramento;
	}
	
	@Override
	public List<VWEncerramento> listar() throws Exception{
		List<VWEncerramento> lista = new ArrayList<VWEncerramento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<VWEncerramento> query = em.createQuery("SELECT e FROM VWEncerramento e ORDER BY e.zona, e.codmunic, e.nome", 
					             VWEncerramento.class);
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
	public List<VWEncerramento> listar(Integer zona, String estadoRecebimento) throws Exception{
		List<VWEncerramento> lista = new ArrayList<VWEncerramento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<VWEncerramento> query = em.createQuery("SELECT e FROM VWEncerramento e WHERE e.dataCad "
						                   + estadoRecebimento + " AND e.zona=?1 ORDER BY e.nome", VWEncerramento.class);
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
	public List<VWEncerramento> listar(CadZonaEleitoralPK pkze, String estadoRecebimento) throws Exception {
		
		List<VWEncerramento> lista = new ArrayList<VWEncerramento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<VWEncerramento> query = null;
			String sql = "SELECT e FROM VWEncerramento e WHERE e.dataCad "+ estadoRecebimento + " ORDER BY e.zona, e.codmunic, e.nome";
			if (pkze.getZona()!=9999) {
				sql = "SELECT e FROM VWEncerramento e WHERE e.dataCad " + estadoRecebimento + " AND e.zona=?1 AND e.codmunic=?2 ORDER BY e.nome";
				query = em.createQuery(sql,	VWEncerramento.class);
				query.setParameter(1, pkze.getZona());
				query.setParameter(2, pkze.getCodmunic());
			}else {
				query = em.createQuery(sql,	VWEncerramento.class);	
			}
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
	public List<VWEncerramento> listar(Tecnico tecnicoResponsavel, String estadoRecebimento) throws Exception {
		List<VWEncerramento> lista = new ArrayList<VWEncerramento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<VWEncerramento> query = em.createQuery("SELECT e FROM VWEncerramento e WHERE e.dataCad "+ 
						estadoRecebimento + " AND e.idTecnicoResp=?1 ORDER BY e.nome", VWEncerramento.class);
			query.setParameter(1, tecnicoResponsavel.getId());
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
	public List<VWEncerramento> listar(Tecnico tecnicoResponsavel) throws Exception{
		List<VWEncerramento> lista = new ArrayList<VWEncerramento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<VWEncerramento> query = em.createQuery("SELECT e FROM VWEncerramento e WHERE e.idTecnicoResp=?1 ORDER BY e.nome", VWEncerramento.class);
			query.setParameter(1, tecnicoResponsavel.getId());
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
	public int reinicializar(CadZonaEleitoralPK pkzona, PontoTransmissaoPK ponto) throws Exception{
		int ret = 0;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			StoredProcedureQuery query = em
				    .createStoredProcedureQuery("reinicializar_encerramento")
				    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT)
				    .setParameter(1, pkzona.getZona())
				    .setParameter(2, pkzona.getCodmunic())
				    .setParameter(3, ponto.getId());
			query.execute();
			ret = (int) query.getOutputParameterValue(4);
		} catch (Exception e) {
			em.close();
		} finally {
			em.close();
		}
		return ret;
	}
	
	
	@Override
	public List<VWEncerramento> listar(String estadoRecebimento) throws Exception {
		List<VWEncerramento> lista = new ArrayList<VWEncerramento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<VWEncerramento> query = em.createQuery("SELECT e FROM VWEncerramento e WHERE e.dataCad "+ estadoRecebimento 
					+" ORDER BY e.zona, e.codmunic, e.nome",VWEncerramento.class);
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
	public int adicionar(Encerramento encerramento) throws Exception {
		int ret = 0;
		try {
			encerramento.setCodigo(dao.gerarCodigoAlpha());
			ret = dao.adicionar(encerramento);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(Encerramento encerramento) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(encerramento);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(Encerramento encerramento) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(encerramento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {

		EncerramentoDAO dao = EncerramentoDAOImpl.getInstance();
		 /*
		  VWEncerramento e = new VWEncerramento(); 
		  Eleicao eleicao = new Eleicao();
		  eleicao.setId(12019); 
		  PontoTransmissaoPK pk = new PontoTransmissaoPK();
		  pk.setId(2412020); pk.setEleicao(eleicao); 
		  e = dao.getBean(pk);
		  System.out.println("Encerramento " + e.toString());
		 */
		
		  // CadZonaEleitoralPK pkze = new CadZonaEleitoralPK("60;2895");
		Tecnico tecresp = new Tecnico(1432018,"");
		  for(VWEncerramento e: dao.listar(tecresp)) { 
			  System.out.println("Status===" +  e.toString()); 
		  }
		 

	}
}
