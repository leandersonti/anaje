package br.jus.tream.DAO;

import java.util.List;

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
	public List<Encerramento> listar() throws Exception {
		List<Encerramento> lista = null;
		try {
			lista = dao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public int inserir(Encerramento encerramento) throws Exception {
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
	public int alterar(Encerramento encerramento) throws Exception {
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
		

	}
}
