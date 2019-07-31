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

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name="unidade_servico")
public class UnidadeServico implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_eleicao", nullable=false)
	private DataEleicao dataEleicao;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo", nullable=false)
	private UnidadeServicoTipo tipo;
	
	@Column(name="cod_objeto")
	private String codObjeto;
		
	private Integer zona;
	
	private Integer local;
	
	private Integer secao;
	
	@Column(length=70, nullable=false)
	private String descricao;	
	
	@Column(length=100)
	private String endereco;	
	
	@Column(name = "id_municipio")
	private Integer codmunic;	
	
	@Column(length=1)
	private String sexo;
	
	@Column(length=40)
	private String sala;
	
	@Column(length=60)
	private String contato;
	
	@Column(name = "cargo_contato", length=30)
	private String cargoContato;
	
	@Column(length=20)
	private String telefone;
	
	@Column(length=40)
	private String latitude;
	
	@Column(length=40)
	private String logitude;
		
	private Integer status;
	
	@Column(nullable=false)
	private Integer oficial;
	
	private Integer jecon;

	public UnidadeServico() {
		super();
	}
	public UnidadeServico(Integer id, String codObjeto, Integer local,	String descricao) {
		super();
		this.id = id;
		this.local = local;
		this.descricao = descricao;
		this.codObjeto = codObjeto;
	}
	
	public UnidadeServico(Integer id, DataEleicao dataEleicao, UnidadeServicoTipo tipo, String codObjeto, Integer zona, Integer local,
			Integer secao, String descricao, String endereco, Integer codmunic, String sexo, String sala,
			String contato, String cargoContato, String telefone, String latitude, String logitude, Integer status,
			Integer oficial, Integer jecon) {
		super();
		this.id = id;
		this.dataEleicao = dataEleicao;
		this.tipo = tipo;
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
		this.telefone = telefone;
		this.latitude = latitude;
		this.logitude = logitude;
		this.status = status;
		this.oficial = oficial;
		this.jecon = jecon;
		this.codObjeto = codObjeto;
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

	public UnidadeServicoTipo getTipo() {
		return tipo;
	}

	public void setTipo(UnidadeServicoTipo tipo) {
		this.tipo = tipo;
	}

	public Integer getZona() {
		return zona;
	}
	
	public String getCodObjeto() {
		return codObjeto;
	}

	public void setCodObjeto(String codObjeto) {
		this.codObjeto = codObjeto;
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

	public Integer getCodmunico() {
		return codmunic;
	}

	public void setCodmunic(Integer codmu) {
		this.codmunic = codmu;
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

	public void setContato(String contrato) {
		this.contato = contrato;
	}

	public String getCargoContato() {
		return cargoContato;
	}

	public void setCargoContato(String cargoContrato) {
		this.cargoContato = cargoContrato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLogitude() {
		return logitude;
	}

	public void setLogitude(String logitude) {
		this.logitude = logitude;
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

	public Integer getJecon() {
		return jecon;
	}

	public void setJecon(Integer jecon) {
		this.jecon = jecon;
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
		UnidadeServico other = (UnidadeServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
