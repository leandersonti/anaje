package br.jus.tream.DAO;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DataEleicao;

public class DataEleicaoDAOImpl implements DataEleicaoDAO {
	
	private DAO<DataEleicao> dao = new DAO<DataEleicao>(DataEleicao.class);
	
    static DataEleicaoDAO db;
	
	public static DataEleicaoDAO getInstance(){
		if (db == null){
			db = new DataEleicaoDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<DataEleicao> listar() throws Exception {
		List<DataEleicao> lista = null;
		  try {
			  lista = dao.listarTodos();
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		return lista;	
	}
	
	@Override
	public DataEleicao getBean(Integer id) throws Exception{
		DataEleicao obj = new DataEleicao();
		try {
			obj = dao.getBean(id); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@Override
	public List<DataEleicao> listarCbx() throws Exception{
		List<DataEleicao> lista = new ArrayList<DataEleicao>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<DataEleicao> query = em.createQuery("SELECT NEW DataEleicao(d.id, d.dataEleicao) FROM DataEleicao d", 
		    		   DataEleicao.class);
			  lista = query.getResultList();
		  }
		  catch (Exception e) {
			     em.close();
				// e.printStackTrace();
		  }	finally {
				em.close();
		  }
		return lista;	
	} 
	
	@Override
	public int inserir (DataEleicao dateEleicao) throws Exception{
		int ret = 0;
		try {
			ret = dao.adicionar(dateEleicao);		 
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int alterar (DataEleicao dateEleicao) throws Exception{
		int ret = 0;
		try {
			ret = dao.atualizar(dateEleicao);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int remover (DataEleicao dateEleicao) throws Exception{
		int ret = 0;
		try {
			ret = dao.remover(dateEleicao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int ativar (int id) throws Exception{
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
		  }
		  catch (Exception e) {
			  if (em.isOpen()) {
					em.close();
				}
				// e.printStackTrace();
		  }	finally {
			  if (em.isOpen()) {
					em.close();
				}
		  }
		return ret;
	}

	    
	public static void main(String[] args) throws Exception{
		DataEleicaoDAO dao = DataEleicaoDAOImpl.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		DataEleicao data = new DataEleicao();
		data.setDataEleicao(sdf.parse("28/07/2019"));
		data.setDescricao("Eleição SAWEB T02");
		data.setTurno(2);
		data.setAtivo(0);
		int ret = dao.inserir(data);
		System.out.println("Retorno = " + ret); 
		
		
		/*
		DataEleicao data = new DataEleicao();
		data = dao.getBean(2);
		System.out.println("Data " + data.getDescricao() + " " + sdf.format(data.getDataEleicao()) );
		*/
		
		/*
		DataEleicao data = new DataEleicao();
		data = dao.getBean(2);
		data.setDescricao("ALTERAÇÃO TESTE");
		data.setTitTRE("TRE-AM");
		data.setEmail("a@a");
		int ret = dao.alterar(data);
		System.out.println("Retorno = " + ret);
		*/
		
		/*
		DataEleicao data = new DataEleicao();
		data = dao.getBean(3);
		int ret = dao.remover(data);
		System.out.println("Retorno = " + ret);
		*/
		
		/*
		int ret = dao.ativar(2);
		System.out.println("Retorno = " + ret);
		System.out.println("Done!!");
		*/
		
	}
}
