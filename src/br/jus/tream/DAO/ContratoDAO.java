package br.jus.tream.DAO;

import java.util.List;

import br.jus.tream.dominio.Contrato;

public interface ContratoDAO {
	
	public List<Contrato> listar() throws Exception;
	
	public List<Contrato> listar(Integer id_eleicao) throws Exception;
	
	public List<Contrato> listarCbx(Integer id_eleicao) throws Exception; 
	
	public Contrato getBean(Integer id) throws Exception;
	
	public int adicionar (Contrato contrato) throws Exception;
	
	public int atualizar (Contrato contrato) throws Exception;
	
	public int remover (Contrato contrato) throws Exception;
	
	
} 