package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.DistribuicaoEquipamento;
import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.UnidadeServico;
import br.jus.tream.dominio.pk.DistribuicaoEquipamentoPK;
import br.jus.tream.dominio.pk.IDEleicaoPK;

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
	public DistribuicaoEquipamento getBean(DistribuicaoEquipamento pk) throws Exception {
		DistribuicaoEquipamento ds = new DistribuicaoEquipamento();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<DistribuicaoEquipamento> query = em.createQuery("WHERE s.id.dataEleicao.id=?1 AND s.id.equipamento.id=?2 ", 
					DistribuicaoEquipamento.class);
			query.setParameter(1, pk.getId().getDataEleicao().getId());
			query.setParameter(2, pk.getId().getEquipamento().getId());
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
	public int inserir(DistribuicaoEquipamento ds) throws Exception {
		int ret = 0;
		try {
			ret = dao.adicionar(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int alterar(DistribuicaoEquipamento ds) throws Exception {
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
	
		Equipamento eq = new Equipamento();
		DataEleicao dt = new DataEleicao();
		Tecnico tec= new Tecnico();
		IDEleicaoPK eleipk = new IDEleicaoPK();

		DistribuicaoEquipamentoPK dspk = new DistribuicaoEquipamentoPK();
		DistribuicaoEquipamento ds = new DistribuicaoEquipamento();
		
		dt = DataEleicaoDAOImpl.getInstance().getBean(2);	
		eq = EquipamentoDAOImpl.getInstance().getBean(169);
		tec= TecnicoDAOImpl.getInstance().getBean(165);
		eleipk.setDataEleicao(dt);
		eleipk.setId(12019);
		UnidadeServico us =	new UnidadeServico();
		us = UnidadeServicoDAOImpl.getInstance().getBean(eleipk);
		
		dspk.setDataEleicao(eleipk.getDataEleicao());
		dspk.setEquipamento(eq);
		ds.setId(dspk);
		ds.setTecnico(tec);
		ds.setUnidade(us);

		
		
		int ret = DistribuicaoEquipamentoDAOImpl.getInstance().inserir(ds);
		System.out.println("Ret === " + ret);
		
		
	   
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
