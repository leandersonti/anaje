package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DistribuicaoEquipamento;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

public class DistribuicaoEquipamentoDAOImpl implements DistribuicaoEquipamentoDAO {
	
	private DAO<DistribuicaoEquipamento> dao = new DAO<DistribuicaoEquipamento>(DistribuicaoEquipamento.class);

	static DistribuicaoEquipamentoDAO db;

	public static DistribuicaoEquipamentoDAO getInstance() {
		if (db == null) {
			db = new DistribuicaoEquipamentoDAOImpl();
		}
		return db;
	}

	@Override
	public List<DistribuicaoEquipamento> listar() throws Exception {
		List<DistribuicaoEquipamento> lista = new ArrayList<DistribuicaoEquipamento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoEquipamento> query = em
					.createQuery("SELECT s FROM DistribuicaoEquipamento s WHERE s.id.pontoTransmissao.id.eleicao.ativo=1",
							DistribuicaoEquipamento.class);			
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
    public List<DistribuicaoEquipamento> listar(CadZonaEleitoralPK pkze) throws Exception{
    	List<DistribuicaoEquipamento> lista = new ArrayList<DistribuicaoEquipamento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoEquipamento> query = em
					.createQuery("SELECT s FROM DistribuicaoEquipamento s WHERE s.id.pontoTransmissao.id.eleicao.ativo=1 "
							+ "AND s.id.pontoTransmissao.zona=?1 AND s.id.pontoTransmissao.codmunic=?2",
							DistribuicaoEquipamento.class);			
			query.setParameter(1, pkze.getZona());
			query.setParameter(2, pkze.getCodmunic());
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
	public List<DistribuicaoEquipamento> listar(Integer unidadeServico) throws Exception{
		List<DistribuicaoEquipamento> lista = new ArrayList<DistribuicaoEquipamento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoEquipamento> query = em
					.createQuery("SELECT s FROM DistribuicaoEquipamento s WHERE s.id.pontoTransmissao.id.eleicao.ativo=1 "
							+ "AND s.id.pontoTransmissao.id.id=?1",
							DistribuicaoEquipamento.class);			
			query.setParameter(1, unidadeServico);
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
	public int adicionar(DistribuicaoEquipamento ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(DistribuicaoEquipamento ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(DistribuicaoEquipamento ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		
		DistribuicaoEquipamentoDAO dao = DistribuicaoEquipamentoDAOImpl.getInstance();
		CadZonaEleitoralPK pkze = new CadZonaEleitoralPK("14;2151");
		for(DistribuicaoEquipamento e : dao.listar(pkze)) {
			System.out.println("PT " + e.getId().getPontoTransmissao().getDescricao() + " == " + e.getId().getEquipamento().getSerie());
		}
		
		System.out.println("Done!!");
		
		

	}

}
