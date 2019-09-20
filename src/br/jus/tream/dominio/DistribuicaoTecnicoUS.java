package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.jus.tream.dominio.pk.DistribuicaoTecnicoUSPK;

@SuppressWarnings("serial")
@Entity
@Table(name="distribuicao_tecnico_us")
public class DistribuicaoTecnicoUS implements Serializable{

	@EmbeddedId
	private DistribuicaoTecnicoUSPK id = new DistribuicaoTecnicoUSPK();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datacad", nullable = false)
	private Date dataCad;
	
	@ManyToOne
	@JoinColumn(name = "id_tecnico_resp", nullable = false)
	private Tecnico tecnicoResponsavel;
	
	public DistribuicaoTecnicoUS() {}

	public DistribuicaoTecnicoUSPK getId() {
		return id;
	}

	public void setId(DistribuicaoTecnicoUSPK id) {
		this.id = id;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public Tecnico getTecnicoResponsavel() {
		return tecnicoResponsavel;
	}

	public void setTecnicoResponsavel(Tecnico tecnicoResponsavel) {
		this.tecnicoResponsavel = tecnicoResponsavel;
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
		DistribuicaoTecnicoUS other = (DistribuicaoTecnicoUS) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
			
	
}
