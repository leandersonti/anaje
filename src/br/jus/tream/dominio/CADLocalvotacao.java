package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@SuppressWarnings("serial")
@Entity
@Table(name="cad_local_votacao")
public class CADLocalvotacao implements Serializable{

	@Id
	@Column(name="cod_objeto_local")
	private String id;
	
	private Integer zona;
	
	@Column(name="nom_localidade")
	private String nomeLocalidade;
	
	@Column(name="cod_localidade_tse")
	private Integer codmunic; 
	
	@Column(name="num_local")
	private Integer numLocal;
	
	@Column(name="nom_local")
	private String nomeLocal;
	
	@Column(name="des_endereco")
	private String endereco;
	
	@Column(name="num_latitude")
	private String latitude;
	
	@Column(name="num_longitude")
	private String longitude;
	
	@OneToMany(mappedBy = "codObjetoLocal", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<DistribuicaoSecao> secoesDistribuidas = new ArrayList<DistribuicaoSecao>();
	
	public CADLocalvotacao() {
		super();
	}
	public CADLocalvotacao(String id, String nomeLocalidade, Integer numLocal) {
		super();
		this.id = id;
		this.nomeLocalidade = nomeLocalidade;
		this.numLocal = numLocal;
	}
	
	public CADLocalvotacao(String id, Integer zona,  Integer codmunic, Integer numLocal,
			String nomeLocal, String endereco, String latitude, String longitude) {
		super();
		this.id = id;
		this.zona = zona;
		this.codmunic = codmunic;
		this.numLocal = numLocal;
		this.nomeLocal = nomeLocal;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public CADLocalvotacao(String id, Integer zona, String nomeLocalidade, Integer codmunic, Integer numLocal,
			String nomeLocal, String endereco, String latitude, String longitude) {
		super();
		this.id = id;
		this.zona = zona;
		this.nomeLocalidade = nomeLocalidade;
		this.codmunic = codmunic;
		this.numLocal = numLocal;
		this.nomeLocal = nomeLocal;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public List<DistribuicaoSecao> getSecoesDistribuidas() {
		return secoesDistribuidas;
	}
	public void setSecoesDistribuidas(List<DistribuicaoSecao> secoesDistribuidas) {
		this.secoesDistribuidas = secoesDistribuidas;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getZona() {
		return zona;
	}
	public void setZona(Integer zona) {
		this.zona = zona;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public Integer getCodmunic() {
		return codmunic;
	}
	public void setCodmunic(Integer codmunic) {
		this.codmunic = codmunic;
	}
	public Integer getNumLocal() {
		return numLocal;
	}
	public void setNumLocal(Integer numLocal) {
		this.numLocal = numLocal;
	}
	public String getNomeLocal() {
		return nomeLocal;
	}
	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
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
		CADLocalvotacao other = (CADLocalvotacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
