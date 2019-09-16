package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.DistribuicaoSecao;

public interface DistribuicaoSecaoDAO {
	
	public List<DistribuicaoSecao> listar(Integer idUnidadeServico) throws Exception;
		
	public List<CADSecao> listarParaDistribuir(Integer zona, Integer codmunic, Integer numlocal) throws Exception;
	
	public List<DistribuicaoSecao> listar(Integer zona, Integer codmunic) throws Exception;	 

	public DistribuicaoSecao getBean(String codibjeto) throws Exception;
	
	public int adicionar (DistribuicaoSecao ds) throws Exception;
	
	public int atualizar (DistribuicaoSecao ds) throws Exception;
	
	public int remover (DistribuicaoSecao ds) throws Exception;
	
	
} 