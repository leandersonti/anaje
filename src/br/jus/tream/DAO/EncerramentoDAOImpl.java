package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Encerramento;

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
	public Encerramento getBean(int id) throws Exception {
		Encerramento encerramento = new Encerramento();
		try {
			encerramento = dao.getBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encerramento;
	}

	@Override
	public List<Encerramento> listar(int zona,int codmunic) throws Exception {
		List<Encerramento> lista = new ArrayList<Encerramento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Encerramento> query = em.createQuery("SELECT e FROM Encerramento e WHERE e.id.eleicao.ativo=1 "
					+ "AND e.id.id IN (SELECT us.id.id FROM PontoTransmissao us "
					+ " WHERE us.zona=?1 AND us.codmunic=?2 AND us.id.eleicao.ativo=1)",Encerramento.class);
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
		//Encerramento e = new Encerramento();
		EncerramentoDAO dao = EncerramentoDAOImpl.getInstance();
		
		for(Encerramento e:dao.listar(33, 2038)) {
			System.out.println("Status===" + e.getStatus());
		}

	}
}
