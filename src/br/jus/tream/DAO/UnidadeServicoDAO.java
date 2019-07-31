package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.UnidadeServico;

public interface UnidadeServicoDAO {
	
	public List<UnidadeServico> listar() throws Exception;
		
	public List<UnidadeServico> listarSemDistribuicaoSecao() throws Exception;
	
	public List<UnidadeServico> listar(Integer zona, Integer codmunic) throws Exception;	 
	
	public UnidadeServico getBean(Integer id) throws Exception;
	
	public int inserir (UnidadeServico us) throws Exception;
	
	public int alterar (UnidadeServico us) throws Exception;
	
	public int remover (UnidadeServico us) throws Exception;
	
	
} 