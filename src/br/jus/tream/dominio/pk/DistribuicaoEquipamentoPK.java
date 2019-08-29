package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.UnidadeServico;

@SuppressWarnings("serial")
public class DistribuicaoEquipamentoPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="id_equipamento")	
	private Equipamento equipamento;

	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "idus", referencedColumnName = "idus", updatable = false, insertable = false),
	        @JoinColumn(name = "id_eleicao", referencedColumnName = "id_eleicao", updatable = false, insertable = false)})
	private UnidadeServico unidadeServico;
	
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public UnidadeServico getUnidadeServico() {
		return unidadeServico;
	}

	public void setUnidadeServico(UnidadeServico unidadeServico) {
		this.unidadeServico = unidadeServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipamento == null) ? 0 : equipamento.hashCode());
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
		DistribuicaoEquipamentoPK other = (DistribuicaoEquipamentoPK) obj;
		if (equipamento == null) {
			if (other.equipamento != null)
				return false;
		} else if (!equipamento.equals(other.equipamento))
			return false;
		if (unidadeServico == null) {
			if (other.unidadeServico != null)
				return false;
		} else if (!unidadeServico.equals(other.unidadeServico))
			return false;
		return true;
	}

			
}
