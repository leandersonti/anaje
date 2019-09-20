package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.DistribuicaoEquipamento;
import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

public interface DistribuicaoEquipamentoDAO {

	public List<DistribuicaoEquipamento> listar() throws Exception;
	
	public List<DistribuicaoEquipamento> listar(CadZonaEleitoralPK pkze) throws Exception;
	
	public List<DistribuicaoEquipamento> listar(Integer unidadeServico) throws Exception;

	public int adicionar(DistribuicaoEquipamento equipamento) throws Exception;

	public int atualizar(DistribuicaoEquipamento equipamento) throws Exception;

	public int remover(DistribuicaoEquipamento equipamento) throws Exception;

	
}