package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.PontoTransmissao;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public interface PontoTransmissaoDAO {
	
	public List<PontoTransmissao> listar() throws Exception;
		
	public List<PontoTransmissao> listarSemDistribuicaoSecao() throws Exception;
	
	public List<PontoTransmissao> listarSemDistribuicaoTecnico() throws Exception;	
	
	public List<PontoTransmissao> listar(CadZonaEleitoralPK pkze) throws Exception;	 
	
	public int oficializar(CadZonaEleitoralPK pkze) throws Exception;	
	
	public PontoTransmissao getBean(PontoTransmissaoPK pk) throws Exception;
	
	public PontoTransmissao getBean(Integer id) throws Exception;
	
	public int adicionar (PontoTransmissao pontoTransmissao) throws Exception;
	
	public int atualizar (PontoTransmissao pontoTransmissao) throws Exception;
	
	public int remover (PontoTransmissao pontoTransmissao) throws Exception;
	
	
} 