package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.EquipamentoTipo;

public class EquipamentoTipoDAOImpl implements EquipamentoTipoDAO {

	private DAO<EquipamentoTipo> dao = new DAO<EquipamentoTipo>(EquipamentoTipo.class);

	static EquipamentoTipoDAO db;

	public static EquipamentoTipoDAO getInstance() {
		if (db == null) {
			db = new EquipamentoTipoDAOImpl();
		}
		return db;
	}

	@Override
	public List<EquipamentoTipo> listar() throws Exception {
		List<EquipamentoTipo> lista = null;
		try {
			lista = dao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public EquipamentoTipo getBean(Integer id) throws Exception {
		EquipamentoTipo obj = new EquipamentoTipo();
		try {
			obj = dao.getBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int adicionar(EquipamentoTipo equipamentotipo) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(equipamentotipo);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(EquipamentoTipo equipamentotipo) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(equipamentotipo);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(EquipamentoTipo equipamentotipo) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(equipamentotipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {

		EquipamentoTipoDAO dao = EquipamentoTipoDAOImpl.getInstance();
		EquipamentoTipo e = new EquipamentoTipo();

		e.setDescricao("Telefone");
		int ret = dao.adicionar(e);
		System.out.println("Ret === " + ret);

		/*
		 * cargo.setDescricao("teste add cargo 2"); int ret = dao.inserir(cargo);
		 * System.out.println(ret);
		 */

		// teste para alterar cargo[OK]
		/*
		 * cargo.setId(82); cargo.setDescricao("alterado com sucess"); int ret =
		 * dao.alterar(cargo); System.out.println(ret);
		 */

		// teste para remover cargo [OK]
		/*
		 * cargo.setId(83); int ret = dao.remover(cargo); System.out.println(ret);
		 */

		// teste para listar cargo [OK]
		/*
		 * for(Cargo c : dao.listar()) { System.out.println("codigo: "+c.getId() +
		 * " descrição: "+c.getDescricao()); }
		 */

		// teste para getBean [OK]
		/*
		 * cargo = dao.getBean(1);
		 * System.out.println("descriçaõ: "+cargo.getDescricao());
		 */

	}
}
