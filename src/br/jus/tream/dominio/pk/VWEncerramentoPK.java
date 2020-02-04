package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class VWEncerramentoPK implements Serializable{
	
	@Column(name="idus")
    private Integer idus;
	
	@Column(name="id_eleicao")
    private Integer idEleicao;
	   
	public VWEncerramentoPK() {
	}

	public VWEncerramentoPK(Integer idus, Integer idEleicao) {
		super();
		this.idus = idus;
		this.idEleicao = idEleicao;
	}

	public Integer getIdus() {
		return idus;
	}

	public void setIdus(Integer idus) {
		this.idus = idus;
	}

	public Integer getIdEleicao() {
		return idEleicao;
	}

	public void setIdEleicao(Integer idEleicao) {
		this.idEleicao = idEleicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEleicao == null) ? 0 : idEleicao.hashCode());
		result = prime * result + ((idus == null) ? 0 : idus.hashCode());
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
		VWEncerramentoPK other = (VWEncerramentoPK) obj;
		if (idEleicao == null) {
			if (other.idEleicao != null)
				return false;
		} else if (!idEleicao.equals(other.idEleicao))
			return false;
		if (idus == null) {
			if (other.idus != null)
				return false;
		} else if (!idus.equals(other.idus))
			return false;
		return true;
	}
	
}
