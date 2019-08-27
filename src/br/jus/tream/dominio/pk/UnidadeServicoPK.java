package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.DataEleicao;

@SuppressWarnings("serial")
public class UnidadeServicoPK implements Serializable{
	
	@Column(name="idus")
    private Integer id;
	   
	@ManyToOne
	@JoinColumn(name="id_eleicao")
	private DataEleicao dataEleicao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UnidadeServicoPK other = (UnidadeServicoPK) obj;
		if (dataEleicao == null) {
			if (other.dataEleicao != null)
				return false;
		} else if (!dataEleicao.equals(other.dataEleicao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
