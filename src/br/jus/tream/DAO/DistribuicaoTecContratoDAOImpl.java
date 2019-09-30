package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DistribuicaoTecnicoContrato;

public class DistribuicaoTecContratoDAOImpl implements DistribuicaoTecContratoDAO {
	
	private DAO<DistribuicaoTecnicoContrato> dao = new DAO<DistribuicaoTecnicoContrato>(DistribuicaoTecnicoContrato.class);

	static DistribuicaoTecContratoDAO db;
	
	public static DistribuicaoTecContratoDAO getInstance() {
		if (db == null) {
			db = new DistribuicaoTecContratoDAOImpl();
		}
		return db;
	}

	@Override
	public List<DistribuicaoTecnicoContrato> listar(Integer idTecnico) throws Exception {
		List<DistribuicaoTecnicoContrato> lista = new ArrayList<DistribuicaoTecnicoContrato>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoTecnicoContrato> query = em.createQuery("SELECT d FROM DistribuicaoTecnicoContrato d "
					+ "WHERE d.id.tecnico.id=?1 ORDER BY d.datacad DESC",DistribuicaoTecnicoContrato.class);
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
	public DistribuicaoTecnicoContrato getBean(Integer idTecnico) throws Exception {
		DistribuicaoTecnicoContrato dt = new DistribuicaoTecnicoContrato();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoTecnicoContrato> query = em.createQuery("SELECT d FROM DistribuicaoTecnicoContrato d "
					+ "WHERE d.ativo=1 AND d.id.tecnico.id=?1 AND d.id.dataEleicao.id=(SELECT a.id FROM DataEleicao a WHERE a.ativo=1) "
					+ "ORDER BY d.datacad DESC",DistribuicaoTecnicoContrato.class);
			 query.setParameter(1, idTecnico);
			 dt = query.getSingleResult();
		} catch (Exception e) {
			em.close();
			 e.printStackTrace();
		} finally {
			em.close();
		}
		return dt;
	}

	@Override
	public int adicionar(DistribuicaoTecnicoContrato distec) throws Exception {
		int ret = 0;
		try {
			// SER HOUVER CONTRATOS ANTERIORES O ATIVO SERÁ AJUSTADO PARA ZERO
			atualizarAtivo(distec);
			distec.setDatacad(new Date(System.currentTimeMillis()));
			distec.setAtivo(1);
			ret = dao.adicionar(distec);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}
	
	public int atualizarAtivo(DistribuicaoTecnicoContrato distec) throws Exception {
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		int ret = 0;
		try {
			em.getTransaction().begin();
			   Query query = em.createQuery("UPDATE DistribuicaoTecnicoContrato d SET d.ativo=0"
			   		       + " WHERE d.id.tecnico.id=?1 AND d.id.dataEleicao.id=(SELECT a.id FROM DataEleicao a WHERE a.ativo=1)");
			   query.setParameter(1, distec.getTecnico().getId());
			   query.executeUpdate();			  
			   query = em.createQuery("UPDATE DistribuicaoTecnicoContrato d SET d.ativo=1"
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
	}
	

	@Override
	public int atualizar(DistribuicaoTecnicoContrato distec) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(distec);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(DistribuicaoTecnicoContrato distec) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(distec);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		DistribuicaoTecContratoDAO dao = DistribuicaoTecContratoDAOImpl.getInstance();

		
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
		
		
		DistribuicaoTecnicoContrato d = dao.getBean(289);
		System.out.println(".................... Contrato ativo = " + d.getId().getContrato().getDescricao() + " " +d.getDatacad());
		
		/*
		for (DistribuicaoTecnicoContrato dd : dao.listar(247)) {
			System.out.println("..........Contarto " + dd.getId().getContrato().getDescricao() + " " + dd.getDatacad());
		}
		*/

	}

}
