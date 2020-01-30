package br.jus.tream.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import br.jus.tream.dominio.Ppo;
import br.jus.tream.dominio.VWPpo;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

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
			// convertendo titulo para 12 caracteres
			String tit = StringUtils.leftPad(tituloEleitor, 12, "0");
			TypedQuery<Ppo> query = em.createQuery("SELECT p FROM Ppo p WHERE p.eleicao.ativo=1 AND p.tecnico.tituloEleitor=?1 ORDER BY p.dataCad desc", Ppo.class);
			lista = query.setParameter(1, tit).getResultList();					
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
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
			TypedQuery<Ppo> query = em.createQuery("SELECT p FROM Ppo p WHERE p.eleicao.ativo=1", Ppo.class);
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
			TypedQuery<Ppo> query = em.createQuery("SELECT p FROM Ppo p WHERE p.eleicao.ativo=1 AND p.tecnicoResp.id=?1", Ppo.class);
			lista = query.setParameter(1, idTecnicoResponsavel).getResultList();					
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}
	
	public List<VWPpo> listarView(CadZonaEleitoralPK pkzona, int idTecResponsavel) throws Exception{
		{
			List<VWPpo> lista = new ArrayList<VWPpo>();
			EntityManager em = EntityManagerProvider.getInstance().createManager();
			TypedQuery<VWPpo> query = null;
			try {
				if (pkzona.getZona()==9999 && idTecResponsavel==9999) {
					query = em.createQuery("SELECT p FROM VWPpo p ORDER BY p.zona, p.codmunic, p.nome", VWPpo.class);	
				}else {					
					if (pkzona.getZona()!=9999 && idTecResponsavel==9999 ) {
						query = em.createQuery("SELECT p FROM VWPpo p WHERE p.zona=?1 AND p.codmunic=?2 ORDER BY p.nome", VWPpo.class);
						query.setParameter(1, pkzona.getZona());
						query.setParameter(2, pkzona.getCodmunic());
					}else {
						if (pkzona.getZona()==9999 && idTecResponsavel!=9999 ) {
							query = em.createQuery("SELECT p FROM VWPpo p WHERE p.idTecnicoResp=?1 ORDER BY p.nome", VWPpo.class);
							query.setParameter(1, idTecResponsavel);
						}else {
							query = em.createQuery("SELECT p FROM VWPpo p WHERE p.zona=?1 AND p.codmunic=?2 AND p.idTecnicoResp=?3 ORDER BY p.nome", VWPpo.class);
							query.setParameter(1, pkzona.getZona());
							query.setParameter(2, pkzona.getCodmunic());
							query.setParameter(3, idTecResponsavel);
						}
					}	
				}				
				lista = query.getResultList();					
			} catch (Exception e) {
				em.close();
				// e.printStackTrace();
			} finally {
				em.close();
			}
			return lista;
		}
	}
	
	@Override
	public int reinicializar(CadZonaEleitoralPK pkzona, PontoTransmissaoPK ponto) throws Exception{
		int ret = 0;
		EntityManager em = EntityManagerProvider.getInstance().createManager();
		System.out.println("Zona " + pkzona.getZona() + " codmunic:" + pkzona.getCodmunic() + " idPonto=" + ponto.getId());
		try {
			StoredProcedureQuery query = em
				    .createStoredProcedureQuery("reinicializar_ppo")
				    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
				    .registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT)
				    .setParameter(1, pkzona.getZona())
				    .setParameter(2, pkzona.getCodmunic())
				    .setParameter(3, ponto.getId());
			query.execute();
			ret = (int) query.getOutputParameterValue(4);
			System.out.println("Ret == " + ret);
		} catch (Exception e) {
			em.close();
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return ret;
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
		/*
		int ret = 0;
		// Zona 60 codmunic:2895 idPonto=999999
		PontoTransmissaoPK pkponto = new PontoTransmissaoPK(99999, 12019);
		CadZonaEleitoralPK pkzona = new CadZonaEleitoralPK("60;2895");
		ret = dao.reinicializar(pkzona, pkponto);
		System.out.println("Ret == " + ret);
		*/
		
		//DistribuicaoTecnico tec = DistribuicaoTecnicoDAOImpl.getInstance().getBean("037443852224");
		//System.out.println(tec.getId().getTecnico().getNome());
		
		/*
		Ppo p = new Ppo();
		Tecnico tec = new Tecnico();
		tec.setId(1312018);		
		Tecnico tecResp = new Tecnico();
		tecResp.setId(1);
		p.setTecnico(tec);
		p.setTecnicoResp(tecResp);
		
		PpoTipo ppotipo = new PpoTipo();
		ppotipo.setId(1);
		p.setPpoTipo(ppotipo);
		Eleicao dataeleicao = new Eleicao();
		dataeleicao.setId(12019);
		p.setDataEleicao(dataeleicao);
		
		int ret = dao.adicionar(p);
		System.out.println("Ret " + ret);
		*/
		
		/*
		Ppo p = new Ppo();
		p = dao.getBean(133);
		System.out.println("Tecnico " + p.getTecnico().getNome());
		int ret = dao.remover(p);
		System.out.println("Ret " + ret);
		*/
		
		/*
		for(Ppo ppo: dao.listar("037443852224")) {
			System.out.println(ppo.getPpoTipo().getDescricao() + " "  + ppo.getTecnico().getNome() + " resp:" + ppo.getTecnicoResp().getNome() );
		}
		*/
		
		CadZonaEleitoralPK pkzona = new CadZonaEleitoralPK("9999;2895");
		int idTecnicoResp = 1432018;
		//int idTecnicoResp = 9999;
				
		for(VWPpo p: dao.listarView(pkzona, idTecnicoResp)) {
			System.out.println(p.toString());
		}
		
		System.out.println("Done!!");
		
		
	}

}
