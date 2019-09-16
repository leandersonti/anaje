package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.UnidadeServico;

@SuppressWarnings("serial")
public class DistribuicaoSecaoPK implements Serializable{
		
	@ManyToOne
	@JoinColumns({
	  @JoinColumn(name = "id_eleicao", nullable = false),
	  @JoinColumn(name = "idus", nullable = false)
	})
	private UnidadeServico unidadeServico;
	
	@Column(name="cod_objeto_secao", nullable = false)
	private String codOjeto;

	public UnidadeServico getUnidadeServico() {
		return unidadeServico;
	}

	public void setUnidadeServico(UnidadeServico unidadeServico) {
		this.unidadeServico = unidadeServico;
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
		DistribuicaoSecaoPK other = (DistribuicaoSecaoPK) obj;
		if (codOjeto == null) {
			if (other.codOjeto != null)
				return false;
		} else if (!codOjeto.equals(other.codOjeto))
			return false;
		if (unidadeServico == null) {
			if (other.unidadeServico != null)
				return false;
		} else if (!unidadeServico.equals(other.unidadeServico))
			return false;
		return true;
	}
			
}
