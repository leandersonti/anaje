package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.jus.tream.dominio.pk.CadZonaEleitoralPK;

@SuppressWarnings("serial")
@Entity
@Table(name="cad_zona_eleitoral")
public class CADZonaEleitoral implements Serializable{
	
	@EmbeddedId
	private CadZonaEleitoralPK id = new CadZonaEleitoralPK();
	
	private String municipio;
	
	@Column(name="des_endereco")
	private String endereco;
	
	@Column(name="cod_objeto")
	private String codObjeto;
	
	public CADZonaEleitoral() {
		super();	
	}
	
	public CADZonaEleitoral(CadZonaEleitoralPK id, String municipio) {
		super();
		this.id = id;
		this.municipio = municipio;
	}
	
	public CADZonaEleitoral(CadZonaEleitoralPK id, String municipio, String endereco, String cod_objeto) {
		super();
		this.id = id;
		this.municipio = municipio;
		this.endereco = endereco;
		this.codObjeto = cod_objeto;
	}
	
	public CadZonaEleitoralPK getId() {
		return id;
	}

	public void setId(CadZonaEleitoralPK id) {
		this.id = id;
	}

	public Integer getCodmunic() {
		return id.getCodmunic();
	}

	public Integer getZona() {
		return id.getZona();
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
