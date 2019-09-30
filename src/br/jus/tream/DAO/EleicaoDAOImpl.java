package br.jus.tream.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Eleicao;

public class EleicaoDAOImpl implements EleicaoDAO {

	private DAO<Eleicao> dao = new DAO<Eleicao>(Eleicao.class);

	static EleicaoDAO db;

	public static EleicaoDAO getInstance() {
		if (db == null) {
			db = new EleicaoDAOImpl();
		}
		return db;
	}

	@Override
	public List<Eleicao> listar() throws Exception {
		List<Eleicao> lista = null;
		try {
			lista = dao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Eleicao getBean(Integer id) throws Exception {
		Eleicao obj = new Eleicao();
		try {
			obj = dao.getBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Eleicao getBeanAtiva() throws Exception {

		Eleicao dataEleicao = new Eleicao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Eleicao> query = em.createQuery("SELECT d FROM Eleicao d WHERE d.ativo=1",
					Eleicao.class);
			dataEleicao = query.getSingleResult();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return dataEleicao;
	}

	@Override
	public List<Eleicao> listarCbx() throws Exception {
		List<Eleicao> lista = new ArrayList<Eleicao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Eleicao> query = em.createQuery(
					"SELECT NEW Eleicao(d.id, d.dataEleicao, d.descricao) FROM Eleicao d", Eleicao.class);
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
	public int adicionar(Eleicao eleicao) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(eleicao);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(Eleicao eleicao) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(eleicao);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(Eleicao eleicao) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(eleicao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int ativar(int id) throws Exception {
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		int ret = 0;
		try {
			String sql = "UPDATE data_eleicao SET ativo=0 WHERE ativo=1";
			em.getTransaction().begin();
			em.createNativeQuery(sql).executeUpdate();
			sql = "UPDATE data_eleicao SET ativo=1 WHERE id=?1";
			em.createNativeQuery(sql).setParameter(1, id).executeUpdate();
			em.getTransaction().commit();
			ret = 1;
		} catch (Exception e) {
			if (em.isOpen()) {
				em.close();
			}
			// e.printStackTrace();
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		EleicaoDAO dao = EleicaoDAOImpl.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Eleicao data = new Eleicao();
		data.setDataEleicao(sdf.parse("28/07/2019"));
		data.setDescricao("Eleição SAWEB T02");
		data.setTurno(2);
		data.setAtivo(0);
		int ret = dao.adicionar(data);
		System.out.println("Retorno = " + ret);

		/*
		 * DataEleicao data = new DataEleicao(); data = dao.getBean(2);
		 * System.out.println("Data " + data.getDescricao() + " " +
		 * sdf.format(data.getDataEleicao()) );
		 */

		/*
		 * DataEleicao data = new DataEleicao(); data = dao.getBean(2);
		 * data.setDescricao("ALTERAÇÃO TESTE"); data.setTitTRE("TRE-AM");
		 * data.setEmail("a@a"); int ret = dao.alterar(data);
		 * System.out.println("Retorno = " + ret);
		 */

		/*
		 * DataEleicao data = new DataEleicao(); data = dao.getBean(3); int ret =
		 * dao.remover(data); System.out.println("Retorno = " + ret);
		 */

		/*
		 * int ret = dao.ativar(2); System.out.println("Retorno = " + ret);
		 * System.out.println("Done!!");
		 */

	}
}
