package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.jus.tream.dominio.pk.DistribuicaoEquipamentoPK;

@SuppressWarnings("serial")
@Entity
@Table(name="distribuicao_equipamento")
public class DistribuicaoEquipamento implements Serializable{

	@EmbeddedId
	private DistribuicaoEquipamentoPK id = new DistribuicaoEquipamentoPK();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_devolucao", nullable = true)
	private Date dataRecebimento;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_resp", nullable = false)
	private Tecnico tecnico;	
	
	public DistribuicaoEquipamento()
	{}
		
	public DistribuicaoEquipamento(DistribuicaoEquipamentoPK id, Date dataRecebimento, Tecnico tecnico) {
		super();
		this.id = id;
		this.dataRecebimento = dataRecebimento;
		this.tecnico = tecnico;
	}

	public DistribuicaoEquipamentoPK getId() {
		return id;
	}

	public void setId(DistribuicaoEquipamentoPK id) {
		this.id = id;
	}
		
	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
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
		DistribuicaoEquipamento other = (DistribuicaoEquipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
