package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.CADLocalvotacao;
import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.CADZonaEleitoral;

public interface CadEloDAO {
	
	public List<CADLocalvotacao> listarLocalVotacao(Integer zona, Integer codmunic) throws Exception;
	public List<CADLocalvotacao> listarLocalVotacaoParaCadastrar(Integer zona, Integer codmunic) throws Exception;
	public CADLocalvotacao getBeanLocalVotacao(String codObjeto) throws Exception;
	public CADLocalvotacao getBeanLocalVotacao(Integer zona, Integer codmunic, Integer numLocal) throws Exception;
	
	public List<CADSecao> listarSecao(Integer zona, Integer codmunic) throws Exception; 
	public List<CADSecao> listarSecao(Integer zona, Integer codmunic, Integer numLocal) throws Exception;
	
	public List<CADZonaEleitoral> listarZonaEleitoral() throws Exception;
	public List<CADZonaEleitoral> listarZonaEleitoral(Integer zona) throws Exception;
	
	public CADZonaEleitoral getZonaEleitoral(String id) throws Exception;
	
	
} 