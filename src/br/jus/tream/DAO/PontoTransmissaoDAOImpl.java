package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.PontoTransmissao;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public class PontoTransmissaoDAOImpl implements PontoTransmissaoDAO {

	private DAO<PontoTransmissao> dao = new DAO<PontoTransmissao>(PontoTransmissao.class);

	static PontoTransmissaoDAO db;

	public static PontoTransmissaoDAO getInstance() {
		if (db == null) {
			db = new PontoTransmissaoDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<PontoTransmissao> listar() throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     						+ "WHERE u.id.dataEleicao.ativo=1 ORDER BY u.zona, u.local", 
		    		 PontoTransmissao.class);
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
	public List<PontoTransmissao> listarSemDistribuicaoSecao() throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
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
		      Query query = em.createNativeQuery(sql, PontoTransmissao.class);
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
	public List<PontoTransmissao> listar(CadZonaEleitoralPK pkze) throws Exception {
		List<PontoTransmissao> lista = new ArrayList<PontoTransmissao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     						+ "WHERE u.id.dataEleicao.ativo=1 AND u.zona=?1 AND u.codmunic=?2 ORDER BY u.local", 
		    		 PontoTransmissao.class);
		      query.setParameter(1, pkze.getZona());
		      lista = query.setParameter(2, pkze.getCodmunic()).getResultList();
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
	public PontoTransmissao getBean(PontoTransmissaoPK id) throws Exception {
		PontoTransmissao uservico = new PontoTransmissao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     												  + "WHERE u.id.dataEleicao.id=?1 AND u.id.id=?2", 
		    		 PontoTransmissao.class);
		     query.setParameter(1, id.getEleicao().getId());
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
	public PontoTransmissao getBean(Integer id) throws Exception{
		PontoTransmissao uservico = new PontoTransmissao();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<PontoTransmissao> query = em.createQuery("SELECT u FROM UnidadeServico u "
		     												  + "WHERE id.dataEleicao.ativo=1 AND u.id.id=?1", 
		    		 PontoTransmissao.class);
		     uservico =  query.setParameter(1, id).getSingleResult();
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
	public int adicionar(PontoTransmissao pontoTransmissao) throws Exception {
		int ret = 0;
		try {
			PontoTransmissaoPK pk = pontoTransmissao.getId();
			pk.setId(dao.gerarId());
			pontoTransmissao.setId(pk);
			ret = dao.adicionar(pontoTransmissao);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(PontoTransmissao pontoTransmissao) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(pontoTransmissao);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(PontoTransmissao pontoTransmissao) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(pontoTransmissao);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}
	

	public static void main(String[] args) throws Exception{
		PontoTransmissaoDAO dao = PontoTransmissaoDAOImpl.getInstance();
		
		PontoTransmissao pontoTransmissao = new PontoTransmissao();
		pontoTransmissao = dao.getBean(12019);
		System.out.println("Descricao " + pontoTransmissao.getZona() + " " + pontoTransmissao.getDescricao());
		
		
		System.out.println("Done!!");

	}

}
