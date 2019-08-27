package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.DistribuicaoEquipamento;

public interface DistribuicaoEquipamentoDAO {

	public List<DistribuicaoEquipamento> listar() throws Exception;

	//public List<DistribuicaoEquipamento> listarCbx() throws Exception; // para preenchimento de combobox

	public DistribuicaoEquipamento getBean(Integer id) throws Exception;

	public int inserir(DistribuicaoEquipamento equipamento) throws Exception;

	public int alterar(DistribuicaoEquipamento equipamento) throws Exception;

	public int remover(DistribuicaoEquipamento equipamento) throws Exception;

	
}