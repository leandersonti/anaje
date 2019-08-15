package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.jus.tream.dominio.pk.DistribuicaoSecaoPK;

@SuppressWarnings("serial")
@Entity
@Table(name="distribuicao_secao")
public class DistribuicaoSecao implements Serializable{

	@EmbeddedId
	private DistribuicaoSecaoPK id = new DistribuicaoSecaoPK();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_recebimento", nullable = true)
	private Date dataRecebimento;
	
	private Integer zona;
	
	private Integer secao;
	
	private Integer codmunic;

	public DistribuicaoSecao() {}
		
	public DistribuicaoSecao(DistribuicaoSecaoPK id, Date dataRecebimento, Integer zona, Integer secao,
			Integer codmunic) {
		super();
		this.id = id;
		this.dataRecebimento = dataRecebimento;
		this.zona = zona;
		this.secao = secao;
		this.codmunic = codmunic;
	}

	public DistribuicaoSecaoPK getId() {
		return id;
	}

	public void setId(DistribuicaoSecaoPK id) {
		this.id = id;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona = zona;
	}

	public Integer getSecao() {
		return secao;
	}

	public void setSecao(Integer secao) {
		this.secao = secao;
	}

	public Integer getCodmunic() {
		return codmunic;
	}

	public void setCodmunic(Integer codmunic) {
		this.codmunic = codmunic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		DistribuicaoSecao other = (DistribuicaoSecao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
			
	
}
