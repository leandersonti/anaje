package br.jus.tream.dominio.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.jus.tream.dominio.Eleicao;

@SuppressWarnings("serial")
public class PontoTransmissaoPK implements Serializable{
	
	@Column(name="idus")
    private Integer id;
	   
	@ManyToOne
	@JoinColumn(name="id_eleicao")
	private Eleicao eleicao;
	
	public Integer getId() {
		return id;
	}

	public Integer getIdEleicao() {
		return eleicao.getId();
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Eleicao getEleicao() {
		return eleicao;
	}

	public void setEleicao(Eleicao eleicao) {
		this.eleicao = eleicao;
	}
	
	public PontoTransmissaoPK() {
	}
	
	public PontoTransmissaoPK(Integer idus, Integer idEleicao ) {
		Eleicao eleicao = new Eleicao();
		eleicao.setId(idEleicao);
		this.eleicao = eleicao;
		this.id = idus;
	}
	
	public PontoTransmissaoPK(Integer id, Eleicao eleicao) {
		super();
		this.id = id;
		this.eleicao = eleicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eleicao == null) ? 0 : eleicao.hashCode());
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
		PontoTransmissaoPK other = (PontoTransmissaoPK) obj;
		if (eleicao == null) {
			if (other.eleicao != null)
				return false;
		} else if (!eleicao.equals(other.eleicao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
