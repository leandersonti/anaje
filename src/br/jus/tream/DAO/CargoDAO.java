package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Cargo;

public interface CargoDAO {

	public List<Cargo> listar() throws Exception;

	public List<Cargo> listarCbx() throws Exception; // para preenchimento de combobox

	public Cargo getBean(Integer id) throws Exception;

	public int adicionar(Cargo cargo) throws Exception;

	public int atualizar(Cargo cargo) throws Exception;

	public int remover(Cargo cargo) throws Exception;
}