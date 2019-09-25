package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.UnidadeServico;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.UnidadeServicoPK;

public interface UnidadeServicoDAO {
	
	public List<UnidadeServico> listar() throws Exception;
		
	public List<UnidadeServico> listarSemDistribuicaoSecao() throws Exception;
	
	public List<UnidadeServico> listar(CadZonaEleitoralPK pkze) throws Exception;	 
	
	public UnidadeServico getBean(UnidadeServicoPK id) throws Exception;
	
	public UnidadeServico getBean(Integer id) throws Exception;
	
	public int adicionar (UnidadeServico us) throws Exception;
	
	public int atualizar (UnidadeServico us) throws Exception;
	
	public int remover (UnidadeServico us) throws Exception;
	
	
} 