package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Encerramento;
import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.VWEncerramento;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public interface EncerramentoDAO {

	//listar todos os registros
	public List<VWEncerramento> listar() throws Exception;
		
	//listar todos os registros pelo estado do recebimento (is null || is not null)
	public List<VWEncerramento> listar(String estadoRecebimento) throws Exception;
	
	//listar por multiplicador (tec. responsável)
	public List<VWEncerramento> listar(Tecnico tecnicoResponsavel, String estadoRecebimento) throws Exception;
	
	//listar por multiplicador (tec. responsável) todos os estados
	public List<VWEncerramento> listar(Tecnico tecnicoResponsavel) throws Exception;
	
	//listar por zona/munic
	public List<VWEncerramento> listar(CadZonaEleitoralPK pkze, String estadoRecebimento) throws Exception;
	
	//listar por zona
	public List<VWEncerramento> listar(Integer zona, String estadoRecebimento) throws Exception;

	public VWEncerramento getBean(PontoTransmissaoPK id) throws Exception;

	public int adicionar(Encerramento encerramento) throws Exception;
	
	public int reinicializar(CadZonaEleitoralPK pkzona, PontoTransmissaoPK ponto) throws Exception;

	public int atualizar(Encerramento encerramento) throws Exception;

	public int remover(Encerramento encerramento) throws Exception;

}