package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.jus.tream.dominio.pk.DistribuicaoTecContratoPK;

@SuppressWarnings("serial")
@Entity
@Table(name="distribuicao_tecnico_contrato")
public class DistribuicaoTecnicoContrato implements Serializable{

	@EmbeddedId
	private DistribuicaoTecContratoPK id = new DistribuicaoTecContratoPK();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datacad", nullable = true)
	private Date datacad;
	
	private Integer ativo;	

	public DistribuicaoTecnicoContrato() {}
			
	public DistribuicaoTecnicoContrato(DistribuicaoTecContratoPK id, Date datacad, Integer ativo) {
		super();
		this.id = id;
		this.datacad = datacad;
		this.ativo = ativo;
	}

	public Contrato getContrato() {
		return id.getContrato();
	}

	public Tecnico getTecnico() {
		return id.getTecnico();
	}
	
	public DistribuicaoTecContratoPK getId() {
		return id;
	}

	public void setId(DistribuicaoTecContratoPK id) {
		this.id = id;
	}

	public Date getDatacad() {
		return datacad;
	}

	public void setDatacad(Date datacad) {
		this.datacad = datacad;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
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
		DistribuicaoTecnicoContrato other = (DistribuicaoTecnicoContrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
			
	
}
