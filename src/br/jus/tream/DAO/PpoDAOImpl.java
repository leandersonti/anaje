package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.Ppo;
import br.jus.tream.dominio.PpoTipo;
import br.jus.tream.dominio.Tecnico;

public class PpoDAOImpl implements PpoDAO {
	private DAO<Ppo> dao = new DAO<Ppo>(Ppo.class);
	
	static PpoDAO db;

	public static PpoDAO getInstance() {
		if (db == null) {
			db = new PpoDAOImpl();
		}
		return db;
	}
	
	@Override
	public List<Ppo> listar(String tituloEleitor) throws Exception{
		List<Ppo> lista = new ArrayList<Ppo>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Ppo> query = em.createQuery("SELECT p FROM Ppo p WHERE p.dataEleicao.ativo=1 AND p.tecnico.tituloEleitor=?1 ORDER BY p.dataCad DESC", Ppo.class);
			lista = query.setParameter(1, tituloEleitor).getResultList();					
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}
	
	@Override
	public List<Ppo> listar() throws Exception {
		List<Ppo> lista = new ArrayList<Ppo>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Ppo> query = em.createQuery("SELECT p FROM Ppo p WHERE p.dataEleicao.ativo=1", Ppo.class);
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
	public List<Ppo> listar(Integer idTecnicoResponsavel) throws Exception{
		List<Ppo> lista = new ArrayList<Ppo>();
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		try {
			TypedQuery<Ppo> query = em.createQuery("SELECT p FROM Ppo p WHERE p.dataEleicao.ativo=1 AND p.tecnicoResp.id=?1", Ppo.class);
			lista = query.setParameter(1, idTecnicoResponsavel).getResultList();					
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}

	@Override
	public Ppo getBean(Integer id) throws Exception {
	  Ppo obj = new Ppo();
		try {
			obj = dao.getBean(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int adicionar(Ppo ppo) throws Exception {
		int ret = 0;
		try {
			ppo.setDataCad(new Date(System.currentTimeMillis()));
			ppo.setCodigo(dao.gerarCodigoAlpha());
			ret = dao.adicionar(ppo);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int atualizar(Ppo ppo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remover(Ppo ppo) throws Exception {
		int ret = 0;
		try {
			ret = dao.remover(ppo);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		PpoDAO dao = PpoDAOImpl.getInstance();
		
		Ppo p = new Ppo();
		Tecnico tec = new Tecnico();
		tec.setId(165);		
		Tecnico tecResp = new Tecnico();
		tecResp.setId(288);
		p.setTecnico(tec);
		p.setTecnicoResp(tecResp);
		
		PpoTipo ppotipo = new PpoTipo();
		ppotipo.setId(1);
		p.setPpoTipo(ppotipo);
		DataEleicao dataeleicao = new DataEleicao();
		dataeleicao.setId(1);
		p.setDataEleicao(dataeleicao);
		
		int ret = dao.adicionar(p);
		System.out.println("Ret " + ret);
		
		
		/*
		Ppo p = new Ppo();
		p = dao.getBean(133);
		System.out.println("Tecnico " + p.getTecnico().getNome());
		int ret = dao.remover(p);
		System.out.println("Ret " + ret);
		*/
		
		
		for(Ppo ppo: dao.listar("034098754422")) {
			System.out.println(ppo.getPpoTipo().getDescricao() + " "  + ppo.getTecnico().getNome() + " resp:" + ppo.getTecnicoResp().getNome() );
		}
		
		System.out.println("Done!!");
		
		
		
		
	}

}
