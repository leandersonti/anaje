package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Tecnico;

public interface TecnicoDAO {
	
	public List<Tecnico> listar() throws Exception;
	
	public List<Tecnico> listarCbx() throws Exception; 
	
	public Tecnico getBean(String tituloEleitor) throws Exception;
	
	public int inserir (Tecnico tecnico) throws Exception;
	
	public int alterar (Tecnico tecnico) throws Exception;
	
	public int remover (Tecnico tecnico) throws Exception;
	
	
} 