package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.EquipamentoTipo;

public class EquipamentoDAOImpl implements EquipamentoDAO {

	private DAO<Equipamento> dao = new DAO<Equipamento>(Equipamento.class);

	static EquipamentoDAO db;

	public static EquipamentoDAO getInstance() {
		if (db == null) {
			db = new EquipamentoDAOImpl();
		}
		return db;
	}

	@Override
	public List<Equipamento> listar() throws Exception {
		List<Equipamento> lista = null;
		try {
			lista = dao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Equipamento getBean(Integer id) throws Exception {
		Equipamento obj = new Equipamento();
		try {
			obj = dao.getBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<Equipamento> listarCbx() throws Exception {
		List<Equipamento> lista = new ArrayList<Equipamento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Equipamento> query = em.createQuery("SELECT NEW Equipamento(d.id, d.tipo.id) FROM Equipamento d", Equipamento.class);
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
	public int inserir(Equipamento equipamento) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(equipamento);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int alterar(Equipamento equipamento) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(equipamento);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(Equipamento equipamento) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(equipamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {

		
	
		/*
		 * EquipamentoTipo et = new EquipamentoTipo(); DataEleicao dt = new
		 * DataEleicao();
		 * 
		 * 
		 * Equipamento e = new Equipamento();
		 * 
		 * et = EquipamentoTipoDAOImpl.getInstance().getBean(121); dt =
		 * DataEleicaoDAOImpl.getInstance().getBean(76); e.setTipo(et);
		 * e.setDataEleicao(dt); e.setSerie("3°"); e.setTomb("26.936");
		 * e.setFone("36361312"); e.setParam("10E"); e.setChave("10"); int ret =
		 * EquipamentoDAOImpl.getInstance().inserir(e); System.out.println(ret);
		 */
		/*
		 * cargo.setDescricao("teste add cargo 2"); int ret = dao.inserir(cargo);
		  System.out.println(ret);
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
