package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.DistribuicaoTecnico;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

public interface DistribuicaoTecnicoDAO {
	
	public List<DistribuicaoTecnico> listar(CadZonaEleitoralPK codzonaMunic, Integer contrato) throws Exception;
		
	public List<DistribuicaoTecnico> listar(CadZonaEleitoralPK codzonaMunic) throws Exception;
	
	public List<DistribuicaoTecnico> listar(Integer idPontoTransmissao) throws Exception;
	
	public List<Tecnico> listarParaDistribuir() throws Exception;
	
	/* public List<Tecnico> listarParaDistribuir(Integer zona) throws Exception; */
	
	public List<Tecnico> listarParaDistribuir(Integer zona, Integer contrato) throws Exception;		
	
	public List<Tecnico> listarParaDistribuir(Integer contrato) throws Exception;
	
	// CONSULTA PELO NUMERO DO TITULO DO TECNICO
	public DistribuicaoTecnico getBean(String tituloEleitor) throws Exception;	
	
	public int adicionar (DistribuicaoTecnico ds) throws Exception;
	
	// public int atualizar (DistribuicaoTecnicoUS ds) throws Exception;
	
	public int remover (DistribuicaoTecnico ds) throws Exception;
	
	
} 