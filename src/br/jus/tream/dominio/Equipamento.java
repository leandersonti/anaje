package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name="equipamento")
public class Equipamento implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_tipo", nullable = false)
	private EquipamentoTipo tipo;

	@ManyToOne
	@JoinColumn(name = "id_eleicao", nullable = false)
	private Eleicao eleicao;

	@Column(length=70)
	private String serie;

	@Column(length=40)
	private String tomb;
	
	@Column(length=40)
	private String fone;
	
	@Column(length=70)
	private String param;
	
	@Column(length=70)
	private String chave;
	
	@Transient
	private boolean inserted;
	

	public Equipamento() {
	}

	public Equipamento(Integer id, EquipamentoTipo tipo, Eleicao eleicao, String serie, String tomb,
			String fone, String param, String chave) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.eleicao = eleicao;
		this.serie = serie;
		this.tomb = tomb;
		this.fone = fone;
		this.param = param;
		this.chave = chave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EquipamentoTipo getTipo() {
		return tipo;
	}

	public void setTipo(EquipamentoTipo tipo) {
		this.tipo = tipo;
	}

	public Eleicao getEleicao() {
		return eleicao;
	}

	public void setEleicao(Eleicao eleicao) {
		this.eleicao = eleicao;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTomb() {
		return tomb;
	}

	public void setTomb(String tomb) {
		this.tomb = tomb;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	

	public boolean isInserted() {
		return inserted;
	}

	public void setInserted(boolean inserted) {
		this.inserted = inserted;
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
		Equipamento other = (Equipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
