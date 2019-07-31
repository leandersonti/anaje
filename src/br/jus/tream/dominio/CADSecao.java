package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="cad_secoes")
public class CADSecao implements Serializable{

	@Id
	@Column(name="cod_objeto_secao")
	private String id;
	
	private Integer zona;
	
	@Column(name="cod_localidade_tse")
	private Integer codmunic;
	
	@Column(name="num_secao")
	private Integer secao;	
	
	@Column(name="num_local")
	private Integer numLocal;
	
	public CADSecao() {
		super();
	}
	public CADSecao(String id, Integer secao) {
		super();
		this.id = id;
		this.secao = secao;
	}

	public CADSecao(String id, Integer zona, Integer secao, Integer codmunic, Integer numLocal) {
		super();
		this.id = id;
		this.zona = zona;
		this.secao = secao;
		this.codmunic = codmunic;
		this.numLocal = numLocal;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getZona() {
		return zona;
	}
	public void setZona(Integer zona) {
		this.zona = zona;
	}
	public Integer getCodmunic() {
		return codmunic;
	}
	public void setCodmunic(Integer codmunic) {
		this.codmunic = codmunic;
	}
	public Integer getNumLocal() {
		return numLocal;
	}
	public void setNumLocal(Integer numLocal) {
		this.numLocal = numLocal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CADSecao other = (CADSecao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
