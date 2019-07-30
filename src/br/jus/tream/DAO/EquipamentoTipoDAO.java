package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.EquipamentoTipo;

public interface EquipamentoTipoDAO {

	public List<EquipamentoTipo> listar() throws Exception;

	public List<EquipamentoTipo> listarCbx() throws Exception; // para preenchimento de combobox

	public EquipamentoTipo getBean(Integer id) throws Exception;

	public int inserir(EquipamentoTipo equipamentotipo) throws Exception;

	public int alterar(EquipamentoTipo equipamentotipo) throws Exception;

	public int remover(EquipamentoTipo equipamentotipo) throws Exception;
}