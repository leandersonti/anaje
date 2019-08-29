package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.Contrato;
import br.jus.tream.dominio.DataEleicao;
import br.jus.tream.dominio.Tecnico;

@SuppressWarnings("serial")
public class DistribuicaoTecContratoPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name = "id_tecnico", nullable = false)
	private Tecnico tecnico;
	
	@ManyToOne
	@JoinColumn(name = "id_contrato", nullable = false)
	private Contrato contrato;

	@ManyToOne
	@JoinColumn(name = "id_eleicao", nullable = false)
	private DataEleicao dataEleicao;
	
	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
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
		result = prime * result + ((contrato == null) ? 0 : contrato.hashCode());
		result = prime * result + ((dataEleicao == null) ? 0 : dataEleicao.hashCode());
		result = prime * result + ((tecnico == null) ? 0 : tecnico.hashCode());
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
		DistribuicaoTecContratoPK other = (DistribuicaoTecContratoPK) obj;
		if (contrato == null) {
			if (other.contrato != null)
				return false;
		} else if (!contrato.equals(other.contrato))
			return false;
		if (dataEleicao == null) {
			if (other.dataEleicao != null)
				return false;
		} else if (!dataEleicao.equals(other.dataEleicao))
			return false;
		if (tecnico == null) {
			if (other.tecnico != null)
				return false;
		} else if (!tecnico.equals(other.tecnico))
			return false;
		return true;
	}

	
	
	
			
}
