package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.Column;

import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.Equipamento;

@SuppressWarnings("serial")
public class DistribuicaoEquipamentoPK implements Serializable{
	
	@Column(name="id_equipamento")	
	private Equipamento equipamento;
	
	@Column(name="id_eleicao")
	private DataEleicao dataEleicao;

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public DataEleicao getDataEleicao() {
		return dataEleicao;
	}

	public void setDataEleicao(DataEleicao dataEleicao) {
		this.dataEleicao = dataEleicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataEleicao == null) ? 0 : dataEleicao.hashCode());
		result = prime * result + ((equipamento == null) ? 0 : equipamento.hashCode());
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
		if (dataEleicao == null) {
			if (other.dataEleicao != null)
				return false;
		} else if (!dataEleicao.equals(other.dataEleicao))
			return false;
		if (equipamento == null) {
			if (other.equipamento != null)
				return false;
		} else if (!equipamento.equals(other.equipamento))
			return false;
		return true;
	}

		
}
