package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Encerramento;
import br.jus.tream.dominio.pk.PontoTransmissaoPK;

public interface EncerramentoDAO {

	public List<Encerramento> listar(int zona,int codmunic) throws Exception;

	public Encerramento getBean(PontoTransmissaoPK id) throws Exception;

	public int adicionar(Encerramento encerramento) throws Exception;

	public int atualizar(Encerramento encerramento) throws Exception;

	public int remover(Encerramento encerramento) throws Exception;

}