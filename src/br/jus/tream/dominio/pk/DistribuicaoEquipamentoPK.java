package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.Equipamento;
import br.jus.tream.dominio.PontoTransmissao;

@SuppressWarnings("serial")
public class DistribuicaoEquipamentoPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="id_equipamento")	
	private Equipamento equipamento;
	
	@ManyToOne
	@JoinColumns({
	  @JoinColumn(name = "id_eleicao", nullable = false),
	  @JoinColumn(name = "idus", nullable = false)
	})
	private PontoTransmissao pontoTransmissao;
	
	/*
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "idus", referencedColumnName = "idus", updatable = false, insertable = false),
	        @JoinColumn(name = "id_eleicao", referencedColumnName = "id_eleicao", updatable = false, insertable = false)})
	private UnidadeServico unidadeServico;
	*/
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public PontoTransmissao getPontoTransmissao() {
		return pontoTransmissao;
	}

	public void setPontoTransmissao(PontoTransmissao pontoTransmissao) {
		this.pontoTransmissao = pontoTransmissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equipamento == null) ? 0 : equipamento.hashCode());
		result = prime * result + ((pontoTransmissao == null) ? 0 : pontoTransmissao.hashCode());
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
		if (pontoTransmissao == null) {
			if (other.pontoTransmissao != null)
				return false;
		} else if (!pontoTransmissao.equals(other.pontoTransmissao))
			return false;
		return true;
	}

			
}
