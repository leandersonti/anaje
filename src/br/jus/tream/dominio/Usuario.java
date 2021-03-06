package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	@Id
	@Column(name = "titulo_eleitor")
	private String tituloEleitor;
	private String nome;
	private int ativo;	
	private int adm;	
	private int zona;
		
	public Usuario() {		
	}

	public Usuario(String tituloEleitor, int ativo, String nome, int admin, int zona) {
		super();
		this.tituloEleitor = tituloEleitor;
		this.ativo = ativo;
		this.nome = nome;
		this.adm = admin;
		this.zona = zona;
	}

	public Usuario(String tituloEleitor, String nome) {
		super();
		this.tituloEleitor = tituloEleitor;
		this.nome = nome;
	}
	
	public String getTituloEleitor() {
		return tituloEleitor;
	}
	
	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public int getAdmin() {
		return adm;
	}

	public void setAdmin(int admin) {
		this.adm = admin;
	}

	public int getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	public int getAdm() {
		return adm;
	}

	public void setAdm(int adm) {
		this.adm = adm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tituloEleitor == null) ? 0 : tituloEleitor.hashCode());
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
		Usuario other = (Usuario) obj;
		if (tituloEleitor == null) {
			if (other.tituloEleitor != null)
				return false;
		} else if (!tituloEleitor.equals(other.tituloEleitor))
			return false;
		return true;
	}
	
}
