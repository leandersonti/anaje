package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.CADLocalvotacao;
import br.jus.tream.dominio.CADSecao;
import br.jus.tream.dominio.DistribuicaoSecao;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

public interface DistribuicaoSecaoDAO {
	
	public List<DistribuicaoSecao> listar(Integer idPontoTransmissao) throws Exception;
	
	public List<CADLocalvotacao> listarByClassLocalVotacao(Integer idPontoTransmissao) throws Exception;
	
	public List<CADLocalvotacao> listarByClassLocalVotacao(CadZonaEleitoralPK pkze) throws Exception;
		
	public List<CADSecao> listarParaDistribuir(CadZonaEleitoralPK pkze, Integer numlocal) throws Exception;
	
	public List<DistribuicaoSecao> listar(CadZonaEleitoralPK pkze) throws Exception;	 

	public DistribuicaoSecao getBean(String codibjeto) throws Exception;
	
	public int adicionar (DistribuicaoSecao ds) throws Exception;
	
	public int atualizar (DistribuicaoSecao ds) throws Exception;
	
	public int remover (DistribuicaoSecao ds) throws Exception;
	
	
} 