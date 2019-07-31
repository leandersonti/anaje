package br.jus.tream.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.Encerramento;
import br.jus.tream.dominio.UnidadeServico;

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
		EncerramentoDAO dao = EncerramentoDAOImpl.getInstance();
		Encerramento e = new Encerramento();
		UnidadeServico u = new UnidadeServico();
		DataEleicao de = new DataEleicao();
		// Adicionar novo ( )
		Date d = new SimpleDateFormat("dd/MM/yyy").parse("30/07/2019");
		e.setDataCad(d);
		e.setStatus(1);
		e.setCodigo("111");
		de.setId(1);
		e.getId().setDataEleicao(de);
		u.setId(1);		
		e.getId().setUnidadeServico(u);
		int ret = dao.inserir(e);
		System.out.println(ret);

		/*
		 * DataEleicaoDAO dao = EncerramentoDAOImpl.getInstance(); SimpleDateFormat sdf
		 * = new SimpleDateFormat("dd/MM/yyyy");
		 * 
		 * DataEleicao data = new DataEleicao();
		 * data.setDataEleicao(sdf.parse("28/07/2019"));
		 * data.setDescricao("Eleição SAWEB T02"); data.setTurno(2); data.setAtivo(0);
		 * int ret = dao.inserir(data); System.out.println("Retorno = " + ret);
		 */

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
