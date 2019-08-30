package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.DistribuicaoTecnicoContrato;

public interface DistribuicaoTecContratoDAO {

	public List<DistribuicaoTecnicoContrato> listar(Integer idTecnico) throws Exception;
	
	// RETORNA O CONTRATO ATUAL DO TECNICO
	public DistribuicaoTecnicoContrato getBean(Integer idTecnico) throws Exception;

	public int adicionar(DistribuicaoTecnicoContrato distec) throws Exception;

	public int atualizar(DistribuicaoTecnicoContrato distec) throws Exception;

	public int remover(DistribuicaoTecnicoContrato distec) throws Exception;

	
}