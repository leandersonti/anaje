package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Ppo;
import br.jus.tream.dominio.VWPpo;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public interface PpoDAO {

	public List<Ppo> listar(String tituloEleitor) throws Exception;
	
	public List<Ppo> listar() throws Exception; // lista os ppo da elei��o ativa
		
	public List<VWPpo> listarView(CadZonaEleitoralPK pkzona, int idTecResponsavel) throws Exception;
		
	public List<Ppo> listar(Integer idTecnicoResponsavel) throws Exception;

	public Ppo getBean(Integer id) throws Exception;
	
	public int reinicializar(CadZonaEleitoralPK pkzona, PontoTransmissaoPK ponto) throws Exception;

	public int adicionar(Ppo ppo) throws Exception;

	public int atualizar(Ppo ppo) throws Exception;

	public int remover(Ppo ppo) throws Exception;
}