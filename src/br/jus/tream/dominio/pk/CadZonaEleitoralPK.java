package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class CadZonaEleitoralPK implements Serializable {

	private Integer zona;

	@Column(name = "cod_localidade_tse")
	private Integer codmunic;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codmunic == null) ? 0 : codmunic.hashCode());
		result = prime * result + ((zona == null) ? 0 : zona.hashCode());
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
		CadZonaEleitoralPK other = (CadZonaEleitoralPK) obj;
		if (codmunic == null) {
			if (other.codmunic != null)
				return false;
		} else if (!codmunic.equals(other.codmunic))
			return false;
		if (zona == null) {
			if (other.zona != null)
				return false;
		} else if (!zona.equals(other.zona))
			return false;
		return true;
	}
	
}
