package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("serial")
@Entity
@Table(name="homologador")
public class Homologador implements Serializable{

	@Id	
	@Column(nullable=false)
	private String homologador;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cad", nullable=false)
	private Date dataCad;
		
	private Integer ativo;
		
	private Integer zona;
	
	public Homologador() {
		super();
	}

	public Homologador(String homologador, Date dataCad, Integer ativo, Integer zona) {
		super();
		this.homologador = homologador;
		this.dataCad = dataCad;
		this.ativo = ativo;
		this.zona = zona;
	}

	public String getHomologador() {
		return homologador;
	}

	public void setHomologador(String homologador) {
		this.homologador = homologador;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona = zona;
	}
	
	
	
}
