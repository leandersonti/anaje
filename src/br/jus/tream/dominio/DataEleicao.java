package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "data_eleicao")
public class DataEleicao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_elei")
	private Date dataEleicao;

	private int turno;

	private int ativo;

	@Column(length = 50)
	private String descricao;

	@Column(name = "titulo_tre", length = 70)
	private String titTRE;

	@Column(length = 70)
	private String email;

	public DataEleicao() {
	}

	public DataEleicao(Integer id, Date dataEleicao, int turno, int ativo, String descricao, String titTRE,
			String email) {
		super();
		this.id = id;
		this.dataEleicao = dataEleicao;
		this.turno = turno;
		this.ativo = ativo;
		this.descricao = descricao;
		this.titTRE = titTRE;
		this.email = email;
	}

	public DataEleicao(Integer id, Date dataEleicao, String descricao) {
		super();
		this.id = id;
		this.dataEleicao = dataEleicao;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataEleicao() {
		return dataEleicao;
	}

	public void setDataEleicao(Date dataEleicao) {
		this.dataEleicao = dataEleicao;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitTRE() {
		return titTRE;
	}

	public void setTitTRE(String titTRE) {
		this.titTRE = titTRE;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		DataEleicao other = (DataEleicao) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
