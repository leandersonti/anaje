package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.DistribuicaoTecnicoUS;

public interface DistribuicaoTecnicoUsDAO {
	
	public List<DistribuicaoTecnicoUS> listar(Integer idUnidadeServico) throws Exception;
		
	public List<CADSecao> listarParaDistribuir(Integer zona, Integer codmunic, Integer numlocal) throws Exception;
	
	public List<DistribuicaoTecnicoUS> listar(Integer zona, Integer codmunic) throws Exception;	 

	public DistribuicaoTecnicoUS getBean(String codibjeto) throws Exception;
	
	public int adicionar (DistribuicaoTecnicoUS ds) throws Exception;
	
	public int atualizar (DistribuicaoTecnicoUS ds) throws Exception;
	
	public int remover (DistribuicaoTecnicoUS ds) throws Exception;
	
	
} 