package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.jus.tream.dominio.pk.PontoTransmissaoPK;

@SuppressWarnings("serial")
@Entity
@Table(name = "ponto_transmissao")
public class PontoTransmissao implements Serializable {

	@EmbeddedId
	private PontoTransmissaoPK id = new PontoTransmissaoPK();

	@Column(name = "cod_objeto_local")
	private String codObjetoLocal;
	
	private Integer zona;

	private Integer local;

	private Integer secao;

	@Column(length = 70, nullable = false)
	private String descricao;

	@Column(length = 100)
	private String endereco;
	
	private Integer codmunic;

	@Column(length = 1)
	private String sexo;

	@Column(length = 40)
	private String sala;

	@Column(length = 60)
	private String contato;

	@Column(name = "cargo_contato", length = 30)
	private String cargoContato;

	@Column(length = 20)
	private String fone;

	@Column(length = 40)
	private String latitude;

	@Column(length = 40)
	private String longitude;

	private Integer status;

	@Column(nullable = false)
	private Integer oficial;

	/*
	@OneToMany(cascade = {CascadeType.REFRESH,CascadeType.REMOVE}, mappedBy = "id.unidadeServico", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<DistribuicaoSecao> distribuicaoSecao = new ArrayList<DistribuicaoSecao>();
	*/
	//@OneToMany(cascade = {CascadeType.REFRESH,CascadeType.REMOVE}, mappedBy = "id.venda", fetch = FetchType.EAGER)
	//@Fetch(FetchMode.SUBSELECT)
	//private List<DistribuicaoEquipamento> distribuicaoEquipamentp = new ArrayList<DistribuicaoEquipamento>();
	

	public PontoTransmissao() {
		super();
	}
	
	public PontoTransmissao(PontoTransmissaoPK id, String codObjetoLocal, Integer zona, Integer local,
			Integer secao, String descricao, String endereco, Integer codmunic, String sexo, String sala,
			String contato, String cargoContato, String telefone, String latitude, String longitude, Integer status,
			Integer oficial) {
		super();
		this.id = id;
		this.codObjetoLocal = codObjetoLocal;
		this.zona = zona;
		this.local = local;
		this.secao = secao;
		this.descricao = descricao;
		this.endereco = endereco;
		this.codmunic = codmunic;
		this.sexo = sexo;
		this.sala = sala;
		this.contato = contato;
		this.cargoContato = cargoContato;
		this.fone = telefone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
		this.oficial = oficial;
	}

	public PontoTransmissaoPK getId() {
		return id;
	}

	public void setId(PontoTransmissaoPK id) {
		this.id = id;
	}

	public String getCodObjetoLocal() {
		return codObjetoLocal;
	}

	public void setCodObjetoLocal(String codObjetoLocal) {
		this.codObjetoLocal = codObjetoLocal;
	}

	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona = zona;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public Integer getSecao() {
		return secao;
	}

	public void setSecao(Integer secao) {
		this.secao = secao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getCodmunic() {
		return codmunic;
	}

	public void setCodmunic(Integer codmunic) {
		this.codmunic = codmunic;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getCargoContato() {
		return cargoContato;
	}

	public void setCargoContato(String cargoContato) {
		this.cargoContato = cargoContato;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String telefone) {
		this.fone = telefone;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOficial() {
		return oficial;
	}

	public void setOficial(Integer oficial) {
		this.oficial = oficial;
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
		PontoTransmissao other = (PontoTransmissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
/*
	public List<DistribuicaoEquipamento> getDistribuicaoEquipamentp() {
		return distribuicaoEquipamentp;
	}

	public void setDistribuicaoEquipamentp(List<DistribuicaoEquipamento> distribuicaoEquipamentp) {
		this.distribuicaoEquipamentp = distribuicaoEquipamentp;
	}
*/
}
