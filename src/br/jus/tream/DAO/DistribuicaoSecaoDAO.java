package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.DistribuicaoSecao;
import br.jus.tream.dominio.pk.DistribuicaoSecaoPK;

public interface DistribuicaoSecaoDAO {
	
	public List<DistribuicaoSecao> listar(Integer idUnidadeServico) throws Exception;
		
	public List<DistribuicaoSecao> listarParaDistribuir() throws Exception;
	
	public List<DistribuicaoSecao> listar(Integer zona, Integer codmunic) throws Exception;	 

	public DistribuicaoSecao getBean(DistribuicaoSecaoPK id) throws Exception;
	
	public int inserir (DistribuicaoSecao ds) throws Exception;
	
	public int alterar (DistribuicaoSecao ds) throws Exception;
	
	public int remover (DistribuicaoSecao ds) throws Exception;
	
	
} 