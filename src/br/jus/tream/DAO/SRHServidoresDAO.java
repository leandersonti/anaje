package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.SRHServidores;

public interface SRHServidoresDAO {
	
	public List<SRHServidores> listar() throws Exception;
	
	public List<SRHServidores> listarCbx() throws Exception; 
	
	public List<SRHServidores> ListUnidade(String siglaUnid) throws Exception;
	
	public SRHServidores getBean(String matricula) throws Exception;
	
	
} 