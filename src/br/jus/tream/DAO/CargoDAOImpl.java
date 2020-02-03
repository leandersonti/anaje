package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Cargo;

public class CargoDAOImpl implements CargoDAO {

	private DAO<Cargo> dao = new DAO<Cargo>(Cargo.class);

	static CargoDAO db;

	public static CargoDAO getInstance() {
		if (db == null) {
			db = new CargoDAOImpl();
		}
		return db;
	}

	@Override
	public List<Cargo> listar() throws Exception {
		List<Cargo> lista = null;
		try {
			lista = dao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Cargo getBean(Integer id) throws Exception {
		Cargo obj = new Cargo();
		try {
			obj = dao.getBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<Cargo> listarCbx() throws Exception {
		List<Cargo> lista = new ArrayList<Cargo>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Cargo> query = em.createQuery("SELECT d FROM Cargo d ORDER BY d.descricao", Cargo.class);
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
	public int adicionar(Cargo cargo) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(cargo);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(Cargo cargo) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(cargo);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(Cargo cargo) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(cargo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {

		// teste para inserir Cargo [OK]
		CargoDAO dao = CargoDAOImpl.getInstance();
		
		/*
		 * cargo.setDescricao("teste add cargo 2"); int ret = dao.inserir(cargo);
		 * System.out.println(ret);
		 */
		
		//teste para alterar cargo[OK]
		/*
		 * cargo.setId(82); cargo.setDescricao("alterado com sucess"); int ret =
		 * dao.alterar(cargo); System.out.println(ret);
		 */

		// teste para remover cargo [OK]
		/*
		 * cargo.setId(83); int ret = dao.remover(cargo); System.out.println(ret);
		 */

		// teste para listar cargo [OK]
		
		  for(Cargo c : dao.listarCbx()) { 
			  System.out.println("codigo: "+c.getId() +  " descrição: "+c.getDescricao()); 
		  }
		 

		// teste para getBean [OK]
		/*
		 * cargo = dao.getBean(1);
		 * System.out.println("descriçaõ: "+cargo.getDescricao());
		 */
		  System.out.println("Done!!!");
	}
}
