package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.UnidadeServico;

public class UnidadeServicoDAOImpl implements UnidadeServicoDAO {

	private DAO<UnidadeServico> dao = new DAO<UnidadeServico>(UnidadeServico.class);

	static UnidadeServicoDAO db;

	public static UnidadeServicoDAO getInstance() {
		if (db == null) {
			db = new UnidadeServicoDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<UnidadeServico> listar() throws Exception {
		List<UnidadeServico> lista = new ArrayList<UnidadeServico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<UnidadeServico> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     						+ "WHERE u.id.dataEleicao.ativo=1 ORDER BY u.zona, u.local", 
		    		 UnidadeServico.class);
		      lista = query.getResultList();
		  }
		  catch (Exception e) {
			     em.close();
				 e.printStackTrace();
		  }	finally {
				em.close();
		  }
		return lista;
	}

	@Override
	public List<UnidadeServico> listarSemDistribuicaoSecao() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UnidadeServico> listar(Integer zona, Integer codmunic) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnidadeServico getBean(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inserir(UnidadeServico us) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int alterar(UnidadeServico us) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remover(UnidadeServico us) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) throws Exception{
		UnidadeServicoDAO dao = UnidadeServicoDAOImpl.getInstance();
		for(UnidadeServico u : dao.listar()) {
			System.out.println(u.getDescricao());
		}

	}

}
