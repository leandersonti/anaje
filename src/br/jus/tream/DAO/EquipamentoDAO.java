package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Equipamento;

public interface EquipamentoDAO {

	public List<Equipamento> listar() throws Exception;
	
	public List<Equipamento> listarParaDistribuir(Integer tipo) throws Exception;

	public Equipamento getBean(Integer id) throws Exception;

	public int adicionar(Equipamento equipamento) throws Exception;

	public int atualizar(Equipamento equipamento) throws Exception;

	public int remover(Equipamento equipamento) throws Exception;
}