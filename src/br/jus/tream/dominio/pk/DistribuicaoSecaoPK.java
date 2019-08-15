package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class DistribuicaoSecaoPK implements Serializable{
	
	@Column(name="id_eleicao")	
	private Integer idEleicao;
	
	@Column(name="id_unidade_servico")
	private Integer idUnidadeServico;
	
	@Column(name="cod_objeto_secao", nullable = false)
	private String codOjeto;

	public Integer getIdEleicao() {
		return idEleicao;
	}

	public void setIdEleicao(Integer idEleicao) {
		this.idEleicao = idEleicao;
	}

	public Integer getIdUnidadeServico() {
		return idUnidadeServico;
	}

	public void setIdUnidadeServico(Integer idUnidadeServico) {
		this.idUnidadeServico = idUnidadeServico;
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
		result = prime * result + ((idEleicao == null) ? 0 : idEleicao.hashCode());
		result = prime * result + ((idUnidadeServico == null) ? 0 : idUnidadeServico.hashCode());
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
		if (idEleicao == null) {
			if (other.idEleicao != null)
				return false;
		} else if (!idEleicao.equals(other.idEleicao))
			return false;
		if (idUnidadeServico == null) {
			if (other.idUnidadeServico != null)
				return false;
		} else if (!idUnidadeServico.equals(other.idUnidadeServico))
			return false;
		return true;
	}

		
}
