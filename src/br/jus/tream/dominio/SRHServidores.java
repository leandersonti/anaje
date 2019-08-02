package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name="srh_servidores")
public class SRHServidores implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	private String matricula;	
	
	private String nome;
	
	@Column(name = "titulo_eleitor")
	private String tituloEleitor;
	
	@Column(name = "sigla_unid")
	private String siglaUnid;

	public SRHServidores() {
		super();
	}	

	public SRHServidores(String matricula, String nome, String tituloEleitor, String siglaUnid) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.tituloEleitor = tituloEleitor;
		this.siglaUnid = siglaUnid;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getSiglaUnid() {
		return siglaUnid;
	}

	public void setSiglaUnid(String siglaUnid) {
		this.siglaUnid = siglaUnid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		SRHServidores other = (SRHServidores) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	
	
}
