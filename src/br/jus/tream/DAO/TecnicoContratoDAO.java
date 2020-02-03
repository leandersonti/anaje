package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.TecnicoContrato;

public interface TecnicoContratoDAO {

	public List<TecnicoContrato> listar(Integer idTecnico) throws Exception;
	
	// RETORNA O CONTRATO ATUAL DO TECNICO
	public TecnicoContrato getBean(Integer idTecnico) throws Exception;

	public int adicionar(TecnicoContrato distec) throws Exception;
	
	public int mudarCargo(TecnicoContrato distec) throws Exception;

	public int atualizar(TecnicoContrato distec) throws Exception;

	public int remover(TecnicoContrato distec) throws Exception;

	
}