package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Encerramento;
import br.jus.tream.dominio.VWEncerramento;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public interface EncerramentoDAO {

	public List<VWEncerramento> listar() throws Exception;
	
	public List<VWEncerramento> listar(CadZonaEleitoralPK pkze) throws Exception;
	
	public List<VWEncerramento> listarPorZona(Integer zona) throws Exception;
		
	public List<VWEncerramento> listar(int tecnicoResponsavel) throws Exception;

	public VWEncerramento getBean(PontoTransmissaoPK id) throws Exception;

	public int adicionar(Encerramento encerramento) throws Exception;

	public int atualizar(Encerramento encerramento) throws Exception;

	public int remover(Encerramento encerramento) throws Exception;

}