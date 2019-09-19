package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.DistribuicaoTecnicoUS;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

public interface DistribuicaoTecnicoUsDAO {
	
	public List<DistribuicaoTecnicoUS> listar(CadZonaEleitoralPK codzonaMunic, Integer contrato) throws Exception;
		
	public List<DistribuicaoTecnicoUS> listar(CadZonaEleitoralPK codzonaMunic) throws Exception;
	
	public List<Tecnico> listarParaDistribuir() throws Exception;
	
	public List<Tecnico> listarParaDistribuir(Integer zona) throws Exception;
	
	// CONSULTA PELO NUMERO DO TITULO DO TECNICO
	public DistribuicaoTecnicoUS getBean(String tituloEleitor) throws Exception;
	
	public int adicionar (DistribuicaoTecnicoUS ds) throws Exception;
	
	// public int atualizar (DistribuicaoTecnicoUS ds) throws Exception;
	
	public int remover (DistribuicaoTecnicoUS ds) throws Exception;
	
	
} 