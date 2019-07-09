package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "homologador")
public class HomologaPonto implements Serializable {

	@EmbeddedId
	private HomologaPontoPK id = new HomologaPontoPK();

	@Column(length = 12, nullable = false)
	private String homologador;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cad", nullable = false)
	private Date dataCad;		

	private String obs;
	
	public HomologaPonto() {
		super();
	}

	public HomologaPonto(HomologaPontoPK id, String homologador, Date dataCad, String obs) {
		super();
		this.id = id;
		this.homologador = homologador;
		this.dataCad = dataCad;
		this.obs = obs;
	}

	public HomologaPontoPK getId() {
		return id;
	}

	public void setId(HomologaPontoPK id) {
		this.id = id;
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

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
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
		HomologaPonto other = (HomologaPonto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
