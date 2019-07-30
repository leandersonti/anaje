package br.jus.tream.DAO;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.Cargo;
import br.jus.tream.dominio.Contrato;
import br.jus.tream.dominio.DataEleicao;

public class ContratoDAOImpl implements ContratoDAO {
	private DAO<Contrato> dao = new DAO<Contrato>(Contrato.class);
	
    static ContratoDAO db;
	
	public static ContratoDAO getInstance(){
		if (db == null){
			db = new ContratoDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<Contrato> listar() throws Exception {
		List<Contrato> lista = null;
		  try {
			  lista = dao.listarTodos();
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		return lista;	
	}
	
	@Override
	public List<Contrato> listar(Integer id_eleicao) throws Exception{
		List<Contrato> lista = new ArrayList<Contrato>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<Contrato> query = em.createQuery("SELECT c FROM Contrato c WHERE c.dataEleicao.id=?1 ORDER BY c.descricao", 
		    		   Contrato.class);
		      lista = query.setParameter(1, id_eleicao).getResultList();
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
	public Contrato getBean(Integer id) throws Exception{
		Contrato contrato = new Contrato();
		try {
			contrato = dao.getBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contrato;
	}
	
	@Override
	public List<Contrato> listarCbx(Integer id_eleicao) throws Exception{
		List<Contrato> lista = new ArrayList<Contrato>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
	   try {	  
		     TypedQuery<Contrato> query = em.createQuery("SELECT NEW Contrato(c.id, c.descricao) FROM Contrato c WHERE c.dataEleicao.id=?1", 
		    		   Contrato.class);
		      lista = query.setParameter(1, id_eleicao).getResultList();
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
	public int inserir (Contrato contrato) throws Exception{
		int ret = 0;
		try {
			dao.adicionar(contrato);
			ret =1;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int alterar (Contrato contrato) throws Exception{
		int ret = 0;
		try {
			dao.atualizar(contrato);
			ret =1;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public int remover (Contrato contrato) throws Exception{
		int ret = 0;
		try {
			dao.remover(contrato);
			ret =1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	

	    
	public static void main(String[] args) throws Exception{
		ContratoDAO dao = ContratoDAOImpl.getInstance();
		Contrato c = new Contrato();
		
		c.setDataEleicao(DataEleicaoDAOImpl.getInstance().getBeanAtiva());
	
		Cargo cargo = new Cargo();
		cargo.setId(3);
		c.setDescricao("TECNICO URNAS");
		c.setEmpresa("UFAM");
		c.setNumContratoTse("UFAMEA021");
		c.setCargo(cargo);
		c.setSigla("TURNA");		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		//sdf.parse("13/12/2018 12:25")
		c.setDataInicio(new Date(System.currentTimeMillis()));
		c.setDataFim(new Date(System.currentTimeMillis()));
		int ret = dao.inserir(c);
		
		
		//c = dao.getBean(97);
		//System.out.println("Cargo " + c.getCargo().getDescricao());
		//int ret = dao.remover(c);
		//Cargo cargo = new Cargo();
		//cargo.setId(11);
		//c.setCargo(cargo);
		// int ret = dao.alterar(c);
		
		System.out.println("Done!! ");

		
	}
}
