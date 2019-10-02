package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.internal.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="tecnico")
public class Tecnico implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	
	@NotNull
	@Column(name="titulo_eleitor")
	private String tituloEleitor;	
	
	private Integer zona;
	
	private Integer secao;
	
	@NotNull
	private String nome;
	
	@Column(name="data_nasc")
	private Date dataNasc;
	
	private String telefone;
	
	private String celular;
	
	private String endereco;
	
	@Column(name="num_casa")
	private String numCasa;
	
	private String bairro;
	
	private String cep;
	
	private String cidade;
	
	@Column(name = "uf")
	private String uf;
	
	private String sexo;
	
	private String email;
	
	private String rg;
	
	@Column(name = "org_exp_rg")
	private String orgaoRg;
	
	private String cpf;
	
	@Column(name = "data_cadastro")
	private Date dataCad;

	public Tecnico() {		
	}

	public Tecnico(Integer id, String tituloEleitor, Integer zona, Integer secao, String nome, Date dataNasc,
			String telefone, String celular, String endereco, String numCasa, String bairro, String cep, String cidade,
			String uf, String sexo, String email, String rg, String orgaoRg, String cpf, Date dataCad) {
		super();
		this.id = id;
		this.tituloEleitor = tituloEleitor;
		this.zona = zona;
		this.secao = secao;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.telefone = telefone;
		this.celular = celular;
		this.endereco = endereco;
		this.numCasa = numCasa;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.sexo = sexo;
		this.email = email;
		this.rg = rg;
		this.orgaoRg = orgaoRg;
		this.cpf = cpf;
		this.dataCad = dataCad;
	}

	public Tecnico(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}



	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}



	public String getNumCasa() {
		return numCasa;
	}



	public void setNumCasa(String numCasa) {
		this.numCasa = numCasa;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDataNasc() {
		return dataNasc;
	}



	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}



	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoRg() {
		return orgaoRg;
	}

	public void setOrgaoRg(String orgaoRg) {
		this.orgaoRg = orgaoRg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCad() {
		return dataCad;
	}

	public void setDataCad(Date dataCad) {
		this.dataCad = dataCad;
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
		Tecnico other = (Tecnico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

	
}
