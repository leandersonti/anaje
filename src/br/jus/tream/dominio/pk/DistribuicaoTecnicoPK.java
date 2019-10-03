package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.Tecnico;
import br.jus.tream.dominio.PontoTransmissao;

@SuppressWarnings("serial")
public class DistribuicaoTecnicoPK implements Serializable{
		
	@ManyToOne
	@JoinColumns({
	  @JoinColumn(name = "id_eleicao", nullable = false),
	  @JoinColumn(name = "idus", nullable = false)
	})
	private PontoTransmissao pontoTransmissao;
	
	@ManyToOne
	@JoinColumn(name = "id_tecnico", nullable = false)
	private Tecnico tecnico;

	public PontoTransmissao getPontoTransmissao() {
		return pontoTransmissao;
	}

	public void setPontoTransmissao(PontoTransmissao ponto) {
		this.pontoTransmissao = ponto;
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
		DistribuicaoTecnicoPK other = (DistribuicaoTecnicoPK) obj;
		if (tecnico == null) {
			if (other.tecnico != null)
				return false;
		} else if (!tecnico.equals(other.tecnico))
			return false;
		if (pontoTransmissao == null) {
			if (other.pontoTransmissao != null)
				return false;
		} else if (!pontoTransmissao.equals(other.pontoTransmissao))
			return false;
		return true;
	}
	
}
