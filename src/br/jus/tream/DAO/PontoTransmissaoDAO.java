package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.PontoTransmissao;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public interface PontoTransmissaoDAO {
	
	public List<PontoTransmissao> listar() throws Exception;
		
	public List<PontoTransmissao> listarSemDistribuicaoSecao() throws Exception;
	
	public List<PontoTransmissao> listar(CadZonaEleitoralPK pkze) throws Exception;	 
	
	public PontoTransmissao getBean(PontoTransmissaoPK id) throws Exception;
	
	public PontoTransmissao getBean(Integer id) throws Exception;
	
	public int adicionar (PontoTransmissao us) throws Exception;
	
	public int atualizar (PontoTransmissao us) throws Exception;
	
	public int remover (PontoTransmissao us) throws Exception;
	
	
} 