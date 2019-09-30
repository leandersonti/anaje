package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Eleicao;

public interface EleicaoDAO {
	
	public List<Eleicao> listar() throws Exception;
	
	public List<Eleicao> listarCbx() throws Exception; // para preenchimento de combobox
	
	public Eleicao getBeanAtiva( ) throws Exception;
	
	public Eleicao getBean(Integer id) throws Exception;
	
	public int adicionar (Eleicao eleicao) throws Exception;
	
	public int atualizar (Eleicao eleicao) throws Exception;
	
	public int remover (Eleicao eleicao) throws Exception;
	
	public int ativar (int id) throws Exception;
	
} 