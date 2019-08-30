package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.EquipamentoTipo;

public interface EquipamentoTipoDAO {

	public List<EquipamentoTipo> listar() throws Exception;

	public List<EquipamentoTipo> listarCbx() throws Exception; // para preenchimento de combobox

	public EquipamentoTipo getBean(Integer id) throws Exception;

	public int adicionar(EquipamentoTipo equipamentotipo) throws Exception;

	public int atualizar(EquipamentoTipo equipamentotipo) throws Exception;

	public int remover(EquipamentoTipo equipamentotipo) throws Exception;
}