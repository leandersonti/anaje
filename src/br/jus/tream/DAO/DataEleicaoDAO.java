package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.DataEleicao;

public interface DataEleicaoDAO {
	
	public List<DataEleicao> listar() throws Exception;
	
	public List<DataEleicao> listarCbx() throws Exception; // para preenchimento de combobox
	
	public DataEleicao getBean(Integer id) throws Exception;
	
	public int inserir (DataEleicao dateEleicao) throws Exception;
	
	public int alterar (DataEleicao dateEleicao) throws Exception;
	
	public int remover (DataEleicao dateEleicao) throws Exception;
	
	public int ativar (int id) throws Exception;
	
} 