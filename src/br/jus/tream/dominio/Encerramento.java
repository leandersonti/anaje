package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.jus.tream.dominio.pk.IDEleicaoPK;

@SuppressWarnings("serial")
@Entity
@Table(name = "encerramento")
public class Encerramento implements Serializable {

	@EmbeddedId
	private IDEleicaoPK id = new IDEleicaoPK();

	@Column(length = 20, nullable = false)
	private String codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datacad", nullable = false)
	private Date dataCad;

	private Integer status;

	public Encerramento() {
		super();
	}

	public Encerramento(IDEleicaoPK id, String codigo, Date dataCad, Integer status) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.dataCad = dataCad;
		this.status = status;
	}

	public IDEleicaoPK getId() {
		return id;
	}

	public void setId(IDEleicaoPK id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		Encerramento other = (Encerramento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
