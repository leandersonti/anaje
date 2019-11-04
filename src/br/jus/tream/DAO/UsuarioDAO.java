package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Usuario;

public interface UsuarioDAO {
	
	public List<Usuario> listar() throws Exception;
	
	public List<Usuario> listarPorZona(int zona) throws Exception;
		
	public List<Usuario> listarCbx() throws Exception; 
	
	public Usuario getBean(String tituloEleitor) throws Exception;
	
	public int adicionar (Usuario usuario) throws Exception;
	
	public int atualizar (Usuario usuario) throws Exception;
	
	public int remover (Usuario usuario) throws Exception;
	
	
} 