package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Equipamento;

public interface EquipamentoDAO {

	public List<Equipamento> listar() throws Exception;

	public List<Equipamento> listarCbx() throws Exception; // para preenchimento de combobox

	public Equipamento getBean(Integer id) throws Exception;

	public int inserir(Equipamento equipamento) throws Exception;

	public int alterar(Equipamento equipamento) throws Exception;

	public int remover(Equipamento equipamento) throws Exception;
}