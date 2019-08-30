package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Ppo;

public interface PpoDAO {

	public List<Ppo> listar(String tituloEleitor) throws Exception;
	
	public List<Ppo> listar() throws Exception; // lista os ppo da eleição ativa
		
	public List<Ppo> listar(Integer idTecnicoResponsavel) throws Exception;

	public Ppo getBean(Integer id) throws Exception;

	public int adicionar(Ppo ppo) throws Exception;

	public int atualizar(Ppo ppo) throws Exception;

	public int remover(Ppo ppo) throws Exception;
}