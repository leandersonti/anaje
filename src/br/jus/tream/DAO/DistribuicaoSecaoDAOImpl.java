package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DistribuicaoSecao;
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
					.createQuery("SELECT s FROM DistribuicaoSecao s ORDER BY s.secao",
							DistribuicaoSecao.class);
			// query.setParameter(1, idUnidadeServico);
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
	public List<DistribuicaoSecao> listarParaDistribuir() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DistribuicaoSecao> listar(Integer zona, Integer codmunic) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DistribuicaoSecao getBean(DistribuicaoSecaoPK id) throws Exception {
		DistribuicaoSecao ds = new DistribuicaoSecao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoSecao> query = em.createQuery("SELECT s FROM DistribuicaoSecao s "
											+ "WHERE s.id.idEleicao=?1 AND s.id.idUnidadeServico=?2 AND s.id.codOjeto=?3", 
							DistribuicaoSecao.class);
			//query.setParameter(1, id.getIdEleicao());
			//query.setParameter(2, id.getIdUnidadeServico());
			query.setParameter(3, id.getCodOjeto());
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
	public int inserir(DistribuicaoSecao ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int alterar(DistribuicaoSecao ds) throws Exception {
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
		
		//dspk.setIdUnidadeServico(2432019);
		//dspk.setIdEleicao(1);
		//dspk.setCodOjeto("Eec517041012551913");
		
		//ds.setId(dspk);
		//ds.setZona(60);
		//ds.setSecao(30);
		//ds.setCodmunic(2895);
		
		for(DistribuicaoSecao d : dao.listar(2432019)) {
			System.out.println("Zona " + d.getZona() + " " + d.getSecao() + " " + d.getCodmunic());
		}
		
		//int ret = dao.inserir(ds);
		
		// System.out.println("ret == " + ret);
		
		System.out.println("Done!!");
	}

}
