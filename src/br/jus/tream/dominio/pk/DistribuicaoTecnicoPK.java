package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.UnidadeServico;

@SuppressWarnings("serial")
public class DistribuicaoTecnicoUSPK implements Serializable{
		
	@ManyToOne
	@JoinColumns({
	  @JoinColumn(name = "id_eleicao", nullable = false),
	  @JoinColumn(name = "idus", nullable = false)
	})
	private UnidadeServico unidadeServico;
	
	@ManyToOne
	@JoinColumn(name = "id_tecnico", nullable = false)
	private Tecnico tecnico;

	public UnidadeServico getUnidadeServico() {
		return unidadeServico;
	}

	public void setUnidadeServico(UnidadeServico unidadeServico) {
		this.unidadeServico = unidadeServico;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tecnico == null) ? 0 : tecnico.hashCode());
		result = prime * result + ((unidadeServico == null) ? 0 : unidadeServico.hashCode());
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
		DistribuicaoTecnicoUSPK other = (DistribuicaoTecnicoUSPK) obj;
		if (tecnico == null) {
			if (other.tecnico != null)
				return false;
		} else if (!tecnico.equals(other.tecnico))
			return false;
		if (unidadeServico == null) {
			if (other.unidadeServico != null)
				return false;
		} else if (!unidadeServico.equals(other.unidadeServico))
			return false;
		return true;
	}
	
}
