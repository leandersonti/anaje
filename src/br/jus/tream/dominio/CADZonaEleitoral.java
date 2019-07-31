package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="cad_zona_eleitoral")
public class CADZonaEleitoral implements Serializable{

	@Id
	@Column(name="cod_objeto")
	private String id;
	
	private Integer zona;
	
	@Column(name="cod_localidade_tse")
	private Integer codmunic;
	
	private String municipio;
	
	@Column(name="des_endereco")
	private String endereco;
	
	public CADZonaEleitoral(Integer zona, Integer codmunic, String municipio) {
		super();
		this.zona = zona;
		this.codmunic = codmunic;
		this.municipio = municipio;
	}
	
	public CADZonaEleitoral(String id, Integer zona, Integer codmunic, String municipio, String endereco) {
		super();
		this.id = id;
		this.zona = zona;
		this.codmunic = codmunic;
		this.municipio = municipio;
		this.endereco = endereco;
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

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
		CADZonaEleitoral other = (CADZonaEleitoral) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
