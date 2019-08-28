package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.UnidadeServico;
import br.jus.tream.dominio.pk.UnidadeServicoPK;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<UnidadeServico> listarSemDistribuicaoSecao() throws Exception {
		List<UnidadeServico> lista = new ArrayList<UnidadeServico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	 
		   String sql = "SELECT a.idus, a.id_eleicao, a.id_tipo, a.zona, a.local, a.secao,\r\n" + 
		   		"       a.descricao, a.endereco, a.id_municipio, a.sexo, a.sala,\r\n" + 
		   		"       a.contato, a.cargo_contato, a.telefone, a.latitude, a.longitude,\r\n" + 
		   		"       a.status, a.oficial, a.jecon, a.cod_objeto\r\n" + 
		   		"  FROM unidade_servico a \r\n" + 
		   		"  WHERE a.id_eleicao = (SELECT id FROM data_eleicao WHERE ativo=1)\r\n" + 
		   		"  AND (a.idus, a.id_eleicao) NOT IN (\r\n" + 
		   		"     SELECT distinct ds.idus, ds.id_eleicao\r\n" + 
		   		"        FROM distribuicao_secao ds\r\n" + 
		   		"        WHERE ds.id_eleicao in (SELECT id FROM data_eleicao WHERE ativo=1)) ORDER BY a.zona, a.local";
		      Query query = em.createNativeQuery(sql, UnidadeServico.class);
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
	public List<UnidadeServico> listar(Integer zona, Integer codmunic) throws Exception {
		List<UnidadeServico> lista = new ArrayList<UnidadeServico>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<UnidadeServico> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     						+ "WHERE u.id.dataEleicao.ativo=1 AND u.zona=?1 AND u.codmunic=?2 ORDER BY u.local", 
		    		 UnidadeServico.class);
		      query.setParameter(1, zona);
		      lista = query.setParameter(2, codmunic).getResultList();
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
	public UnidadeServico getBean(UnidadeServicoPK id) throws Exception {
		UnidadeServico uservico = new UnidadeServico();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<UnidadeServico> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     												  + "WHERE u.id.dataEleicao.id=?1 AND u.id.id=?2", 
		    		 UnidadeServico.class);
		     query.setParameter(1, id.getDataEleicao().getId());
		     uservico =  query.setParameter(2, id.getId()).getSingleResult();
		  }
		  catch (Exception e) {
			     em.close();
				 e.printStackTrace();
		  }	finally {
				em.close();
		  }
		return uservico;
	}

	@Override
	public int inserir(UnidadeServico us) throws Exception {
		int ret = 0;
		try {
			UnidadeServicoPK pk = us.getId();
			pk.setId(dao.gerarId());
			us.setId(pk);
			ret = dao.adicionar(us);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int alterar(UnidadeServico us) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(us);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(UnidadeServico us) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(us);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}
	

	public static void main(String[] args) throws Exception{
		UnidadeServicoDAO dao = UnidadeServicoDAOImpl.getInstance();
		
		for(UnidadeServico u : dao.listar()) {
			System.out.println("Zona = " + u.getZona() + " " + u.getLocal() + " " + u.getDescricao());
		}
		
		
		/*
		UnidadeServico u2 = new UnidadeServico();
		DataEleicao dt = new DataEleicao();
		dt.setId(1);
		IDEleicaoPK pk = new IDEleicaoPK();
		pk.setDataEleicao(dt);
		pk.setId(2262019);
		u2.setId(pk);
		*/
		
		/*
		UnidadeServicoTipo tipo = new UnidadeServicoTipo();
		tipo.setId(1);
		u2.setTipo(tipo);
		u2.setDescricao("alteração dos dados".toUpperCase());
		u2.setCodmunic(2550);
		u2.setZona(40);
		u2.setLocal(1900);
		u2.setOficial(0);
		u2.setSexo("N");
		*/
		
		// int ret = dao.remover(u2);
		
		//System.out.println("RET ==  " + ret);
		
		System.out.println("Done!!");

	}

}
