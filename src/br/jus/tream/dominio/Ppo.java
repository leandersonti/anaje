package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="ppo")
public class Ppo implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_eleicao", nullable=false)
	private DataEleicao dataEleicao;
	
	@ManyToOne
	@JoinColumn(name = "id_tecnico", nullable=false)
	private Tecnico tecnico;
	
	@ManyToOne
	@JoinColumn(name = "id_ppo_tipo", nullable=false)
	private PpoTipo ppoTipo;
	
	@Column(name="datacad")
	private Date dataCad;
	
	@Column(length=10)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "id_tecnico_resp", nullable=false)
	private Tecnico tecnicoResp;
	
	public Ppo() {
	}

	public Ppo(Integer id, DataEleicao dataEleicao, Tecnico tecnico, PpoTipo ppoTipo, Date dataCad, String codigo,
			Tecnico tecnicoResp) {
		super();
		this.id = id;
		this.dataEleicao = dataEleicao;
		this.tecnico = tecnico;
		this.ppoTipo = ppoTipo;
		this.dataCad = dataCad;
		this.codigo = codigo;
		this.tecnicoResp = tecnicoResp;
	}

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

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public PpoTipo getPpoTipo() {
		return ppoTipo;
	}

	public void setPpoTipo(PpoTipo ppoTipo) {
		this.ppoTipo = ppoTipo;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Tecnico getTecnicoResp() {
		return tecnicoResp;
	}

	public void setTecnicoResp(Tecnico tecnicoResp) {
		this.tecnicoResp = tecnicoResp;
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
		Ppo other = (Ppo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
