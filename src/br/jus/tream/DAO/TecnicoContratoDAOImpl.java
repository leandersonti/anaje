package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.TecnicoContrato;

public class TecnicoContratoDAOImpl implements TecnicoContratoDAO {
	
	private DAO<TecnicoContrato> dao = new DAO<TecnicoContrato>(TecnicoContrato.class);

	static TecnicoContratoDAO db;
	
	public static TecnicoContratoDAO getInstance() {
		if (db == null) {
			db = new TecnicoContratoDAOImpl();
		}
		return db;
	}

	@Override
	public List<TecnicoContrato> listar(Integer idTecnico) throws Exception {
		List<TecnicoContrato> lista = new ArrayList<TecnicoContrato>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<TecnicoContrato> query = em.createQuery("SELECT d FROM TecnicoContrato d "
					+ "WHERE d.id.tecnico.id=?1 ORDER BY d.datacad DESC",TecnicoContrato.class);
			 query.setParameter(1, idTecnico);
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
	public TecnicoContrato getBean(Integer idTecnico) throws Exception {
		TecnicoContrato dt = new TecnicoContrato();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<TecnicoContrato> query = em.createQuery("SELECT d FROM TecnicoContrato d "
					+ "WHERE d.ativo=1 AND d.id.tecnico.id=?1 AND d.id.eleicao.ativo=1 ORDER BY d.datacad DESC",TecnicoContrato.class);
			 query.setParameter(1, idTecnico);
			 dt = query.getSingleResult();
		} catch (Exception e) {
			em.close();
		    // e.printStackTrace();
		} finally {
			em.close();
		}
		return dt;
	}

	@Override
	public int adicionar(TecnicoContrato distec) throws Exception {
		int ret = 0;
		try {
			// SER HOUVER CONTRATOS ANTERIORES O ATIVO SER� AJUSTADO PARA ZERO
			// atualizarAtivo(distec);
			distec.setDatacad(new Date(System.currentTimeMillis()));
			distec.setAtivo(1);
			ret = dao.adicionar(distec);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}
	
	/*
	public int atualizarAtivo(TecnicoContrato distec) throws Exception {
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		int ret = 0;
		try {
			em.getTransaction().begin();
			   Query query = em.createQuery("UPDATE TecnicoContrato d SET d.ativo=0"
			   		       + " WHERE d.id.tecnico.id=?1 AND d.id.eleicao.ativo=1");
			   query.setParameter(1, distec.getTecnico().getId());
			   query.executeUpdate();			  
			   query = em.createQuery("UPDATE TecnicoContrato d SET d.ativo=1" 
			   		       + " WHERE d.id.tecnico.id=?1 AND d.id.contrato.id=?2");
				   query.setParameter(1, distec.getTecnico().getId());
				   query.setParameter(2, distec.getContrato().getId());
			   query.executeUpdate();
			 em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			 e.printStackTrace();
		} finally {
			em.close();
		}
		return ret;
	} */
	
	@Override
	public int mudarCargo(TecnicoContrato distec) throws Exception{
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		int ret = 0;
		try {
			em.getTransaction().begin();
			   Query query = em.createQuery("UPDATE TecnicoContrato d SET d.id.contrato.id=?1" 
			   		                 + " WHERE d.id.tecnico.id=?2 AND d.id.eleicao.id=?3");
			       query.setParameter(1, distec.getContrato().getId());
				   query.setParameter(2, distec.getTecnico().getId());
				   query.setParameter(3, distec.getEleicao().getId());
			   query.executeUpdate();
			 em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			 e.printStackTrace();
		} finally {
			em.close();
		}
		return ret;
	}
	
	@Override
	public int atualizar(TecnicoContrato distec) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(distec);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(TecnicoContrato distec) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(distec);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		TecnicoContratoDAO dao = TecnicoContratoDAOImpl.getInstance();

		
		/*
		DistribuicaoTecnicoContrato dt = new DistribuicaoTecnicoContrato();
		DataEleicao eleicao = new DataEleicao();
		eleicao = DataEleicaoDAOImpl.getInstance().getBeanAtiva();
		Tecnico t = new Tecnico();
		t.setId(289);
		Contrato c = new Contrato();
		c = ContratoDAOImpl.getInstance().getBean(96);
		
		DistribuicaoTecContratoPK pk = new DistribuicaoTecContratoPK();
		pk.setContrato(c);
		pk.setTecnico(t);
		pk.setDataEleicao(eleicao);
		dt.setId(pk);
		int ret = dao.inserir(dt);
		System.out.println("Ret = " + ret);
		*/
		
		
		TecnicoContrato d = dao.getBean(1102018);
		System.out.println("Tecnico " + d.getTecnico().getNome());
		System.out.println("............ Contrato ativo = " + d.getContrato().getDescricao() + " " +d.getDatacad());
		System.out.println("ContratoId " + d.getContrato().getId());
		// TecnicoContratoPK pk = d.getId();
		
		// mudando contrato
		//Contrato contrato = new Contrato();
		//contrato.setId(20);
		//pk.setContrato(contrato);
		//int ret=dao.mudarCargo(d);
		//System.out.println("Ret == " + ret);
		
		
		/*
		for (DistribuicaoTecnicoContrato dd : dao.listar(247)) {
			System.out.println("..........Contarto " + dd.getId().getContrato().getDescricao() + " " + dd.getDatacad());
		}
		*/

	}

}
