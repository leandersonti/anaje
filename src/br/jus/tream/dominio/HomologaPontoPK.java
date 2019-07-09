package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class HomologaPontoPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	   @JoinColumn(name="id_unidade_servico")
	   private UnidadeServico unidadeServico;
	   
	   @ManyToOne
	   @JoinColumn(name="id_eleicao")
	   private DataEleicao dataEleicao;

	public UnidadeServico getUnidadeServico() {
		return unidadeServico;
	}

	public void setUnidadeServico(UnidadeServico unidadeServico) {
		this.unidadeServico = unidadeServico;
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
		HomologaPontoPK other = (HomologaPontoPK) obj;
		if (dataEleicao == null) {
			if (other.dataEleicao != null)
				return false;
		} else if (!dataEleicao.equals(other.dataEleicao))
			return false;
		if (unidadeServico == null) {
			if (other.unidadeServico != null)
				return false;
		} else if (!unidadeServico.equals(other.unidadeServico))
			return false;
		return true;
	}
	
	
	
}
