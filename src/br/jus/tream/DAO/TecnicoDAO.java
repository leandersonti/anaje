package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Tecnico;

public interface TecnicoDAO {
	
	public List<Tecnico> listar() throws Exception;
	
	public List<Tecnico> listarCbx() throws Exception; 
	
	public Tecnico getBean(Integer id) throws Exception;
	
	public Tecnico getBean(String tituloEleitor) throws Exception;
	
	public int adicionar (Tecnico tecnico) throws Exception;
	
	public int atualizar (Tecnico tecnico) throws Exception;
	
	public int remover (Tecnico tecnico) throws Exception;
	
	
} 