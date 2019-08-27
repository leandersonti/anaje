package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Encerramento;

public interface EncerramentoDAO {

	public List<Encerramento> listar(int zona,int codmunic) throws Exception;

	public Encerramento getBean(int id) throws Exception;

	public int inserir(Encerramento encerramento) throws Exception;

	public int alterar(Encerramento encerramento) throws Exception;

	public int remover(Encerramento encerramento) throws Exception;

}