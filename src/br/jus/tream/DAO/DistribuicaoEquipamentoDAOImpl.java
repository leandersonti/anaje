package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.DistribuicaoEquipamento;
import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.UnidadeServico;
import br.jus.tream.dominio.pk.DistribuicaoEquipamentoPK;
import br.jus.tream.dominio.pk.UnidadeServicoPK;

public class DistribuicaoEquipamentoDAOImpl implements DistribuicaoEquipamentoDAO {
	
	private DAO<DistribuicaoEquipamento> dao = new DAO<DistribuicaoEquipamento>(DistribuicaoEquipamento.class);

	static DistribuicaoEquipamentoDAO db;

	public static DistribuicaoEquipamentoDAO getInstance() {
		if (db == null) {
			db = new DistribuicaoEquipamentoDAOImpl();
		}
		return db;
	}


	@Override
	public List<DistribuicaoEquipamento> listar() throws Exception {
		List<DistribuicaoEquipamento> lista = new ArrayList<DistribuicaoEquipamento>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoEquipamento> query = em
					.createQuery("SELECT s FROM DistribuicaoEquipamento s ",
							DistribuicaoEquipamento.class);			
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
	public DistribuicaoEquipamento getBean(Integer id) throws Exception {
		DistribuicaoEquipamento ds = new DistribuicaoEquipamento();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoEquipamento> query = em.createQuery("WHERE s.id.unidadeServico.id.dataEleicao.ativo=1 AND s.id.equipamento.id=?1 ", 
					DistribuicaoEquipamento.class);
			query.setParameter(1, id);
			ds = query.getSingleResult();
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return ds;
	}

	@Override
	public int adicionar(DistribuicaoEquipamento ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(DistribuicaoEquipamento ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.atualizar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int remover(DistribuicaoEquipamento ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		
		DistribuicaoEquipamentoDAO dao = DistribuicaoEquipamentoDAOImpl.getInstance();
		DataEleicao dataEleicao = new DataEleicao();
		dataEleicao.setId(1);
		Equipamento eq = new Equipamento();
		eq.setId(1);
		
		UnidadeServicoPK uspk = new UnidadeServicoPK();
		uspk.setId(1);
		uspk.setDataEleicao(dataEleicao);	
		UnidadeServico us= new UnidadeServico();
		us.setId(uspk);
		
		DistribuicaoEquipamentoPK pke = new DistribuicaoEquipamentoPK();
		pke.setEquipamento(eq);
		pke.setUnidadeServico(us);
		
		DistribuicaoEquipamento de = new DistribuicaoEquipamento();
		de.setId(pke);
		
		Tecnico t = new Tecnico();
		t.setId(1);
		de.setTecnico(t);
		
		
		
		 int ret =dao.adicionar(de);
		 
		 System.out.println("Retorno = " + ret);
		
		
		
	   
		/*
		dspk.setIdUnidadeServico(2432019);
		dspk.setIdEleicao(1);
		dspk.setCodOjeto("Eec517041012551913");
		
		ds.setId(dspk);
		ds.setZona(60);
		ds.setSecao(30);
		ds.setCodmunic(2895);
		*/
		//ds = dao.getBean(dspk);
		
		//for(DistribuicaoSecao d : dao.listar(dspk)) {
		//	System.out.println("Zona " + d.getZona() + " " + d.getSecao() + " " + d.getCodmunic());
		//}
		
		
		
		System.out.println("Done!!");
		
		

	}

}
