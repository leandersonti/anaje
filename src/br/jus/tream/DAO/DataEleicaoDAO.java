package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.DataEleicao;

public interface DataEleicaoDAO {
	
	public List<DataEleicao> listar() throws Exception;
	
	public List<DataEleicao> listarCbx() throws Exception; // para preenchimento de combobox
	
	public DataEleicao getBeanAtiva( ) throws Exception;
	
	public DataEleicao getBean(Integer id) throws Exception;
	
	public int adicionar (DataEleicao dateEleicao) throws Exception;
	
	public int atualizar (DataEleicao dateEleicao) throws Exception;
	
	public int remover (DataEleicao dateEleicao) throws Exception;
	
	public int ativar (int id) throws Exception;
	
} 