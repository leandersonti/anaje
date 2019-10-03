package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.PontoTransmissao;

@SuppressWarnings("serial")
public class DistribuicaoSecaoPK implements Serializable{
		
	@ManyToOne
	@JoinColumns({
	  @JoinColumn(name = "id_eleicao", nullable = false),
	  @JoinColumn(name = "idus", nullable = false)
	})
	private PontoTransmissao pontoTransmissao;
	
	@Column(name="cod_objeto_secao", nullable = false)
	private String codOjeto;

	public PontoTransmissao getPontoTransmissao() {
		return pontoTransmissao;
	}

	public void setPontoTransmissaoo(PontoTransmissao ponto) {
		this.pontoTransmissao = ponto;
	}

	public String getCodOjeto() {
		return codOjeto;
	}

	public void setCodOjeto(String codOjeto) {
		this.codOjeto = codOjeto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codOjeto == null) ? 0 : codOjeto.hashCode());
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
		DistribuicaoSecaoPK other = (DistribuicaoSecaoPK) obj;
		if (codOjeto == null) {
			if (other.codOjeto != null)
				return false;
		} else if (!codOjeto.equals(other.codOjeto))
			return false;
		if (pontoTransmissao == null) {
			if (other.pontoTransmissao != null)
				return false;
		} else if (!pontoTransmissao.equals(other.pontoTransmissao))
			return false;
		return true;
	}
			
}
