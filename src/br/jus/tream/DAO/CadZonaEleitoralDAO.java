package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.CADZonaEleitoral;

public interface CadZonaEleitoralDAO {
	
	public List<CADZonaEleitoral> listar() throws Exception;
	
	public List<CADZonaEleitoral> listarCbx() throws Exception; 	
	
} 